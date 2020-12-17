package com.quick.app.repository;

import com.quick.app.domain.Editor;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Editor entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EditorRepository extends JpaRepository<Editor, Long> {
}
