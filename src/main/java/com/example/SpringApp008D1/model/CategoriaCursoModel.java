package com.example.SpringApp008D1.model;

import com.example.SpringApp008D1.assembler.CategoriaCursoAssembler;

public class CategoriaCursoModel {

    private Long id;
    private String nombre;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public static CategoriaCursoModel fromAssembler(CategoriaCursoAssembler assembler) {
        CategoriaCursoModel model = new CategoriaCursoModel();
        model.setId(assembler.getId());
        model.setNombre(assembler.getNombre());
        return model;
    }

    public CategoriaCursoAssembler toAssembler() {
        CategoriaCursoAssembler assembler = new CategoriaCursoAssembler();
        assembler.setId(this.getId());
        assembler.setNombre(this.getNombre());
        return assembler;
    }
}

