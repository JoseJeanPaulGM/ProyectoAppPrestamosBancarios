package com.cibertec.prestamos.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.Date;

@Entity
@Table(name = "perfil_modulo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerfilModulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_perfil_modulo")
    private int idPerfilModulo;

    @ManyToOne
    @JoinColumn(name = "id_perfil")
    @JsonBackReference
    private Perfil perfil;

    @ManyToOne
    @JoinColumn(name = "id_modulo")
    @JsonBackReference

    private Modulo modulo;

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
}