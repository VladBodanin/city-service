package com.resliv.place.controller;

import com.resliv.place.dto.CityDto;
import com.resliv.place.dto.CountryDto;
import com.resliv.place.dto.CreateCountryDto;
import com.resliv.place.dto.CreateCityDto;
import com.resliv.place.dto.UpdateCityDto;
import com.resliv.place.service.CityService;
import com.resliv.place.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/countries")
@RestController
public class CountryController {

  private final CountryService countryService;
  private final CityService cityService;

  @GetMapping
  public ResponseEntity<List<CountryDto>> getAllCountries() {
    return ResponseEntity.ok(countryService.getAllCountries());
  }

  @GetMapping("/{id}")
  public ResponseEntity<CountryDto> getCountryById(@PathVariable BigInteger id) {
    return ResponseEntity.ok(countryService.getCountryById(id));
  }

  @PostMapping
  public ResponseEntity<CountryDto> createCountry(
      @Valid @RequestBody CreateCountryDto createCountryDto) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(countryService.createCountry(createCountryDto));
  }

  @PutMapping("/{id}")
  public ResponseEntity<CountryDto> updatedCountry(
      @PathVariable BigInteger id, @Valid @RequestBody UpdateCityDto countryDto) {

    return ResponseEntity.ok(countryService.updateCountry(id, countryDto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCountry(@PathVariable BigInteger id) {
    countryService.deleteCountry(id);

    return ResponseEntity.noContent().build();
  }

  @PostMapping("/{countryId}/cities")
  public ResponseEntity<CityDto> createCity(
      @PathVariable BigInteger countryId, @Valid @RequestBody CreateCityDto createCityDto) {

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(cityService.createCity(countryId, createCityDto));
  }

  @DeleteMapping("/{countryId}/cities/{cityId}")
  public ResponseEntity<Void> deleteCity(
      @PathVariable BigInteger countryId, @PathVariable BigInteger cityId) {
    cityService.deleteCity(countryId, cityId);

    return ResponseEntity.noContent().build();
  }

  @PutMapping("/{countryId}/cities/{cityId}")
  public ResponseEntity<CityDto> updateCity(
      @PathVariable BigInteger countryId,
      @PathVariable BigInteger cityId,
      @Valid @RequestBody UpdateCityDto cityDto) {

    return ResponseEntity.ok(cityService.updateCity(countryId, cityId, cityDto));
  }

  @GetMapping("/{countryId}/cities")
  public ResponseEntity<List<CityDto>> getCityByCountry(@PathVariable BigInteger countryId) {
    return ResponseEntity.ok(cityService.getCityByCountry(countryId));
  }
}
