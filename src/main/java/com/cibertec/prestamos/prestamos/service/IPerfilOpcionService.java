package com.cibertec.prestamos.service;

import com.cibertec.prestamos.domain.model.PerfilOpcion;

import java.util.List;

public interface IPerfilOpcionService {
    List<PerfilOpcion> obtenerTodosLosPerfilesOpciones();

    PerfilOpcion obtenerPerfilOpcionPorId(int id);

    void guardarPerfilOpcion(PerfilOpcion perfilOpcion);

    void eliminarPerfilOpcion(int id);
}
