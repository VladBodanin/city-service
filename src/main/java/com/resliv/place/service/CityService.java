package com.resliv.place.service;

import com.resliv.place.dto.CityDto;
import com.resliv.place.dto.CreateCityDto;
import com.resliv.place.dto.UpdateCityDto;
import com.resliv.place.entity.CityEntity;
import com.resliv.place.entity.CountryEntity;
import com.resliv.place.exception.PlaceServiceException;
import com.resliv.place.exception.EntityAlreadyPresentException;
import com.resliv.place.exception.handler.ErrorReason;
import com.resliv.place.exception.NotFoundException;
import com.resliv.place.mapper.CityMapper;
import com.resliv.place.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CityService {

  private final CountryService countryService;
  private final CityRepository cityRepository;
  private final CityMapper cityMapper;

  public List<CityDto> getCityByCountry(BigInteger countryId) {
    return cityRepository.findByCountryId(countryId).stream()
        .map(cityMapper::toDto)
        .collect(Collectors.toList());
  }

  @Transactional
  public CityDto createCity(BigInteger countryId, CreateCityDto createCityDto) {
    CountryEntity country = countryService.getByIdOrThrow(countryId);
    validateCityUniqueness(countryId, createCityDto.getName(), Collections.emptyList());
    CityEntity cityEntity = cityMapper.toEntity(createCityDto);
    cityEntity.setCountry(country);

    return cityMapper.toDto(cityRepository.save(cityEntity));
  }

  @Transactional
  public CityDto updateCity(BigInteger countryId, BigInteger cityId, UpdateCityDto cityDto) {
    CityEntity city = getByIdOrThrow(cityId);
    if (!Objects.equals(countryId, city.getCountry().getId())) {
      throw new PlaceServiceException(
          "You try to update city of another country", ErrorReason.INVALID_COUNTRY);
    }
    validateCityUniqueness(countryId, cityDto.getName(), Set.of(city.getName()));
    city.setDescription(cityDto.getDescription());
    city.setName(cityDto.getName());

    return cityMapper.toDto(city);
  }

  @Transactional
  public void deleteCity(BigInteger countryId, BigInteger id) {
    CityEntity city = getByIdOrThrow(id);
    if (!Objects.equals(countryId, city.getCountry().getId())) {
      throw new PlaceServiceException(
          "You try to delete city of another country", ErrorReason.INVALID_COUNTRY);
    }
    cityRepository.delete(city);
  }

  private CityEntity getByIdOrThrow(BigInteger id) {
    return cityRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException("Not found city with id:" + id));
  }

  private void validateCityUniqueness(
      BigInteger countryId, String cityName, Collection<String> excludedCityNames) {
    cityRepository
        .findByNameAndCountryId(cityName, countryId)
        .ifPresent(
            city -> {
              if (!excludedCityNames.contains(cityName)) {
                throw new EntityAlreadyPresentException(
                    "City with name "
                        + cityName
                        + " already present in "
                        + city.getCountry().getName()
                        + " country");
              }
            });
  }
}
