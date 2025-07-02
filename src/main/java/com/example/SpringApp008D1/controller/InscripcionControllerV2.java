package com.example.SpringApp008D1.controller;

import com.example.SpringApp008D1.model.InscripcionModel;
import com.example.SpringApp008D1.service.InscripcionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Listar inscripciones", description = "obtiene una lista de inscripciones")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente"),
            @ApiResponse(responseCode = "400", description = "Lista no encontrada")})
    public List<InscripcionModel> getAllInscripciones() {
        return inscripcionService.getAllInscripciones();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener inscripciones por id", description = "obtiene inscripciones por id especifico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inscripcion obtenida exitosamente"),
            @ApiResponse(responseCode = "400", description = "Inscripcion no encontrada")})
    public ResponseEntity<InscripcionModel> getInscripcionById(@PathVariable Long id) {
        return inscripcionService.getInscripcionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear inscripciones", description = "Crea inscripciones")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inscripcion creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos para crear")})
    public ResponseEntity<String> createInscripcion(@RequestBody InscripcionModel inscripcion) {
        inscripcionService.saveInscripcion(inscripcion);
        return ResponseEntity.ok("Inscripción agregada con éxito");
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar inscripciones", description = "Actualiza inscripciones")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inscripcion actualizada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos para actualizar")})
    public ResponseEntity<String> updateInscripcion(@PathVariable Long id, @RequestBody InscripcionModel inscripcion) {
        if (inscripcionService.updateInscripcion(id, inscripcion)) {
            return ResponseEntity.ok("Inscripción actualizada con éxito");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar inscripciones", description = "Elimina inscripciones")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inscripcion eliminada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Inscripcion no encontrada")})
    public ResponseEntity<String> deleteInscripcion(@PathVariable Long id) {
        if (inscripcionService.deleteInscripcion(id)) {
            return ResponseEntity.ok("Inscripción eliminada con éxito");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
