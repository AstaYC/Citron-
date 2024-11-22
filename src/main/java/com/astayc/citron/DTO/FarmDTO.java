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

    // Getters and Setters
}

