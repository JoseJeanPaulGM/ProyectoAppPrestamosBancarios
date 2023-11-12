package com.cibertec.prestamos.controller;

import com.cibertec.prestamos.domain.model.Solicitud;
import com.cibertec.prestamos.service.ICuentaBancariaService;
import com.cibertec.prestamos.service.ISolicitudService;
import com.cibertec.prestamos.util.AppSettings;
import com.cibertec.prestamos.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping(path = "api/v1/solicitud")
@CrossOrigin(origins = AppSettings.CrossOriginUrl)
public class SolicitudController {

    @Autowired
    private ISolicitudService solicitudService;

    @Autowired
    private ICuentaBancariaService cuentaBancariaService;

    private Logger log = LoggerFactory.getLogger(UsuarioController.class);

    @GetMapping(path = "/listar")
    public ResponseEntity<Response> listar() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Response(solicitudService.obtenerTodasLasSolicitudes(), "Lista de solicitudes."));
        } catch (Exception e) {
            log.info("Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Error al listar las solicitudes."));
        }
    }

    @GetMapping(path = "/listarCuentas")
    public ResponseEntity<Response> listarCuentasBancarias() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Response(cuentaBancariaService.obtenerTodasLasCuentasBancarias(), "Lista de cuentas bancarias."));
        } catch (Exception e) {
            log.info("Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Error al listar las cuentas bancarias."));
        }
    }

    @GetMapping(path = "/listarCuenta/{id}")
    public ResponseEntity<Response> listarCuentaBancariaPorUsuario(int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Response(cuentaBancariaService.obtenerCuentaPorIdPrestatario(id), "Cuenta bancaria."));
        } catch (Exception e) {
            log.info("Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Error al listar la cuenta bancaria."));
        }
    }

    @PostMapping(path = "/registrar")
    public ResponseEntity<Response> registrar(@RequestBody Solicitud solicitud) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Response(solicitudService.guardarSolicitud(solicitud), "Solicitud registrada."));
        } catch (Exception e) {
            log.info("Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Error al registrar la solicitud."));
        }
    }

    @PutMapping(path = "/actualizar")
    public ResponseEntity<Response> actualizar(@RequestBody Solicitud solicitud) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Response(solicitudService.actualizarSolicitud(solicitud), "Solicitud actualizada."));
        } catch (Exception e) {
            log.info("Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Error al actualizar la solicitud."));
        }

    }

}