package com.cibertec.prestamos.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "solicitud")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Solicitud {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitud")
    private int idSolicitud;

    @ManyToOne
    @JoinColumn(name = "id_prestatario")
    @JsonBackReference
    private Usuario prestatario;

    @ManyToOne
    @JoinColumn(name = "id_prestamista")
    @JsonBackReference
    private Usuario prestamista;

    @Column(name = "monto", precision = 10, scale = 2, nullable = false, columnDefinition = "Decimal(10,2)")
    private BigDecimal monto;

    @Column(name = "concepto", length = 300)
    private String concepto;

    @Column(name = "cuenta_bancaria", nullable = false)
    private String cuentaBancaria;

    @Column(name = "estado", nullable = false)
    private String estado;

    @Column(name = "observaciones", length = 400)
    private String observaciones;

    @Column(name = "usuario_creacion")
    private String usuarioCreacion;

    @Column(name = "fecha_creacion")
    private Date fechaCreacion;

    @Column(name = "usuario_modificacion")
    private String usuarioModificacion;

    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;

    @OneToOne
    @JoinColumn(name = "id_prestamo")
    @JsonManagedReference
    private Prestamo prestamo;

}
