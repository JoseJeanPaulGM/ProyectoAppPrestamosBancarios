package com.cibertec.prestamos.service;

import com.cibertec.prestamos.domain.model.TipoDocumento;

import java.util.List;

public interface ITipoDocumentoService {
    List<TipoDocumento> obtenerTodosLosTiposDeDocumento();

    TipoDocumento obtenerTipoDeDocumentoPorId(int id);

    void guardarTipoDeDocumento(TipoDocumento tipoDocumento);

    void eliminarTipoDeDocumento(int id);
}
