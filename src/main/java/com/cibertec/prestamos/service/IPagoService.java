package com.cibertec.prestamos.service;

import com.cibertec.prestamos.domain.model.Pago;

import java.util.List;
import java.util.Optional;

public interface IPagoService {
    List<Pago> obtenerTodosLosPagos();

    Optional<Pago> obtenerPagoPorId(int id);

    Pago guardarPago(Pago pago);

    Pago actualizarPago(Pago pago);

    void eliminarPago(int id);
}
