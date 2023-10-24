package com.cibertec.prestamos.service;

import com.cibertec.prestamos.domain.model.Modulo;
import com.cibertec.prestamos.domain.model.Perfil;

import java.util.List;

public interface IPerfilService {
    List<Perfil> obtenerTodosLosPerfiles();

    Perfil obtenerPerfilPorId(int id);

    Perfil guardarPerfil(Perfil perfil);

    void eliminarPerfil(int id);

    List<Modulo> obtenerModuloPorPefil(Perfil perfil);
}
