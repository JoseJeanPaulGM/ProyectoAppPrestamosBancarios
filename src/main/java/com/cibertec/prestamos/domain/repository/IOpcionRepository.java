package com.cibertec.prestamos.domain.repository;

import com.cibertec.prestamos.domain.model.Opcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IOpcionRepository extends JpaRepository<Opcion, Integer> {

    @Query("SELECT o FROM Opcion o WHERE o.modulo.idModulo = :idModulo")
    public abstract List<Opcion> findByIdModulo(int idModulo);
}

