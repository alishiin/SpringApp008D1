package com.example.SpringApp008D1.controller;

import com.example.SpringApp008D1.assembler.RespuestaEvaluacionAssembler;
import com.example.SpringApp008D1.model.RespuestaEvaluacionModel;
import com.example.SpringApp008D1.service.RespuestaEvaluacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Listar respuesta evaluacion", description = "obtiene una lista de respuestas de evaluacion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente"),
            @ApiResponse(responseCode = "400", description = "Lista no encontrada")})

    public List<EntityModel<RespuestaEvaluacionModel>> getAllRespuestas() {
        return respuestaService.getAllRespuestas()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener respuesta evaluacion por id", description = "obtiene respuestas de evaluacion por id especifico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "respuesta evaluacion obtenida exitosamente"),
            @ApiResponse(responseCode = "400", description = "respuesta evaluacion no encontrada")})
    public ResponseEntity<EntityModel<RespuestaEvaluacionModel>> getRespuestaById(@PathVariable Long id) {
        return respuestaService.getRespuestaById(id)
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear respuesta evaluacion", description = "Crea respuestas de evaluacion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Respuesta evaluacion creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos para crear invalidos")})
    public ResponseEntity<String> createRespuesta(@RequestBody RespuestaEvaluacionModel respuesta) {
        respuestaService.saveRespuesta(respuesta);
        return ResponseEntity.ok("Respuesta agregada con éxito");
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar respuesta evaluacion", description = "Actualiza respuestas de evaluacion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Respuesta evaluacion actualizada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos para actualizar invalidos")})
    public ResponseEntity<String> updateRespuesta(@PathVariable Long id, @RequestBody RespuestaEvaluacionModel respuesta) {
        if (respuestaService.updateRespuesta(id, respuesta)) {
            return ResponseEntity.ok("Respuesta actualizada con éxito");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar respuesta evaluacion", description = "Elimina respuestas de evaluacion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Respuesta evaluacion eliminada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Respuesta evaluacion no encontrada")})
    public ResponseEntity<String> deleteRespuesta(@PathVariable Long id) {
        if (respuestaService.deleteRespuesta(id)) {
            return ResponseEntity.ok("Respuesta eliminada con éxito");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
