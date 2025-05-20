package com.example.SpringApp008D1.service;

import com.example.SpringApp008D1.model.UsuarioModel;
import com.example.SpringApp008D1.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioModel crearUsuario(UsuarioModel usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<UsuarioModel> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public boolean eliminarUsuario(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
