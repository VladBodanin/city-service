package com.resliv.place.repository;

import com.resliv.place.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface CityRepository extends JpaRepository<CityEntity, BigInteger> {

  List<CityEntity> findByName(String name);

  List<CityEntity> findByCountryId(BigInteger countyId);

  Optional<CityEntity> findByNameAndCountryId(String cityName, BigInteger countyId);
}
