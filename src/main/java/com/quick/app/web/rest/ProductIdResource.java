package com.quick.app.web.rest;

import com.quick.app.domain.ProductId;
import com.quick.app.repository.ProductIdRepository;
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
 * REST controller for managing {@link com.quick.app.domain.ProductId}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ProductIdResource {

    private final Logger log = LoggerFactory.getLogger(ProductIdResource.class);

    private static final String ENTITY_NAME = "productId";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProductIdRepository productIdRepository;

    public ProductIdResource(ProductIdRepository productIdRepository) {
        this.productIdRepository = productIdRepository;
    }

    /**
     * {@code POST  /product-ids} : Create a new productId.
     *
     * @param productId the productId to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new productId, or with status {@code 400 (Bad Request)} if the productId has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/product-ids")
    public ResponseEntity<ProductId> createProductId(@RequestBody ProductId productId) throws URISyntaxException {
        log.debug("REST request to save ProductId : {}", productId);
        if (productId.getId() != null) {
            throw new BadRequestAlertException("A new productId cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProductId result = productIdRepository.save(productId);
        return ResponseEntity.created(new URI("/api/product-ids/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /product-ids} : Updates an existing productId.
     *
     * @param productId the productId to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated productId,
     * or with status {@code 400 (Bad Request)} if the productId is not valid,
     * or with status {@code 500 (Internal Server Error)} if the productId couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/product-ids")
    public ResponseEntity<ProductId> updateProductId(@RequestBody ProductId productId) throws URISyntaxException {
        log.debug("REST request to update ProductId : {}", productId);
        if (productId.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProductId result = productIdRepository.save(productId);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, productId.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /product-ids} : get all the productIds.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of productIds in body.
     */
    @GetMapping("/product-ids")
    public List<ProductId> getAllProductIds() {
        log.debug("REST request to get all ProductIds");
        return productIdRepository.findAll();
    }

    /**
     * {@code GET  /product-ids/:id} : get the "id" productId.
     *
     * @param id the id of the productId to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the productId, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/product-ids/{id}")
    public ResponseEntity<ProductId> getProductId(@PathVariable Long id) {
        log.debug("REST request to get ProductId : {}", id);
        Optional<ProductId> productId = productIdRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(productId);
    }

    /**
     * {@code DELETE  /product-ids/:id} : delete the "id" productId.
     *
     * @param id the id of the productId to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/product-ids/{id}")
    public ResponseEntity<Void> deleteProductId(@PathVariable Long id) {
        log.debug("REST request to delete ProductId : {}", id);
        productIdRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
