package com.astayc.citron.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;
import java.time.Period;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tree")
public class Tree {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private LocalDate datePlantation;

    private int age;

    private double productivity;

    @ManyToOne
    @JoinColumn(name = "field_id", nullable = false)
    @ToString.Exclude
    private Field field;

    public void calculateAge() {
        if (datePlantation != null) {
            this.age = Period.between(datePlantation, LocalDate.now()).getYears();
        } else {
            this.age = 0;
        }
    }

    public void calculateProductivity() {
        if (age < 3) {
            this.productivity = 2.5; // Young trees
        } else if (age <= 10) {
            this.productivity = 12.0; // Peak productivity
        } else {
            this.productivity = 20.0; // Older trees
        }
    }

    @PrePersist
    @PreUpdate
    public void updateDerivedFields() {
        calculateAge();
        calculateProductivity();
    }
}
