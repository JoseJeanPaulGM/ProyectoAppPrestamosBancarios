package com.cibertec.prestamos.controller;

import com.cibertec.prestamos.dto.UsuarioDto;
import com.cibertec.prestamos.domain.model.Persona;
import com.cibertec.prestamos.domain.model.Usuario;
import com.cibertec.prestamos.service.IPersonaService;
import com.cibertec.prestamos.service.IUsuarioService;
import com.cibertec.prestamos.util.AppSettings;
import com.cibertec.prestamos.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

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

//
//    @PostMapping(path = "/login")
//    public ResponseEntity<Response> login(@RequestBody UsuarioDto usuario) {
//
//        log.info("Accesos : {}" +usuario.getNombreUsuario() );
//        log.info("Accesos : {}" +usuario.getContrasena() );
//        try {
//            String username = usuario.getNombreUsuario();
//            String password = usuario.getContrasena();
//            String email = usuario.getEmail();
//
//            boolean isUsername = username != null && !username.isEmpty();
//            boolean isEmail = email != null && !email.isEmpty();
//
//            if (!isUsername && !isEmail) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("El nombre de usuario o el email son campos obligatorio."));
//            }
//            if (password == null || password.isEmpty()) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("La contraseña es obligatoria."));
//            }
//
//         if (isUsername) {
//                Optional<Usuario> _usuario = usuarioService.obtenerUsuarioPorUsername(username);
//                if (_usuario.isPresent()) {
//                    if (_usuario.get().getContrasena().equals(usuario.getContrasena())) {
//                        log.info("Usuario existe: {}", _usuario.get());
//                        return ResponseEntity.status(HttpStatus.OK).body(new Response(_usuario.get(), "Validación de Login exitoso."));
//                    } else {
//                        log.info("Usuario o Password  incorrecto.");
//                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Response("Usuario o Password  incorrecto."));
//                    }
//                } else {
//                    log.info("Usuario no existe");
//                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("El usuario [" + usuario.getNombreUsuario() + "] no existe."));
//                }
//            }
//         else {
//                Optional<Persona> _persona = personaService.obtenerPersonaPorEmail(email);
//                if (_persona.isPresent()) {
//                    Optional<Usuario> _usuario = usuarioService.obtenerUsuarioPorIdPersona(_persona.get().getIdPersona());
//                    if (_usuario.isPresent()) {
//                        if (passEncode.matches(_usuario.get().getContrasena(), usuario.getContrasena())) {
//                            log.info("Usuario existe: {}", _usuario.get());
//                            return ResponseEntity.status(HttpStatus.OK).body(new Response(_usuario.get(), "Validación de Login exitoso."));
//                        } else {
//                            log.info("Usuario o Password  incorrecto.");
//                            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Response("Usuario o Password  incorrecto."));
//                        }
//                    } else {
//                        log.info("Usuario no existe");
//                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("El usuario [" + usuario.getPersona().getEmail() + "] no existe."));
//                    }
//                } else {
//                    log.info("Usuario no existe");
//                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("El usuario [" + usuario.getPersona().getEmail() + "] no existe."));
//                }
//            }
//
//        } catch (Exception e) {
//            log.info("Error: {}", e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Error al validar el usuario."));
//        }
//
//    }

    //@PostMapping(path = "/register")


}
