package com.resliv.place.controller;

import com.resliv.place.dto.CityDto;
import com.resliv.place.service.PlaceSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping("/search")
@RestController
public class CitySearchController {

  private final PlaceSearchService placeSearchService;

  @GetMapping
  public ResponseEntity<List<CityDto>> getPlace(
      @RequestParam(required = false) Optional<String> cityName,
      @RequestParam(required = false) Optional<String> countryName) {
    return ResponseEntity.ok(placeSearchService.search(cityName, countryName));
  }
}
