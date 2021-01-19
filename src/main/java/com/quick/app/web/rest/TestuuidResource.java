package com.quick.app.web.rest;

import com.quick.app.domain.Testuuid;
import com.quick.app.repository.TestuuidRepository;
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
 * REST controller for managing {@link com.quick.app.domain.Testuuid}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TestuuidResource {

    private final Logger log = LoggerFactory.getLogger(TestuuidResource.class);

    private static final String ENTITY_NAME = "testuuid";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TestuuidRepository testuuidRepository;

    public TestuuidResource(TestuuidRepository testuuidRepository) {
        this.testuuidRepository = testuuidRepository;
    }

    /**
     * {@code POST  /testuuids} : Create a new testuuid.
     *
     * @param testuuid the testuuid to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new testuuid, or with status {@code 400 (Bad Request)} if the testuuid has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/testuuids")
    public ResponseEntity<Testuuid> createTestuuid(@RequestBody Testuuid testuuid) throws URISyntaxException {
        log.debug("REST request to save Testuuid : {}", testuuid);
        if (testuuid.getId() != null) {
            throw new BadRequestAlertException("A new testuuid cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Testuuid result = testuuidRepository.save(testuuid);
        return ResponseEntity.created(new URI("/api/testuuids/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /testuuids} : Updates an existing testuuid.
     *
     * @param testuuid the testuuid to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated testuuid,
     * or with status {@code 400 (Bad Request)} if the testuuid is not valid,
     * or with status {@code 500 (Internal Server Error)} if the testuuid couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/testuuids")
    public ResponseEntity<Testuuid> updateTestuuid(@RequestBody Testuuid testuuid) throws URISyntaxException {
        log.debug("REST request to update Testuuid : {}", testuuid);
        if (testuuid.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Testuuid result = testuuidRepository.save(testuuid);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, testuuid.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /testuuids} : get all the testuuids.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of testuuids in body.
     */
    @GetMapping("/testuuids")
    public List<Testuuid> getAllTestuuids() {
        log.debug("REST request to get all Testuuids");
        return testuuidRepository.findAll();
    }

    /**
     * {@code GET  /testuuids/:id} : get the "id" testuuid.
     *
     * @param id the id of the testuuid to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the testuuid, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/testuuids/{id}")
    public ResponseEntity<Testuuid> getTestuuid(@PathVariable Long id) {
        log.debug("REST request to get Testuuid : {}", id);
        Optional<Testuuid> testuuid = testuuidRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(testuuid);
    }

    /**
     * {@code DELETE  /testuuids/:id} : delete the "id" testuuid.
     *
     * @param id the id of the testuuid to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/testuuids/{id}")
    public ResponseEntity<Void> deleteTestuuid(@PathVariable Long id) {
        log.debug("REST request to delete Testuuid : {}", id);
        testuuidRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
