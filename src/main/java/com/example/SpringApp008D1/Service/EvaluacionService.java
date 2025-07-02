package com.example.SpringApp008D1.service;

import com.example.SpringApp008D1.model.EvaluacionModel;
import com.example.SpringApp008D1.repository.EvaluacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EvaluacionService {

    private final EvaluacionRepository evaluacionRepository;

    public EvaluacionService(EvaluacionRepository evaluacionRepository) {
        this.evaluacionRepository = evaluacionRepository;
    }

    public String crearEvaluacion(EvaluacionModel evaluacion) {
        evaluacionRepository.save(evaluacion);
        return "Evaluación creada exitosamente.";
    }

    public List<EvaluacionModel> listarEvaluaciones() {
        return evaluacionRepository.findAll();
    }

    public Optional<EvaluacionModel> obtenerEvaluacionPorId(Long id) {
        return evaluacionRepository.findById(id);
    }

    public String actualizarEvaluacion(EvaluacionModel evaluacion) {
        if (evaluacionRepository.existsById(evaluacion.getId())) {
            evaluacionRepository.save(evaluacion);
            return "Evaluación actualizada.";
        }
        return "Evaluación no encontrada.";
    }

    public String eliminarEvaluacion(Long id) {
        if (evaluacionRepository.existsById(id)) {
            evaluacionRepository.deleteById(id);
            return "Evaluación eliminada.";
        }
        return "Evaluación no encontrada.";
    }
}
