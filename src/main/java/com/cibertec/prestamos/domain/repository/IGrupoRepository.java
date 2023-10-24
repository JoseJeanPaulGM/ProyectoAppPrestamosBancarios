package com.cibertec.prestamos.domain.repository;

import com.cibertec.prestamos.domain.model.Grupo;
import com.cibertec.prestamos.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IGrupoRepository extends JpaRepository<Grupo, Integer> {
    // Puedes agregar métodos personalizados aquí si es necesario
    @Query(value = "SELECT g FROM Grupo g WHERE g.idJefePrestamista = :idJefePrestamista")
    public abstract Optional<Grupo> findByIdJefePrestamista(int idJefePrestamista);
}

