package com.example.demo.service;

import com.example.demo.model.EmployeeEntity;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository repo;

    public EmployeeService(EmployeeRepository repo) {
        this.repo = repo;
    }

    public EmployeeEntity create(EmployeeEntity emp) {
        return repo.save(emp);
    }

    public List<EmployeeEntity> getAll() {
        return repo.findAll();
    }

    public EmployeeEntity update(Long id, EmployeeEntity updated) {
        EmployeeEntity emp = repo.findById(id).orElseThrow();
        emp.setEmpName(updated.getEmpName());
        emp.setEmail(updated.getEmail());
        emp.setPhone(updated.getPhone());
        emp.setSalary(updated.getSalary());
        emp.setHireDate(updated.getHireDate());
        emp.setStatus(updated.getStatus());
        return repo.save(emp);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public List<EmployeeEntity> findByDept(Long deptId) {
        return repo.findByDepartment_DeptId(deptId);
    }

    public List<EmployeeEntity> findBySalary(Double salary) {
        return repo.findBySalaryGreaterThan(salary);
    }

    public List<EmployeeEntity> findByStatus(String status) {
        return repo.findByStatus(status);
    }

    public List<EmployeeEntity> hiredAfter(LocalDate date) {
        return repo.findByHireDateAfter(date);
    }

    public List<EmployeeEntity> searchByName(String keyword) {
        return repo.findByEmpNameContaining(keyword);
    }
}
