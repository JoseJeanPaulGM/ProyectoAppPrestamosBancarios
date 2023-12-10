package com.cibertec.prestamos.service;

import com.cibertec.prestamos.domain.model.CuentaBancaria;
import com.cibertec.prestamos.domain.repository.ICuentaBancariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentaBancariaServiceImpl  implements ICuentaBancariaService   {

    @Autowired
    private ICuentaBancariaRepository cuentaBancariaRepository;

    @Override
    public List<CuentaBancaria> obtenerTodasLasCuentasBancarias() {
        return cuentaBancariaRepository.findAll();
    }

    @Override
    public List<CuentaBancaria> obtenerCuentaPorIdPrestatario( int idPrestatario) {
        return cuentaBancariaRepository.findAllByIdPrestatario(idPrestatario);
    }

    @Override
    public CuentaBancaria guardarCuentaBancaria(CuentaBancaria cuentaBancaria) {
        return cuentaBancariaRepository.save(cuentaBancaria);
    }

    @Override
    public CuentaBancaria obtenerCuentaBancariaPorId(int idCuentaBancaria) {
        return cuentaBancariaRepository.findById(idCuentaBancaria).orElse(null);
    }
}
