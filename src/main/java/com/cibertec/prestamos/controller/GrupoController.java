package com.cibertec.prestamos.controller;

import com.cibertec.prestamos.domain.model.Grupo;
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

    @PostMapping(path = "/guardar")
    public ResponseEntity<Response> guardar(@RequestBody Grupo grupo) {
        try {
            grupoService.guardarGrupo(grupo);
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Grupo guardado correctamente."));
        } catch (Exception e) {
            log.info("Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Error al guardar el grupo."));
        }
    }

    @DeleteMapping(path = "/eliminar/{id}")
    public ResponseEntity<Response> eliminar(@PathVariable("id") int id) {
        try {
            grupoService.eliminarGrupo(id);
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Grupo eliminado correctamente."));
        } catch (Exception e) {
            log.info("Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Error al eliminar el grupo."));
        }
    }

    @GetMapping(path = "/obtener/{id}")
    public ResponseEntity<Response> obtener(@PathVariable("id") int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Response(grupoService.obtenerGrupoPorId(id), "Grupo obtenido correctamente."));
        } catch (Exception e) {
            log.info("Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Error al obtener el grupo."));
        }
    }

    @PutMapping(path = "/actualizar")
    public ResponseEntity<Response> actualizar(@RequestBody Grupo grupo) {
        try {
            grupoService.guardarGrupo(grupo);
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Grupo actualizado correctamente."));
        } catch (Exception e) {
            log.info("Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Error al actualizar el grupo."));
        }
    }

}
