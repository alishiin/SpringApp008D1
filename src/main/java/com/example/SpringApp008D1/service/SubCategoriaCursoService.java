package com.example.SpringApp008D1.service;

import com.example.SpringApp008D1.model.SubCategoriaCursoModel;
import com.example.SpringApp008D1.repository.SubCategoriaCursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubCategoriaCursoService {

    private final SubCategoriaCursoRepository subCategoriaCursoRepository;

    public SubCategoriaCursoService(SubCategoriaCursoRepository subCategoriaCursoRepository) {
        this.subCategoriaCursoRepository = subCategoriaCursoRepository;
    }

    public String crearSubCategoria(SubCategoriaCursoModel subcategoria) {
        subCategoriaCursoRepository.save(subcategoria);
        return "Subcategoría creada correctamente.";
    }

    public List<SubCategoriaCursoModel> listarSubCategorias() {
        return subCategoriaCursoRepository.findAll();
    }

    public boolean eliminarSubCategoria(Long id) {
        Optional<SubCategoriaCursoModel> subcategoria = subCategoriaCursoRepository.findById(id);
        if (subcategoria.isPresent()) {
            subCategoriaCursoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}