package com.cibertec.prestamos.service;

import com.cibertec.prestamos.domain.model.Grupo;
import com.cibertec.prestamos.domain.repository.IGrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GrupoServiceImpl implements IGrupoService {

    @Autowired
    private IGrupoRepository grupoRepository;


    @Override
    public List<Grupo> obtenerTodosLosGrupos() {
        return grupoRepository.findAll();
    }

    @Override
    public Optional<Grupo> obtenerGrupoPorId(int id) {
        return grupoRepository.findById(id);
    }

    @Override
    public Grupo guardarGrupo(Grupo grupo) {
        return grupoRepository.save(grupo);
    }

    @Override
    public void eliminarGrupo(Grupo grupo) {
        grupoRepository.delete(grupo);
    }

    @Override
    public Optional<Grupo> obtenerGrupoPorIdJefePrestamista(int idJefePrestamista) {
        return grupoRepository.findByIdJefePrestamista(idJefePrestamista);
    }
}
