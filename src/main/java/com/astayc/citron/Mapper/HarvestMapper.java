package com.astayc.citron.Mapper;

import com.astayc.citron.DTO.HarvestDTO;
import com.astayc.citron.Entity.Harvest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface HarvestMapper {
    HarvestMapper INSTANCE = Mappers.getMapper(HarvestMapper.class);

    HarvestDTO toDTO(Harvest harvest);
    Harvest toEntity(HarvestDTO harvestDTO);
}
