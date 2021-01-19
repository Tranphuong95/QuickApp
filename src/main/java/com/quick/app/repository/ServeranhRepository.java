package com.quick.app.repository;

import com.quick.app.domain.Serveranh;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Spring Data  repository for the Serveranh entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ServeranhRepository extends JpaRepository<Serveranh, Long> {
  Optional<Serveranh> findByUuid(UUID uuid);
}
