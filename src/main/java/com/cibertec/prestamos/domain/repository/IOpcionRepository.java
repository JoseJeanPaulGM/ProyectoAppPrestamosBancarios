package com.cibertec.prestamos.domain.repository;

import com.cibertec.prestamos.domain.model.Opcion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOpcionRepository extends JpaRepository<Opcion, Integer> {

}