package com.cibertec.prestamos.service;

import com.cibertec.prestamos.domain.model.Prestamo;
import com.cibertec.prestamos.domain.repository.IPrestamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrestamoServiceImpl implements IPrestamoService{

    @Autowired
    private IPrestamoRepository prestamoRepository;

    @Override
    public List<Prestamo> obtenerTodosLosPrestamos() {
        return prestamoRepository.findAll();
    }

    @Override
    public List<Prestamo> obtenerPrestamosPorPrestamista(int idUsuario) {
        return prestamoRepository.findAllByPrestamista(idUsuario);
    }

    @Override
    public List<Prestamo> obtenerPrestamosPorPrestatarioAndEstado(int idUsuario) {
        return prestamoRepository.findAllByPrestatarioAndEstado(idUsuario);
    }

    @Override
    public List<Prestamo> obtenerPrestamosPorPrestatario(int idUsuario) {
        return  prestamoRepository.findAllByPrestatario(idUsuario);
    }

    @Override
    public List<Prestamo> obtenerPrestamosPorGrupoPrestamista(int idGrupoPrestamista) {
        return  prestamoRepository.findAllByGrupoPrestamista(idGrupoPrestamista);
    }

    @Override
    public Prestamo obtenerPrestamoPorId(int id) {

        return prestamoRepository.findById(id).orElse(null);
    }

    @Override
    public Prestamo guardarPrestamo(Prestamo prestamo) {
        return prestamoRepository.save(prestamo);

    }

    @Override
    public Prestamo actualizarPrestamo(Prestamo prestamo) {
        return prestamoRepository.save(prestamo);
    }

    @Override
    public void eliminarPrestamo(int id) {
        prestamoRepository.deleteById(id);

    }


}
