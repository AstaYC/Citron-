package com.astayc.citron.Service.Impl;

import com.astayc.citron.DTO.FieldDTO;
import com.astayc.citron.Entity.Field;
import com.astayc.citron.Entity.Farm;
import com.astayc.citron.Repository.FieldRepository;
import com.astayc.citron.Repository.FarmRepository;
import com.astayc.citron.Mapper.FieldMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.astayc.citron.Service.FieldService;


import jakarta.validation.ValidationException;
import java.util.List;

@Service
public class FieldServiceImpl implements FieldService {

    @Autowired
    private FieldRepository fieldRepository;

    @Autowired
    private FarmRepository farmRepository;

    @Override
    public Field addField(Long farmId, FieldDTO fieldDTO) {
        Farm farm = farmRepository.findById(farmId)
                .orElseThrow(() -> new IllegalArgumentException("Farm not found"));

        // Convert DTO to Entity using a mapper (MapStruct or manual mapping)
        Field field = FieldMapper.INSTANCE.toEntity(fieldDTO);

        // Validate constraints
        List<Field> existingFields = fieldRepository.findByFarmId(farmId);
        double totalArea = existingFields.stream().mapToDouble(Field::getArea).sum() + field.getArea();

        if (existingFields.size() >= 10) {
            throw new ValidationException("A farm cannot have more than 10 fields.");
        }

        if (field.getArea() < 0.1 || field.getArea() > farm.getArea() / 2) {
            throw new ValidationException("Field area must be between 0.1 hectares and 50% of the farm area.");
        }

        if (totalArea > farm.getArea()) {
            throw new ValidationException("Total field area cannot exceed the farm area.");
        }

        // Additional constraints can be validated here (e.g., tree spacing, age, plantation period)

        field.setFarm(farm);  // Linking the field to the farm entity
        return fieldRepository.save(field);  // Saving field to the repository
    }

    @Override
    public List<Field> getFieldsByFarm(Long farmId) {
        return fieldRepository.findByFarmId(farmId);  // Fetching fields for a farm
    }
}
