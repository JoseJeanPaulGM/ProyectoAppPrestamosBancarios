package com.cibertec.prestamos.domain.repository;

import com.cibertec.prestamos.domain.model.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface ISolicitudRepository extends JpaRepository<Solicitud, Integer> {

    //obtener por cliente
    @Query(value = "SELECT s FROM Solicitud s WHERE s.idPrestatario.idUsuario = :idCliente")
    public abstract List<Solicitud> findByIdCliente(int idCliente);

    //obtener por prestamista
    @Query(value = "SELECT s FROM Solicitud s WHERE s.idPrestamista = :idPrestamista")
    public abstract List<Solicitud> findByIdPrestamista(int idPrestamista);

    //obtener por grupo prestamista
    @Query(value = "SELECT s FROM Solicitud s WHERE s.idGrupoPrestamista = :idGrupoPrestamista")
    public abstract List<Solicitud> findByIdGrupoPrestamista(int idGrupoPrestamista);

    //actualizar estado

    @Transactional
    @Modifying
    @Query(value = "UPDATE Solicitud s SET s.estado = :estado WHERE s.idSolicitud = :idSolicitud")
    public abstract void actualizarEstado(@Param("idSolicitud") int idSolicitud, @Param("estado") int estado);
}
