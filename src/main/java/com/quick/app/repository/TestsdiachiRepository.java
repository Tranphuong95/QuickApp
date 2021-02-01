package com.quick.app.repository;

import com.quick.app.domain.Testsdiachi;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Testsdiachi entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TestsdiachiRepository extends JpaRepository<Testsdiachi, Long> {
}
