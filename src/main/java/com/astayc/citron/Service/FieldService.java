package com.astayc.citron.Service;

import com.astayc.citron.DTO.FieldDTO;
import com.astayc.citron.Entity.Field;

import java.util.List;

public interface FieldService {
    Field addField(Long farmId, FieldDTO fieldDTO);
    List<FieldDTO> getFieldsByFarm(Long farmId);
}
