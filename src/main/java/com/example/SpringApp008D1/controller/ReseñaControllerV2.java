package com.example.SpringApp008D1.controller;

import com.example.SpringApp008D1.assembler.ReseñaAssembler;
import com.example.SpringApp008D1.model.ReseñaModel;
import com.example.SpringApp008D1.service.ReseñaService;
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
@RequestMapping("/api/v2/reseñas")
@Tag(name = "Reseñas", description = "Reseñas de curso")
public class ReseñaControllerV2 {

    private final ReseñaService reseñaService;
    private final ReseñaAssembler assembler;

    public ReseñaControllerV2(ReseñaService reseñaService, ReseñaAssembler assembler) {
        this.reseñaService = reseñaService;
        this.assembler = assembler;
    }

    @PostMapping
    @Operation(summary = "Crear reseñas", description = "crea usuarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reseña creada obtenida exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos para crear reseña")})
    public ResponseEntity<String> crear(@RequestBody ReseñaModel reseña) {
        reseñaService.crearReseña(reseña);
        return ResponseEntity.ok("Reseña agregada correctamente.");
    }

    @GetMapping
    @Operation(summary = "Obtener todas las reseñas", description = "Obtiene lista de reseñas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de reseñas obtenida exitosamente"),
            @ApiResponse(responseCode = "400", description = "Lista no encontrada")})
    public List<EntityModel<ReseñaModel>> listar() {
        return reseñaService.listarReseñas()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Reseña", description = "Elimina una reseñas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reseña eliminada obtenida exitosamente"),
            @ApiResponse(responseCode = "400", description = "Reseña no encontrada")})
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        boolean eliminado = reseñaService.eliminarReseña(id);
        if (eliminado) {
            return ResponseEntity.ok("Reseña eliminada exitosamente.");
        } else {
            return ResponseEntity.badRequest().body("Reseña no encontrada.");
        }
    }
}
