package com.cibertec.prestamos.service;

import com.cibertec.prestamos.domain.model.Opcion;

import java.util.List;

public interface IOpcionService {
    List<Opcion> obtenerTodasLasOpciones();

    Opcion obtenerOpcionPorId(int id);

    List<Opcion> obtenerOpcionesPorModulo(int idModulo);

    Opcion guardarOpcion(Opcion opcion);

    void eliminarOpcion(int id);
}

