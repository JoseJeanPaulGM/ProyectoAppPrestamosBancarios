package com.cibertec.prestamos.service;

import com.cibertec.prestamos.domain.model.Usuario;

import java.util.List;
import java.util.Optional;


public interface IUsuarioService {
    List<Usuario> obtenerTodosLosUsuarios();

    Usuario obtenerUsuarioPorId(int id);
    Usuario guardarUsuario(Usuario usuario);

    void eliminarUsuario(int id);

    void cambiarEstadoUsuario(int usuarioId,int estado);

    String generarUsernameUnico(String nombre, String apellido);

    Optional<Usuario> obtenerUsuarioPorUsername(String username);



}
