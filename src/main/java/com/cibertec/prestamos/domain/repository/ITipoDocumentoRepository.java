package com.cibertec.prestamos.domain.repository;

import com.cibertec.prestamos.domain.model.TipoDocumento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITipoDocumentoRepository extends JpaRepository<TipoDocumento, Integer> {
    // Puedes agregar métodos personalizados aquí si es necesario
}

