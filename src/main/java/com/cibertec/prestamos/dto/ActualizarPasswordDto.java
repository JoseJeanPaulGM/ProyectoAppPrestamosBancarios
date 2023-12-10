package com.cibertec.prestamos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActualizarPasswordDto {
    public int idUsuario;
    public String contrasena;
    public String contrasenaNueva;
    public String usuarioModificacion;
}
