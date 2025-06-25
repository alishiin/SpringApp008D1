package com.example.SpringApp008D1.controller;

import com.example.SpringApp008D1.assembler.CategoriaCursoAssembler;
import com.example.SpringApp008D1.model.CategoriaCursoModel;
import com.example.SpringApp008D1.service.CategoriaCursoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v2/categorias")
public class CategoriaCursoControllerV2 {

    private final CategoriaCursoService categoriaCursoService;

    public CategoriaCursoControllerV2(CategoriaCursoService categoriaCursoService) {
        this.categoriaCursoService = categoriaCursoService;
    }

    @PostMapping
    public ResponseEntity<String> crear(@RequestBody CategoriaCursoAssembler dto) {
        CategoriaCursoModel model = CategoriaCursoModel.fromAssembler(dto); // Conversión de Assembler a Model
        categoriaCursoService.crearCategoria(model);
        return ResponseEntity.ok("✅ Categoría creada correctamente. [v2]");
    }

    @GetMapping
    public ResponseEntity<List<CategoriaCursoAssembler>> listar() {
        List<CategoriaCursoAssembler> dtos = categoriaCursoService.listarCategorias()
                .stream()
                .map(CategoriaCursoModel::toAssembler) // Conversión de Model a Assembler
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        boolean eliminado = categoriaCursoService.eliminarCategoria(id);
        if (eliminado) {
            return ResponseEntity.ok("🗑️ Categoría eliminada exitosamente. [v2]");
        } else {
            return ResponseEntity.status(404).body("Categoría no encontrada. [v2]");
        }
    }
}



//asdas

