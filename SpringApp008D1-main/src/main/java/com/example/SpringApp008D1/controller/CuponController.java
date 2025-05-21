package com.example.SpringApp008D1.controller;

import com.example.SpringApp008D1.model.CuponModel;
import com.example.SpringApp008D1.service.CuponService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cupones")
public class CuponController {

    private final CuponService cuponService;

    public CuponController(CuponService cuponService) {
        this.cuponService = cuponService;
    }

    @PostMapping
    public ResponseEntity<String> crear(@RequestBody CuponModel cupon) {
        cuponService.crearCupon(cupon);
        return ResponseEntity.ok("Cupón creado correctamente.");
    }

    @GetMapping
    public List<CuponModel> listar() {
        return cuponService.listarCupones();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        boolean eliminado = cuponService.eliminarCupon(id);
        if (eliminado) {
            return ResponseEntity.ok("Cupón eliminado correctamente.");
        } else {
            return ResponseEntity.badRequest().body("Cupón no encontrado.");
        }
    }
}
