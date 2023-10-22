package com.cibertec.prestamos.controller;

import com.cibertec.prestamos.domain.model.*;
import com.cibertec.prestamos.dto.EmpleadoRequestDto;
import com.cibertec.prestamos.dto.LoginResponseDto;
import com.cibertec.prestamos.dto.LoginUsuarioDto;
import com.cibertec.prestamos.dto.ClienteRequestDto;
import com.cibertec.prestamos.service.IGrupoPrestamistaService;
import com.cibertec.prestamos.service.IGrupoService;
import com.cibertec.prestamos.util.AppSettings;
import com.cibertec.prestamos.service.IPersonaService;
import com.cibertec.prestamos.service.IUsuarioService;
import com.cibertec.prestamos.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/usuario")
@CrossOrigin(origins = AppSettings.CrossOriginUrl)
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IPersonaService personaService;

    @Autowired
    private IGrupoService grupoService;

    @Autowired
    private IGrupoPrestamistaService grupoPrestamistaService;

    BCryptPasswordEncoder passEncode = new BCryptPasswordEncoder();

    private Logger log = LoggerFactory.getLogger(UsuarioController.class);

    @GetMapping(path = "/listar")
    public ResponseEntity<Response> listar() {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new Response(usuarioService.obtenerTodosLosUsuarios(), "Lista de usuarios."));
        } catch (Exception e) {
            log.info("Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response("Error al listar los usuarios."));
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
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new Response("El nombre de usuario o el email son campos obligatorio."));
            }
            if (password == null || password.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new Response("La contraseña es obligatoria."));
            }

            Optional<Usuario> _usuario = usuarioService.obtenerUsuarioPorEmail(email);
            if (_usuario.isPresent()) {

                if (passEncode.matches(password, _usuario.get().getContrasena())) {
                    log.info("Usuario existe: {}", _usuario.get());
                    LoginResponseDto loginResponseDto = new LoginResponseDto();

                    loginResponseDto.setIdUsuario(_usuario.get().getIdUsuario());

                    return ResponseEntity.status(HttpStatus.OK)
                            .body(new Response(_usuario.get(), "Validación de Login exitoso."));
                } else {
                    log.info("Usuario o Password  incorrecto.");
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                            .body(new Response("Usuario o Password  incorrecto."));
                }
            } else {
                log.info("Usuario no existe");
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new Response("El usuario [ " + usuario.getEmail() + " ] no existe."));
            }
        } catch (Exception e) {
            log.info("Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response("Error al validar el usuario."));
        }
    }

    @PostMapping(path = "/registrar/cliente")
    public ResponseEntity<Response> registrarCliente(@RequestBody ClienteRequestDto usuario) {
        try {
            log.info("Usuario registro: {}", usuario);
            Optional<Usuario> _usuario = usuarioService.obtenerUsuarioPorEmail(usuario.getPersona().getEmail());
            if (_usuario.isPresent()) {
                log.info("Usuario existe: {}", _usuario.get().getIdUsuario());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new Response("El e-mail [" + usuario.getPersona().getEmail() + "] ya está registrado."));
            } else {
                log.info("Usuario no existe");
                Usuario newUser = new Usuario();
                Persona persona = new Persona();
                Optional<Persona> _persona = personaService
                        .obtenerPersonaPorDni(usuario.getPersona().getNumeroDocumento());

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
                newUser.setContrasena(passEncode.encode(usuario.getContrasena()));
                newUser.setEstado(1);
                newUser.setFechaCreacion(Date.from(java.time.ZonedDateTime.now().toInstant()));
                newUser.setUsuarioCreacion(usuario.getUsuarioCreacion());

                Optional<Usuario> newUserCreate = Optional.ofNullable(usuarioService.guardarUsuario(newUser));
                if (newUserCreate.isPresent()) {
                    return ResponseEntity.status(HttpStatus.CREATED)
                            .body(new Response(newUser, "Se registró correctamente al usuario."));
                } else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body(new Response("No se pudo registrar al usuario."));
                }
            }
        } catch (Exception e) {
            log.info("Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response("Error al registrar el usuario."));
        }
    }

    @PostMapping(path = "/registrar/empleado")
    public ResponseEntity<Response> registrarEmpleado(@RequestBody EmpleadoRequestDto empleado) {
        try {
            log.info("Usuario registro: {}", empleado);
            Optional<Usuario> _usuario = usuarioService.obtenerUsuarioPorEmail(empleado.getPersona().getEmail());
            if (_usuario.isPresent()) {
                log.info("Usuario existe: {}", _usuario.get().getIdUsuario());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new Response("El e-mail [" + empleado.getPersona().getEmail() + "] ya está registrado."));
            } else {
                log.info("Usuario no existe");
                Usuario newUser = new Usuario();
                Persona persona = new Persona();
                Optional<Persona> _persona = personaService
                        .obtenerPersonaPorDni(empleado.getPersona().getNumeroDocumento());

                if (_persona.isPresent()) {
                    log.info("Persona existe: {}", _persona.get().getIdPersona());
                    persona = _persona.get();

                } else {

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
                    persona.setEmail(empleado.getPersona().getEmail());
                    persona.setTelefono(empleado.getPersona().getTelefono());
                }

                Perfil perfil = new Perfil();
                perfil.setIdPerfil(empleado.getIdPerfil());

                newUser.setPerfil(perfil);
                newUser.setPersona(persona);
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
                            return ResponseEntity.status(HttpStatus.CREATED)
                                    .body(new Response(newUser, "Se registró correctamente al usuario."));
                        } else {
                            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                                    new Response("No se pudo registrar al usuario, Asociación de Grupo Incorrecto."));
                        }
                    } else if (empleado.getIdPerfil() == 3) { // Cuando se registra al Prestamista

                        GrupoPrestamista grupoPrestamista = new GrupoPrestamista();
                        grupoPrestamista.setEstado(1);
                        grupoPrestamista.setFechaCreacion(Date.from(java.time.ZonedDateTime.now().toInstant()));
                        grupoPrestamista.setUsuarioCreacion(empleado.getUsuarioCreacion());
                        grupoPrestamista.setUsuario(newUserCreate.get());

                        Grupo grupo = new Grupo();
                        grupo.setIdGrupo(empleado.getGrupo().getIdGrupo());

                        grupoPrestamista.setGrupo(grupo);

                        Optional<GrupoPrestamista> newGrupoPrestamista = grupoPrestamistaService
                                .guardarGrupoDePrestamista(grupoPrestamista);

                        if (newGrupoPrestamista.isPresent()) {
                            return ResponseEntity.status(HttpStatus.CREATED)
                                    .body(new Response(newUser, "Se registró correctamente al usuario."));
                        } else {
                            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                                    new Response("No se pudo registrar al usuario, Asociación de Grupo Incorrecto."));
                        }
                    }
                    return ResponseEntity.status(HttpStatus.CREATED)
                            .body(new Response(newUser, "Se registró correctamente al usuario."));
                } else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body(new Response("No se pudo registrar al usuario."));
                }
            }
        } catch (Exception e) {
            log.info("Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response("Error al registrar el usuario."));
        }
    }

    @GetMapping(path = "/obtener/{id}")
    public ResponseEntity<Response> obtener(@PathVariable("id") int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new Response(usuarioService.obtenerUsuarioPorId(id), "Usuario obtenido correctamente."));
        } catch (Exception e) {
            log.info("Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response("Error al obtener el usuario."));
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
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new Response("El usuario [ " + usuario.getIdUsuario() + " ] no existe."));
            }

            usuarioService.guardarUsuario(usuario);
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Usuario actualizado correctamente."));
        } catch (Exception e) {
            log.info("Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response("Error al actualizar el usuario."));
        }
    }
}