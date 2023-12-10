package com.cibertec.prestamos.domain.repository;

import com.cibertec.prestamos.domain.model.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IPrestamoRepository extends JpaRepository<Prestamo, Integer> {
    // Puedes agregar métodos personalizados aquí si es necesario

    @Query("SELECT p FROM Prestamo p WHERE p.idPrestamista = :idUsuario")
    public abstract List<Prestamo> findAllByPrestamista(int idUsuario);

    @Query("SELECT p FROM Prestamo p WHERE p.idPrestatario = :idUsuario")
    public abstract List<Prestamo> findAllByPrestatario(int idUsuario);

    //validar que el usuario tenga prestamos activos y solicitudes pendientes
    @Query("SELECT p FROM Prestamo p WHERE p.idPrestatario = :idUsuario AND p.estado = 1")
    public abstract List<Prestamo> findAllByPrestatarioAndEstado(int idUsuario);

    //Listar prestamos por id grupo prestamista
    @Query("SELECT p FROM Prestamo p WHERE p.idGrupoPrestamista = :idGrupoPrestamista")
    public abstract List<Prestamo> findAllByGrupoPrestamista(int idGrupoPrestamista);

}
