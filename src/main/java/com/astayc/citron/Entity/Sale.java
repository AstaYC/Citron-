package com.astayc.citron.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "sale")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDate saleDate;

    @NotNull
    private double unitPrice;

    @NotNull
    private double revenue;

    @NotNull
    private String clientName;

    @OneToOne
    @JoinColumn(name = "harvest_id", nullable = false)
    private Harvest harvest;
}
