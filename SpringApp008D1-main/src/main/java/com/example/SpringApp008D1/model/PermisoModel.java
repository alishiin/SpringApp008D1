package com.example.SpringApp008D1.model;

import jakarta.persistence.*;

@Entity
@Table(name = "permiso")
public class PermisoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre; // Ej: "GESTION_USUARIOS", "VER_REPORTES"

    private String descripcion;

    public PermisoModel() {
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
