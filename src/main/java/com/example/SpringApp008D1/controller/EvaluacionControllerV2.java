package com.example.SpringApp008D1.controller;

import com.example.SpringApp008D1.assembler.EvaluacionAssembler;
import com.example.SpringApp008D1.model.EvaluacionModel;
import com.example.SpringApp008D1.service.EvaluacionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v2/evaluaciones")
public class EvaluacionControllerV2 {

    @Autowired
    private EvaluacionService evaluacionService;


    @GetMapping
    public ResponseEntity<List<EvaluacionAssembler>> listarEvaluaciones() {
        List<EvaluacionModel> evaluaciones = evaluacionService.listarEvaluaciones();
        List<EvaluacionAssembler> evaluacionesAssembler = evaluaciones.stream()
                .map(EvaluacionModel::toAssembler)
                .collect(Collectors.toList());
        return ResponseEntity.ok(evaluacionesAssembler);
    }


    @GetMapping("/{id}")
    public ResponseEntity<EvaluacionAssembler> obtenerEvaluacion(@PathVariable Long id) {
        return evaluacionService.obtenerEvaluacionPorId(id)
                .map(evaluacion -> ResponseEntity.ok(evaluacion.toAssembler()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<String> crearEvaluacion(@RequestBody EvaluacionAssembler evaluacionAssembler) {
        EvaluacionModel evaluacionModel = EvaluacionModel.fromAssembler(evaluacionAssembler);
        String mensaje = evaluacionService.crearEvaluacion(evaluacionModel);
        return ResponseEntity.ok(mensaje);
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarEvaluacion(@PathVariable Long id, @RequestBody EvaluacionAssembler evaluacionAssembler) {
        return evaluacionService.obtenerEvaluacionPorId(id)
                .map(evaluacionExistente -> {
                    evaluacionExistente.setTitulo(evaluacionAssembler.getTitulo());
                    evaluacionExistente.setDescripcion(evaluacionAssembler.getDescripcion());

                    String mensaje = evaluacionService.actualizarEvaluacion(evaluacionExistente);
                    return ResponseEntity.ok(mensaje);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarEvaluacion(@PathVariable Long id) {
        String mensaje = evaluacionService.eliminarEvaluacion(id);
        if (mensaje.equals("Evaluación eliminada.")) {
            return ResponseEntity.ok(mensaje);
        } else {
            return ResponseEntity.status(404).body(mensaje);
        }
    }
}
