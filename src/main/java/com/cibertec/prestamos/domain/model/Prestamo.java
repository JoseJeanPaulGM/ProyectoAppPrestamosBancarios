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
@Table(name = "prestamo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prestamo")
    private int idPrestamo;

    @OneToOne
    @JoinColumn(name = "id_solicitud")
    private Solicitud solicitud;

    @Column(name = "id_prestamista", nullable = false)
    private int idPrestamista;

    @Column(name = "id_grupo_prestamista")
    private int idGrupoPrestamista;

    @Column(name = "id_prestatario", nullable = false)
    private int idPrestatario;

    @Column(name = "monto_total", precision = 10, scale = 2, nullable = false, columnDefinition = "Decimal(10,2)")
    private BigDecimal montoTotal;

    @Column(name = "monto_interes", precision = 10, scale = 2, nullable = false, columnDefinition = "Decimal(10,2)")
    private BigDecimal montoInteres;

    @Column(name = "numer_cuotas", nullable = false)
    private int numeroCuotas;

    @Column(name = "tasa_interes", nullable = false)
    private double tasaInteres;

    @Column(name = "fecha_inicio", nullable = false)
    private Date fechaInicio;

    @Column(name = "fecha_vencimiento", nullable = false)
    private Date fechaVencimiento;

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

    @OneToMany(mappedBy = "prestamo", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<CuotaPrestamo> cuotasPrestamo;

//    @JsonProperty("idSolicitud")
//    public int getSolicitudId() {
//        return idSolicitud != null ? idSolicitud.getIdSolicitud() : 0;
//    }

}
