package com.cibertec.prestamos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {
    private int idUsuario;
    private String email;
    private String contrasena;
    private PersonaDto persona;
    private int estado;
    private int idPerfil;
    private String perfil;
    private String usuarioCreacion;
    private String usuarioModificacion;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private List<ModuloResponseDto> modulos;
}
