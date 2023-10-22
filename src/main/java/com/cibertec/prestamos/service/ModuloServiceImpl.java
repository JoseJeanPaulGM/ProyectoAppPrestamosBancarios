package com.cibertec.prestamos.service;

import com.cibertec.prestamos.domain.model.Modulo;
import com.cibertec.prestamos.domain.repository.IModuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuloServiceImpl implements IModuloService{

    @Autowired
    private IModuloRepository moduloRepository;

    @Override
    public Modulo guardarModulo( Modulo modulo) {
        return  moduloRepository.save(modulo);
    }

    @Override
    public List<Modulo> obtenerTodosLosModulos() {
        return moduloRepository.findAll();
    }

    @Override
    public Modulo obtenerModuloPorId(int idModulo) {
        return moduloRepository.findById(idModulo).orElse(null);
    }

    @Override
    public void eliminarModulo(int idModulo) {
        moduloRepository.deleteById(idModulo);
    }
}