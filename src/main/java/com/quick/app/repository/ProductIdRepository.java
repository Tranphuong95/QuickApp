package com.quick.app.repository;

import com.quick.app.domain.ProductId;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ProductId entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductIdRepository extends JpaRepository<ProductId, Long> {
}
