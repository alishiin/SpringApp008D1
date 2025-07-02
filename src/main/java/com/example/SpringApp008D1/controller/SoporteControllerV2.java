package com.example.SpringApp008D1.controller;

import com.example.SpringApp008D1.assembler.SoporteAssembler;
import com.example.SpringApp008D1.model.SoporteModel;
import com.example.SpringApp008D1.service.SoporteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v2/soporte")
@Tag(name = "Soporte", description = "Soporte")
public class SoporteControllerV2 {

    private final SoporteService soporteService;
    private final SoporteAssembler assembler;

    public SoporteControllerV2(SoporteService soporteService, SoporteAssembler assembler) {
        this.soporteService = soporteService;
        this.assembler = assembler;
    }

    @PostMapping
    public ResponseEntity<String> crear(@RequestBody SoporteModel soporte) {
        soporteService.crearSoporte(soporte);
        return ResponseEntity.ok("Soporte registrado exitosamente.");
    }

    @GetMapping
    public List<EntityModel<SoporteModel>> listar() {
        List<SoporteModel> lista = soporteService.listarSoportes();
        return lista.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<SoporteModel>> getSoporteById(@PathVariable Long id) {
        Optional<SoporteModel> soporteOpt = soporteService.listarSoportes()
                .stream()
                .filter(s -> s.getId().equals(id))
                .findFirst();

        return soporteOpt
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
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
