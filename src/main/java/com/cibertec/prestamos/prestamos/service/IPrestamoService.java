package com.cibertec.prestamos.service;

import com.cibertec.prestamos.domain.model.Prestamo;

import java.util.List;

public interface IPrestamoService {
    List<Prestamo> obtenerTodosLosPrestamos();

    Prestamo obtenerPrestamoPorId(int id);

    void guardarPrestamo(Prestamo prestamo);

    void eliminarPrestamo(int id);
}
