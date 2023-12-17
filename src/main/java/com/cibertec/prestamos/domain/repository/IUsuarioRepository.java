package com.cibertec.prestamos.domain.repository;

import com.cibertec.prestamos.domain.model.Perfil;
import com.cibertec.prestamos.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {

    public abstract Optional<Usuario> findByIdUsuario(int idUsuario);

    public abstract Optional<Usuario> findByEmail(String email);

    public abstract List<Usuario> findAllByPerfil(Perfil perfil);

    @Query("SELECT u FROM Usuario u WHERE u.usuarioCreacion = :usuarioCreacion")
    public abstract List<Usuario> findAllByUsuarioCreacion(String usuarioCreacion);

    @Query("SELECT u FROM Usuario u WHERE u.persona.idPersona = :idPersona")
    public abstract Optional<Usuario> findByIdPersona(int idPersona);

    public abstract void deleteByIdUsuario(int idUsuario);

    //Obtener Usuarios por Perfil
    @Query("SELECT u FROM Usuario u WHERE u.perfil.idPerfil = :idPerfil and u.estado = 1")
    public abstract List<Usuario> findAllUsersByIdPerfil(int idPerfil);

}