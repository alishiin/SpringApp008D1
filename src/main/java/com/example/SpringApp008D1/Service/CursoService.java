package com.example.SpringApp008D1.service;

import com.example.SpringApp008D1.model.CursoModel;
import com.example.SpringApp008D1.repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public String crearCurso(CursoModel curso) {
        cursoRepository.save(curso);
        return "Curso agregado correctamente.";
    }

    public List<CursoModel> listarCursos() {
        return cursoRepository.findAll();
    }

    public Optional<CursoModel> obtenerCursoPorId(Long id) {
        return cursoRepository.findById(id);
    }

    public String eliminarCurso(Long id) {
        Optional<CursoModel> curso = cursoRepository.findById(id);
        if (curso.isPresent()) {
            cursoRepository.deleteById(id);
            return "Curso eliminado exitosamente.";
        } else {
            return "Curso no encontrado.";
        }
    }
}
