package com.example.SpringApp008D1.controller;

import com.example.SpringApp008D1.assembler.SesionAssembler;
import com.example.SpringApp008D1.model.SesionModel;
import com.example.SpringApp008D1.service.SesionService;
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
@RequestMapping("/api/v2/sesiones")
@Tag(name = "Sesiones", description = "Sesiones de Cursos")
public class SesionControllerV2 {

    private final SesionService sesionService;
    private final SesionAssembler assembler;

    public SesionControllerV2(SesionService sesionService, SesionAssembler assembler) {
        this.sesionService = sesionService;
        this.assembler = assembler;
    }

    @PostMapping
    @Operation(summary = "Crear sesion", description = "Crea sesion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sesion creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos")})
    public ResponseEntity<String> crear(@RequestBody SesionModel sesion) {
        sesionService.crearSesion(sesion);
        return ResponseEntity.ok("Sesión registrada correctamente.");
    }

    @GetMapping
    @Operation(summary = "Listar sesiones", description = "Obtiene una lista de las sesiones")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente"),
            @ApiResponse(responseCode = "400", description = "Lista no encontrada")})
    public List<EntityModel<SesionModel>> listar() {
        List<SesionModel> sesiones = sesionService.listarSesiones();
        return sesiones.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener sesion por id", description = "Obtiene sesion por id especifico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sesion obtenida exitosamente"),
            @ApiResponse(responseCode = "400", description = "Sesion no encontrada")})
    public ResponseEntity<EntityModel<SesionModel>> getSesionById(@PathVariable Long id) {
        return sesionService.listarSesiones().stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar sesion", description = "Elimina sesiones")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sesion eliminada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Sesion no encontrada")})
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        boolean eliminado = sesionService.eliminarSesion(id);
        if (eliminado) {
            return ResponseEntity.ok("Sesión eliminada correctamente.");
        } else {
            return ResponseEntity.badRequest().body("Sesión no encontrada.");
        }
    }
}
