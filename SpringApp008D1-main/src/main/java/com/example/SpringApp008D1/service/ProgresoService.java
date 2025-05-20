package com.example.SpringApp008D1.service;

import com.example.SpringApp008D1.model.ProgresoModel;
import com.example.SpringApp008D1.repository.ProgresoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgresoService {

    private final ProgresoRepository progresoRepository;

    public ProgresoService(ProgresoRepository progresoRepository) {
        this.progresoRepository = progresoRepository;
    }

    public ProgresoModel crearProgreso(ProgresoModel progreso) {
        return progresoRepository.save(progreso);
    }

    public List<ProgresoModel> listarProgresos() {
        return progresoRepository.findAll();
    }

    public boolean eliminarProgreso(Long id) {
        if (progresoRepository.existsById(id)) {
            progresoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
