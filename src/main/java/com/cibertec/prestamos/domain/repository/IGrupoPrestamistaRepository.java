package com.cibertec.prestamos.domain.repository;

import com.cibertec.prestamos.domain.model.Grupo;
import com.cibertec.prestamos.domain.model.GrupoPrestamista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IGrupoPrestamistaRepository extends JpaRepository<GrupoPrestamista, Integer> {
    @Query("SELECT gp FROM GrupoPrestamista gp WHERE gp.grupo = :grupo")
    public abstract List<GrupoPrestamista> findPrestamistasByGrupo(@Param("grupo") Grupo grupo);

}
