package com.example.SpringApp008D1.controller;

import com.example.SpringApp008D1.assembler.EvaluacionAssembler;
import com.example.SpringApp008D1.model.EvaluacionModel;
import com.example.SpringApp008D1.service.EvaluacionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v2/evaluaciones")
@Tag(name = "Evaluaciones", description = "Evaluaciones de Curso")
public class EvaluacionControllerV2 {


    private EvaluacionService evaluacionService;


    @GetMapping
    @Operation(summary = "Obtener todas las evaluaciones", description = "Obtiene una lista de evaluaciones")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de evaluacion obtenida exitosamente"),
            @ApiResponse(responseCode = "400", description = "Lista no encontrada")})
    public ResponseEntity<List<EvaluacionAssembler>> listarEvaluaciones() {
        List<EvaluacionModel> evaluaciones = evaluacionService.listarEvaluaciones();
        List<EvaluacionAssembler> evaluacionesAssembler = evaluaciones.stream()
                .map(EvaluacionModel::toAssembler)
                .collect(Collectors.toList());
        return ResponseEntity.ok(evaluacionesAssembler);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Obtener evaluaciones por id", description = "Obtiene una evaluacion por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Evaluacion obtenida exitosamente"),
            @ApiResponse(responseCode = "400", description = "Evaluacion no encontrada")})
    public ResponseEntity<EvaluacionAssembler> obtenerEvaluacion(@PathVariable Long id) {
        return evaluacionService.obtenerEvaluacionPorId(id)
                .map(evaluacion -> ResponseEntity.ok(evaluacion.toAssembler()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    @Operation(summary = "Crear nueva evaluacion", description = "crea y guarda una evaluacion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Evaluacion creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos para crear")})
    public ResponseEntity<String> crearEvaluacion(@RequestBody EvaluacionAssembler evaluacionAssembler) {
        EvaluacionModel evaluacionModel = EvaluacionModel.fromAssembler(evaluacionAssembler);
        String mensaje = evaluacionService.crearEvaluacion(evaluacionModel);
        return ResponseEntity.ok(mensaje);
    }


    @PutMapping("/{id}")
    @Operation(summary = "Actualizar evaluacion", description = "Actualiza los datos de una evaluacion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Evaluacion actualizada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos para actualizar evaluacion")})
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
    @Operation(summary = "Eliminar evaluacion", description = "Elimina una evaluacion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Evaluacion eliminada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Evaluacion no encontrada")})
    public ResponseEntity<String> eliminarEvaluacion(@PathVariable Long id) {
        String mensaje = evaluacionService.eliminarEvaluacion(id);
        if (mensaje.equals("Evaluación eliminada.")) {
            return ResponseEntity.ok(mensaje);
        } else {
            return ResponseEntity.status(404).body(mensaje);
        }
    }
}
