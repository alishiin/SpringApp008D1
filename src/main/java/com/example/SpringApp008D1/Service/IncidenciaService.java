package com.example.SpringApp008D1.service;

import com.example.SpringApp008D1.model.IncidenciaModel;
import com.example.SpringApp008D1.repository.IncidenciaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IncidenciaService {

    private final IncidenciaRepository incidenciaRepository;

    public IncidenciaService(IncidenciaRepository incidenciaRepository){
        this.incidenciaRepository = incidenciaRepository;
    }

    public List<IncidenciaModel> getAllIncidencias() {
        return incidenciaRepository.findAll();
    }

    public Optional<IncidenciaModel> getIncidenciaById(Long id) {
        return incidenciaRepository.findById(id);
    }

    public IncidenciaModel saveIncidencia(IncidenciaModel incidencia) {
        return incidenciaRepository.save(incidencia);
    }

    public boolean updateIncidencia(Long id, IncidenciaModel incidenciaData) {
        Optional<IncidenciaModel> incidenciaOpt = incidenciaRepository.findById(id);
        if (incidenciaOpt.isPresent()) {
            IncidenciaModel incidencia = incidenciaOpt.get();
            incidencia.setDescripcion(incidenciaData.getDescripcion());
            incidencia.setEstado(incidenciaData.getEstado());
            incidencia.setFechaReporte(incidenciaData.getFechaReporte());
            incidenciaRepository.save(incidencia);
            return true;
        }
        return false;
    }

    public boolean deleteIncidencia(Long id) {
        if (incidenciaRepository.existsById(id)) {
            incidenciaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
