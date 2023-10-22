package com.cibertec.prestamos.controller;

import com.cibertec.prestamos.service.IOpcionService;
import com.cibertec.prestamos.util.AppSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.cibertec.prestamos.domain.model.Modulo;
import com.cibertec.prestamos.domain.model.Opcion;
import com.cibertec.prestamos.dto.OpcionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.cibertec.prestamos.util.Response;

import java.util.Date;

@RestController
@RequestMapping(path = "api/v1/opcion")
@CrossOrigin(origins = AppSettings.CrossOriginUrl)
public class OpcionController {

    @Autowired
    private IOpcionService opcionService;

    private Logger log = LoggerFactory.getLogger(OpcionController.class);

    @GetMapping(path = "/listar")
    public ResponseEntity<Response> listar() {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new Response(opcionService.obtenerTodasLasOpciones(), "Lista de opciones."));
        } catch (Exception e) {
            log.info("Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response("Error al listar las opciones."));
        }
    }

    @GetMapping(path = "/listar/{idModulo}")
    public ResponseEntity<Response> listarPorModulo(@PathVariable("idModulo") int idModulo) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new Response(opcionService.obtenerOpcionesPorModulo(idModulo), "Lista de opciones por modulo."));
        } catch (Exception e) {
            log.info("Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response("Error al listar las opciones por modulo."));
        }
    }

    @PostMapping(path = "/registrar")
    public ResponseEntity<Response> registrar(@RequestBody OpcionDto opcion) {
        try {
            Opcion newOpcion = new Opcion();
            newOpcion.setDescripcion(opcion.getDescripcion());
            newOpcion.setEstado(opcion.getEstado());
            newOpcion.setUrl(opcion.getUrl());
            newOpcion.setUsuarioCreacion(opcion.getUsuarioCreacion());
            newOpcion.setFechaCreacion(Date.from(java.time.ZonedDateTime.now().toInstant()));
            Modulo modulo = new Modulo();
            modulo.setIdModulo(opcion.getIdModulo());
            newOpcion.setModulo(modulo);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new Response(opcionService.guardarOpcion(newOpcion), "Opcion registrado correctamente."));
        } catch (Exception e) {
            log.info("Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response("Error al registrar la opcion."));
        }
    }

}