package com.example.SpringApp008D1.Controller;

import org.springframework.web.bind.annotation.*;



public class SuscripcionController {
    private final SuscripcionService service;

    public SuscripcionController(SuscripcionService service) {
        this.service = service;
    }

    @GetMapping
    public List<Suscripcion> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Suscripcion obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    @PostMapping
    public Suscripcion crear(@RequestBody Suscripcion s) {
        return service.crear(s);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);

    }
}
