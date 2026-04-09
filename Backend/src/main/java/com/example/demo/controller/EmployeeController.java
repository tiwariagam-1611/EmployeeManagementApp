package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.model.EmployeeEntity;
import com.example.demo.service.EmployeeService;

@RestController
@RequestMapping("/employees")
@CrossOrigin(origins= "http://localhost:3000")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @PostMapping
    public EmployeeEntity create(@RequestBody EmployeeEntity emp) {
        return service.create(emp);
    }

    @GetMapping
    public List<EmployeeEntity> getAll() {
        return service.getAll();
    }

    @PutMapping("/{id}")
    public EmployeeEntity update(@PathVariable Long id, @RequestBody EmployeeEntity emp) {
        return service.update(id, emp);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    // Custom Queries
//    @GetMapping("/department/{deptId}")
//    public List<EmployeeEntity> byDept(@PathVariable Long deptId) {
//        return service.findByDept(deptId);
//    }
//
//    @GetMapping("/salary/{amount}")
//    public List<EmployeeEntity> bySalary(@PathVariable Double amount) {
//        return service.findBySalary(amount);
//    }
//
//    @GetMapping("/status/{status}")
//    public List<EmployeeEntity> byStatus(@PathVariable String status) {
//        return service.findByStatus(status);
//    }
//
//    @GetMapping("/hired-after/{date}")
//    public List<EmployeeEntity> hiredAfter(@PathVariable String date) {
//        return service.hiredAfter(LocalDate.parse(date));
//    }
//
//    @GetMapping("/search/{keyword}")
//    public List<EmployeeEntity> search(@PathVariable String keyword) {
//        return service.searchByName(keyword);
//    }
}