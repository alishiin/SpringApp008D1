package com.example.SpringApp008D1.service;

import com.example.SpringApp008D1.model.RespuestaEvaluacionModel;
import com.example.SpringApp008D1.repository.RespuestaEvaluacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RespuestaEvaluacionService {

    private final RespuestaEvaluacionRepository repository;

    public RespuestaEvaluacionService(RespuestaEvaluacionRepository repository) {
        this.repository = repository;
    }

    public List<RespuestaEvaluacionModel> getAllRespuestas() {
        return repository.findAll();
    }

    public Optional<RespuestaEvaluacionModel> getRespuestaById(Long id) {
        return repository.findById(id);
    }

    public RespuestaEvaluacionModel saveRespuesta(RespuestaEvaluacionModel respuesta) {
        return repository.save(respuesta);
    }

    public boolean updateRespuesta(Long id, RespuestaEvaluacionModel respuestaActualizada) {
        Optional<RespuestaEvaluacionModel> existente = repository.findById(id);
        if (existente.isPresent()) {
            RespuestaEvaluacionModel respuesta = existente.get();
            respuesta.setRespuesta(respuestaActualizada.getRespuesta());
            respuesta.setPreguntaId(respuestaActualizada.getPreguntaId());
            repository.save(respuesta);
            return true;
        }
        return false;
    }

    public boolean deleteRespuesta(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}