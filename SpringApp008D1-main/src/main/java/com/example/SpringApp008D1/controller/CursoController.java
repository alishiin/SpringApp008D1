package com.example.SpringApp008D1.controller;

import com.example.SpringApp008D1.model.CursoModel;
import com.example.SpringApp008D1.service.CursoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @PostMapping
    public ResponseEntity<String> crear(@RequestBody CursoModel curso) {
        String mensaje = cursoService.crearCurso(curso);
        return ResponseEntity.ok(mensaje);
    }

    @GetMapping
    public ResponseEntity<List<CursoModel>> listar() {
        List<CursoModel> cursos = cursoService.listarCursos();
        return ResponseEntity.ok(cursos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        String mensaje = cursoService.eliminarCurso(id);
        if (mensaje.contains("exitosamente")) {
            return ResponseEntity.ok(mensaje);
        } else {
            return ResponseEntity.badRequest().body(mensaje);
        }
    }
}
