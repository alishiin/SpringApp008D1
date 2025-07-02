package com.example.SpringApp008D1.model;

import jakarta.persistence.*;
import com.example.SpringApp008D1.assembler.CursoAssembler;

@Entity
@Table(name = "cursos")
public class CursoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descripcion;
    private String estado;



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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


    public CursoAssembler toAssembler() {
        CursoAssembler assembler = new CursoAssembler();
        assembler.setId(this.id);
        assembler.setTitulo(this.titulo);
        assembler.setDescripcion(this.descripcion);
        assembler.setEstado(this.estado);
        return assembler;
    }

    public static CursoModel fromAssembler(CursoAssembler assembler) {
        CursoModel model = new CursoModel();
        model.setId(assembler.getId());
        model.setTitulo(assembler.getTitulo());
        model.setDescripcion(assembler.getDescripcion());
        model.setEstado(assembler.getEstado());
        return model;
    }
}
