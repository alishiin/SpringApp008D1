package com.example.SpringApp008D1.service;

import com.example.SpringApp008D1.model.ContenidoModel;
import com.example.SpringApp008D1.repository.ContenidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContenidoService {

    private final ContenidoRepository contenidoRepository;

    public ContenidoService(ContenidoRepository contenidoRepository) {
        this.contenidoRepository = contenidoRepository;
    }

    public ContenidoModel crearContenido(ContenidoModel contenido) {
        return contenidoRepository.save(contenido);
    }

    public List<ContenidoModel> listarContenidos() {
        return contenidoRepository.findAll();
    }

    public boolean eliminarContenido(Long id) {
        if (contenidoRepository.existsById(id)) {
            contenidoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
