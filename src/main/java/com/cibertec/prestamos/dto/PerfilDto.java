package com.cibertec.prestamos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerfilDto {
    public int idPerfil;
    public String descripcion;
    public int estado;
    public String usuarioCreacion;
    public String usuarioModificacion;
    public List<ModuloDto> listaModulos;
}