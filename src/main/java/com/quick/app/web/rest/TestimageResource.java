package com.quick.app.web.rest;

import com.quick.app.domain.Testimage;
import com.quick.app.repository.TestimageRepository;
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
 * REST controller for managing {@link com.quick.app.domain.Testimage}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TestimageResource {

    private final Logger log = LoggerFactory.getLogger(TestimageResource.class);

    private static final String ENTITY_NAME = "testimage";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TestimageRepository testimageRepository;

    public TestimageResource(TestimageRepository testimageRepository) {
        this.testimageRepository = testimageRepository;
    }

    /**
     * {@code POST  /testimages} : Create a new testimage.
     *
     * @param testimage the testimage to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new testimage, or with status {@code 400 (Bad Request)} if the testimage has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/testimages")
    public ResponseEntity<Testimage> createTestimage(@RequestBody Testimage testimage) throws URISyntaxException {
        log.debug("REST request to save Testimage : {}", testimage);
        if (testimage.getId() != null) {
            throw new BadRequestAlertException("A new testimage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Testimage result = testimageRepository.save(testimage);
        return ResponseEntity.created(new URI("/api/testimages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /testimages} : Updates an existing testimage.
     *
     * @param testimage the testimage to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated testimage,
     * or with status {@code 400 (Bad Request)} if the testimage is not valid,
     * or with status {@code 500 (Internal Server Error)} if the testimage couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/testimages")
    public ResponseEntity<Testimage> updateTestimage(@RequestBody Testimage testimage) throws URISyntaxException {
        log.debug("REST request to update Testimage : {}", testimage);
        if (testimage.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Testimage result = testimageRepository.save(testimage);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, testimage.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /testimages} : get all the testimages.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of testimages in body.
     */
    @GetMapping("/testimages")
    public List<Testimage> getAllTestimages() {
        log.debug("REST request to get all Testimages");
        return testimageRepository.findAll();
    }

    /**
     * {@code GET  /testimages/:id} : get the "id" testimage.
     *
     * @param id the id of the testimage to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the testimage, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/testimages/{id}")
    public ResponseEntity<Testimage> getTestimage(@PathVariable Long id) {
        log.debug("REST request to get Testimage : {}", id);
        Optional<Testimage> testimage = testimageRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(testimage);
    }

    /**
     * {@code DELETE  /testimages/:id} : delete the "id" testimage.
     *
     * @param id the id of the testimage to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/testimages/{id}")
    public ResponseEntity<Void> deleteTestimage(@PathVariable Long id) {
        log.debug("REST request to delete Testimage : {}", id);
        testimageRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
