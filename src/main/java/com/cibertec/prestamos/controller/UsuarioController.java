package com.cibertec.prestamos.controller;

import com.cibertec.prestamos.domain.model.*;
import com.cibertec.prestamos.domain.repository.IPerfilModuloRepository;
import com.cibertec.prestamos.domain.repository.IPerfilRepository;
import com.cibertec.prestamos.dto.*;
import com.cibertec.prestamos.service.*;
import com.cibertec.prestamos.util.AppSettings;
import com.cibertec.prestamos.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/usuario")
@CrossOrigin(origins = AppSettings.CrossOriginUrl)
public class UsuarioController {

    BCryptPasswordEncoder passEncode = new BCryptPasswordEncoder();
    @Autowired
    private IUsuarioService usuarioService;
    @Autowired
    private IPersonaService personaService;
    @Autowired
    private IGrupoService grupoService;
    @Autowired
    private IGrupoPrestamistaService grupoPrestamistaService;

    @Autowired
    private IPerfilService perfilService;

    private Logger log = LoggerFactory.getLogger(UsuarioController.class);

    @GetMapping(path = "/listar")
    public ResponseEntity<Response> listar() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Response(usuarioService.obtenerTodosLosUsuarios(), "Lista de usuarios."));
        } catch (Exception e) {
            log.info("Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Error al listar los usuarios."));
        }
    }

    @GetMapping(path = "/listar/jefes")
    public ResponseEntity<Response> listarJefes() {
        try {
            Perfil perfil = new Perfil();
            perfil.setIdPerfil(2);
            return ResponseEntity.status(HttpStatus.OK).body(new Response(usuarioService.obtenerUsuariosPorPerfil(perfil), "Lista de jefes."));
        } catch (Exception e) {
            log.info("Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Error al listar los jefes."));
        }
    }

    @GetMapping(path = "/listar/prestamistas")
    public ResponseEntity<Response> listarPrestamistas() {
        try {
            Perfil perfil = new Perfil();
            perfil.setIdPerfil(3);
            return ResponseEntity.status(HttpStatus.OK).body(new Response(usuarioService.obtenerUsuariosPorPerfil(perfil), "Lista de prestamistas."));
        } catch (Exception e) {
            log.info("Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Error al listar los prestamistas."));
        }
    }

    @GetMapping(path = "/listar/prestamistas/{jefe}")
    public ResponseEntity<Response> listarPrestamistas(@PathVariable("jefe") String jefe) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Response(usuarioService.obtenerUsuariosPorUsuarioCreacion(jefe), "Lista de prestamistas."));
        } catch (Exception e) {
            log.info("Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Error al listar los prestamistas."));
        }
    }

    @PostMapping(path = "/login")
    public ResponseEntity<Response> login(@RequestBody LoginUsuarioDto usuario) {

        log.info("Accesos : {}" + usuario.getEmail());
        log.info("Accesos : {}" + usuario.getContrasena());
        try {
            String email = usuario.getEmail();
            String password = usuario.getContrasena();

            if (email.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("El nombre de usuario o el email son campos obligatorio."));
            }
            if (password == null || password.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("La contraseña es obligatoria."));
            }

            Optional<Usuario> _usuario = usuarioService.obtenerUsuarioPorEmail(email);
            if (_usuario.isPresent()) {

                if (passEncode.matches(password, _usuario.get().getContrasena())) {
                    log.info("Usuario existe: {}", _usuario.get());
                    LoginResponseDto loginResponseDto = new LoginResponseDto();

                    loginResponseDto.setIdUsuario(_usuario.get().getIdUsuario());
                    loginResponseDto.setEmail(_usuario.get().getEmail());
                    loginResponseDto.setContrasena(_usuario.get().getContrasena());
                    loginResponseDto.setEstado(_usuario.get().getEstado());
                    loginResponseDto.setFechaCreacion(_usuario.get().getFechaCreacion());
                    loginResponseDto.setUsuarioCreacion(_usuario.get().getUsuarioCreacion());
                    loginResponseDto.setFechaModificacion(_usuario.get().getFechaModificacion());
                    loginResponseDto.setUsuarioModificacion(_usuario.get().getUsuarioModificacion());

                    PersonaDto personaDto = new PersonaDto();
                    personaDto.setIdPersona(_usuario.get().getPersona().getIdPersona());
                    personaDto.setNombres(_usuario.get().getPersona().getNombres());
                    personaDto.setApellidoPaterno(_usuario.get().getPersona().getApellidoPaterno());
                    personaDto.setApellidoMaterno(_usuario.get().getPersona().getApellidoMaterno());
                    personaDto.setNumeroDocumento(_usuario.get().getPersona().getNumeroDocumento());
                    personaDto.setTipoDocumento(_usuario.get().getPersona().getTipoDocumento().getIdDocumento());
                    personaDto.setDireccion(_usuario.get().getPersona().getDireccion());
                    personaDto.setEmail(_usuario.get().getPersona().getEmail());
                    personaDto.setTelefono(_usuario.get().getPersona().getTelefono());
                    personaDto.setEstado(_usuario.get().getPersona().getEstado());
                    personaDto.setFechaRegistro(_usuario.get().getPersona().getFechaCreacion().toString());

                    loginResponseDto.setIdPerfil(_usuario.get().getPerfil().getIdPerfil());
                    loginResponseDto.setPerfil(_usuario.get().getPerfil().getDescripcion());

                    List<Modulo> listaModulos = perfilService.obtenerModuloPorPefil(_usuario.get().getPerfil());
                    List<ModuloResponseDto> listaModulosDto = new ArrayList<>();
                    for (Modulo modulo : listaModulos){
                        ModuloResponseDto moduloDto = new ModuloResponseDto();
                        moduloDto.setIdModulo(modulo.getIdModulo());
                        moduloDto.setDescripcion(modulo.getDescripcion());
                        moduloDto.setEstado(modulo.getEstado());
                        List<Opcion> listaOpciones = modulo.getOpciones();
                        List<OpcionResponseDto> listaOpcionesDto = new ArrayList<>();
                        for (Opcion opcion : listaOpciones) {

                            OpcionResponseDto opcionDto = new OpcionResponseDto();
                            opcionDto.setIdOpcion(opcion.getIdOpcion());
                            opcionDto.setDescripcion(opcion.getDescripcion());
                            opcionDto.setEstado(opcion.getEstado());
                            opcionDto.setUrl(opcion.getUrl());
                            listaOpcionesDto.add(opcionDto);
                        }
                        moduloDto.setOpciones(listaOpcionesDto);
                        listaModulosDto.add(moduloDto);
                    }

                    loginResponseDto.setModulos(listaModulosDto);
                    loginResponseDto.setPersona(personaDto);
                    return ResponseEntity.status(HttpStatus.OK).body(new Response(loginResponseDto, "Validación de Login exitoso."));
                } else {
                    log.info("Usuario o Password  incorrecto.");
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Response("Usuario o Password  incorrecto."));
                }
            } else {
                log.info("Usuario no existe");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("El usuario [ " + usuario.getEmail() + " ] no existe."));
            }
        } catch (Exception e) {
            log.info("Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Error al validar el usuario."));
        }
    }

    @PostMapping(path = "/registrar/cliente")
    public ResponseEntity<Response> registrarCliente(@RequestBody ClienteRequestDto usuario) {
        try {
            log.info("Usuario registro: {}", usuario);
            Optional<Usuario> _usuario = usuarioService.obtenerUsuarioPorEmail(usuario.getEmail());
            if (_usuario.isPresent()) {
                log.info("Usuario existe: {}", _usuario.get().getIdUsuario());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("El e-mail [" + usuario.getPersona().getEmail() + "] ya está registrado."));
            } else {
                log.info("Usuario no existe");
                Usuario newUser = new Usuario();
                Persona persona = new Persona();
                Optional<Persona> _persona = personaService.obtenerPersonaPorDni(usuario.getPersona().getNumeroDocumento());

                if (_persona.isPresent()) {
                    log.info("Persona existe: {}", _persona.get().getIdPersona());

                    persona = _persona.get();

                } else {

                    log.info("Persona no existe");

                    persona.setEstado(1);
                    persona.setFechaCreacion(Date.from(java.time.ZonedDateTime.now().toInstant()));
                    persona.setUsuarioCreacion(usuario.getUsuarioCreacion());
                    persona.setNombres(usuario.getPersona().getNombres());
                    persona.setApellidoPaterno(usuario.getPersona().getApellidoPaterno());
                    persona.setApellidoMaterno(usuario.getPersona().getApellidoMaterno());
                    persona.setNumeroDocumento(usuario.getPersona().getNumeroDocumento());
                    TipoDocumento tipoDocumento = new TipoDocumento();
                    tipoDocumento.setIdDocumento(usuario.getPersona().getTipoDocumento());
                    persona.setTipoDocumento(tipoDocumento);
                    persona.setDireccion(usuario.getPersona().getDireccion());
                    persona.setEmail(usuario.getPersona().getEmail());
                    persona.setTelefono(usuario.getPersona().getTelefono());
                }

                Perfil perfil = new Perfil();
                perfil.setIdPerfil(usuario.getIdPerfil());

                newUser.setPerfil(perfil);
                newUser.setPersona(persona);
                newUser.setEmail(usuario.getEmail());
                newUser.setContrasena(passEncode.encode(usuario.getContrasena()));
                newUser.setEstado(1);
                newUser.setFechaCreacion(Date.from(java.time.ZonedDateTime.now().toInstant()));
                newUser.setUsuarioCreacion(usuario.getUsuarioCreacion());

                Optional<Usuario> newUserCreate = Optional.ofNullable(usuarioService.guardarUsuario(newUser));
                if (newUserCreate.isPresent()) {
                    return ResponseEntity.status(HttpStatus.CREATED).body(new Response(newUser, "Se registró correctamente al usuario."));
                } else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("No se pudo registrar al usuario."));
                }
            }
        } catch (Exception e) {
            log.info("Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Error al registrar el usuario."));
        }
    }

    @PostMapping(path = "/registrar/empleado")
    public ResponseEntity<Response> registrarEmpleado(@RequestBody EmpleadoRequestDto empleado) {
        try {
            log.info("Usuario registro: {}", empleado);
            Optional<Usuario> _usuario = usuarioService.obtenerUsuarioPorEmail(empleado.getEmail());
            if (_usuario.isPresent()) {
                log.info("Usuario existe: {}", _usuario.get().getIdUsuario());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("El e-mail ingresado ya se encuentra registrado."));
            } else {
                log.info("Usuario no existe");
                Usuario newUser = new Usuario();
                Persona persona = new Persona();
                Optional<Persona> _persona = personaService.obtenerPersonaPorDni(empleado.getPersona().getNumeroDocumento());

                if (_persona.isPresent()) {
                    log.info("Persona existe: {}", _persona.get().getIdPersona());
                   return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("El número de documento ingresado ya se encuentra registrado."));

                }
                    log.info("Persona no existe");

                    persona.setEstado(1);
                    persona.setFechaCreacion(Date.from(java.time.ZonedDateTime.now().toInstant()));
                    persona.setUsuarioCreacion(empleado.getUsuarioCreacion());
                    persona.setNombres(empleado.getPersona().getNombres());
                    persona.setApellidoPaterno(empleado.getPersona().getApellidoPaterno());
                    persona.setApellidoMaterno(empleado.getPersona().getApellidoMaterno());
                    persona.setNumeroDocumento(empleado.getPersona().getNumeroDocumento());
                    TipoDocumento tipoDocumento = new TipoDocumento();
                    tipoDocumento.setIdDocumento(empleado.getPersona().getTipoDocumento());
                    persona.setTipoDocumento(tipoDocumento);
                    persona.setDireccion(empleado.getPersona().getDireccion());
                    persona.setEmail(empleado.getEmail());
                    persona.setTelefono(empleado.getPersona().getTelefono());


                Perfil perfil = new Perfil();
                perfil.setIdPerfil(empleado.getIdPerfil());

                newUser.setPerfil(perfil);
                newUser.setPersona(persona);
                newUser.setEmail(empleado.getEmail());
                newUser.setContrasena(passEncode.encode(empleado.getContrasena()));
                newUser.setEstado(1);
                newUser.setFechaCreacion(Date.from(java.time.ZonedDateTime.now().toInstant()));
                newUser.setUsuarioCreacion(empleado.getUsuarioCreacion());

                Optional<Usuario> newUserCreate = Optional.ofNullable(usuarioService.guardarUsuario(newUser));
                if (newUserCreate.isPresent()) {

                    if (empleado.getIdPerfil() == 2) { // Cuando se registra al Jefe de Prestamista
                        Grupo grupo = new Grupo();
                        grupo.setDescripcion(empleado.getGrupo().getDescripcion());
                        grupo.setEstado(1);
                        grupo.setFechaCreacion(Date.from(java.time.ZonedDateTime.now().toInstant()));
                        grupo.setUsuarioCreacion(empleado.getUsuarioCreacion());
                        grupo.setIdJefePrestamista(newUserCreate.get().getIdUsuario());


                        Optional<Grupo> _grupo = Optional.ofNullable(grupoService.guardarGrupo(grupo));
                        if (_grupo.isPresent()) {
                            return ResponseEntity.status(HttpStatus.CREATED).body(new Response(newUser, "Se registró correctamente al usuario."));
                        } else {
                            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("No se pudo registrar al usuario, Asociación de Grupo Incorrecto."));
                        }
                    } else if (empleado.getIdPerfil() == 3) { // Cuando se registra al Prestamista

                        GrupoPrestamista grupoPrestamista = new GrupoPrestamista();
                        grupoPrestamista.setEstado(1);
                        grupoPrestamista.setFechaCreacion(Date.from(java.time.ZonedDateTime.now().toInstant()));
                        grupoPrestamista.setUsuarioCreacion(empleado.getUsuarioCreacion());
                        grupoPrestamista.setUsuario(newUserCreate.get());

                        Optional<Usuario> _empleado = usuarioService.obtenerUsuarioPorEmail(empleado.getUsuarioCreacion());
                        Optional<Grupo> grupo = grupoService.obtenerGrupoPorIdJefePrestamista(_empleado.get().getIdUsuario());
                        if (grupo.isEmpty()) {
                            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("No se pudo registrar al usuario, Asociación de Grupo Incorrecto."));
                        }
                        grupoPrestamista.setGrupo(grupo.get());

                        Optional<GrupoPrestamista> newGrupoPrestamista = grupoPrestamistaService.guardarGrupoDePrestamista(grupoPrestamista);

                        if (newGrupoPrestamista.isPresent()) {
                            return ResponseEntity.status(HttpStatus.CREATED).body(new Response(newUser, "Se registró correctamente al usuario."));
                        } else {
                            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("No se pudo registrar al usuario, Asociación de Grupo Incorrecto."));
                        }
                    }
                    return ResponseEntity.status(HttpStatus.CREATED).body(new Response(newUser, "Se registró correctamente al usuario."));
                } else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("No se pudo registrar al usuario."));
                }
            }
        } catch (Exception e) {
            log.info("Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Error al registrar el usuario."));
        }
    }

    @GetMapping(path = "/obtener/{id}")
    public ResponseEntity<Response> obtener(@PathVariable("id") int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Response(usuarioService.obtenerUsuarioPorId(id), "Usuario obtenido correctamente."));
        } catch (Exception e) {
            log.info("Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Error al obtener el usuario."));
        }
    }

    @PutMapping(path = "/actualizar")
    public ResponseEntity<Response> actualizar(@RequestBody Usuario usuario) {
        try {

            Optional<Usuario> _usuario = usuarioService.obtenerUsuarioPorId(usuario.getIdUsuario());

            if (_usuario.isPresent()) {
                log.info("Usuario existe: {}", _usuario.get().getIdUsuario());
                usuario.setFechaCreacion(_usuario.get().getFechaCreacion());
                usuario.setUsuarioCreacion(_usuario.get().getUsuarioCreacion());
                usuario.setFechaModificacion(Date.from(java.time.ZonedDateTime.now().toInstant()));
                usuario.setUsuarioModificacion(usuario.getUsuarioModificacion());
                usuario.setEstado(_usuario.get().getEstado());
                usuario.setContrasena(_usuario.get().getContrasena());
            } else {
                log.info("Usuario no existe");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("El usuario [ " + usuario.getIdUsuario() + " ] no existe."));
            }

            usuarioService.guardarUsuario(usuario);
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Usuario actualizado correctamente."));
        } catch (Exception e) {
            log.info("Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Error al actualizar el usuario."));
        }
    }
}