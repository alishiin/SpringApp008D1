package com.example.SpringApp008D1.repository;

import com.example.SpringApp008D1.model.ContenidoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContenidoRepository extends JpaRepository<ContenidoModel, Long> {
}
