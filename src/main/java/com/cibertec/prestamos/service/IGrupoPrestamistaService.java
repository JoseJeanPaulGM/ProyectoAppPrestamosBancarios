package com.cibertec.prestamos.service;

import com.cibertec.prestamos.domain.model.GrupoPrestamista;

import java.util.List;

public interface IGrupoPrestamistaService {
    List<GrupoPrestamista> obtenerTodosLosGruposDePrestamista();

    GrupoPrestamista obtenerGrupoDePrestamistaPorId(int id);

    void guardarGrupoDePrestamista(GrupoPrestamista grupoPrestamista);

    void eliminarGrupoDePrestamista(int id);
}
