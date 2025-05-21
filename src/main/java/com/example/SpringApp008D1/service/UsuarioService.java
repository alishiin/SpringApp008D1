package com.example.SpringApp008D1.service;

import com.example.SpringApp008D1.model.UsuarioModel;
import com.example.SpringApp008D1.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioModel> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<UsuarioModel> getUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }

    public void saveUsuario(UsuarioModel usuario) {
        usuarioRepository.save(usuario);
    }

    public boolean updateUsuario(Long id, UsuarioModel usuarioActualizado) {
        Optional<UsuarioModel> existente = usuarioRepository.findById(id);
        if (existente.isPresent()) {
            UsuarioModel usuario = existente.get();
            usuario.setNombre(usuarioActualizado.getNombre());
            usuario.setEmail(usuarioActualizado.getEmail());
            usuario.setPassword(usuarioActualizado.getPassword());
            usuario.setRol(usuarioActualizado.getRol());
            usuarioRepository.save(usuario);
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteUsuario(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

//esto es un comentario para subirlo al git y que cuente como cambio.