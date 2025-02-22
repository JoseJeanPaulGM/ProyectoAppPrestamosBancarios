package com.cibertec.prestamos.service;

import com.cibertec.prestamos.domain.model.Perfil;
import com.cibertec.prestamos.domain.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
    List<Usuario> obtenerTodosLosUsuarios();

    Usuario guardarUsuario(Usuario usuario);

    Optional<Usuario> obtenerUsuarioPorEmail(String email);

    Optional<Usuario> obtenerUsuarioPorId(int idUsuario);

    List<Usuario> obtenerUsuariosPorPerfil(Perfil perfil);

    List<Usuario> obtenerUsuariosPorUsuarioCreacion(String usuarioCreacion);

    Optional<Usuario> obtenerUsuarioPorIdPersona(int idPersona);

    void eliminarUsuarioPorId(int idUsuario);

    void eliminarUsuario(Usuario usuario);

    //Obtener Usuarios por Perfil
    List<Usuario> obtenerUsuariosPorIdPerfil(int idPerfil);

}
