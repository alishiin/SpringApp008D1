package com.example.SpringApp008D1.controller;

import com.example.SpringApp008D1.assembler.UsuarioAssembler;
import com.example.SpringApp008D1.model.UsuarioModel;
import com.example.SpringApp008D1.service.UsuarioService;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v2/usuarios")
public class UsuarioControllerV2 {

    private final UsuarioService usuarioService;
    private final UsuarioAssembler assembler;

    public UsuarioControllerV2(UsuarioService usuarioService, UsuarioAssembler assembler) {
        this.usuarioService = usuarioService;
        this.assembler = assembler;
    }

    @GetMapping
    public List<EntityModel<UsuarioModel>> getAllUsuarios() {
        return usuarioService.getAllUsuarios()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<UsuarioModel>> getUsuarioById(@PathVariable Long id) {
        return usuarioService.getUsuarioById(id)
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> createUsuario(@RequestBody UsuarioModel usuario) {
        usuarioService.saveUsuario(usuario);
        return ResponseEntity.ok("Usuario agregado con éxito");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUsuario(@PathVariable Long id, @RequestBody UsuarioModel usuario) {
        if (usuarioService.updateUsuario(id, usuario)) {
            return ResponseEntity.ok("Usuario actualizado con éxito");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUsuario(@PathVariable Long id) {
        if (usuarioService.deleteUsuario(id)) {
            return ResponseEntity.ok("Usuario eliminado con éxito");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
