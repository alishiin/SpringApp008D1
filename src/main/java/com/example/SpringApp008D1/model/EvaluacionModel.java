package com.example.SpringApp008D1.model;

import com.example.SpringApp008D1.assembler.EvaluacionAssembler;
import jakarta.persistence.*;

@Entity
@Table(name = "evaluaciones")
public class EvaluacionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descripcion;

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public EvaluacionAssembler toAssembler() {
        EvaluacionAssembler assembler = new EvaluacionAssembler();
        assembler.setId(this.id);
        assembler.setTitulo(this.titulo);
        assembler.setDescripcion(this.descripcion);
        return assembler;
    }

    public static EvaluacionModel fromAssembler(EvaluacionAssembler assembler) {
        EvaluacionModel model = new EvaluacionModel();
        model.setId(assembler.getId());
        model.setTitulo(assembler.getTitulo());
        model.setDescripcion(assembler.getDescripcion());
        return model;
    }
}
