package com.cibertec.prestamos.service;

import com.cibertec.prestamos.domain.model.Modulo;

import java.util.List;

public interface IModuloService {

    List<Modulo> obtenerTodosLosModulos();

    Modulo guardarModulo(Modulo modulo);

    Modulo obtenerModuloPorId(int idModulo);


    void eliminarModulo(int idModulo);

}