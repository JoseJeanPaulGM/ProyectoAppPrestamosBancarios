package com.cibertec.prestamos.service;

import com.cibertec.prestamos.domain.model.Pago;

import java.util.List;

public interface IPagoService {
    List<Pago> obtenerTodosLosPagos();

    Pago obtenerPagoPorId(int id);

    void guardarPago(Pago pago);

    void eliminarPago(int id);
}
