package com.example.SpringApp008D1.repository;

import com.example.SpringApp008D1.model.SubCategoriaCursoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoriaCursoRepository extends JpaRepository<SubCategoriaCursoModel, Long> {
}
