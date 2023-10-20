package com.cibertec.prestamos.service;

import com.cibertec.prestamos.domain.model.Solicitud;

import java.util.List;

public interface ISolicitudService {
    List<Solicitud> obtenerTodasLasSolicitudes();

    Solicitud obtenerSolicitudPorId(int id);

    void guardarSolicitud(Solicitud solicitud);

    void eliminarSolicitud(int id);
}

