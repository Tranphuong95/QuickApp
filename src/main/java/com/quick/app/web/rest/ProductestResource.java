package com.quick.app.web.rest;

import com.quick.app.domain.Productest;
import com.quick.app.repository.ProductestRepository;
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
 * REST controller for managing {@link com.quick.app.domain.Productest}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ProductestResource {

    private final Logger log = LoggerFactory.getLogger(ProductestResource.class);

    private static final String ENTITY_NAME = "productest";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProductestRepository productestRepository;

    public ProductestResource(ProductestRepository productestRepository) {
        this.productestRepository = productestRepository;
    }

    /**
     * {@code POST  /productests} : Create a new productest.
     *
     * @param productest the productest to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new productest, or with status {@code 400 (Bad Request)} if the productest has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/productests")
    public ResponseEntity<Productest> createProductest(@RequestBody Productest productest) throws URISyntaxException {
        log.debug("REST request to save Productest : {}", productest);
        if (productest.getId() != null) {
            throw new BadRequestAlertException("A new productest cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Productest result = productestRepository.save(productest);
        return ResponseEntity.created(new URI("/api/productests/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /productests} : Updates an existing productest.
     *
     * @param productest the productest to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated productest,
     * or with status {@code 400 (Bad Request)} if the productest is not valid,
     * or with status {@code 500 (Internal Server Error)} if the productest couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/productests")
    public ResponseEntity<Productest> updateProductest(@RequestBody Productest productest) throws URISyntaxException {
        log.debug("REST request to update Productest : {}", productest);
        if (productest.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Productest result = productestRepository.save(productest);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, productest.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /productests} : get all the productests.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of productests in body.
     */
    @GetMapping("/productests")
    public List<Productest> getAllProductests() {
        log.debug("REST request to get all Productests");
        return productestRepository.findAll();
    }

    /**
     * {@code GET  /productests/:id} : get the "id" productest.
     *
     * @param id the id of the productest to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the productest, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/productests/{id}")
    public ResponseEntity<Productest> getProductest(@PathVariable Long id) {
        log.debug("REST request to get Productest : {}", id);
        Optional<Productest> productest = productestRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(productest);
    }

    /**
     * {@code DELETE  /productests/:id} : delete the "id" productest.
     *
     * @param id the id of the productest to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/productests/{id}")
    public ResponseEntity<Void> deleteProductest(@PathVariable Long id) {
        log.debug("REST request to delete Productest : {}", id);
        productestRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
