package com.quick.app.repository;

import com.quick.app.domain.Imagex;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Imagex entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ImagexRepository extends JpaRepository<Imagex, Long> {
}
