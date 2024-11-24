package com.astayc.citron.Mapper;

import com.astayc.citron.DTO.HarvestDTO;
import com.astayc.citron.Entity.Harvest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HarvestMapper {
    @Mapping(target = "season", ignore = true)
    @Mapping(target = "totalQuantity", ignore = true)
    Harvest toEntity(HarvestDTO harvestDTO);

    HarvestDTO toDTO(Harvest harvest);
}
