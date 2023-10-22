package com.cibertec.prestamos.controller;

import com.cibertec.prestamos.util.AppSettings;
import com.cibertec.prestamos.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cibertec.prestamos.service.IGrupoService;

@RestController
@RequestMapping(path = "api/v1/grupo")
@CrossOrigin(origins = AppSettings.CrossOriginUrl)
public class GrupoController {

    @Autowired
    private IGrupoService grupoService;

    private Logger log = LoggerFactory.getLogger(UsuarioController.class);

    @GetMapping(path = "/listar")
    public ResponseEntity<Response> listar() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Response(grupoService.obtenerTodosLosGrupos(), "Lista de grupos."));
        } catch (Exception e) {
            log.info("Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Error al listar los grupos."));
        }
    }


}
