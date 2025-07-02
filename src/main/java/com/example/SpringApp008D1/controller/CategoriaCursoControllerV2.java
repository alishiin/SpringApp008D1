package com.example.SpringApp008D1.controller;

import com.example.SpringApp008D1.assembler.CategoriaCursoAssembler;
import com.example.SpringApp008D1.model.CategoriaCursoModel;
import com.example.SpringApp008D1.service.CategoriaCursoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v2/categorias")
@Tag(name = "Categorías", description = "categorías de cursos")
public class CategoriaCursoControllerV2 {

    private final CategoriaCursoService categoriaCursoService;

    public CategoriaCursoControllerV2(CategoriaCursoService categoriaCursoService) {
        this.categoriaCursoService = categoriaCursoService;
    }

    @PostMapping
    @Operation(summary = "Crear categoria de cursos", description = "crea una categoria de curso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoria de curso creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos para crear")})
    public ResponseEntity<String> crear(@RequestBody CategoriaCursoAssembler dto) {
        CategoriaCursoModel model = CategoriaCursoModel.fromAssembler(dto);
        categoriaCursoService.crearCategoria(model);
        return ResponseEntity.ok("Categoría creada correctamente. [v2]");
    }

    @GetMapping
    @Operation(summary = "Listar categoria de cursos", description = "Obtiene una lista de las categorias de cada curso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "lista obtenida exitosamente"),
            @ApiResponse(responseCode = "400", description = "Lista no encontrada")})
    public ResponseEntity<List<CategoriaCursoAssembler>> listar() {
        List<CategoriaCursoAssembler> dtos = categoriaCursoService.listarCategorias()
                .stream()
                .map(CategoriaCursoModel::toAssembler) // Conversión de Model a Assembler
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elinminar categoria de cursos", description = "elimina categorias de cada curso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoria elmininada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Categoria no encontrada")})
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        boolean eliminado = categoriaCursoService.eliminarCategoria(id);
        if (eliminado) {
            return ResponseEntity.ok("Categoría eliminada exitosamente. [v2]");
        } else {
            return ResponseEntity.status(404).body("Categoría no encontrada. [v2]");
        }
    }
}



//asdas

