package com.cibertec.prestamos.service;

import com.cibertec.prestamos.domain.model.Usuario;
import com.cibertec.prestamos.domain.model.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll(Sort.by(Sort.Direction.ASC, "nombreUsuario")  );
    }

    @Override
    public Usuario obtenerUsuarioPorId(int id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public Usuario guardarUsuario(Usuario usuario) {

        return usuarioRepository.save(usuario);
    }

    @Override
    public void eliminarUsuario(int id) {

    }

    @Override
    public void cambiarEstadoUsuario(int usuarioId, int estado) {
        usuarioRepository.cambiarEstadoUsuario(usuarioId,estado);
    }

    @Override
    public String generarUsernameUnico(String nombre, String apellido) {
        String usernameBase = nombre.charAt(0) + apellido;
        int correlativo = 1;
        String username = usernameBase;

        while (usuarioRepository.existeUsername(username) == 1) {
            username = usernameBase + correlativo;
            correlativo++;
        }

        return username;
    }

    @Override
    public Optional<Usuario> obtenerUsuarioPorUsername(String username) {
        return usuarioRepository.findByNombreUsuario(username);
    }


}
