package com.example.SpringApp008D1.controller;

import com.example.SpringApp008D1.assembler.ProgresoAssembler;
import com.example.SpringApp008D1.model.ProgresoModel;
import com.example.SpringApp008D1.service.ProgresoService;
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
@RequestMapping("/api/v2/progreso")
@Tag(name = "progreso", description = "Progresos de Cursos")
public class ProgresoControllerV2 {

    private final ProgresoService progresoService;
    private final ProgresoAssembler assembler;

    public ProgresoControllerV2(ProgresoService progresoService, ProgresoAssembler assembler) {
        this.progresoService = progresoService;
        this.assembler = assembler;
    }

    @PostMapping
    @Operation(summary = "Crear un progreso", description = "Crea un progreso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "progreso registrado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos para crear")})
    public ResponseEntity<String> crear(@RequestBody ProgresoModel progreso) {
        progresoService.crearProgreso(progreso);
        return ResponseEntity.ok("Progreso registrado correctamente.");
    }

    @GetMapping
    @Operation(summary = "Obtener todos los progresos", description = "Obtiene todos los progresos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de evaluacion obtenida exitosamente"),
            @ApiResponse(responseCode = "400", description = "Lista no encontrada")})
    public List<EntityModel<ProgresoModel>> listar() {
        List<ProgresoModel> lista = progresoService.listarProgresos();
        return lista.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar progreso", description = "Elimina progresos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Progreso eliminado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Progreso no encontrado")})
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        boolean eliminado = progresoService.eliminarProgreso(id);
        if (eliminado) {
            return ResponseEntity.ok("Progreso eliminado exitosamente.");
        } else {
            return ResponseEntity.badRequest().body("Progreso no encontrado.");
        }
    }
}
