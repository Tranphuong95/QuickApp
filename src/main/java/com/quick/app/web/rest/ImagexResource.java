package com.quick.app.web.rest;

import com.quick.app.domain.Imagex;
import com.quick.app.repository.ImagexRepository;
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
 * REST controller for managing {@link com.quick.app.domain.Imagex}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ImagexResource {

    private final Logger log = LoggerFactory.getLogger(ImagexResource.class);

    private static final String ENTITY_NAME = "imagex";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ImagexRepository imagexRepository;

    public ImagexResource(ImagexRepository imagexRepository) {
        this.imagexRepository = imagexRepository;
    }

    /**
     * {@code POST  /imagexes} : Create a new imagex.
     *
     * @param imagex the imagex to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new imagex, or with status {@code 400 (Bad Request)} if the imagex has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/imagexes")
    public ResponseEntity<Imagex> createImagex(@RequestBody Imagex imagex) throws URISyntaxException {
        log.debug("REST request to save Imagex : {}", imagex);
        if (imagex.getId() != null) {
            throw new BadRequestAlertException("A new imagex cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Imagex result = imagexRepository.save(imagex);
        return ResponseEntity.created(new URI("/api/imagexes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /imagexes} : Updates an existing imagex.
     *
     * @param imagex the imagex to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated imagex,
     * or with status {@code 400 (Bad Request)} if the imagex is not valid,
     * or with status {@code 500 (Internal Server Error)} if the imagex couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/imagexes")
    public ResponseEntity<Imagex> updateImagex(@RequestBody Imagex imagex) throws URISyntaxException {
        log.debug("REST request to update Imagex : {}", imagex);
        if (imagex.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Imagex result = imagexRepository.save(imagex);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, imagex.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /imagexes} : get all the imagexes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of imagexes in body.
     */
    @GetMapping("/imagexes")
    public List<Imagex> getAllImagexes() {
        log.debug("REST request to get all Imagexes");
        return imagexRepository.findAll();
    }

    /**
     * {@code GET  /imagexes/:id} : get the "id" imagex.
     *
     * @param id the id of the imagex to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the imagex, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/imagexes/{id}")
    public ResponseEntity<Imagex> getImagex(@PathVariable Long id) {
        log.debug("REST request to get Imagex : {}", id);
        Optional<Imagex> imagex = imagexRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(imagex);
    }

    /**
     * {@code DELETE  /imagexes/:id} : delete the "id" imagex.
     *
     * @param id the id of the imagex to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/imagexes/{id}")
    public ResponseEntity<Void> deleteImagex(@PathVariable Long id) {
        log.debug("REST request to delete Imagex : {}", id);
        imagexRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
