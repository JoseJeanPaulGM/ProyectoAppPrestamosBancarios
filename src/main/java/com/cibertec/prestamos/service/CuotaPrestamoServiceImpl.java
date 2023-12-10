package com.cibertec.prestamos.service;

import com.cibertec.prestamos.domain.model.CuotaPrestamo;
import com.cibertec.prestamos.domain.repository.ICuotaPrestamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuotaPrestamoServiceImpl implements ICuotaPrestamoService {

    @Autowired
    private ICuotaPrestamoRepository cuotaPrestamoRepository;

    @Override
    public List<CuotaPrestamo> obtenerTodasLasCuotasDePrestamo() {
        return cuotaPrestamoRepository.findAll();
    }

    @Override
    public CuotaPrestamo obtenerCuotaDePrestamoPorId(int id) {
        return cuotaPrestamoRepository.findById(id).orElse(null);
    }

    @Override
    public CuotaPrestamo guardarCuotaDePrestamo(CuotaPrestamo cuotaPrestamo) {
        return cuotaPrestamoRepository.save(cuotaPrestamo);
    }

    @Override
    public CuotaPrestamo actualizarCuotaDePrestamo(CuotaPrestamo cuotaPrestamo) {
        return cuotaPrestamoRepository.save(cuotaPrestamo)  ;
    }

    @Override
    public void eliminarCuotaDePrestamo(int id) {
        cuotaPrestamoRepository.deleteById(id);
    }
}
