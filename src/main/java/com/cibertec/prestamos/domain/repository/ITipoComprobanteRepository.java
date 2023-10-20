package com.cibertec.prestamos.domain.repository;

import com.cibertec.prestamos.domain.model.TipoComprobante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITipoComprobanteRepository extends JpaRepository<TipoComprobante, Integer> {
    // Puedes agregar métodos personalizados aquí si es necesario
}
