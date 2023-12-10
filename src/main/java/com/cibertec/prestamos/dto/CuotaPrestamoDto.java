package com.cibertec.prestamos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CuotaPrestamoDto {

    private int idCuotaPrestamo;
    private int numeroCuota;
    private BigDecimal monto;
    private BigDecimal montoPagado;
    private BigDecimal montoPendiente;
    private double interes;
    private String fechaVencimiento;
    private int estado;
    private String fechaRegistro;
    private String usuarioCreacion;
    private String fechaModificacion;
    private String usuarioModificacion;


}
