package com.example.SpringApp008D1.service;

import com.example.SpringApp008D1.model.EvaluacionModel;
import com.example.SpringApp008D1.repository.EvaluacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluacionService {

    private final EvaluacionRepository evaluacionRepository;

    public EvaluacionService(EvaluacionRepository evaluacionRepository) {
        this.evaluacionRepository = evaluacionRepository;
    }

    public EvaluacionModel crearEvaluacion(EvaluacionModel evaluacion) {
        return evaluacionRepository.save(evaluacion);
    }

    public List<EvaluacionModel> listarEvaluaciones() {
        return evaluacionRepository.findAll();
    }

    public boolean eliminarEvaluacion(Long id) {
        if (evaluacionRepository.existsById(id)) {
            evaluacionRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
