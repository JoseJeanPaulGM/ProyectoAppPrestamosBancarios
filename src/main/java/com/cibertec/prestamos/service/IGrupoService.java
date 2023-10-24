package com.cibertec.prestamos.service;

import com.cibertec.prestamos.domain.model.Grupo;
import com.cibertec.prestamos.domain.repository.IGrupoRepository;

import java.util.List;
import java.util.Optional;

public interface IGrupoService {
    List<Grupo> obtenerTodosLosGrupos();

    Optional<Grupo> obtenerGrupoPorId(int id);

    Grupo guardarGrupo(Grupo grupo);

    void eliminarGrupo(Grupo grupo);

    Optional<Grupo> obtenerGrupoPorIdJefePrestamista(int idJefePrestamista);
}

