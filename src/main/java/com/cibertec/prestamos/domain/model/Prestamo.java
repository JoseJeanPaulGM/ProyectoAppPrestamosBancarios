package com.cibertec.prestamos.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "prestamo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prestamo")
    private int idPrestamo;

    @ManyToOne
    @JoinColumn(name = "id_solicitud")
    @JsonBackReference
    private Solicitud solicitud;

    @ManyToOne
    @JoinColumn(name = "id_prestamista")
    private Usuario prestamista;

    @Column(name = "monto_total", precision = 10, scale = 2, nullable = false, columnDefinition = "Decimal(10,2)")
    private BigDecimal montoTotal;

    @Column(name = "monto_interes", precision = 10, scale = 2, nullable = false, columnDefinition = "Decimal(10,2)")
    private BigDecimal montoInteres;

    @Column(name = "cuotas", nullable = false)
    private int cuotas;

    @Column(name = "tasa_interes", nullable = false)
    private double tasaInteres;

    @Column(name = "fecha_inicio", nullable = false)
    private Date fechaInicio;

    @Column(name = "fecha_vencimiento", nullable = false)
    private String fechaVencimiento;

    @Column(name = "estado", nullable = false)
    private int estado;

    @Column(name = "usuario_creacion")
    private String usuarioCreacion;

    @Column(name = "fecha_creacion")
    private Date fechaCreacion;

    @Column(name = "usuario_modificacion")
    private String usuarioModificacion;

    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;

    @OneToMany(mappedBy = "prestamo")
    @JsonManagedReference
    private List<CuotaPrestamo> cuotasPrestamo;

}
