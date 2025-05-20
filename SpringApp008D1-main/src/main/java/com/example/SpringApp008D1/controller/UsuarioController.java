package com.example.SpringApp008D1.controller;

import com.example.SpringApp008D1.model.UsuarioModel;
import com.example.SpringApp008D1.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<UsuarioModel> getAllUsuarios() {
        return usuarioService.getAllUsuarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioModel> getUsuarioById(@PathVariable Long id) {
        return usuarioService.getUsuarioById(id)
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
