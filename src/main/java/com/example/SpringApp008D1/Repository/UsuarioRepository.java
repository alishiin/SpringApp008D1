package com.example.SpringApp008D1.repository;

import com.example.SpringApp008D1.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {
}

