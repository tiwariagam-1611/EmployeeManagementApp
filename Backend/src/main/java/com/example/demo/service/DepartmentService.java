package com.example.demo.service;

import com.example.demo.model.DepartmentEntity;
import com.example.demo.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository repo;

    public DepartmentService(DepartmentRepository repo) {
        this.repo = repo;
    }

    public DepartmentEntity create(DepartmentEntity dept) {
        return repo.save(dept);
    }

    public List<DepartmentEntity> getAll() {
        return repo.findAll();
    }

    public DepartmentEntity update(Long id, DepartmentEntity updated) {
        DepartmentEntity dept = repo.findById(id).orElseThrow();
        dept.setDeptName(updated.getDeptName());
        dept.setBudget(updated.getBudget());
        return repo.save(dept);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public List<DepartmentEntity> findByLocation(Long locationId) {
        return repo.findByLocation_LocationId(locationId);
    }

    public List<DepartmentEntity> findByBudget(Double budget) {
        return repo.findByBudgetGreaterThan(budget);
    }
}
