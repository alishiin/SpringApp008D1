package com.example.SpringApp008D1.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "inscripciones")
public class InscripcionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Por simplicidad, solo IDs de curso y estudiante (puedes cambiar a relaciones si quieres)
    @Column(name = "curso_id", nullable = false)
    private Long cursoId;

    @Column(name = "estudiante_id", nullable = false)
    private Long estudianteId;

    @Column(name = "fecha_inscripcion")
    private LocalDate fechaInscripcion;

    // Constructor vacío
    public InscripcionModel() {}

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCursoId() {
        return cursoId;
    }

    public void setCursoId(Long cursoId) {
        this.cursoId = cursoId;
    }

    public Long getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(Long estudianteId) {
        this.estudianteId = estudianteId;
    }

    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(LocalDate fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }
}
