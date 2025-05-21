package com.example.SpringApp008D1.repository;

import com.example.SpringApp008D1.model.InscripcionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscripcionRepository extends JpaRepository<InscripcionModel, Long> {
}
