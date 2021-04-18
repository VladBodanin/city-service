package com.resliv.place.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true, exclude = "country")
@ToString(exclude = "country")
@SuperBuilder
@Data
@NoArgsConstructor
@Entity
@Table(name = "cities")
public class CityEntity extends AbstractEntity {

  @Column private String name;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "country_id", nullable = false)
  private CountryEntity country;

  @Column private String description;

}
