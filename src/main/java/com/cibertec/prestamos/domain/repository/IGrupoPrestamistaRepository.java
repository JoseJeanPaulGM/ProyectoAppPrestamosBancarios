package com.cibertec.prestamos.domain.repository;

import com.cibertec.prestamos.domain.model.GrupoPrestamista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IGrupoPrestamistaRepository extends JpaRepository<GrupoPrestamista, Integer> {
    @Query("SELECT gp FROM GrupoPrestamista gp WHERE gp.idGrupo.idGrupo = :idGrupo")
    public abstract List<GrupoPrestamista> findPrestamistasByGrupo(@Param("idGrupo") int grupo);

    @Query("SELECT gp FROM GrupoPrestamista gp WHERE gp.usuario.idUsuario = :idPrestamista")
    public abstract GrupoPrestamista findByIdPrestamista(int idPrestamista);

    // Obetner por usuario_creacion
    @Query("SELECT gp FROM GrupoPrestamista gp WHERE gp.usuarioCreacion = :usuarioCreacion")
    public abstract List<GrupoPrestamista> findPrestamistasByUsuarioCreacion(
            @Param("usuarioCreacion") String usuarioCreacion);

}
