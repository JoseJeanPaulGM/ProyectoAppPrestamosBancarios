package com.cibertec.prestamos.service;

import com.cibertec.prestamos.domain.model.CuotaPrestamo;

import java.util.List;

public interface ICuotaPrestamoService {
    List<CuotaPrestamo> obtenerTodasLasCuotasDePrestamo();

    CuotaPrestamo obtenerCuotaDePrestamoPorId(int id);

    CuotaPrestamo guardarCuotaDePrestamo(CuotaPrestamo cuotaPrestamo);

    CuotaPrestamo actualizarCuotaDePrestamo(CuotaPrestamo cuotaPrestamo);

    void eliminarCuotaDePrestamo(int id);
}
