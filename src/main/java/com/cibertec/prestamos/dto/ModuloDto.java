package com.cibertec.prestamos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModuloDto {

    public int idModulo;
    public String descripcion;
    public int estado;
    public String usuarioCreacion;
    public String usuarioModificacion;
    public int idPerfil;
}