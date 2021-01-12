package com.quick.app.repository;

import com.quick.app.domain.Productest;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Productest entity.
 */
@SuppressWarnings("unused")
//@Repository
//public interface ProductestRepository extends JpaRepository<Productest, Long> {
//}
@Repository
public interface ProductestRepository extends JpaRepository<Productest, Long> {
  @Query("SELECT p FROM  Productest as p WHERE LOWER(p.tensanpham) LIKE %?1%")
  public List<Productest>searchProduct(String keyword );
}