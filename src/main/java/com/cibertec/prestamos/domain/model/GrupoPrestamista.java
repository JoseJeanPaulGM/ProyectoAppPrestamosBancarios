package com.cibertec.prestamos.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "grupo_prestamista")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GrupoPrestamista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grupo_prestamista")
    private int idGrupoPrestamista;

//    @Column(name = "id_prestamista")
//    private int idPrestamista;

    @ManyToOne
    @JoinColumn(name = "id_grupo")
    @JsonBackReference
    private Grupo grupo;

    @ManyToOne
    @JoinColumn(name = "id_prestamista")
    @JsonBackReference
    private Usuario usuario;


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
