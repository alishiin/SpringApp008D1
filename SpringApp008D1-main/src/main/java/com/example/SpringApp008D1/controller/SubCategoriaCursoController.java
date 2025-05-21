package com.example.SpringApp008D1.controller;

import com.example.SpringApp008D1.model.SubCategoriaCursoModel;
import com.example.SpringApp008D1.service.SubCategoriaCursoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subcategorias")
public class SubCategoriaCursoController {

    private final SubCategoriaCursoService subCategoriaCursoService;

    public SubCategoriaCursoController(SubCategoriaCursoService subCategoriaCursoService) {
        this.subCategoriaCursoService = subCategoriaCursoService;
    }

    @PostMapping
    public ResponseEntity<String> crear(@RequestBody SubCategoriaCursoModel subcategoria) {
        subCategoriaCursoService.crearSubCategoria(subcategoria);
        return ResponseEntity.ok("Subcategoría de curso creada correctamente.");
    }

    @GetMapping
    public List<SubCategoriaCursoModel> listar() {
        return subCategoriaCursoService.listarSubCategorias();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        boolean eliminado = subCategoriaCursoService.eliminarSubCategoria(id);
        if (eliminado) {
            return ResponseEntity.ok("Subcategoría eliminada exitosamente.");
        } else {
            return ResponseEntity.badRequest().body("Subcategoría no encontrada.");
        }
    }
}
