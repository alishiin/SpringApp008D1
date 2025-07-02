package com.example.SpringApp008D1.controller;

import com.example.SpringApp008D1.assembler.RespuestaEvaluacionAssembler;
import com.example.SpringApp008D1.model.RespuestaEvaluacionModel;
import com.example.SpringApp008D1.service.RespuestaEvaluacionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v2/respuestas-evaluacion")
@Tag(name = "Respuesta-Evaluación", description = "Respuestas de Evaluaciones")
public class RespuestaEvaluacionControllerV2 {

    private final RespuestaEvaluacionService respuestaService;
    private final RespuestaEvaluacionAssembler assembler;

    public RespuestaEvaluacionControllerV2(RespuestaEvaluacionService respuestaService, RespuestaEvaluacionAssembler assembler) {
        this.respuestaService = respuestaService;
        this.assembler = assembler;
    }

    @GetMapping
    public List<EntityModel<RespuestaEvaluacionModel>> getAllRespuestas() {
        return respuestaService.getAllRespuestas()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<RespuestaEvaluacionModel>> getRespuestaById(@PathVariable Long id) {
        return respuestaService.getRespuestaById(id)
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> createRespuesta(@RequestBody RespuestaEvaluacionModel respuesta) {
        respuestaService.saveRespuesta(respuesta);
        return ResponseEntity.ok("Respuesta agregada con éxito");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateRespuesta(@PathVariable Long id, @RequestBody RespuestaEvaluacionModel respuesta) {
        if (respuestaService.updateRespuesta(id, respuesta)) {
            return ResponseEntity.ok("Respuesta actualizada con éxito");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRespuesta(@PathVariable Long id) {
        if (respuestaService.deleteRespuesta(id)) {
            return ResponseEntity.ok("Respuesta eliminada con éxito");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
