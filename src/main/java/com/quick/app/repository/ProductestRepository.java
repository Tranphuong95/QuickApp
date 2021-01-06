package com.quick.app.repository;

import com.quick.app.domain.Productest;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Productest entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductestRepository extends JpaRepository<Productest, Long> {
}
