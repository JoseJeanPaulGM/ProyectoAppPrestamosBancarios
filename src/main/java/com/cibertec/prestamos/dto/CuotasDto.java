package com.cibertec.prestamos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CuotasDto {
    public int idCuota;
    public int numeroCuota;
    public Date fechaVencimiento;
    public BigDecimal monto;
    public BigDecimal interes;
    public int estado;
    public String usuarioCreacion;
}
