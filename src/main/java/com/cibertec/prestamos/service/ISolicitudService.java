package com.cibertec.prestamos.service;

import com.cibertec.prestamos.domain.model.Solicitud;

import java.util.List;

public interface ISolicitudService {
    List<Solicitud> obtenerTodasLasSolicitudes();

    List<Solicitud> obtenerSolicitudesPorIdCliente(int idCliente);

    List<Solicitud> obtenerSolicitudesPorIdPrestamista(int idPrestamista);

    List<Solicitud> obtenerSolicitudesPorIdGrupoPrestamista(int idGrupoPrestamista);

    Solicitud obtenerSolicitudPorId(int id);

    Solicitud guardarSolicitud(Solicitud solicitud);

    Solicitud actualizarSolicitud(Solicitud solicitud);

    void actualizarEstado(int idSolicitud, int estado);

    void eliminarSolicitud(int id);
}

