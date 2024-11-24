package com.astayc.citron.Mapper;

import com.astayc.citron.DTO.HarvestDetailDTO;
import com.astayc.citron.Entity.HarvestDetail;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HarvestDetailMapper {
    HarvestDetail toEntity(HarvestDetailDTO dto);
    HarvestDetailDTO toDTO(HarvestDetail entity);
}
