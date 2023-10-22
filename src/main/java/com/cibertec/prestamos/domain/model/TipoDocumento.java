package com.cibertec.prestamos.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tipo_documento")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoDocumento {
    @Id
    @Column(name = "id_documento")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDocumento;

    @Column(name = "valor", nullable = false)
    private String valor;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;


    @Column(name = "estado", nullable = false)
    private int estado;
}
