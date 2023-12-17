package com.cibertec.prestamos.controller;

import com.cibertec.prestamos.domain.model.*;
import com.cibertec.prestamos.dto.CuotaPrestamoDto;
import com.cibertec.prestamos.service.*;
import com.cibertec.prestamos.util.AppSettings;
import com.cibertec.prestamos.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Objects;

@RestController
@RequestMapping(path = "api/v1/cuota")
@CrossOrigin(origins = AppSettings.CrossOriginUrl)
public class CuotaController {

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IPagoService pagoService;

    @Autowired
    private IPrestamoService prestamoService;

    @Autowired
    private ICuotaPrestamoService cuotaPrestamoService;

    private Logger log = LoggerFactory.getLogger(CuotaController.class);

    //Obtener cuotas por prestamo
    @GetMapping(path = "/listar/{id}")
    public ResponseEntity<Response> listarPorPrestamo(@PathVariable("id") int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Response(cuotaPrestamoService.obtenerCuotaDePrestamoPorId(id), "Lista de cuotas del prestamo Id = [ " + id + " ]."));
        } catch (Exception e) {
            log.info("Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Error al listar las cuotas del prestamo Id = [ " + id + " ]."));
        }
    }

    //Actualizar cuota
    @PutMapping(path = "/actualizar/{id}")
    public ResponseEntity<Response> actualizar(@PathVariable("id") int id, @RequestBody CuotaPrestamoDto cuotaPrestamo) {
        try {
            CuotaPrestamo newCuotaPrestamo = cuotaPrestamoService.obtenerCuotaDePrestamoPorId(id);
            if(newCuotaPrestamo == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("No se encontro la cuota con el Id = [ " + id + " ]."));
            }
//            BigDecimal montoPendiente= newCuotaPrestamo.getMontoPendiente();
//            BigDecimal montoPagado = cuotaPrestamo.getMontoPagado();
//            BigDecimal montoPagadoRestaTotal = montoPendiente.subtract(montoPagado);
//            int estado = montoPagadoRestaTotal.toString().equals("0.00")? 3 : 2;
            newCuotaPrestamo.setEstado(cuotaPrestamo.getEstado());
            newCuotaPrestamo.setFechaModificacion(java.sql.Date.valueOf(java.time.LocalDate.now()));
            newCuotaPrestamo.setUsuarioModificacion(cuotaPrestamo.getUsuarioModificacion());

            BigDecimal montoPagadoActual = newCuotaPrestamo.getMontoPagado();
            // Modificacion  de monto pagado
            newCuotaPrestamo.setMontoPagado(montoPagadoActual.add(cuotaPrestamo.getMontoPagado()));

            newCuotaPrestamo.setMontoPendiente(cuotaPrestamo.getMontoPendiente());

            //Actualizar cuota
            CuotaPrestamo updateCuota =  cuotaPrestamoService.actualizarCuotaDePrestamo(newCuotaPrestamo);

            //Registrar Pago
            Pago newPago = new Pago();
            newPago.setCuota(updateCuota);
            newPago.setMonto(cuotaPrestamo.getMontoPagado());
            newPago.setEstado(cuotaPrestamo.getEstado()-1 );
            newPago.setFechaRegistro(java.sql.Date.valueOf(java.time.LocalDate.now()));
            newPago.setUsuarioRegistro(cuotaPrestamo.getUsuarioModificacion());



            //Tipo Comprobante
            TipoComprobante tipoComprobante = new TipoComprobante();
            tipoComprobante.setIdTipoComprobante(1);
            newPago.setTipoComprobante(tipoComprobante);

            //Medio Pago
            MedioPago medioPago = new MedioPago();
            medioPago.setIdMedioPago(1);
            newPago.setMedioPago(medioPago);


            pagoService.guardarPago(newPago);

            //Validar si es la ultima cuota
            Prestamo prestamo = prestamoService.obtenerPrestamoPorId(newCuotaPrestamo.getPrestamo().getIdPrestamo());
            if(prestamo.getNumeroCuotas() == updateCuota.getNumeroCuota()){
                prestamo.setEstado(2);
                prestamoService.actualizarPrestamo(prestamo);
            }



            return ResponseEntity.status(HttpStatus.OK).body(new Response(updateCuota, "Se registró el pago correctamente."));
        } catch (Exception e) {
            log.info("Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Error al actualizar las cuotas con Id = [ " + id + " ]."));
        }
    }




    //Obtener cuotas por usuario
    //Obtener cuotas por prestamo y usuario

}
