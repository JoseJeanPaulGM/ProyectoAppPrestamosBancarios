package com.cibertec.prestamos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrestamistaGrupoDto {
    public int idGrupoPrestamista;
    public int idPrestamista;
    public int idJefePrestamista;
    public String nombres;
    public String apellidoPaterno;
    public String apellidoMaterno;
    public String dni;
    public String telefono;
    public String email;
}
