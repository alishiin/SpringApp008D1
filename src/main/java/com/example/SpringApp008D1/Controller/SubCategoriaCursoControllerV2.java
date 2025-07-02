package com.example.SpringApp008D1.controller;

import com.example.SpringApp008D1.assembler.SubCategoriaCursoAssembler;
import com.example.SpringApp008D1.model.SubCategoriaCursoModel;
import com.example.SpringApp008D1.service.SubCategoriaCursoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v2/subcategorias")
@Tag(name = "Subcategorías", description = "Subcategorías")
public class SubCategoriaCursoControllerV2 {

    private final SubCategoriaCursoService subCategoriaCursoService;
    private final SubCategoriaCursoAssembler assembler;

    public SubCategoriaCursoControllerV2(SubCategoriaCursoService subCategoriaCursoService, SubCategoriaCursoAssembler assembler) {
        this.subCategoriaCursoService = subCategoriaCursoService;
        this.assembler = assembler;
    }

    @PostMapping
    @Operation(summary = "Crear subcategorias", description = "Creacion de subcategorias")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subcategoria creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos para crear Subcategoria")})
    public ResponseEntity<String> crear(@RequestBody SubCategoriaCursoModel subcategoria) {
        subCategoriaCursoService.crearSubCategoria(subcategoria);
        return ResponseEntity.ok("Subcategoría de curso creada correctamente.");
    }

    @GetMapping
    @Operation(summary = "obtener todos las subcategorias", description = "Obtiene una lista de todos las subcategorias")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de subcategoria obtenida exitosamente"),
            @ApiResponse(responseCode = "400", description = "lista no encontrada")})
    public List<EntityModel<SubCategoriaCursoModel>> listar() {
        return subCategoriaCursoService.listarSubCategorias()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener Subcategorias por id", description = "obtiene subcategorias por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subcategoria obtenida exitosamente"),
            @ApiResponse(responseCode = "400", description = "Subcategoria no encontrada")})
    public ResponseEntity<EntityModel<SubCategoriaCursoModel>> getSubCategoriaById(@PathVariable Long id) {
        Optional<SubCategoriaCursoModel> subcategoriaOpt = subCategoriaCursoService.listarSubCategorias()
                .stream()
                .filter(sc -> sc.getId().equals(id))
                .findFirst();

        return subcategoriaOpt
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Subcategoria", description = "Elimina una subcategoria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subcategoria eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Subcategoria no encontrada")})
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        boolean eliminado = subCategoriaCursoService.eliminarSubCategoria(id);
        if (eliminado) {
            return ResponseEntity.ok("Subcategoría eliminada exitosamente.");
        } else {
            return ResponseEntity.badRequest().body("Subcategoría no encontrada.");
        }
    }
}

//agregando un cambio
