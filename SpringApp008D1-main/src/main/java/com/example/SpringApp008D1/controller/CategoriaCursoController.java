package com.example.SpringApp008D1.controller;

import com.example.SpringApp008D1.model.CategoriaCursoModel;
import com.example.SpringApp008D1.service.CategoriaCursoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaCursoController {

    private final CategoriaCursoService categoriaCursoService;

    public CategoriaCursoController(CategoriaCursoService categoriaCursoService) {
        this.categoriaCursoService = categoriaCursoService;
    }

    @PostMapping
    public ResponseEntity<String> crear(@RequestBody CategoriaCursoModel categoria) {
        categoriaCursoService.crearCategoria(categoria);
        return ResponseEntity.ok("📚 Categoría de curso creada correctamente.");
    }

    @GetMapping
    public ResponseEntity<List<CategoriaCursoModel>> listar() {
        List<CategoriaCursoModel> categorias = categoriaCursoService.listarCategorias();
        return ResponseEntity.ok(categorias);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        boolean eliminado = categoriaCursoService.eliminarCategoria(id);
        if (eliminado) {
            return ResponseEntity.ok("🗑️ Categoría eliminada exitosamente.");
        } else {
            return ResponseEntity.badRequest().body("❌ Categoría no encontrada.");
        }
    }
}
