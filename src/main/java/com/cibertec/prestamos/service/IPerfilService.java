package com.cibertec.prestamos.service;

import com.cibertec.prestamos.domain.model.Perfil;

import java.util.List;

public interface IPerfilService {
    List<Perfil> obtenerTodosLosPerfiles();

    Perfil obtenerPerfilPorId(int id);

    void guardarPerfil(Perfil perfil);

    void eliminarPerfil(int id);
}
