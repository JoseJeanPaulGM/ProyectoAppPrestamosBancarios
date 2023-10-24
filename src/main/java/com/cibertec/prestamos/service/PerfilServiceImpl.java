package com.cibertec.prestamos.service;

import com.cibertec.prestamos.domain.model.Modulo;
import com.cibertec.prestamos.domain.model.Perfil;
import com.cibertec.prestamos.domain.repository.IPerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerfilServiceImpl implements IPerfilService {

    @Autowired
    private IPerfilRepository perfilRepository;

    @Override
    public List<Perfil> obtenerTodosLosPerfiles() {
        return perfilRepository.findAll();
    }

    @Override
    public Perfil obtenerPerfilPorId(int id) {
        return perfilRepository.findById(id).orElse(null);
    }

    @Override
    public Perfil guardarPerfil(Perfil perfil) {
        return perfilRepository.save(perfil);
    }

    @Override
    public void eliminarPerfil(int id) {
        perfilRepository.deleteById(id);
    }

    @Override
    public List<Modulo> obtenerModuloPorPefil(Perfil perfil) {
        return perfilRepository.findModulosByPerfil(perfil);
    }
}