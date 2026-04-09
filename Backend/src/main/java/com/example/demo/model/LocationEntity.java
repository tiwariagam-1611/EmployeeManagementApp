package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
public class LocationEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long locationId;

    private String city;
    private String state;
    private String country;
    
	public LocationEntity()
	{
		
	}

    public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<DepartmentEntity> getDepartments() {
		return departments;
	}

	public void setDepartments(List<DepartmentEntity> departments) {
		this.departments = departments;
	}

	public LocationEntity(Long locationId, String city, String state, String country,
			List<DepartmentEntity> departments) {
		super();
		this.locationId = locationId;
		this.city = city;
		this.state = state;
		this.country = country;
		this.departments = departments;
	}


    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<DepartmentEntity> departments;
}
