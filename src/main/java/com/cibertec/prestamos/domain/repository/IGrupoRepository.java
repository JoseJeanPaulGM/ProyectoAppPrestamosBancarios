package com.cibertec.prestamos.domain.repository;

import com.cibertec.prestamos.domain.model.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IGrupoRepository extends JpaRepository<Grupo, Integer> {
    // Puedes agregar métodos personalizados aquí si es necesario
}

