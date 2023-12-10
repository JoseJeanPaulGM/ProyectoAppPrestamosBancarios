package com.cibertec.prestamos.domain.repository;

import com.cibertec.prestamos.domain.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IPersonaRepository extends JpaRepository<Persona, Integer> {
    // Puedes agregar métodos personalizados aquí si es necesario
    Optional<Persona> findByEmail(String email);

    Optional<Persona> findByNumeroDocumento(String dni);


}