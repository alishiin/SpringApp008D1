package com.example.SpringApp008D1.controller;

import com.example.SpringApp008D1.model.SoporteModel;
import com.example.SpringApp008D1.service.SoporteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/soporte")
public class SoporteController {

    private final SoporteService soporteService;

    public SoporteController(SoporteService soporteService) {
        this.soporteService = soporteService;
    }

    @PostMapping
    public ResponseEntity<String> crear(@RequestBody SoporteModel soporte) {
        soporteService.crearSoporte(soporte);
        return ResponseEntity.ok("Soporte registrado exitosamente.");
    }

    @GetMapping
    public List<SoporteModel> listar() {
        return soporteService.listarSoportes();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        boolean eliminado = soporteService.eliminarSoporte(id);
        if (eliminado) {
            return ResponseEntity.ok("Soporte eliminado correctamente.");
        } else {
            return ResponseEntity.badRequest().body("Soporte no encontrado.");
        }
    }
}
