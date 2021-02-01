package com.quick.app.web.rest;

import com.quick.app.domain.Testsdiachi;
import com.quick.app.repository.TestsdiachiRepository;
import com.quick.app.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.quick.app.domain.Testsdiachi}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TestsdiachiResource {

    private final Logger log = LoggerFactory.getLogger(TestsdiachiResource.class);

    private static final String ENTITY_NAME = "testsdiachi";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TestsdiachiRepository testsdiachiRepository;

    public TestsdiachiResource(TestsdiachiRepository testsdiachiRepository) {
        this.testsdiachiRepository = testsdiachiRepository;
    }

    /**
     * {@code POST  /testsdiachis} : Create a new testsdiachi.
     *
     * @param testsdiachi the testsdiachi to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new testsdiachi, or with status {@code 400 (Bad Request)} if the testsdiachi has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/testsdiachis")
    public ResponseEntity<Testsdiachi> createTestsdiachi(@RequestBody Testsdiachi testsdiachi) throws URISyntaxException {
        log.debug("REST request to save Testsdiachi : {}", testsdiachi);
        if (testsdiachi.getId() != null) {
            throw new BadRequestAlertException("A new testsdiachi cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Testsdiachi result = testsdiachiRepository.save(testsdiachi);
        return ResponseEntity.created(new URI("/api/testsdiachis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /testsdiachis} : Updates an existing testsdiachi.
     *
     * @param testsdiachi the testsdiachi to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated testsdiachi,
     * or with status {@code 400 (Bad Request)} if the testsdiachi is not valid,
     * or with status {@code 500 (Internal Server Error)} if the testsdiachi couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/testsdiachis")
    public ResponseEntity<Testsdiachi> updateTestsdiachi(@RequestBody Testsdiachi testsdiachi) throws URISyntaxException {
        log.debug("REST request to update Testsdiachi : {}", testsdiachi);
        if (testsdiachi.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Testsdiachi result = testsdiachiRepository.save(testsdiachi);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, testsdiachi.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /testsdiachis} : get all the testsdiachis.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of testsdiachis in body.
     */
    @GetMapping("/testsdiachis")
    public ResponseEntity<List<Testsdiachi>> getAllTestsdiachis(Pageable pageable) {
        log.debug("REST request to get a page of Testsdiachis");
        Page<Testsdiachi> page = testsdiachiRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /testsdiachis/:id} : get the "id" testsdiachi.
     *
     * @param id the id of the testsdiachi to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the testsdiachi, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/testsdiachis/{id}")
    public ResponseEntity<Testsdiachi> getTestsdiachi(@PathVariable Long id) {
        log.debug("REST request to get Testsdiachi : {}", id);
        Optional<Testsdiachi> testsdiachi = testsdiachiRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(testsdiachi);
    }

    /**
     * {@code DELETE  /testsdiachis/:id} : delete the "id" testsdiachi.
     *
     * @param id the id of the testsdiachi to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/testsdiachis/{id}")
    public ResponseEntity<Void> deleteTestsdiachi(@PathVariable Long id) {
        log.debug("REST request to delete Testsdiachi : {}", id);
        testsdiachiRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
