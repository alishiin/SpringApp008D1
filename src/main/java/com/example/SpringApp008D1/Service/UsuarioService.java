package com.example.SpringApp008D1.Service;

import com.example.SpringApp008D1.Model.UsuarioModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    private final List<UsuarioModel> usuarios = new ArrayList<>();
    private Long contadorId = 1L;

    public List<UsuarioModel> listar() {
        return usuarios;
    }

    public UsuarioModel obtener(Long id) {
        return usuarios.stream()
                .filter(usuario -> usuario.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public UsuarioModel crear(UsuarioModel usuario) {
        usuario.setId(contadorId++);
        usuarios.add(usuario);
        return usuario;
    }

    public void eliminar(Long id) {
        usuarios.removeIf(usuario -> usuario.getId().equals(id));
    }

    public boolean existe(Long id) {
        return usuarios.stream().anyMatch(usuario -> usuario.getId().equals(id));
    }
}
