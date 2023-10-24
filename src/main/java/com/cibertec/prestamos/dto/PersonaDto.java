package com.cibertec.prestamos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonaDto {
    public int idPersona;
    public String nombres;
    public String apellidoPaterno;
    public String apellidoMaterno;
    public int tipoDocumento;
    public String numeroDocumento;
    public String direccion;
    public String email;
    public String telefono;
    public int estado;
    public String fechaRegistro;

}
