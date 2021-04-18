package com.resliv.place.mapper;

import com.resliv.place.dto.CityDto;
import com.resliv.place.dto.CreateCityDto;
import com.resliv.place.entity.CityEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CityMapper {

  CityEntity toEntity(CityDto cityDto);

  CityEntity toEntity(CreateCityDto placeDto);

  CityDto toDto(CityEntity cityEntity);
}
