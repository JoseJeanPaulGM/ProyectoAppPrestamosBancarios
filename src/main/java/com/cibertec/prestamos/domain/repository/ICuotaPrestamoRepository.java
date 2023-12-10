package com.cibertec.prestamos.domain.repository;

import com.cibertec.prestamos.domain.model.CuotaPrestamo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICuotaPrestamoRepository extends JpaRepository<CuotaPrestamo, Integer> {
    // Puedes agregar métodos personalizados aquí si es necesario
    //actual
}
