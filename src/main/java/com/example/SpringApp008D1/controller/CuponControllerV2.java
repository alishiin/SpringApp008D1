package com.example.SpringApp008D1.controller;

import com.example.SpringApp008D1.assembler.CuponAssembler;
import com.example.SpringApp008D1.model.CuponModel;
import com.example.SpringApp008D1.service.CuponService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v2/cupones")
public class CuponControllerV2 {

    @Autowired
    private CuponService cuponService;

    @GetMapping
    public ResponseEntity<List<CuponAssembler>> listarCupones() {
        List<CuponModel> cupones = cuponService.listarCupones();
        List<CuponAssembler> cuponesAssembler = cupones.stream()
                .map(CuponModel::toAssembler)
                .collect(Collectors.toList());
        return ResponseEntity.ok(cuponesAssembler);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuponAssembler> obtenerCupon(@PathVariable Long id) {
        Optional<CuponModel> cuponOpt = cuponService.obtenerCuponPorId(id);
        return cuponOpt
                .map(cupon -> ResponseEntity.ok(cupon.toAssembler()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CuponAssembler> crearCupon(@RequestBody CuponAssembler cuponAssembler) {
        CuponModel cuponModel = CuponModel.fromAssembler(cuponAssembler);
        CuponModel creado = cuponService.crearCupon(cuponModel);
        return ResponseEntity.ok(creado.toAssembler());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CuponAssembler> actualizarCupon(@PathVariable Long id, @RequestBody CuponAssembler cuponAssembler) {
        Optional<CuponModel> existenteOpt = cuponService.obtenerCuponPorId(id);
        if (existenteOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        CuponModel existente = existenteOpt.get();
        existente.setCodigo(cuponAssembler.getCodigo());
        existente.setDescuento(cuponAssembler.getDescuento());

        CuponModel actualizado = cuponService.crearCupon(existente);
        return ResponseEntity.ok(actualizado.toAssembler());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCupon(@PathVariable Long id) {
        boolean eliminado = cuponService.eliminarCupon(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
