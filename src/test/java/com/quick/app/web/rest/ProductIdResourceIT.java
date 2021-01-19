package com.quick.app.web.rest;

import com.quick.app.QuicklyappApp;
import com.quick.app.domain.ProductId;
import com.quick.app.repository.ProductIdRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ProductIdResource} REST controller.
 */
@SpringBootTest(classes = QuicklyappApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ProductIdResourceIT {

    private static final UUID DEFAULT_UUID = UUID.randomUUID();
    private static final UUID UPDATED_UUID = UUID.randomUUID();

    @Autowired
    private ProductIdRepository productIdRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProductIdMockMvc;

    private ProductId productId;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProductId createEntity(EntityManager em) {
        ProductId productId = new ProductId()
            .uuid(DEFAULT_UUID);
        return productId;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProductId createUpdatedEntity(EntityManager em) {
        ProductId productId = new ProductId()
            .uuid(UPDATED_UUID);
        return productId;
    }

    @BeforeEach
    public void initTest() {
        productId = createEntity(em);
    }

    @Test
    @Transactional
    public void createProductId() throws Exception {
        int databaseSizeBeforeCreate = productIdRepository.findAll().size();
        // Create the ProductId
        restProductIdMockMvc.perform(post("/api/product-ids")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productId)))
            .andExpect(status().isCreated());

        // Validate the ProductId in the database
        List<ProductId> productIdList = productIdRepository.findAll();
        assertThat(productIdList).hasSize(databaseSizeBeforeCreate + 1);
        ProductId testProductId = productIdList.get(productIdList.size() - 1);
        assertThat(testProductId.getUuid()).isEqualTo(DEFAULT_UUID);
    }

    @Test
    @Transactional
    public void createProductIdWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = productIdRepository.findAll().size();

        // Create the ProductId with an existing ID
        productId.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProductIdMockMvc.perform(post("/api/product-ids")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productId)))
            .andExpect(status().isBadRequest());

        // Validate the ProductId in the database
        List<ProductId> productIdList = productIdRepository.findAll();
        assertThat(productIdList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllProductIds() throws Exception {
        // Initialize the database
        productIdRepository.saveAndFlush(productId);

        // Get all the productIdList
        restProductIdMockMvc.perform(get("/api/product-ids?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(productId.getId().intValue())))
            .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())));
    }
    
    @Test
    @Transactional
    public void getProductId() throws Exception {
        // Initialize the database
        productIdRepository.saveAndFlush(productId);

        // Get the productId
        restProductIdMockMvc.perform(get("/api/product-ids/{id}", productId.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(productId.getId().intValue()))
            .andExpect(jsonPath("$.uuid").value(DEFAULT_UUID.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingProductId() throws Exception {
        // Get the productId
        restProductIdMockMvc.perform(get("/api/product-ids/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProductId() throws Exception {
        // Initialize the database
        productIdRepository.saveAndFlush(productId);

        int databaseSizeBeforeUpdate = productIdRepository.findAll().size();

        // Update the productId
        ProductId updatedProductId = productIdRepository.findById(productId.getId()).get();
        // Disconnect from session so that the updates on updatedProductId are not directly saved in db
        em.detach(updatedProductId);
        updatedProductId
            .uuid(UPDATED_UUID);

        restProductIdMockMvc.perform(put("/api/product-ids")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedProductId)))
            .andExpect(status().isOk());

        // Validate the ProductId in the database
        List<ProductId> productIdList = productIdRepository.findAll();
        assertThat(productIdList).hasSize(databaseSizeBeforeUpdate);
        ProductId testProductId = productIdList.get(productIdList.size() - 1);
        assertThat(testProductId.getUuid()).isEqualTo(UPDATED_UUID);
    }

    @Test
    @Transactional
    public void updateNonExistingProductId() throws Exception {
        int databaseSizeBeforeUpdate = productIdRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProductIdMockMvc.perform(put("/api/product-ids")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productId)))
            .andExpect(status().isBadRequest());

        // Validate the ProductId in the database
        List<ProductId> productIdList = productIdRepository.findAll();
        assertThat(productIdList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProductId() throws Exception {
        // Initialize the database
        productIdRepository.saveAndFlush(productId);

        int databaseSizeBeforeDelete = productIdRepository.findAll().size();

        // Delete the productId
        restProductIdMockMvc.perform(delete("/api/product-ids/{id}", productId.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProductId> productIdList = productIdRepository.findAll();
        assertThat(productIdList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
