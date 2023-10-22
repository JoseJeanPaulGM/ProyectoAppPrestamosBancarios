package com.cibertec.prestamos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GrupoPrestamistaDto {
    public int idGrupoPrestamista;
    public String descripcion;
    public int estado;
    public String usuarioCreacion;
    public String usuarioModificacion;
}
