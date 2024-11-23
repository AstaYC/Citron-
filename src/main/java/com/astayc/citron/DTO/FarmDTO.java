package com.astayc.citron.DTO;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class FarmDTO {
    private Long id;

    @NotBlank(message = "Name is required.")
    @Size(max = 100, message = "Name cannot exceed 100 characters.")
    private String name;

    @NotBlank(message = "Location is required.")
    private String location;

    @NotNull(message = "Area is required.")
    @Min(value = 0, message = "Area must be greater than 0.")
    private Double area;

    @NotNull(message = "Creation date is required.")
    private LocalDate creationDate;

    // Getter and Setter for 'id'
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter and Setter for 'name'
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for 'location'
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    // Getter and Setter for 'area'
    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    // Getter and Setter for 'creationDate'
    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }}

