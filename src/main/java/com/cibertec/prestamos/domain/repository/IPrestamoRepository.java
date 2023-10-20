package com.cibertec.prestamos.domain.repository;

import com.cibertec.prestamos.domain.model.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPrestamoRepository extends JpaRepository<Prestamo, Integer> {
    // Puedes agregar métodos personalizados aquí si es necesario
}
