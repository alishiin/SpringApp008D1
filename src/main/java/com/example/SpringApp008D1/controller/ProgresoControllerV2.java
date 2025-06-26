package com.example.SpringApp008D1.controller;

import com.example.SpringApp008D1.assembler.ProgresoAssembler;
import com.example.SpringApp008D1.model.ProgresoModel;
import com.example.SpringApp008D1.service.ProgresoService;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/progreso")
public class ProgresoControllerV2 {

    private final ProgresoService progresoService;
    private final ProgresoAssembler assembler;

    public ProgresoControllerV2(ProgresoService progresoService, ProgresoAssembler assembler) {
        this.progresoService = progresoService;
        this.assembler = assembler;
    }

    @PostMapping
    public ResponseEntity<String> crear(@RequestBody ProgresoModel progreso) {
        progresoService.crearProgreso(progreso);
        return ResponseEntity.ok("Progreso registrado correctamente.");
    }

    @GetMapping
    public List<EntityModel<ProgresoModel>> listar() {
        List<ProgresoModel> lista = progresoService.listarProgresos();
        return lista.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        boolean eliminado = progresoService.eliminarProgreso(id);
        if (eliminado) {
            return ResponseEntity.ok("🗑Progreso eliminado exitosamente.");
        } else {
            return ResponseEntity.badRequest().body("Progreso no encontrado.");
        }
    }
}
