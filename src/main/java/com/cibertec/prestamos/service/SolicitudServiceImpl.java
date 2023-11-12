package com.cibertec.prestamos.service;

import com.cibertec.prestamos.domain.model.Solicitud;
import com.cibertec.prestamos.domain.repository.ISolicitudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolicitudServiceImpl implements ISolicitudService {

    @Autowired
    private ISolicitudRepository solicitudRepository;

    @Override
    public List<Solicitud> obtenerTodasLasSolicitudes() {
        return solicitudRepository.findAll();
    }

    @Override
    public Solicitud obtenerSolicitudPorId(int id) {

        return solicitudRepository.findById(id).orElse(null);
    }

    @Override
    public Solicitud guardarSolicitud(Solicitud solicitud) {
       return  solicitudRepository.save(solicitud);
    }

    @Override
    public Solicitud actualizarSolicitud(Solicitud solicitud) {
        return solicitudRepository.save(solicitud);
    }

    @Override
    public void eliminarSolicitud(int id) {
        solicitudRepository.deleteById(id);
    }
}