package com.example.demo.controller;

import com.example.demo.model.LocationEntity;
import com.example.demo.service.LocationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locations")
@CrossOrigin(origins= "http://localhost:3000")
public class LocationController {

    private final LocationService service;

    public LocationController(LocationService service) {
        this.service = service;
    }

    @PostMapping
    public LocationEntity create(@RequestBody LocationEntity location) {
        return service.create(location);
    }

    @GetMapping
    public List<LocationEntity> getAll() {
        return service.getAll();
    }

    @PutMapping("/{id}")
    public LocationEntity update(@PathVariable Long id, @RequestBody LocationEntity loc) {
        return service.update(id, loc);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    // Custom
//    @GetMapping("/country/{country}")
//    public List<LocationEntity> byCountry(@PathVariable String country) {
//        return service.findByCountry(country);
//    }
}
