package com.cibertec.prestamos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRequestDto {

    public PersonaDto persona;
    public String email;
    public String contrasena;
    public int idPerfil;
    public int estado;
    public String numeroCuenta;
    public String banco;
    public String usuarioCreacion;
}