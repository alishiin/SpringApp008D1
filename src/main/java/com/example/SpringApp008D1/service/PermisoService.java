package com.example.SpringApp008D1.service;

import com.example.SpringApp008D1.model.PermisoModel;
import com.example.SpringApp008D1.repository.PermisoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermisoService {

    private final PermisoRepository permisoRepository;

    public PermisoService(PermisoRepository permisoRepository) {
        this.permisoRepository = permisoRepository;
    }

    public PermisoModel crearPermiso(PermisoModel permiso) {
        return permisoRepository.save(permiso);
    }

    public List<PermisoModel> listarPermisos() {
        return permisoRepository.findAll();
    }

    public boolean eliminarPermiso(Long id) {
        if (permisoRepository.existsById(id)) {
            permisoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
