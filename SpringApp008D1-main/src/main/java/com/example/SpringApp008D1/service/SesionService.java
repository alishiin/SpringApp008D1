package com.example.SpringApp008D1.service;

import com.example.SpringApp008D1.model.SesionModel;
import com.example.SpringApp008D1.repository.SesionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SesionService {

    private final SesionRepository sesionRepository;

    public SesionService(SesionRepository sesionRepository) {
        this.sesionRepository = sesionRepository;
    }

    public String crearSesion(SesionModel sesion) {
        sesionRepository.save(sesion);
        return "Sesión registrada correctamente.";
    }

    public List<SesionModel> listarSesiones() {
        return sesionRepository.findAll();
    }

    public boolean eliminarSesion(Long id) {
        Optional<SesionModel> sesion = sesionRepository.findById(id);
        if (sesion.isPresent()) {
            sesionRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
