package com.quick.app.repository;

import com.quick.app.domain.Testuuid;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Testuuid entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TestuuidRepository extends JpaRepository<Testuuid, Long> {
}
