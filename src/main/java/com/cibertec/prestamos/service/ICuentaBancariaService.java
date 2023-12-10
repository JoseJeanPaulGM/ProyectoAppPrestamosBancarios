package com.cibertec.prestamos.service;

import com.cibertec.prestamos.domain.model.CuentaBancaria;

import java.util.List;

public interface ICuentaBancariaService {

    List<CuentaBancaria> obtenerTodasLasCuentasBancarias();

    List<CuentaBancaria> obtenerCuentaPorIdPrestatario( int idPrestatario);
    CuentaBancaria guardarCuentaBancaria(CuentaBancaria cuentaBancaria);

    CuentaBancaria obtenerCuentaBancariaPorId(int idCuentaBancaria);

}
