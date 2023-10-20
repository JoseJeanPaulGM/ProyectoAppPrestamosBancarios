package com.cibertec.prestamos.service;

import com.cibertec.prestamos.domain.model.Usuario;

import java.util.List;
import java.util.Optional;


public interface IUsuarioService {
    List<Usuario> obtenerTodosLosUsuarios();

    Usuario guardarUsuario(Usuario usuario);

    Optional<Usuario> obtenerUsuarioPorEmail(String email);

    Optional<Usuario> obtenerUsuarioPorId(int idUsuario);

}
