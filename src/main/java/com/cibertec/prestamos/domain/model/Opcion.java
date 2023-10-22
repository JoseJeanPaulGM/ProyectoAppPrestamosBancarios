package com.cibertec.prestamos.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "opcion")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Opcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_opcion")
    private int idOpcion;

    @ManyToOne
    @JoinColumn(name = "id_modulo")
    @JsonBackReference
    private Modulo modulo;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "estado", nullable = false)
    private int estado;

    @Column(name = "usuario_creacion")
    private String usuarioCreacion;

    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    @Column(name = "usuario_modificacion")
    private String usuarioModificacion;

    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;

}
