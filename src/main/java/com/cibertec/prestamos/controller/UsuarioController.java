package com.cibertec.prestamos.controller;


import com.cibertec.prestamos.domain.model.Usuario;
import com.cibertec.prestamos.dto.LoginUsuarioDto;
import com.cibertec.prestamos.util.AppSettings;
import com.cibertec.prestamos.service.IPersonaService;
import com.cibertec.prestamos.service.IUsuarioService;
import com.cibertec.prestamos.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    BCryptPasswordEncoder passEncode = new BCryptPasswordEncoder();

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
                if (_usuario.get().getContrasena().equals(usuario.getContrasena())) {
                    log.info("Usuario existe: {}", _usuario.get());
                    return ResponseEntity.status(HttpStatus.OK).body(new Response(_usuario.get(), "Validación de Login exitoso."));
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

    @PostMapping(path = "/registrar")
    public ResponseEntity<Response> registrar(@RequestBody Usuario usuario) {
        try {
            log.info("Usuario registro: {}", usuario);
            Optional<Usuario> _usuario = usuarioService.obtenerUsuarioPorEmail(usuario.getPersona().getEmail());
            if (_usuario.isPresent()) {
                log.info("Usuario existe: {}", _usuario.get().getIdUsuario());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("El e-mail [" + usuario.getPersona().getEmail() + "] ya está registrado."));
            } else {
                log.info("Usuario no existe");
                Usuario newUser = new Usuario();
                newUser.setPersona(usuario.getPersona());
                newUser.setPerfil(usuario.getPerfil());
                newUser.setContrasena(usuario.getContrasena());
                newUser.setEstado(1);
                newUser.setFechaCreacion( Date.from(java.time.ZonedDateTime.now().toInstant()));
                newUser.setUsuarioCreacion(usuario.getUsuarioCreacion());

                Optional<Usuario> newUserCreate = Optional.ofNullable(usuarioService.guardarUsuario(usuario));
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

    //@PostMapping(path = "/register")


}
