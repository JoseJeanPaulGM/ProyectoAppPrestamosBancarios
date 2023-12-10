package com.cibertec.prestamos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {

        private int idUsuario;
        private String email;
        private int estado;
        private int idPerfil;
        private String perfil;
        private String usuarioCreacion;
        private String usuarioModificacion;
        private String fechaCreacion;
        private String fechaModificacion;
}
