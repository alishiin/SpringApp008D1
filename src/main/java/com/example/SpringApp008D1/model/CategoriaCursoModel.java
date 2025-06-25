package com.example.SpringApp008D1.model;

import com.example.SpringApp008D1.assembler.CategoriaCursoAssembler;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CategoriaCursoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    // Constructor vacío requerido por JPA
    public CategoriaCursoModel() {
    }


    public CategoriaCursoModel(String nombre) {
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
        assembler.setId(this.id);
        assembler.setNombre(this.nombre);
        return assembler;
    }

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
}
