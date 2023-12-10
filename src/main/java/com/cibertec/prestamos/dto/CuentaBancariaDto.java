package com.cibertec.prestamos.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CuentaBancariaDto {

    private int idCuentaBancaria;

    private int idUsuario;

    private String numeroCuenta;

    private String banco;

    private int estado;
}
