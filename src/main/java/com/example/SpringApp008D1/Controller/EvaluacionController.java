package com.example.SpringApp008D1.controller;

import com.example.SpringApp008D1.model.EvaluacionModel;
import com.example.SpringApp008D1.service.EvaluacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evaluaciones")
public class EvaluacionController {

    private final EvaluacionService evaluacionService;

    public EvaluacionController(EvaluacionService evaluacionService) {
        this.evaluacionService = evaluacionService;
    }

    @PostMapping
    public ResponseEntity<String> crear(@RequestBody EvaluacionModel evaluacion) {
        evaluacionService.crearEvaluacion(evaluacion);
        return ResponseEntity.ok("Evaluación agregada.");
    }

    @GetMapping
    public ResponseEntity<List<EvaluacionModel>> listar() {
        List<EvaluacionModel> evaluaciones = evaluacionService.listarEvaluaciones();
        return ResponseEntity.ok(evaluaciones);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        String mensaje = evaluacionService.eliminarEvaluacion(id);
        if ("Evaluación eliminada.".equals(mensaje)) {
            return ResponseEntity.ok(mensaje);
        } else {
            return ResponseEntity.badRequest().body(mensaje);
        }
    }

}
