package com.cibertec.prestamos.service;

import com.cibertec.prestamos.domain.model.Grupo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrupoServiceImpl implements IGrupoService{
    @Override
    public List<Grupo> obtenerTodosLosGrupos() {
        return null;
    }

    @Override
    public Grupo obtenerGrupoPorId(int id) {
        return null;
    }

    @Override
    public void guardarGrupo(Grupo grupo) {

    }

    @Override
    public void eliminarGrupo(int id) {

    }
}
