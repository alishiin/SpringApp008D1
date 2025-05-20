package com.example.SpringApp008D1.repository;

import com.example.SpringApp008D1.model.PermisoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermisoRepository extends JpaRepository<PermisoModel, Long> {
}
