package com.quick.app.repository;

import com.quick.app.domain.Testimage;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Testimage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TestimageRepository extends JpaRepository<Testimage, Long> {
}
