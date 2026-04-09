package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.model.DepartmentEntity;
import com.example.demo.service.DepartmentService;

@RestController
@RequestMapping("/departments")
@CrossOrigin(origins= "http://localhost:3000")
public class DepartmentController {

    private final DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @PostMapping
    public DepartmentEntity create(@RequestBody DepartmentEntity dept) {
        return service.create(dept);
    }

    @GetMapping("/get")
    public List<DepartmentEntity> getAll() {
        return service.getAll();
    }

    @PutMapping("/{id}")
    public DepartmentEntity update(@PathVariable Long id, @RequestBody DepartmentEntity dept) {
        return service.update(id, dept);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    // Custom
//    @GetMapping("/location/{locationId}")
//    public List<DepartmentEntity> byLocation(@PathVariable Long locationId) {
//        return service.findByLocation(locationId);
//    }
//
//    @GetMapping("/budget/{amount}")
//    public List<DepartmentEntity> byBudget(@PathVariable Double amount) {
//        return service.findByBudget(amount);
//    }
}
