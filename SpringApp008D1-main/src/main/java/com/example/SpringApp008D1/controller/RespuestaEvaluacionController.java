package com.example.SpringApp008D1.controller;

import com.example.SpringApp008D1.model.RespuestaEvaluacionModel;
import com.example.SpringApp008D1.service.RespuestaEvaluacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/respuestas-evaluacion")
public class RespuestaEvaluacionController {

    private final RespuestaEvaluacionService respuestaService;

    public RespuestaEvaluacionController(RespuestaEvaluacionService respuestaService){
        this.respuestaService = respuestaService;
    }

    @GetMapping
    public List<RespuestaEvaluacionModel> getAllRespuestas() {
        return respuestaService.getAllRespuestas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaEvaluacionModel> getRespuestaById(@PathVariable Long id) {
        return respuestaService.getRespuestaById(id)
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
