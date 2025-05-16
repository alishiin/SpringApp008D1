package com.example.SpringApp008D1.Model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "inscripciones")
public class InscripcionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long usuarioId;
    private Long cursoId;
    private LocalDate fechaInscripcion;
    private boolean activa;

    public InscripcionModel() {}

    public InscripcionModel(Long usuarioId, Long cursoId, LocalDate fechaInscripcion, boolean activa) {
        this.usuarioId = usuarioId;
        this.cursoId = cursoId;
        this.fechaInscripcion = fechaInscripcion;
        this.activa = activa;
    }



    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public Long getCursoId() { return cursoId; }
    public void setCursoId(Long cursoId) { this.cursoId = cursoId; }

    public LocalDate getFechaInscripcion() { return fechaInscripcion; }
    public void setFechaInscripcion(LocalDate fechaInscripcion) { this.fechaInscripcion = fechaInscripcion; }

    public boolean isActiva() { return activa; }
    public void setActiva(boolean activa) { this.activa = activa; }
}
