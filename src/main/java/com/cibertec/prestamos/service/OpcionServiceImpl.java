package com.cibertec.prestamos.service;

import com.cibertec.prestamos.domain.model.Opcion;
import com.cibertec.prestamos.domain.repository.IOpcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpcionServiceImpl implements IOpcionService{

    @Autowired
    private IOpcionRepository opcionRepository;
    @Override
    public List<Opcion> obtenerTodasLasOpciones() {
        return opcionRepository.findAll();
    }

    @Override
    public Opcion obtenerOpcionPorId(int id) {
        return opcionRepository.findById(id).orElse(null);
    }

    @Override
    public List<Opcion> obtenerOpcionesPorModulo(int idModulo) {
        return opcionRepository.findByIdModulo(idModulo);
    }

    @Override
    public Opcion guardarOpcion(Opcion opcion) {
        return opcionRepository.save(opcion);
    }

    @Override
    public void eliminarOpcion(int id) {
        opcionRepository.deleteById(id);
    }
}