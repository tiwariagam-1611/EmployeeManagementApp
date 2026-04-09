package com.example.demo.repository;

import com.example.demo.model.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    List<EmployeeEntity> findByDepartment_DeptId(Long deptId);

    List<EmployeeEntity> findBySalaryGreaterThan(Double salary);

    List<EmployeeEntity> findByStatus(String status);

    List<EmployeeEntity> findByHireDateAfter(LocalDate date);

    List<EmployeeEntity> findByEmpNameContaining(String keyword);
}
