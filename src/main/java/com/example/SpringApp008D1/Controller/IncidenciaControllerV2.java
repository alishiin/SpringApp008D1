package com.example.SpringApp008D1.controller;

import com.example.SpringApp008D1.model.IncidenciaModel;
import com.example.SpringApp008D1.service.IncidenciaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/incidencias")
@Tag(name = "Incidencias", description = "incidencia curso")
public class IncidenciaControllerV2 {

    private final IncidenciaService incidenciaService;

    public IncidenciaControllerV2(IncidenciaService incidenciaService) {
        this.incidenciaService = incidenciaService;
    }

    @GetMapping
    @Operation(summary = "Listar incidencias", description = "obtiene una lista de incidencias")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente"),
            @ApiResponse(responseCode = "400", description = "Lista no encontrada")})
    public List<IncidenciaModel> getAllIncidencias() {
        return incidenciaService.getAllIncidencias();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener incidencia por id", description = "obtiene una incidenica por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "incidenica obtenida exitosamente"),
            @ApiResponse(responseCode = "400", description = "incidencia no encontrada")})
    public ResponseEntity<IncidenciaModel> getIncidenciaById(@PathVariable Long id) {
        return incidenciaService.getIncidenciaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear incidencia", description = "Crea una incidencia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Incidenica creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos para crear")})
    public ResponseEntity<String> createIncidencia(@RequestBody IncidenciaModel incidencia) {
        incidenciaService.saveIncidencia(incidencia);
        return ResponseEntity.ok("Incidencia agregada con éxito");
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar incidencia", description = "Actualiza una incidencia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Incidenica actualizada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos para actualizar")})
    public ResponseEntity<String> updateIncidencia(@PathVariable Long id, @RequestBody IncidenciaModel incidencia) {
        if (incidenciaService.updateIncidencia(id, incidencia)) {
            return ResponseEntity.ok("Incidencia actualizada con éxito");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar incidencia", description = "Elimina una incidencia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Incidenica Eliminada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Incidencia no encontrada")})
    public ResponseEntity<String> deleteIncidencia(@PathVariable Long id) {
        if (incidenciaService.deleteIncidencia(id)) {
            return ResponseEntity.ok("Incidencia eliminada con éxito");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
