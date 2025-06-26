package com.example.SpringApp008D1.controller;

import com.example.SpringApp008D1.assembler.ReseñaAssembler;
import com.example.SpringApp008D1.model.ReseñaModel;
import com.example.SpringApp008D1.service.ReseñaService;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v2/resenas")
public class ReseñaControllerV2 {

    private final ReseñaService reseñaService;
    private final ReseñaAssembler assembler;

    public ReseñaControllerV2(ReseñaService reseñaService, ReseñaAssembler assembler) {
        this.reseñaService = reseñaService;
        this.assembler = assembler;
    }

    @PostMapping
    public ResponseEntity<String> crear(@RequestBody ReseñaModel reseña) {
        reseñaService.crearReseña(reseña);
        return ResponseEntity.ok("Reseña agregada correctamente.");
    }

    @GetMapping
    public List<EntityModel<ReseñaModel>> listar() {
        return reseñaService.listarReseñas()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
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
