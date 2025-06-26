package com.example.SpringApp008D1.controller;

import com.example.SpringApp008D1.assembler.SubCategoriaCursoAssembler;
import com.example.SpringApp008D1.model.SubCategoriaCursoModel;
import com.example.SpringApp008D1.service.SubCategoriaCursoService;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v2/subcategorias")
public class SubCategoriaCursoControllerV2 {

    private final SubCategoriaCursoService subCategoriaCursoService;
    private final SubCategoriaCursoAssembler assembler;

    public SubCategoriaCursoControllerV2(SubCategoriaCursoService subCategoriaCursoService, SubCategoriaCursoAssembler assembler) {
        this.subCategoriaCursoService = subCategoriaCursoService;
        this.assembler = assembler;
    }

    @PostMapping
    public ResponseEntity<String> crear(@RequestBody SubCategoriaCursoModel subcategoria) {
        subCategoriaCursoService.crearSubCategoria(subcategoria);
        return ResponseEntity.ok("Subcategoría de curso creada correctamente.");
    }

    @GetMapping
    public List<EntityModel<SubCategoriaCursoModel>> listar() {
        return subCategoriaCursoService.listarSubCategorias()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<SubCategoriaCursoModel>> getSubCategoriaById(@PathVariable Long id) {
        Optional<SubCategoriaCursoModel> subcategoriaOpt = subCategoriaCursoService.listarSubCategorias()
                .stream()
                .filter(sc -> sc.getId().equals(id))
                .findFirst();

        return subcategoriaOpt
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
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
