package com.cibertec.prestamos.service;

import com.cibertec.prestamos.domain.model.Pago;
import com.cibertec.prestamos.domain.repository.IPagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagoServiceImpl implements IPagoService{

    @Autowired
    private IPagoRepository pagoRepository;

    @Override
    public List<Pago> obtenerTodosLosPagos() {
        return pagoRepository.findAll();
    }

    @Override
    public Optional<Pago> obtenerPagoPorId(int id) {
        return Optional.ofNullable(pagoRepository.findById(id).orElse(null));
    }

    @Override
    public Pago guardarPago(Pago pago) {
        return pagoRepository.save(pago);
    }

    @Override
    public Pago actualizarPago(Pago pago) {
        return pagoRepository.save(pago);
    }

    @Override
    public void eliminarPago(int id) {

    }
}
