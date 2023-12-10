package com.cibertec.prestamos.domain.repository;

import com.cibertec.prestamos.domain.model.CuentaBancaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICuentaBancariaRepository extends JpaRepository<CuentaBancaria, Integer> {

    @Query("SELECT cb FROM CuentaBancaria cb WHERE cb.cliente.idUsuario = :idPrestatario")
    public List<CuentaBancaria> findAllByIdPrestatario(int idPrestatario);

}
