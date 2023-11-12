package com.cibertec.prestamos.controller;

import com.cibertec.prestamos.domain.model.Prestamo;
import com.cibertec.prestamos.service.ICuentaBancariaService;
import com.cibertec.prestamos.service.IPrestamoService;
import com.cibertec.prestamos.service.ISolicitudService;
import com.cibertec.prestamos.service.IUsuarioService;
import com.cibertec.prestamos.util.AppSettings;
import com.cibertec.prestamos.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/prestamo")
@CrossOrigin(origins = AppSettings.CrossOriginUrl)
public class PrestamoController {


    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IPrestamoService prestamoService;

    private Logger log = LoggerFactory.getLogger(UsuarioController.class);

    @GetMapping(path = "/listar")
    public ResponseEntity<Response> listar() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Response(prestamoService.obtenerTodosLosPrestamos(), "Lista de prestamos."));
        } catch (Exception e) {
            log.info("Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Error al listar los prestamos."));
        }
    }



    @PostMapping(path = "/registrar")
    public ResponseEntity<Response> registrar(@RequestBody Prestamo prestamo) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Response(prestamoService.guardarPrestamo(prestamo), "Prestamo registrado."));
        } catch (Exception e) {
            log.info("Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Error al registrar el prestamo."));
        }
    }

    @PutMapping(path = "/actualizar")
    public ResponseEntity<Response> actualizar(@RequestBody Prestamo prestamo) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Response(prestamoService.actualizarPrestamo(prestamo), "Prestamo actualizado."));
        } catch (Exception e) {
            log.info("Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Error al actualizar el prestamo."));
        }
    }




}