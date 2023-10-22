package com.cibertec.prestamos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpcionDto {
    public int idOpcion;
    public String descripcion;
    public String url;
    public int estado;
    public String usuarioCreacion;
    public String usuarioModificacion;
    public int idModulo;
}
