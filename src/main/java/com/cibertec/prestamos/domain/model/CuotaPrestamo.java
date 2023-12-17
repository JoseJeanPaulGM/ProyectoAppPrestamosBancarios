package com.cibertec.prestamos.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "cuota_prestamo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CuotaPrestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cuota_prestamo")
    private int idCuotaPrestamo;

    @ManyToOne
    @JoinColumn(name = "id_prestamo")
    @JsonBackReference
    private Prestamo prestamo;

    @Column(name = "numero_cuota", nullable = false)
    private int numeroCuota;

    @Column(name = "monto", precision = 10, scale = 2, nullable = false)
    private BigDecimal monto;

    @Column(name = "monto_pagado", precision = 10, scale = 2, nullable = false)
    private BigDecimal montoPagado;

    @Column(name = "monto_pendiente", precision = 10, scale = 2, nullable = false)
    private BigDecimal montoPendiente;

    @Column(name = "interes", precision = 10, scale = 2, nullable = false)
    private BigDecimal interes;

    @Column(name = "fecha_vencimiento", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaVencimiento;

    @Column(name = "estado", nullable = false)
    private int estado;

    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;

    @Column(name = "usuario_creacion")
    private String usuarioCreacion;

    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;

    @Column(name = "usuario_modificacion")
    private String usuarioModificacion;

    @OneToMany(mappedBy = "cuota" , fetch =  FetchType.LAZY)
    @JsonManagedReference
    private List<Pago> pagos;

}
