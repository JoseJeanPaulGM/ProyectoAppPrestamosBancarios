package com.cibertec.prestamos.controller;

import com.cibertec.prestamos.domain.model.Grupo;
import com.cibertec.prestamos.domain.model.Prestamo;
import com.cibertec.prestamos.domain.model.Solicitud;
import com.cibertec.prestamos.service.*;
import com.cibertec.prestamos.util.AppSettings;
import com.cibertec.prestamos.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/prestamo")
@CrossOrigin(origins = AppSettings.CrossOriginUrl)
public class PrestamoController {

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private ISolicitudService solicitudService;

    @Autowired
    private IPrestamoService prestamoService;

    @Autowired
    private IGrupoService grupoService;

    private Logger log = LoggerFactory.getLogger(PrestamoController.class);

    @GetMapping(path = "/listar")
    public ResponseEntity<Response> listar() {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new Response(prestamoService.obtenerTodosLosPrestamos(), "Lista de prestamos."));
        } catch (Exception e) {
            log.info("Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response("Error al listar los prestamos."));
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Response> listarPorPrestamo(@PathVariable("id") int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Response(prestamoService.obtenerPrestamoPorId(id),
                    "Lista de prestamos del Id = [ " + id + " ]."));
        } catch (Exception e) {
            log.info("Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response("Error al listar el prestamo = [ " + id + " ]."));
        }
    }

    // Validar si el usuario tiene solictudes pendientes y prestamos activos
    @GetMapping(path = "/validar/{id}")
    public ResponseEntity<Response> validarSolicitud(@PathVariable("id") int id) {
        try {

            List<Prestamo> prestamos = prestamoService.obtenerPrestamosPorPrestatarioAndEstado(id);

            if (!prestamos.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new Response(prestamos, "Usted tiene un préstamo en Curso."));
            } else {
                List<Solicitud> solicitudes = solicitudService.obtenerSolicitudesPorIdCliente(id);
                if (!solicitudes.isEmpty()) {
                    var _solicitud = solicitudes.stream().filter(x -> x.getEstado() == 1).findFirst();
                    if (_solicitud.isPresent()) {
                        return ResponseEntity.status(HttpStatus.OK)
                                .body(new Response(_solicitud, "Usted tiene una solicitud de prestamo por Aprobar."));
                    } else {
                        return ResponseEntity.status(HttpStatus.OK)
                                .body(new Response("No tiene solicitudes pendientes."));
                    }
                } else {
                    return ResponseEntity.status(HttpStatus.OK).body(new Response("El Cliente no tiene solicitudes."));
                }
            }
        } catch (Exception e) {
            log.info("Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response("Ocurrió un error al validar las solicitudes Pendientes."));
        }
    }

    @PostMapping(path = "/registrar")
    public ResponseEntity<Response> registrar(@RequestBody Prestamo prestamo) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new Response(prestamoService.guardarPrestamo(prestamo), "Prestamo registrado."));
        } catch (Exception e) {
            log.info("Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response("Error al registrar el prestamo."));
        }
    }
    // Actualizar prestamista por id
    @PutMapping(path = "/actualizar")
    public ResponseEntity<Response> actualizar(@RequestBody Prestamo prestamo) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new Response(prestamoService.actualizarPrestamo(prestamo), "Prestamo actualizado."));
        } catch (Exception e) {
            log.info("Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response("Error al actualizar el prestamo."));
        }
    }

    // Listar por prestamista por id
    @GetMapping(path = "/prestamista/{id}")
    public ResponseEntity<Response> listarPorPrestamista(@PathVariable("id") int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Response(
                    prestamoService.obtenerPrestamosPorPrestamista(id), "Lista de prestamos por prestamista."));
        } catch (Exception e) {
            log.info("Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response("Error al listar los prestamos por prestamista."));
        }
    }

    // Listar por prestatario
    @GetMapping(path = "/prestatario/{id}")
    public ResponseEntity<Response> listarPorPrestatario(@PathVariable("id") int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Response(
                    prestamoService.obtenerPrestamosPorPrestatario(id), "Lista de prestamos por prestatario."));
        } catch (Exception e) {
            log.info("Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response("Error al listar los prestamos por prestatario."));
        }
    }

    // Listar por grupo prestamista
    @GetMapping(path = "/grupo/{id}")
    public ResponseEntity<Response> listarPorGrupoPrestamista(@PathVariable("id") int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new Response(prestamoService.obtenerPrestamosPorGrupoPrestamista(id),
                            "Lista de prestamos por grupo prestamista."));
        } catch (Exception e) {
            log.info("Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response("Error al listar los prestamos por grupo prestamista."));
        }
    }

    // Listar por jefe de grupo por id
    @GetMapping(path = "/jefePrestamista/{id}")
    public ResponseEntity<Response> listarPorJefePrestamista(@PathVariable("id") int id) {
        try {
            Optional<Grupo> grupo = grupoService.obtenerGrupoPorIdJefePrestamista(id);
            if (!grupo.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new Response("No se encontró el Jefe de Prestamis - Id = [ " + id + " ]."));
            }

            List<Prestamo> prestamos = prestamoService.obtenerPrestamosPorGrupoPrestamista(grupo.get().getIdGrupo());
            if (prestamos.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new Response("No se encontró prestamos del Jefe de Prestamis - Id = [ " + id + " ]."));
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new Response(prestamos, "Lista de prestamos del Jefe de prestamista Id = [ " + id + " ]."));
            }
        } catch (Exception e) {
            log.info("Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response("Error al listar los prestamos por jefe de grupo prestamista."));
        }
    }

}
