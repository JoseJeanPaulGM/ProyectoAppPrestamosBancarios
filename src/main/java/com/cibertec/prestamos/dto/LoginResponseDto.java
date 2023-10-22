package com.cibertec.prestamos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {
    private int idUsuario;
    private String email;
    private String contrasena;
    private PersonaDto persona;
    private int estado;
    private PerfilDto perfil;
    private GrupoDto grupo;
    private String usuarioCreacion;
    private String usuarioModificacion;
    private String fechaCreacion;
    private String fechaModificacion;
}
