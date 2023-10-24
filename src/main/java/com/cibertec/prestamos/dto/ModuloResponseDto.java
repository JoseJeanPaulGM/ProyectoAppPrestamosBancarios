package com.cibertec.prestamos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModuloResponseDto {

    public int idModulo;
    public String descripcion;
    public int estado;
    public List<OpcionResponseDto> opciones;
}