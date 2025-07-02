package com.example.SpringApp008D1.controller;

import com.example.SpringApp008D1.assembler.CursoAssembler;
import com.example.SpringApp008D1.model.CursoModel;
import com.example.SpringApp008D1.service.CursoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v2/cursos")
@Tag(name = "Cursos", description = "Cursos")
public class CursoControllerV2 {

    @Autowired
    private CursoService cursoService;

    
    @GetMapping
    @Operation(summary = "Obtener todos los cursos", description = "Obtiene una lista de cursos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de cursos obtenida exitosamente"),
            @ApiResponse(responseCode = "400", description = "Lista no encontrada")})
    public ResponseEntity<List<CursoAssembler>> listarCursos() {
        List<CursoModel> cursos = cursoService.listarCursos();
        List<CursoAssembler> cursosAssembler = cursos.stream()
                .map(CursoModel::toAssembler)
                .collect(Collectors.toList());
        return ResponseEntity.ok(cursosAssembler);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Obtener curso por id", description = "Obtiene un cursos especifico por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "curso obtenido exitosamente"),
            @ApiResponse(responseCode = "400", description = "curso no encontrado")})
    public ResponseEntity<CursoAssembler> obtenerCurso(@PathVariable Long id) {
        return cursoService.obtenerCursoPorId(id)
                .map(curso -> ResponseEntity.ok(curso.toAssembler()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear curso nuevo
    @PostMapping
    @Operation(summary = "Crear nuevo curso", description = "Crea curso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "curso creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos para crear curso")})
    public ResponseEntity<String> crearCurso(@RequestBody CursoAssembler cursoAssembler) {
        CursoModel cursoModel = CursoModel.fromAssembler(cursoAssembler);
        String mensaje = cursoService.crearCurso(cursoModel);
        return ResponseEntity.ok(mensaje);
    }

    // Actualizar curso existente
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar curso", description = "Actualiza un curso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "curso actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos para actualizar curso")})
    public ResponseEntity<String> actualizarCurso(@PathVariable Long id, @RequestBody CursoAssembler cursoAssembler) {
        return cursoService.obtenerCursoPorId(id)
                .map(cursoExistente -> {
                    cursoExistente.setTitulo(cursoAssembler.getTitulo());
                    cursoExistente.setDescripcion(cursoAssembler.getDescripcion());
                    cursoExistente.setEstado(cursoAssembler.getEstado());

                    String mensaje = cursoService.crearCurso(cursoExistente);
                    return ResponseEntity.ok(mensaje);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Eliminar curso
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar curso", description = "Elimina un curso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "curso eliminado exitosamente"),
            @ApiResponse(responseCode = "400", description = "curso no encontrado")})
    public ResponseEntity<String> eliminarCurso(@PathVariable Long id) {
        String mensaje = cursoService.eliminarCurso(id);
        if (mensaje.equals("Curso eliminado exitosamente.")) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
        }
    }
}
