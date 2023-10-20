package com.cibertec.prestamos.service;

import com.cibertec.prestamos.domain.model.TipoComprobante;

import java.util.List;

public interface ITipoComprobanteService {
    List<TipoComprobante> obtenerTodosLosTiposDeComprobante();

    TipoComprobante obtenerTipoDeComprobantePorId(int id);

    void guardarTipoDeComprobante(TipoComprobante tipoComprobante);

    void eliminarTipoDeComprobante(int id);
}

