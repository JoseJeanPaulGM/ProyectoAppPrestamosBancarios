package com.cibertec.prestamos.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "solicitud")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class    Solicitud {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitud")
    private int idSolicitud;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    @JsonManagedReference
    private Usuario idPrestatario;

    @Column(name = "id_prestamista", nullable = false)
    private int idPrestamista;

    @Column(name = "monto", precision = 10, scale = 2, nullable = false, columnDefinition = "Decimal(10,2)")
    private BigDecimal monto;

    @Column(name = "concepto", length = 300)
    private String concepto;

    @Column(name = "cuenta_bancaria", nullable = false)
    private String cuentaBancaria;

    @Column(name = "interes", nullable = false)
    private double interes;

    @Column(name = "cantidad_cuotas", nullable = false)
    private int cantidadCuotas;

    @Column(name = "estado", nullable = false)
    private int estado;

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

    @Column(name = "id_grupo_prestamista", nullable = true)
    private Integer idGrupoPrestamista;

    @JsonProperty("idPrestatario")
    public int getPrestatarioId() {
        return idPrestatario != null ? idPrestatario.getIdUsuario() : 0;
    }


}
