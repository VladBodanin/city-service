package com.resliv.place.repository;

import com.resliv.place.entity.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface CountryRepository extends JpaRepository<CountryEntity, BigInteger> {

    Optional<CountryEntity> findByName(String name);

}
