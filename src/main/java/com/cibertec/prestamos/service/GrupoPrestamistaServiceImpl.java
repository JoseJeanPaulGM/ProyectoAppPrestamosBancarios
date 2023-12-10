package com.cibertec.prestamos.service;

import com.cibertec.prestamos.domain.model.GrupoPrestamista;
import com.cibertec.prestamos.domain.repository.IGrupoPrestamistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GrupoPrestamistaServiceImpl implements IGrupoPrestamistaService {

    @Autowired
    private IGrupoPrestamistaRepository grupoPrestamistaRepository;

    @Override
    public List<GrupoPrestamista> obtenerTodosLosGruposDePrestamista() {
        return grupoPrestamistaRepository.findAll();
    }

    @Override
    public GrupoPrestamista obtenerGrupoDePrestamistaPorId(int id) {
        return grupoPrestamistaRepository.findById(id).orElse(null);
    }

    @Override
    public Optional<GrupoPrestamista> guardarGrupoDePrestamista(GrupoPrestamista grupoPrestamista) {

        return Optional.of(grupoPrestamistaRepository.save(grupoPrestamista));
    }

    @Override
    public void eliminarGrupoDePrestamista(int id) {
        grupoPrestamistaRepository.deleteById(id);
    }

    @Override
    public List<GrupoPrestamista> obtenerPrestamistasPorGrupo(int grupo) {
        return grupoPrestamistaRepository.findPrestamistasByGrupo(grupo);
    }

    @Override
    public GrupoPrestamista obtenerGrupoPorIdPrestamista(int idPrestamista) {
        return grupoPrestamistaRepository.findByIdPrestamista(idPrestamista);
    }

    @Override
    public List<GrupoPrestamista> obtenerPrestamistasPorUsuarioCreacion(String usuarioCreacion) {
        return grupoPrestamistaRepository.findPrestamistasByUsuarioCreacion(usuarioCreacion);
    }

}
