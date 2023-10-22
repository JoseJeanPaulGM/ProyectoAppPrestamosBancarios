package com.cibertec.prestamos.controller;

import com.cibertec.prestamos.domain.model.Modulo;
import com.cibertec.prestamos.domain.model.Perfil;
import com.cibertec.prestamos.dto.ModuloDto;
import com.cibertec.prestamos.service.IModuloService;
import com.cibertec.prestamos.util.AppSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.cibertec.prestamos.util.Response;

import java.util.Date;

@RestController
@RequestMapping(path = "api/v1/modulo")
@CrossOrigin(origins = AppSettings.CrossOriginUrl)
public class ModuloController {

    @Autowired
    private IModuloService moduloService;

    private Logger log = LoggerFactory.getLogger(ModuloController.class);

    @GetMapping(path = "/listar")
    public ResponseEntity<Response> listar() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Response(moduloService.obtenerTodosLosModulos(), "Lista de modulos."));
        } catch (Exception e) {
            log.info("Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Error al listar los modulos."));
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Response> listarPorPerfil(@PathVariable("id") int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Response(moduloService.obtenerModuloPorId(id), "Lista de modulos del Id = [ " + id + " ]."));
        } catch (Exception e) {
            log.info("Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Error al listar el módulo = [ "+id+" ]."));
        }
    }

    @PostMapping(path = "/registrar")
    public ResponseEntity<Response> registrar(@RequestBody ModuloDto modulo) {
        try {
            Modulo newModulo = new Modulo();
            newModulo.setDescripcion(modulo.getDescripcion());
            newModulo.setEstado(modulo.getEstado());
            newModulo.setFechaCreacion(Date.from(java.time.ZonedDateTime.now().toInstant()));
            newModulo.setUsuarioCreacion(modulo.getUsuarioCreacion());
            Perfil perfil = new Perfil();
            perfil.setIdPerfil(modulo.getIdPerfil());
//            newModulo.setPerfil(perfil);

            return ResponseEntity.status(HttpStatus.OK).body(new Response(moduloService.guardarModulo(newModulo), "Modulo registrado correctamente."));
        } catch (Exception e) {
            log.info("Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Error al registrar el modulo."));
        }
    }

    @PutMapping(path = "/actualizar")
    public ResponseEntity<Response> actualizar(@RequestBody ModuloDto modulo) {
        try {
            Modulo newModulo = moduloService.obtenerModuloPorId(modulo.getIdModulo());
            newModulo.setDescripcion(modulo.getDescripcion());
            newModulo.setEstado(modulo.getEstado());
            newModulo.setFechaModificacion(Date.from(java.time.ZonedDateTime.now().toInstant()));
            newModulo.setUsuarioModificacion(modulo.getUsuarioModificacion());
            Perfil perfil = new Perfil();
            perfil.setIdPerfil(modulo.getIdPerfil());
//            newModulo.setPerfil(perfil);

            return ResponseEntity.status(HttpStatus.OK).body(new Response(moduloService.guardarModulo(newModulo), "Modulo actualizado correctamente."));
        } catch (Exception e) {
            log.info("Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Error al actualizar el modulo."));
        }
    }

}