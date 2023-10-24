package com.cibertec.prestamos.domain.repository;

import com.cibertec.prestamos.domain.model.Modulo;
import com.cibertec.prestamos.domain.model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IPerfilRepository extends JpaRepository<Perfil, Integer> {
    // Puedes agregar métodos personalizados aquí si es necesario
    @Query("SELECT pm.modulo FROM PerfilModulo pm WHERE pm.perfil = :perfil")
    List<Modulo> findModulosByPerfil(@Param("perfil") Perfil perfil);
}
