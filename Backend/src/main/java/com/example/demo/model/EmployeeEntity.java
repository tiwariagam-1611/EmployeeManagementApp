package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
public class EmployeeEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empId;

    private String empName;
    private String email;
    private String phone;
    private Double salary;
    private LocalDate hireDate;
    private String status;
	
	public EmployeeEntity(){
		
	}

    public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public LocalDate getHireDate() {
		return hireDate;
	}

	public void setHireDate(LocalDate hireDate) {
		this.hireDate = hireDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public DepartmentEntity getDepartment() {
	    return department;
	}

	public void setDepartment(DepartmentEntity department) {
	    this.department = department;
	}

	public EmployeeEntity(Long empId, String empName, String email, String phone, Double salary, LocalDate hireDate,
			String status, DepartmentEntity departmentEntity) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.email = email;
		this.phone = phone;
		this.salary = salary;
		this.hireDate = hireDate;
		this.status = status;
		this.department = departmentEntity;
	}

	

    @ManyToOne
    @JoinColumn(name = "dept_id")
    @JsonBackReference
    private DepartmentEntity department;
}