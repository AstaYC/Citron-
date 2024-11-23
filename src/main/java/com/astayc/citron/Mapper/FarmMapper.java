package com.astayc.citron.Mapper;

import com.astayc.citron.DTO.FarmDTO;
import com.astayc.citron.Entity.Farm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FarmMapper {
    @Mapping(target = "id", source = "id")

    FarmDTO toDTO(Farm farm);
    Farm toEntity(FarmDTO farmDto);
    List<FarmDTO> toDtoList(List<Farm> farms);
}
