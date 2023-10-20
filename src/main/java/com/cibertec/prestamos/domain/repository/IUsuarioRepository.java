package com.cibertec.prestamos.domain.repository;

import com.cibertec.prestamos.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {


    public abstract Optional<Usuario> findByIdUsuario(int idUsuario);

    //@Query("SELECT u FROM Usuario u WHERE u.email = :email")
    public abstract Optional<Usuario> findByEmail(String email);

}