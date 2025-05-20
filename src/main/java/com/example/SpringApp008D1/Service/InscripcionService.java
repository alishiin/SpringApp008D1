package com.example.SpringApp008D1.Service;

import com.example.SpringApp008D1.Model.InscripcionModel;
import com.example.SpringApp008D1.Repository.InscripcionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class InscripcionService {

    private final InscripcionRepository repository;

    public InscripcionService(InscripcionRepository repository) {
        this.repository = repository;
    }

    public List<InscripcionModel> listar() {
        return repository.findAll();
    }

    public InscripcionModel inscribir(Long usuarioId, Long cursoId) {
        InscripcionModel inscripcion = new InscripcionModel(usuarioId, cursoId, LocalDate.now(), true);
        return repository.save(inscripcion);
    }

    public Optional<InscripcionModel> obtener(Long id) {
        return repository.findById(id);
    }

    public void cancelar(Long id) {
        repository.findById(id).ifPresent(inscripcion -> {
            inscripcion.setActiva(false);
            repository.save(inscripcion);
        });
    }

    public List<InscripcionModel> listarPorUsuario(Long usuarioId) {
        return repository.findByUsuarioId(usuarioId);
    }

    public List<InscripcionModel> listarPorCurso(Long cursoId) {
        return repository.findByCursoId(cursoId);
    }
}
