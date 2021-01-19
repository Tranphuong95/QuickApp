package com.quick.app.repository;

import com.quick.app.domain.ImageServer;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ImageServer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ImageServerRepository extends JpaRepository<ImageServer, Long> {
}
