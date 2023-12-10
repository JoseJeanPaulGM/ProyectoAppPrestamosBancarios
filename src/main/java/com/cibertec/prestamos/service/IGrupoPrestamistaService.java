package com.cibertec.prestamos.service;

import com.cibertec.prestamos.domain.model.Grupo;
import com.cibertec.prestamos.domain.model.GrupoPrestamista;

import java.util.List;
import java.util.Optional;

public interface IGrupoPrestamistaService {
    List<GrupoPrestamista> obtenerTodosLosGruposDePrestamista();

    GrupoPrestamista obtenerGrupoDePrestamistaPorId(int id);

    Optional<GrupoPrestamista> guardarGrupoDePrestamista(GrupoPrestamista grupoPrestamista);

    void eliminarGrupoDePrestamista(int id);

    List<GrupoPrestamista> obtenerPrestamistasPorGrupo(int grupo);

    GrupoPrestamista obtenerGrupoPorIdPrestamista(int idPrestamista);

    List<GrupoPrestamista> obtenerPrestamistasPorUsuarioCreacion(String usuarioCreacion);

}
