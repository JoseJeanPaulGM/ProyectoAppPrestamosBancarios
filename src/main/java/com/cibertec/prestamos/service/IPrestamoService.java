package com.cibertec.prestamos.service;

import com.cibertec.prestamos.domain.model.Prestamo;

import java.util.List;

public interface IPrestamoService {
    List<Prestamo> obtenerTodosLosPrestamos();

    List<Prestamo> obtenerPrestamosPorPrestamista(int idUsuario);

    List<Prestamo> obtenerPrestamosPorPrestatario(int idUsuario);

    List<Prestamo> obtenerPrestamosPorGrupoPrestamista(int idGrupoPrestamista);

    List<Prestamo> obtenerPrestamosPorPrestatarioAndEstado(int idUsuario);

    Prestamo obtenerPrestamoPorId(int id);

    Prestamo guardarPrestamo(Prestamo prestamo);

    Prestamo actualizarPrestamo(Prestamo prestamo);

    void eliminarPrestamo(int id);
}
