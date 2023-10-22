package com.cibertec.prestamos.service;

import com.cibertec.prestamos.domain.model.Persona;

import java.util.List;
import java.util.Optional;

public interface IPersonaService {
    List<Persona> obtenerTodasLasPersonas();

    Optional<Persona> obtenerPersonaPorId(int id);

    Persona guardarPersona(Persona persona);

    void eliminarPersona(int id);

    Optional<Persona> obtenerPersonaPorEmail(String email);

    Optional<Persona> obtenerPersonaPorDni(String dni);
}