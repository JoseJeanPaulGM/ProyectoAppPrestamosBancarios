package com.cibertec.prestamos.domain.repository;

import com.cibertec.prestamos.domain.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPagoRepository extends JpaRepository<Pago, Integer> {
    // Puedes agregar métodos personalizados aquí si es necesario
}

