package com.cibertec.prestamos.domain.repository;

import com.cibertec.prestamos.domain.model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPerfilRepository extends JpaRepository<Perfil, Integer> {
    // Puedes agregar métodos personalizados aquí si es necesario
}
