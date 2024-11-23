package com.astayc.citron.Mapper;

import com.astayc.citron.DTO.FieldDTO;
import com.astayc.citron.Entity.Field;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FieldMapper {

    FieldMapper INSTANCE = Mappers.getMapper(FieldMapper.class);

    @Mapping(source = "area", target = "area")
    Field toEntity(FieldDTO fieldDTO);

    @Mapping(source = "area", target = "area")
    FieldDTO toDTO(Field field);
}