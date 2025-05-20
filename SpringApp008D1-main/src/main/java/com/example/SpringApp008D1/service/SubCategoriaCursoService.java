package com.example.SpringApp008D1.service;

import com.example.SpringApp008D1.model.SubCategoriaCursoModel;
import com.example.SpringApp008D1.repository.SubCategoriaCursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubCategoriaCursoService {

    private final SubCategoriaCursoRepository subCategoriaCursoRepository;

    public SubCategoriaCursoService(SubCategoriaCursoRepository subCategoriaCursoRepository){
        this.subCategoriaCursoRepository = subCategoriaCursoRepository;
    }

    public List<SubCategoriaCursoModel> getAllSubCategorias() {
        return subCategoriaCursoRepository.findAll();
    }

    public Optional<SubCategoriaCursoModel> getSubCategoriaById(Long id) {
        return subCategoriaCursoRepository.findById(id);
    }

    public SubCategoriaCursoModel saveSubCategoria(SubCategoriaCursoModel subCategoria) {
        return subCategoriaCursoRepository.save(subCategoria);
    }

    public boolean updateSubCategoria(Long id, SubCategoriaCursoModel subCategoriaData) {
        Optional<SubCategoriaCursoModel> subCategoriaOpt = subCategoriaCursoRepository.findById(id);
        if (subCategoriaOpt.isPresent()) {
            SubCategoriaCursoModel subCategoria = subCategoriaOpt.get();
            subCategoria.setNombre(subCategoriaData.getNombre());
            subCategoriaCursoRepository.save(subCategoria);
            return true;
        }
        return false;
    }

    public boolean deleteSubCategoria(Long id) {
        if (subCategoriaCursoRepository.existsById(id)) {
            subCategoriaCursoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
