package com.quick.app.web.rest;

import com.quick.app.domain.Editor;
import com.quick.app.repository.EditorRepository;
import com.quick.app.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.quick.app.domain.Editor}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class EditorResource {

    private final Logger log = LoggerFactory.getLogger(EditorResource.class);

    private static final String ENTITY_NAME = "editor";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EditorRepository editorRepository;

    public EditorResource(EditorRepository editorRepository) {
        this.editorRepository = editorRepository;
    }

    /**
     * {@code POST  /editors} : Create a new editor.
     *
     * @param editor the editor to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new editor, or with status {@code 400 (Bad Request)} if the editor has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/editors")
    public ResponseEntity<Editor> createEditor(@RequestBody Editor editor) throws URISyntaxException {
        log.debug("REST request to save Editor : {}", editor);
        if (editor.getId() != null) {
            throw new BadRequestAlertException("A new editor cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Editor result = editorRepository.save(editor);
        return ResponseEntity.created(new URI("/api/editors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /editors} : Updates an existing editor.
     *
     * @param editor the editor to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated editor,
     * or with status {@code 400 (Bad Request)} if the editor is not valid,
     * or with status {@code 500 (Internal Server Error)} if the editor couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/editors")
    public ResponseEntity<Editor> updateEditor(@RequestBody Editor editor) throws URISyntaxException {
        log.debug("REST request to update Editor : {}", editor);
        if (editor.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Editor result = editorRepository.save(editor);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, editor.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /editors} : get all the editors.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of editors in body.
     */
    @GetMapping("/editors")
    public List<Editor> getAllEditors() {
        log.debug("REST request to get all Editors");
        return editorRepository.findAll();
    }

    /**
     * {@code GET  /editors/:id} : get the "id" editor.
     *
     * @param id the id of the editor to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the editor, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/editors/{id}")
    public ResponseEntity<Editor> getEditor(@PathVariable Long id) {
        log.debug("REST request to get Editor : {}", id);
        Optional<Editor> editor = editorRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(editor);
    }

    /**
     * {@code DELETE  /editors/:id} : delete the "id" editor.
     *
     * @param id the id of the editor to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/editors/{id}")
    public ResponseEntity<Void> deleteEditor(@PathVariable Long id) {
        log.debug("REST request to delete Editor : {}", id);
        editorRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
