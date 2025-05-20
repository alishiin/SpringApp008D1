package com.example.SpringApp008D1.Repository;

import com.example.SpringApp008D1.Model.InscripcionModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InscripcionRepository extends JpaRepository<InscripcionModel, Long> {
    List<InscripcionModel> findByUsuarioId(Long usuarioId);
    List<InscripcionModel> findByCursoId(Long cursoId);
}
