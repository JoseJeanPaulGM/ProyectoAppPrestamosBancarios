package com.cibertec.prestamos.service;

import com.cibertec.prestamos.domain.model.Grupo;

import java.util.List;

public interface IGrupoService {
    List<Grupo> obtenerTodosLosGrupos();

    Grupo obtenerGrupoPorId(int id);

    void guardarGrupo(Grupo grupo);

    void eliminarGrupo(int id);
}

