package com.example.SpringApp008D1.service;

import com.example.SpringApp008D1.model.InscripcionModel;
import com.example.SpringApp008D1.repository.InscripcionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InscripcionService {

    private final InscripcionRepository inscripcionRepository;

    public InscripcionService(InscripcionRepository inscripcionRepository) {
        this.inscripcionRepository = inscripcionRepository;
    }

    public List<InscripcionModel> getAllInscripciones() {
        return inscripcionRepository.findAll();
    }

    public Optional<InscripcionModel> getInscripcionById(Long id) {
        return inscripcionRepository.findById(id);
    }

    public InscripcionModel saveInscripcion(InscripcionModel inscripcion) {
        return inscripcionRepository.save(inscripcion);
    }

    public boolean updateInscripcion(Long id, InscripcionModel nuevaInscripcion) {
        Optional<InscripcionModel> inscripcionOptional = inscripcionRepository.findById(id);
        if (inscripcionOptional.isPresent()) {
            InscripcionModel inscripcionExistente = inscripcionOptional.get();
            inscripcionExistente.setCursoId(nuevaInscripcion.getCursoId());
            inscripcionExistente.setEstudianteId(nuevaInscripcion.getEstudianteId());
            inscripcionExistente.setFechaInscripcion(nuevaInscripcion.getFechaInscripcion());
            inscripcionRepository.save(inscripcionExistente);
            return true;
        }
        return false;
    }

    public boolean deleteInscripcion(Long id) {
        if (inscripcionRepository.existsById(id)) {
            inscripcionRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
