package com.example.SpringApp008D1.service;

import com.example.SpringApp008D1.model.SoporteModel;
import com.example.SpringApp008D1.repository.SoporteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoporteService {

    private final SoporteRepository soporteRepository;

    public SoporteService(SoporteRepository soporteRepository) {
        this.soporteRepository = soporteRepository;
    }

    public SoporteModel crearSoporte(SoporteModel soporte) {
        return soporteRepository.save(soporte);
    }

    public List<SoporteModel> listarSoportes() {
        return soporteRepository.findAll();
    }

    public boolean eliminarSoporte(Long id) {
        if (soporteRepository.existsById(id)) {
            soporteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
