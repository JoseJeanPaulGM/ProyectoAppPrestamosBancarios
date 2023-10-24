package com.cibertec.prestamos.domain.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "grupo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Grupo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grupo")
    private int idGrupo;

    @Column(name = "id_jefe_prestamista")
    private int idJefePrestamista;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

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

    @OneToMany(mappedBy = "grupo" ,cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<GrupoPrestamista> grupoPrestamistas;

}
