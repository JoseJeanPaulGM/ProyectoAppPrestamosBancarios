package com.cibertec.prestamos.domain.repository;

import com.cibertec.prestamos.domain.model.MedioPago;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMedioPagoRepository extends JpaRepository<MedioPago, Integer> {
    // Puedes agregar métodos personalizados aquí si es necesario
}
