package com.example.SpringApp008D1.controller;

import com.example.SpringApp008D1.assembler.UsuarioAssembler;
import com.example.SpringApp008D1.model.UsuarioModel;
import com.example.SpringApp008D1.service.UsuarioService;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v2/usuarios")
@Tag(name = "Usuarios", description = "Usuarios")

public class UsuarioControllerV2 {


    private final UsuarioService usuarioService;

    private final UsuarioAssembler assembler;


    public UsuarioControllerV2(UsuarioService usuarioService, UsuarioAssembler assembler) {
        this.usuarioService = usuarioService;
        this.assembler = assembler;
    }

    @GetMapping
    @Operation(summary = "obtener todos los usuarios", description = "Obtiene una lista de todos los usuarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida exitosamente"),
            @ApiResponse(responseCode = "400", description = "lista no encontrada")})

    public List<EntityModel<UsuarioModel>> getAllUsuarios() {
        return usuarioService.getAllUsuarios()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "obtener usuarios por id", description = "Obtiene un usuario especifico por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario obtenido exitosamente"),
            @ApiResponse(responseCode = "400", description = "Usuario no encontrado")})

    public ResponseEntity<EntityModel<UsuarioModel>> getUsuarioById(@PathVariable Long id) {
        return usuarioService.getUsuarioById(id)
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo usuario", description = "Crea y guarda un nuevo usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos para crear usuario")})
    public ResponseEntity<String> createUsuario(@RequestBody UsuarioModel usuario) {
        usuarioService.saveUsuario(usuario);
        return ResponseEntity.ok("Usuario agregado con éxito");
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar usuario", description = "Actualiza los datos de un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Datos invalidos para actualizar usuario")})
    public ResponseEntity<String> updateUsuario(@PathVariable Long id, @RequestBody UsuarioModel usuario) {
        if (usuarioService.updateUsuario(id, usuario)) {
            return ResponseEntity.ok("Usuario actualizado con éxito");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar usuario", description = "Elimina un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")})
    public ResponseEntity<String> deleteUsuario(@PathVariable Long id) {
        if (usuarioService.deleteUsuario(id)) {
            return ResponseEntity.ok("Usuario eliminado con éxito");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
