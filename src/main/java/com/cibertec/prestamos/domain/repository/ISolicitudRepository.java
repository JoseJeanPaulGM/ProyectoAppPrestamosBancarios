package com.cibertec.prestamos.domain.repository;

import com.cibertec.prestamos.domain.model.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISolicitudRepository extends JpaRepository<Solicitud, Integer> {
    // Puedes agregar métodos personalizados aquí si es necesario
}
