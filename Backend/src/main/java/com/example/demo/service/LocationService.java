package com.example.demo.service;

import com.example.demo.model.LocationEntity;
import com.example.demo.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    private final LocationRepository repo;

    public LocationService(LocationRepository repo) {
        this.repo = repo;
    }

    public LocationEntity create(LocationEntity location) {
        return repo.save(location);
    }

    public List<LocationEntity> getAll() {
        return repo.findAll();
    }

    public LocationEntity update(Long id, LocationEntity updated) {
        LocationEntity loc = repo.findById(id).orElseThrow();
        loc.setCity(updated.getCity());
        loc.setState(updated.getState());
        loc.setCountry(updated.getCountry());
        return repo.save(loc);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public List<LocationEntity> findByCountry(String country) {
        return repo.findByCountry(country);
    }
}