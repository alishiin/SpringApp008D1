package com.example.SpringApp008D1.Repository;

import com.example.SpringApp008D1.Model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {
    Optional<UsuarioModel> findByEmail(String email);
    boolean existsByEmail(String email);
}
