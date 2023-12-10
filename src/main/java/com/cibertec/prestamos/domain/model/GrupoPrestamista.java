package com.cibertec.prestamos.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    private Grupo idGrupo;

    @JsonProperty("idGrupo")
    public int getGrupoId() {
       return idGrupo != null ? idGrupo.getIdGrupo() : 0;
    }


    @OneToOne
    @JoinColumn(name = "id_prestamista")
    @JsonBackReference
    private Usuario usuario;

    @JsonProperty("idPrestamista")
    public int getUsuarioId() {
       return usuario != null ? usuario.getIdUsuario() : 0;
    }


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

    @Override
    public String toString() {
        return "GrupoPrestamista{" +
                "idGrupoPrestamista=" + idGrupoPrestamista +
                ", grupo=" + idGrupo +
                ", usuario=" + usuario +
                ", estado=" + estado +
                ", usuarioCreacion='" + usuarioCreacion + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", usuarioModificacion='" + usuarioModificacion + '\'' +
                ", fechaModificacion=" + fechaModificacion +
                '}';
    }

}
