package com.cibertec.prestamos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GrupoDto {
    public int idGrupo;
    public String descripcion;
    public int estado;
    public String usuarioCreacion;
    public String usuarioModificacion;

    public GrupoPrestamistaDto grupoPrestamista;
}
