package com.example.SpringApp008D1.controller;

import com.example.SpringApp008D1.model.IncidenciaModel;
import com.example.SpringApp008D1.service.IncidenciaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incidencias")
public class IncidenciaController {

    private final IncidenciaService incidenciaService;

    public IncidenciaController(IncidenciaService incidenciaService){
        this.incidenciaService = incidenciaService;
    }

    @GetMapping
    public List<IncidenciaModel> getAllIncidencias() {
        return incidenciaService.getAllIncidencias();
    }

    @GetMapping("/{id}")
    public ResponseEntity<IncidenciaModel> getIncidenciaById(@PathVariable Long id) {
        return incidenciaService.getIncidenciaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> createIncidencia(@RequestBody IncidenciaModel incidencia) {
        incidenciaService.saveIncidencia(incidencia);
        return ResponseEntity.ok("Incidencia agregada con éxito");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateIncidencia(@PathVariable Long id, @RequestBody IncidenciaModel incidencia) {
        if (incidenciaService.updateIncidencia(id, incidencia)) {
            return ResponseEntity.ok("Incidencia actualizada con éxito");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteIncidencia(@PathVariable Long id) {
        if (incidenciaService.deleteIncidencia(id)) {
            return ResponseEntity.ok("Incidencia eliminada con éxito");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}