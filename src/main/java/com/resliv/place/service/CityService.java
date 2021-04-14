package com.resliv.place.service;

import com.resliv.place.dto.CityDto;
import com.resliv.place.dto.CreateCityDto;
import com.resliv.place.entity.CityEntity;
import com.resliv.place.entity.CountryEntity;
import com.resliv.place.exception.EntityAlreadyPresentException;
import com.resliv.place.exception.NotFoundException;
import com.resliv.place.mapper.CityMapper;
import com.resliv.place.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
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
    validateCityUniqueness(createCityDto.getName(), countryId);
    CityEntity cityEntity = cityMapper.toEntity(createCityDto);
    cityEntity.setCountry(country);

    return cityMapper.toDto(cityRepository.save(cityEntity));
  }

  @Transactional
  public CityDto updateCity(BigInteger countryId, BigInteger cityId, CityDto cityDto) {
    CityEntity city = getByIdOrThrow(cityId);
    if (!Objects.equals(countryId, city.getCountry().getId())) {
      // todo thw you try to update city from other country
    }
    validateCityUniqueness(cityDto.getName(), countryId);
    city.setDescription(cityDto.getDescription());
    city.setName(cityDto.getName());

    return cityMapper.toDto(city);
  }

  @Transactional
  public void deleteCity(BigInteger countryId, BigInteger id) {
    CityEntity city = getByIdOrThrow(id);
    if (!Objects.equals(countryId, city.getCountry().getId())) {
      // todo thw you try to delete city from other country
    }
    cityRepository.delete(city);
  }

  private CityEntity getByIdOrThrow(BigInteger id) {
    return cityRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException("Not found city with id:" + id));
  }

  private void validateCityUniqueness(String cityName, BigInteger countryId) {
    cityRepository
        .findByNameAndCountryId(cityName, countryId)
        .ifPresent(
            city -> {
              throw new EntityAlreadyPresentException(
                  "City with name "
                      + city
                      + " already present in "
                      + city.getCountry()
                      + " country");
            });
  }
}
