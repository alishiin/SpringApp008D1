package com.example.SpringApp008D1.controller;

import com.example.SpringApp008D1.model.ReseñaModel;
import com.example.SpringApp008D1.service.ReseñaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resenas")
public class ReseñaController {

    private final ReseñaService reseñaService;

    public ReseñaController(ReseñaService reseñaService) {
        this.reseñaService = reseñaService;
    }

    @PostMapping
    public ResponseEntity<String> crear(@RequestBody ReseñaModel reseña) {
        reseñaService.crearReseña(reseña);
        return ResponseEntity.ok("Reseña agregada correctamente.");
    }

    @GetMapping
    public List<ReseñaModel> listar() {
        return reseñaService.listarReseñas();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        boolean eliminado = reseñaService.eliminarReseña(id);
        if (eliminado) {
            return ResponseEntity.ok("Reseña eliminada exitosamente.");
        } else {
            return ResponseEntity.badRequest().body("Reseña no encontrada.");
        }
    }
}
