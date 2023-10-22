package com.cibertec.prestamos.controller;

import com.cibertec.prestamos.domain.model.Modulo;
import com.cibertec.prestamos.domain.model.Perfil;
import com.cibertec.prestamos.domain.model.PerfilModulo;
import com.cibertec.prestamos.dto.ModuloDto;
import com.cibertec.prestamos.dto.PerfilDto;
import com.cibertec.prestamos.service.IPerfilService;
import com.cibertec.prestamos.util.AppSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.cibertec.prestamos.util.Response;

import java.util.Date;
import java.util.List;

@RequestMapping(path = "api/v1/perfil")
@CrossOrigin(origins = AppSettings.CrossOriginUrl)
@RestController
public class PerfilController {

    @Autowired
    private IPerfilService perfilService;
    private Logger log = LoggerFactory.getLogger(UsuarioController.class);

    @GetMapping(path = "/listar")
    public ResponseEntity<Response> listar() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Response(perfilService.obtenerTodosLosPerfiles(), "Lista de perfiles."));
        } catch (Exception e) {
            log.info("Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Error al listar los perfiles."));
        }
    }

    @PostMapping(path = "/registrar")
    public ResponseEntity<Response> registrar(@RequestBody PerfilDto perfil) {
        try {
            Perfil newPerfil = new Perfil();
            newPerfil.setDescripcion(perfil.getDescripcion());
            newPerfil.setEstado(perfil.getEstado());
            newPerfil.setFechaCreacion(Date.from(java.time.ZonedDateTime.now().toInstant()));
            newPerfil.setUsuarioCreacion(perfil.getUsuarioCreacion());

            List<PerfilModulo> listaPerfilModulo = new java.util.ArrayList<>();
            for (ModuloDto modulo : perfil.getListaModulos()) {
                PerfilModulo perfilModulo = new PerfilModulo();
                perfilModulo.setPerfil(newPerfil);
                perfilModulo.setFechaCreacion(Date.from(java.time.ZonedDateTime.now().toInstant()));
                perfilModulo.setUsuarioCreacion(perfil.getUsuarioCreacion());
                perfilModulo.setEstado(perfil.getEstado());
                Modulo newModulo = new Modulo();
                newModulo.setIdModulo(modulo.getIdModulo());
                perfilModulo.setModulo(newModulo);
                listaPerfilModulo.add(perfilModulo);
            }
            newPerfil.setPerfilModulos(listaPerfilModulo);

            var result = perfilService.guardarPerfil(newPerfil);

            return ResponseEntity.status(HttpStatus.OK).body(new Response(result, "Perfil registrado correctamente."));


        } catch (Exception e) {
            log.info("Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Error al registrar el perfil."));
        }
    }

    
}