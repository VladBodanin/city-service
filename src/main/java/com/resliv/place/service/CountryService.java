package com.resliv.place.service;

import com.resliv.place.dto.CountryDto;
import com.resliv.place.dto.CreateCountryDto;
import com.resliv.place.dto.UpdateCityDto;
import com.resliv.place.entity.CountryEntity;
import com.resliv.place.exception.EntityAlreadyPresentException;
import com.resliv.place.exception.NotFoundException;
import com.resliv.place.mapper.CountryMapper;
import com.resliv.place.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CountryService {

  private final CountryRepository countryRepository;
  private final CountryMapper countryMapper;

  public List<CountryDto> getAllCountries() {
    return countryRepository.findAll().stream()
        .map(countryMapper::toDto)
        .collect(Collectors.toList());
  }

  public CountryDto getCountryById(BigInteger id) {
    CountryEntity countryEntity = getByIdOrThrow(id);

    return countryMapper.toDto(countryEntity);
  }

  @Transactional
  public CountryDto createCountry(CreateCountryDto createCountryDto) {
    validateCountryUniqueness(createCountryDto.getName());
    CountryEntity countryEntity = countryMapper.toEntity(createCountryDto);

    return countryMapper.toDto(countryRepository.save(countryEntity));
  }

  @Transactional
  public CountryDto updateCountry(BigInteger id, UpdateCityDto countryDto) {
    CountryEntity countryToUpdate = getByIdOrThrow(id);
    validateCountryUniqueness(countryDto.getName());
    countryToUpdate.setName(countryDto.getName());

    return countryMapper.toDto(countryToUpdate);
  }

  @Transactional
  public void deleteCountry(BigInteger id) {
    CountryEntity countryEntity = getByIdOrThrow(id);
    countryRepository.delete(countryEntity);
  }

  CountryEntity getByIdOrThrow(BigInteger id) {
    return countryRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException("Not found country with id:" + id));
  }

  private void validateCountryUniqueness(String countryName) {
    countryRepository
        .findByName(countryName)
        .ifPresent(
            e -> {
              throw new EntityAlreadyPresentException(
                  "Country with name " + countryName + " already exists");
            });
  }
}
