package com.resliv.place.repository.spec;

import com.resliv.place.entity.CityEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@RequiredArgsConstructor
public class CityNameEqualSpec implements Specification<CityEntity> {

  private final String name;

  @Override
  public Predicate toPredicate(
      Root<CityEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
    return criteriaBuilder.equal(root.get("name"), name);
  }
}
