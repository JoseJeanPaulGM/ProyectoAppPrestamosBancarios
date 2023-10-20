package com.cibertec.prestamos.domain.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "pago")
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago")
    private int idPago;

    @ManyToOne
    @JoinColumn(name = "id_tipo_comprobante")
    @JsonManagedReference
    private TipoComprobante tipoComprobante;

    @Column(name = "monto", precision = 10, scale = 2, nullable = false)
    private BigDecimal monto;

    @ManyToOne
    @JoinColumn(name = "id_medio_pago")
    @JsonManagedReference
    private MedioPago medioPago;

    @Column(name = "observaciones", length = 200)
    private String observaciones;

    @Column(name = "estado", nullable = false)
    private int estado;

    @Column(name = "usuario_registro", nullable = false)
    private String usuarioRegistro;

    @Column(name = "fecha_registro", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;

}
