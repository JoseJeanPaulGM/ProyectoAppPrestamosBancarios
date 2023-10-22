package com.cibertec.prestamos.service;

import com.cibertec.prestamos.domain.model.Persona;
import com.cibertec.prestamos.domain.repository.IPersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaServiceImpl implements IPersonaService{

    @Autowired
    private IPersonaRepository personaRepository;

    @Override
    public List<Persona> obtenerTodasLasPersonas() {
        return personaRepository.findAll();
    }

    @Override
    public Optional<Persona> obtenerPersonaPorId(int id) {
        return personaRepository.findById(id)   ;
    }

    @Override
    public Persona guardarPersona(Persona persona) {
        return personaRepository.save(persona);
    }

    @Override
    public void eliminarPersona(int id) {

    }

    @Override
    public Optional<Persona> obtenerPersonaPorEmail(String email) {
        return personaRepository.findByEmail(email);
    }

    @Override
    public Optional<Persona> obtenerPersonaPorDni(String dni) {
        return personaRepository.findByNumeroDocumento(dni);
    }
}
