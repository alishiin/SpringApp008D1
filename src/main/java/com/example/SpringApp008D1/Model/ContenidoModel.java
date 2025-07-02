package com.example.SpringApp008D1.model;

import jakarta.persistence.*;

@Entity
public class ContenidoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descripcion;
    private String urlRecurso;

    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getUrlRecurso() { return urlRecurso; }
    public void setUrlRecurso(String urlRecurso) { this.urlRecurso = urlRecurso; }
}
