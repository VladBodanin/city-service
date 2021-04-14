package com.resliv.place.service;

import com.resliv.place.dto.CityDto;
import com.resliv.place.entity.CityEntity;
import com.resliv.place.mapper.CityMapper;
import com.resliv.place.repository.CitySearchRepository;
import com.resliv.place.repository.spec.CityNameEqualSpec;
import com.resliv.place.repository.spec.CountryNameEqualSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CitySearchService {

  private final CityMapper cityMapper;
  private final CitySearchRepository searchRepository;

  public List<CityDto> search(Optional<String> city, Optional<String> country) {
    Set<Specification<CityEntity>> specifications = new HashSet<>();
    city.ifPresent(e -> specifications.add(new CityNameEqualSpec(e)));
    country.ifPresent(e -> specifications.add(new CountryNameEqualSpec(e)));

    return searchRepository.search(specifications).stream()
        .map(cityMapper::toDto)
        .collect(Collectors.toList());
  }
}
