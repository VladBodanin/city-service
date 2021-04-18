package com.resliv.place.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CityDto {
  private BigInteger id;
  private String name;
  private String description;
  private CountryDto country;
}
