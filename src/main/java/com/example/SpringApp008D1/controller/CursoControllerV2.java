package com.example.SpringApp008D1.controller;

import com.example.SpringApp008D1.assembler.CursoAssembler;
import com.example.SpringApp008D1.model.CursoModel;
import com.example.SpringApp008D1.service.CursoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v2/cursos")
public class CursoControllerV2 {

    @Autowired
    private CursoService cursoService;

    // Listar todos los cursos
    @GetMapping
    public ResponseEntity<List<CursoAssembler>> listarCursos() {
        List<CursoModel> cursos = cursoService.listarCursos();
        List<CursoAssembler> cursosAssembler = cursos.stream()
                .map(CursoModel::toAssembler)
                .collect(Collectors.toList());
        return ResponseEntity.ok(cursosAssembler);
    }

    // Obtener curso por id
    @GetMapping("/{id}")
    public ResponseEntity<CursoAssembler> obtenerCurso(@PathVariable Long id) {
        return cursoService.obtenerCursoPorId(id)
                .map(curso -> ResponseEntity.ok(curso.toAssembler()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear curso nuevo
    @PostMapping
    public ResponseEntity<String> crearCurso(@RequestBody CursoAssembler cursoAssembler) {
        CursoModel cursoModel = CursoModel.fromAssembler(cursoAssembler);
        String mensaje = cursoService.crearCurso(cursoModel);
        return ResponseEntity.ok(mensaje);
    }

    // Actualizar curso existente
    @PutMapping("/{id}")
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
    public ResponseEntity<String> eliminarCurso(@PathVariable Long id) {
        String mensaje = cursoService.eliminarCurso(id);
        if (mensaje.equals("Curso eliminado exitosamente.")) {
            return ResponseEntity.noContent().build(); // 204 No Content, sin cuerpo
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje); // 404 con cuerpo
        }
    }
}
