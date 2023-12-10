package com.cibertec.prestamos.controller;

import com.cibertec.prestamos.domain.model.*;
import com.cibertec.prestamos.dto.CuentaBancariaDto;
import com.cibertec.prestamos.dto.CuotasDto;
import com.cibertec.prestamos.dto.SolicitudDto;
import com.cibertec.prestamos.dto.SolictudPrestamoDto;
import com.cibertec.prestamos.service.*;
import com.cibertec.prestamos.util.AppSettings;
import com.cibertec.prestamos.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/solicitud")
@CrossOrigin(origins = AppSettings.CrossOriginUrl)
public class SolicitudController {

    @Autowired
    private ISolicitudService solicitudService;

    @Autowired
    private IPrestamoService prestamoService;

    @Autowired
    private ICuentaBancariaService cuentaBancariaService;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IGrupoPrestamistaService grupoPrestamistaService;

    @Autowired
    private IGrupoService grupoService;

    private Logger log = LoggerFactory.getLogger(SolicitudController.class);

    @GetMapping(path = "/listar")
    public ResponseEntity<Response> listar() {
        try {
            List<Solicitud> solicitudes = solicitudService.obtenerTodasLasSolicitudes();
            return ResponseEntity.status(HttpStatus.OK).body(new Response(solicitudes, "Lista de solicitudes."));
        } catch (Exception e) {
            log.info("Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Error al listar las solicitudes."));
        }
    }

    //listar solicitudes por prestatario
    @GetMapping(path = "/cliente/{id}")
    public ResponseEntity<Response> listarSolicitudesPorPrestatario(@PathVariable("id") int id) {
        try {
            List<Solicitud> solicitudes = solicitudService.obtenerSolicitudesPorIdCliente(id);
            return ResponseEntity.status(HttpStatus.OK).body(new Response(solicitudes, "Lista de solicitudes."));
        } catch (Exception e) {
            log.info("Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Error al listar las solicitudes."));
        }
    }

    //listar solicitudes por prestamista
    @GetMapping(path = "/prestamista/{id}")
    public ResponseEntity<Response> listarSolicitudesPorPrestamista(@PathVariable("id") int id) {
        try {
            List<Solicitud> solicitudes = solicitudService.obtenerSolicitudesPorIdPrestamista(id);
            return ResponseEntity.status(HttpStatus.OK).body(new Response(solicitudes, "Lista de solicitudes."));
        } catch (Exception e) {
            log.info("Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Error al listar las solicitudes."));
        }
    }

    //listar solicitudes por Jefe de Prestamista
    @GetMapping(path = "/jefePrestamista/{id}")
    public ResponseEntity<Response> listarSolicitudesPorJefePrestamista(@PathVariable("id") int id) {
        try {
            Optional<Usuario> usuario = usuarioService.obtenerUsuarioPorId(id);
            if (usuario.isPresent()) {
                List<Solicitud> solicitudes = solicitudService.obtenerSolicitudesPorIdGrupoPrestamista(15);
                return ResponseEntity.status(HttpStatus.OK).body(new Response(solicitudes, "Lista de solicitudes."));
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(new Response("No se encontró el usuario."));
            }
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
    public ResponseEntity<Response> listarCuentaBancariaPorUsuario(@PathVariable("id") int id) {
        try {
            var cuentas = cuentaBancariaService.obtenerCuentaPorIdPrestatario(id);
            return ResponseEntity.status(HttpStatus.OK).body(new Response(cuentas, "Cuenta bancaria."));
        } catch (Exception e) {
            log.info("Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Error al listar la cuenta bancaria."));
        }
    }


    @PostMapping(path = "/registrar")
    public ResponseEntity<Response> registrar(@RequestBody SolicitudDto solicitud) {
        try {

            Optional<Usuario> prestatario = usuarioService.obtenerUsuarioPorId(solicitud.getIdPrestatario());

            Solicitud newSolicitud = new Solicitud();
            newSolicitud.setIdPrestatario(prestatario.get());
            newSolicitud.setIdPrestamista(solicitud.getIdPrestamista());
            newSolicitud.setMonto(solicitud.getMonto());
            newSolicitud.setConcepto(solicitud.getConcepto());
            newSolicitud.setCuentaBancaria(solicitud.getCuentaBancaria());
            newSolicitud.setCantidadCuotas(solicitud.getCantidadCuotas());
            newSolicitud.setEstado(1); //-- 1: Pendiente, 2: Aprobado, 3: Rechazado
            newSolicitud.setUsuarioCreacion(solicitud.getUsuarioCreacion());
            newSolicitud.setInteres(solicitud.getInteres());
            newSolicitud.setFechaCreacion(new Date());

            return ResponseEntity.status(HttpStatus.OK).body(new Response(solicitudService.guardarSolicitud(newSolicitud), "Solicitud registrada."));
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

    //actualizr estado de solicitud
    @PutMapping(path = "/aprobar/{id}")
    public ResponseEntity<Response> actualizarEstado(@PathVariable("id") int id, @RequestBody SolictudPrestamoDto solicitud) {
        try {

            //Obtener Datos de Solitud
            Solicitud solicitudData = solicitudService.obtenerSolicitudPorId(id);
            GrupoPrestamista grupo = grupoPrestamistaService.obtenerGrupoPorIdPrestamista(solicitud.getIdPrestamista());
            Date fechaVencimiento = solicitud.cuotas.get(solicitudData.getCantidadCuotas() - 1).getFechaVencimiento();

            //crear prestamo, actualizar estado de solicitud y generar cuotas
            Prestamo prestamo = new Prestamo();
            prestamo.setNumeroCuotas(solicitudData.getCantidadCuotas());
            prestamo.setEstado(1); //-- 1: Aprobado, 2: Pagado
            prestamo.setFechaInicio(new Date());
            prestamo.setFechaCreacion(new Date());
            prestamo.setFechaVencimiento(fechaVencimiento);
            prestamo.setIdPrestamista(solicitud.getIdPrestamista());
            prestamo.setIdPrestatario(solicitud.getIdPrestatario());
            prestamo.setMontoInteres(solicitud.getMontoInteres());
            prestamo.setMontoTotal(solicitud.getMontoTotal());
            prestamo.setTasaInteres(solicitudData.getInteres());
            prestamo.setUsuarioCreacion(solicitud.getUsuarioCreacion());
            prestamo.setIdGrupoPrestamista(grupo.getGrupoId());

            Solicitud _solicitud = new Solicitud();
            _solicitud.setIdSolicitud(id);
            prestamo.setSolicitud(_solicitud);

            //setear cuotas
            List<CuotaPrestamo> cuotas = new ArrayList<>();

            for (CuotasDto cuota : solicitud.cuotas) {
                CuotaPrestamo _cuota = new CuotaPrestamo();
                _cuota.setEstado(1); //-- 1: Pendiente, 2:Parcialmente , 3: Pagado
                _cuota.setFechaRegistro(new Date());
                _cuota.setFechaVencimiento(cuota.getFechaVencimiento());
                _cuota.setInteres(cuota.getInteres());
                _cuota.setMonto(cuota.getMonto());
                _cuota.setNumeroCuota(cuota.getNumeroCuota());
                _cuota.setUsuarioCreacion(solicitud.getUsuarioCreacion());
                _cuota.setPrestamo(prestamo);
                _cuota.setMontoPagado(BigDecimal.valueOf(0.00));
                _cuota.setMontoPendiente(cuota.getMonto());
                cuotas.add(_cuota);

            }

            prestamo.setCuotasPrestamo(cuotas);

            //guardar prestamo
            prestamoService.guardarPrestamo(prestamo);

            solicitudData.setEstado(2);
            solicitudData.setUsuarioModificacion(solicitud.getUsuarioCreacion());
            solicitudData.setFechaModificacion(new Date());
            solicitudData.setIdPrestamista(solicitud.getIdPrestamista());
            solicitudData.setIdGrupoPrestamista(grupo.getGrupoId());
            solicitudService.actualizarSolicitud(solicitudData);

            return ResponseEntity.status(HttpStatus.OK).body(new Response("Solicitud: " + id + " fue aprobada.", "Solicitud actualizada."));
        } catch (Exception e) {
            log.info("Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Error al actualizar la solicitud."));
        }

    }

    //actualizr estado de solicitud
    @PutMapping(path = "/rechazar/{id}")
    public ResponseEntity<Response> actualizarEstadoRechazado(@PathVariable("id") int id, @RequestBody SolictudPrestamoDto solicitud) {
        try {
            Solicitud solicitudData = solicitudService.obtenerSolicitudPorId(id);
            GrupoPrestamista grupo = grupoPrestamistaService.obtenerGrupoPorIdPrestamista(solicitud.getIdPrestamista());

            solicitudData.setEstado(3);
            solicitudData.setUsuarioModificacion(solicitud.getUsuarioModificacion());
            solicitudData.setFechaModificacion(new Date());
            solicitudData.setIdPrestamista(solicitud.getIdPrestamista());
            solicitudData.setIdGrupoPrestamista(grupo.getGrupoId());
            solicitudData.setObservaciones(solicitud.getObservaciones());
            solicitudService.actualizarSolicitud(solicitudData);

            return ResponseEntity.status(HttpStatus.OK).body(new Response("Solicitud: " + id + " fue rechazada.", "Solicitud actualizada."));
        } catch (Exception e) {
            log.info("Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Error al actualizar la solicitud."));
        }

    }

    //registrar cuenta bancaria
    @PostMapping(path = "/registrarCuenta")
    @CrossOrigin(origins = AppSettings.CrossOriginUrl)
    public ResponseEntity<Response> registrarCuenta(@RequestBody CuentaBancariaDto cuentaBancaria) {
        try {
            CuentaBancaria newCuentaBancaria = new CuentaBancaria();
            Usuario prestatario = new Usuario();
            prestatario.setIdUsuario(cuentaBancaria.getIdUsuario());
            newCuentaBancaria.setCliente(prestatario);
            newCuentaBancaria.setNumeroCuenta(cuentaBancaria.getNumeroCuenta());
            newCuentaBancaria.setBanco(cuentaBancaria.getBanco());
            newCuentaBancaria.setEstado(1); //-- 1: Activo, 2: Inactivo

            return ResponseEntity.status(HttpStatus.OK).body(new Response(cuentaBancariaService.guardarCuentaBancaria(newCuentaBancaria), "Cuenta bancaria registrada."));
        } catch (Exception e) {
            log.info("Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Error al registrar la cuenta bancaria."));
        }
    }

}
