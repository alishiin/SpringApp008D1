package com.example.SpringApp008D1.service;

import com.example.SpringApp008D1.model.CategoriaCursoModel;
import com.example.SpringApp008D1.repository.CategoriaCursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaCursoService {

    private final CategoriaCursoRepository repository;

    public CategoriaCursoService(CategoriaCursoRepository repository) {
        this.repository = repository;
    }

    public void crearCategoria(CategoriaCursoModel categoria) {
        repository.save(categoria);
    }

    public List<CategoriaCursoModel> listarCategorias() {
        return repository.findAll();
    }

    public boolean eliminarCategoria(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
