package com.resliv.place.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UpdateCityDto {
  @NotBlank(message = "name field should be NotBlank")
  private String name;

  @NotBlank(message = "description field should be NotBlank")
  private String description;
}
