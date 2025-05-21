package com.example.SpringApp008D1.controller;

import com.example.SpringApp008D1.model.SesionModel;
import com.example.SpringApp008D1.service.SesionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sesiones")
public class SesionController {

    private final SesionService sesionService;

    public SesionController(SesionService sesionService) {
        this.sesionService = sesionService;
    }

    @PostMapping
    public ResponseEntity<String> crear(@RequestBody SesionModel sesion) {
        sesionService.crearSesion(sesion);
        return ResponseEntity.ok("Sesión registrada correctamente.");
    }

    @GetMapping
    public List<SesionModel> listar() {
        return sesionService.listarSesiones();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        boolean eliminado = sesionService.eliminarSesion(id);
        if (eliminado) {
            return ResponseEntity.ok("Sesión eliminada correctamente.");
        } else {
            return ResponseEntity.badRequest().body("Sesión no encontrada.");
        }
    }
}
