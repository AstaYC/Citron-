package com.astayc.citron.DTO;

import com.astayc.citron.Entity.Enum.Season;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HarvestDTO {
    public void setId(Long id) {
        this.id = id;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public void setTotalQuantity(double totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public void setHarvestDate(LocalDate harvestDate) {
        this.harvestDate = harvestDate;
    }

    public void setFieldId(Long fieldId) {
        this.fieldId = fieldId;
    }

    private Long id; // Ensure this field is defined


    private String season;
    private double totalQuantity;
    private LocalDate harvestDate;
    private Long fieldId; // If this maps to the field entity

    public Long getId() {
        return id;
    }

    public String getSeason() {
        return season;
    }

    public double getTotalQuantity() {
        return totalQuantity;
    }

    public LocalDate getHarvestDate() {
        return harvestDate;
    }

    public Long getFieldId() {
        return fieldId;
    }
}
