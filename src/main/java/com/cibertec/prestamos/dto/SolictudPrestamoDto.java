package com.cibertec.prestamos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SolictudPrestamoDto {
    public int idSolicitud;
    public int idPrestatario;
    public int idPrestamista;
    public BigDecimal montoInteres;
    public BigDecimal montoTotal;
    public BigDecimal montoSolicitado;
    public String usuarioModificacion;
    public String usuarioCreacion;
    public String observaciones;
    public List<CuotasDto> cuotas;
}
