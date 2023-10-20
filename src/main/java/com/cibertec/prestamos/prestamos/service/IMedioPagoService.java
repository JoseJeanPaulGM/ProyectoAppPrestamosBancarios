package com.cibertec.prestamos.service;

import com.cibertec.prestamos.domain.model.MedioPago;

import java.util.List;

public interface IMedioPagoService {
    List<MedioPago> obtenerTodosLosMediosDePago();

    MedioPago obtenerMedioDePagoPorId(int id);

    void guardarMedioDePago(MedioPago medioPago);

    void eliminarMedioDePago(int id);
}
