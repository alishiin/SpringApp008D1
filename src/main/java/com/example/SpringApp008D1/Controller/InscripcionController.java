package com.example.SpringApp008D1.Controller;

import com.example.SpringApp008D1.Model.InscripcionModel;
import com.example.SpringApp008D1.Service.InscripcionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inscripciones")
public class InscripcionController {

    private final InscripcionService service;

    public InscripcionController(InscripcionService service) {
        this.service = service;
    }

    @GetMapping
    public List<InscripcionModel> listar() {
        return service.listar();
    }

    @PostMapping
    public InscripcionModel inscribir(@RequestParam Long usuarioId, @RequestParam Long cursoId) {
        return service.inscribir(usuarioId, cursoId);
    }

    @GetMapping("/{id}")
    public Optional<InscripcionModel> obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    @PostMapping("/{id}/cancelar")
    public void cancelar(@PathVariable Long id) {
        service.cancelar(id);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<InscripcionModel> listarPorUsuario(@PathVariable Long usuarioId) {
        return service.listarPorUsuario(usuarioId);
    }

    @GetMapping("/curso/{cursoId}")
    public List<InscripcionModel> listarPorCurso(@PathVariable Long cursoId) {
        return service.listarPorCurso(cursoId);
    }
}
