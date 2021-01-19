package com.quick.app.repository;

import com.quick.app.domain.Fileanh;

import com.quick.app.domain.Productest;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Spring Data  repository for the Fileanh entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FileanhRepository extends JpaRepository<Fileanh, Long> {
  Optional<Fileanh>findByName(String name);
}
