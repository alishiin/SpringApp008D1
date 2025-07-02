package com.example.SpringApp008D1.controller;

import com.example.SpringApp008D1.model.InscripcionModel;
import com.example.SpringApp008D1.service.InscripcionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/inscripciones")
@Tag(name = "Inscripciones", description = "inscripcion para curso")
public class InscripcionControllerV2 {

    private final InscripcionService inscripcionService;

    public InscripcionControllerV2(InscripcionService inscripcionService) {
        this.inscripcionService = inscripcionService;
    }

    @GetMapping
    public List<InscripcionModel> getAllInscripciones() {
        return inscripcionService.getAllInscripciones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InscripcionModel> getInscripcionById(@PathVariable Long id) {
        return inscripcionService.getInscripcionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> createInscripcion(@RequestBody InscripcionModel inscripcion) {
        inscripcionService.saveInscripcion(inscripcion);
        return ResponseEntity.ok("Inscripción agregada con éxito");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateInscripcion(@PathVariable Long id, @RequestBody InscripcionModel inscripcion) {
        if (inscripcionService.updateInscripcion(id, inscripcion)) {
            return ResponseEntity.ok("Inscripción actualizada con éxito");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInscripcion(@PathVariable Long id) {
        if (inscripcionService.deleteInscripcion(id)) {
            return ResponseEntity.ok("Inscripción eliminada con éxito");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
