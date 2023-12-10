package com.cibertec.prestamos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SolicitudDto {

    public int idSolicitud;
    public int idPrestatario;
    public int idPrestamista;
    public BigDecimal monto;
    public double interes;
    public String concepto;
    public int cantidadCuotas;
    public String cuentaBancaria;
    public String observaciones;
    public int estado;
    public String usuarioCreacion;
}
