package com.resliv.place.repository;

import com.resliv.place.entity.CityEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class CitySearchRepository {

  private final EntityManager entityManager;

  public List<CityEntity> search(Collection<Specification<CityEntity>> specifications) {
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<CityEntity> query = criteriaBuilder.createQuery(CityEntity.class);
    Root<CityEntity> root = query.from(CityEntity.class);

    query.where(
        criteriaBuilder.and(
            specifications.stream()
                .map(e -> e.toPredicate(root, query, criteriaBuilder))
                .toArray(Predicate[]::new)));

    query.select(root);

   return entityManager.createQuery(query).getResultList();
  }
}
