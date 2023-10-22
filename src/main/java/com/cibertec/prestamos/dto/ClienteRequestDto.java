package com.cibertec.prestamos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRequestDto {

    public  PersonaDto persona;
    public String email;
    public String contrasena;
    public int idPerfil;
    public int estado;
    public String usuarioCreacion;
}
