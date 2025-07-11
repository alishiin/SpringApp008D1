package com.example.SpringApp008D1.controller;

import com.example.SpringApp008D1.assembler.CuponAssembler;
import com.example.SpringApp008D1.model.CuponModel;
import com.example.SpringApp008D1.service.CuponService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v2/cupones")
@Tag(name = "cupon", description = "cupones para curso")
public class CuponControllerV2 {

    @Autowired
    private CuponService cuponService;

    @GetMapping
    @Operation(summary = "Listar Cupones", description = "Obtiene una lista de cupones")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente"),
            @ApiResponse(responseCode = "400", description = "Lista no encontrada")})
    public ResponseEntity<List<CuponAssembler>> listarCupones() {
        List<CuponModel> cupones = cuponService.listarCupones();
        List<CuponAssembler> cuponesAssembler = cupones.stream()
                .map(CuponModel::toAssembler)
                .collect(Collectors.toList());
        return ResponseEntity.ok(cuponesAssembler);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener cupon por id", description = "Obtiene un cupon especifico por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "cupon obtenido exitosamente"),
            @ApiResponse(responseCode = "400", description = "cupon no encontrado")})
    public ResponseEntity<CuponAssembler> obtenerCupon(@PathVariable Long id) {
        Optional<CuponModel> cuponOpt = cuponService.obtenerCuponPorId(id);
        return cuponOpt
                .map(cupon -> ResponseEntity.ok(cupon.toAssembler()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear cupon", description = "Crea cupones")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cupon creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos para crear cupon")})
    public ResponseEntity<CuponAssembler> crearCupon(@RequestBody CuponAssembler cuponAssembler) {
        CuponModel cuponModel = CuponModel.fromAssembler(cuponAssembler);
        CuponModel creado = cuponService.crearCupon(cuponModel);
        return ResponseEntity.ok(creado.toAssembler());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar cupon", description = "Actualiza un cupon en especifico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cupon actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos para actualizar cupon")})
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
    @Operation(summary = "Eliminar cupon", description = "Elimina un cupon en especifico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cupon eliminado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Cupon no encontrado para eliminar")})
    public ResponseEntity<Void> eliminarCupon(@PathVariable Long id) {
        boolean eliminado = cuponService.eliminarCupon(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
