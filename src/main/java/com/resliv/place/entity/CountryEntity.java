package com.resliv.place.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@EqualsAndHashCode(callSuper = true, exclude = "cities")
@ToString(exclude = "cities")
@Data
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "countries")
public class CountryEntity extends AbstractEntity {

  @Column
  private String name;

  @OneToMany(mappedBy = "country")
  @Column
  private Set<CityEntity> cities;

}
