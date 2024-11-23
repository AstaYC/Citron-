package com.astayc.citron.Controller;  // Correct directory naming, lowercase package, uppercase class

import com.astayc.citron.DTO.FieldDTO;  // Correct import, class name starts with uppercase
import com.astayc.citron.Entity.Field;
import com.astayc.citron.Service.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/fields")
@Validated
public class FieldController {

    @Autowired
    private FieldService fieldService;  // Autowiring the FieldService

    @PostMapping("/add/{farmId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Field addField(@PathVariable @NotNull Long farmId, @RequestBody @Valid FieldDTO fieldDTO) {
        return fieldService.addField(farmId, fieldDTO);  // Delegating business logic to service
    }

    @GetMapping("/{farmId}")
    public List<Field> getFieldsByFarm(@PathVariable @NotNull Long farmId) {
        return fieldService.getFieldsByFarm(farmId);  // Fetching fields related to a farm
    }
}
