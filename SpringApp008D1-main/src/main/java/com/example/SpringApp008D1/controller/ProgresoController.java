package com.example.SpringApp008D1.controller;

import com.example.SpringApp008D1.model.ProgresoModel;
import com.example.SpringApp008D1.service.ProgresoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/progreso")
public class ProgresoController {

    private final ProgresoService progresoService;

    public ProgresoController(ProgresoService progresoService) {
        this.progresoService = progresoService;
    }

    @PostMapping
    public ResponseEntity<String> crear(@RequestBody ProgresoModel progreso) {
        progresoService.crearProgreso(progreso);
        return ResponseEntity.ok("📈 Progreso registrado correctamente.");
    }

    @GetMapping
    public List<ProgresoModel> listar() {
        return progresoService.listarProgresos();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        boolean eliminado = progresoService.eliminarProgreso(id);
        if (eliminado) {
            return ResponseEntity.ok("🗑️ Progreso eliminado exitosamente.");
        } else {
            return ResponseEntity.badRequest().body("❌ Progreso no encontrado.");
        }
    }
}
