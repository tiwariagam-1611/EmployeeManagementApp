package com.example.demo.repository;

import com.example.demo.model.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
    List<DepartmentEntity> findByLocation_LocationId(Long locationId);
    List<DepartmentEntity> findByBudgetGreaterThan(Double budget);
}
