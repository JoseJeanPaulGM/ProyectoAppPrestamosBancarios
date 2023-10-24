package com.cibertec.prestamos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpcionResponseDto {
    public int idOpcion;
    public String descripcion;
    public String url;
    public int estado;
}