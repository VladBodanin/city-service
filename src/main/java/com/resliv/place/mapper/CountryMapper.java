package com.resliv.place.mapper;

import com.resliv.place.dto.CountryDto;
import com.resliv.place.dto.CreateCountryDto;
import com.resliv.place.entity.CountryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CountryMapper {

  CountryEntity toEntity(CountryDto countryDto);

  CountryEntity toEntity(CreateCountryDto createCountryDto);

  CountryDto toDto(CountryEntity countryEntity);
}
