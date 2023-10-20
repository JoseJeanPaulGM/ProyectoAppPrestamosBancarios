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

    @OneToOne
    @JoinColumn(name = "id_pago")
    @JsonManagedReference
    private  Pago pago;

    @Column(name = "monto", precision = 10, scale = 2, nullable = false)
    private BigDecimal monto;

    @Column(name = "interes", precision = 10, scale = 0, nullable = false)
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

}
