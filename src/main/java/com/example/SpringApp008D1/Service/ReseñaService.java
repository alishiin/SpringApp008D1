package com.example.SpringApp008D1.service;

import com.example.SpringApp008D1.model.ReseñaModel;
import com.example.SpringApp008D1.repository.ReseñaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReseñaService {

    private final ReseñaRepository reseñaRepository;

    public ReseñaService(ReseñaRepository reseñaRepository) {
        this.reseñaRepository = reseñaRepository;
    }

    public void crearReseña(ReseñaModel reseña) {
        reseñaRepository.save(reseña);
    }

    public List<ReseñaModel> listarReseñas() {
        return reseñaRepository.findAll();
    }

    public boolean eliminarReseña(Long id) {
        if (reseñaRepository.existsById(id)) {
            reseñaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
