package com.quick.app.web.rest;

import com.quick.app.QuicklyappApp;
import com.quick.app.domain.Productest;
import com.quick.app.repository.ProductestRepository;

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

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ProductestResource} REST controller.
 */
@SpringBootTest(classes = QuicklyappApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ProductestResourceIT {

    private static final String DEFAULT_TENSANPHAM = "AAAAAAAAAA";
    private static final String UPDATED_TENSANPHAM = "BBBBBBBBBB";

    @Autowired
    private ProductestRepository productestRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProductestMockMvc;

    private Productest productest;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Productest createEntity(EntityManager em) {
        Productest productest = new Productest()
            .tensanpham(DEFAULT_TENSANPHAM);
        return productest;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Productest createUpdatedEntity(EntityManager em) {
        Productest productest = new Productest()
            .tensanpham(UPDATED_TENSANPHAM);
        return productest;
    }

    @BeforeEach
    public void initTest() {
        productest = createEntity(em);
    }

    @Test
    @Transactional
    public void createProductest() throws Exception {
        int databaseSizeBeforeCreate = productestRepository.findAll().size();
        // Create the Productest
        restProductestMockMvc.perform(post("/api/productests")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productest)))
            .andExpect(status().isCreated());

        // Validate the Productest in the database
        List<Productest> productestList = productestRepository.findAll();
        assertThat(productestList).hasSize(databaseSizeBeforeCreate + 1);
        Productest testProductest = productestList.get(productestList.size() - 1);
        assertThat(testProductest.getTensanpham()).isEqualTo(DEFAULT_TENSANPHAM);
    }

    @Test
    @Transactional
    public void createProductestWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = productestRepository.findAll().size();

        // Create the Productest with an existing ID
        productest.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProductestMockMvc.perform(post("/api/productests")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productest)))
            .andExpect(status().isBadRequest());

        // Validate the Productest in the database
        List<Productest> productestList = productestRepository.findAll();
        assertThat(productestList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllProductests() throws Exception {
        // Initialize the database
        productestRepository.saveAndFlush(productest);

        // Get all the productestList
        restProductestMockMvc.perform(get("/api/productests?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(productest.getId().intValue())))
            .andExpect(jsonPath("$.[*].tensanpham").value(hasItem(DEFAULT_TENSANPHAM)));
    }
    
    @Test
    @Transactional
    public void getProductest() throws Exception {
        // Initialize the database
        productestRepository.saveAndFlush(productest);

        // Get the productest
        restProductestMockMvc.perform(get("/api/productests/{id}", productest.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(productest.getId().intValue()))
            .andExpect(jsonPath("$.tensanpham").value(DEFAULT_TENSANPHAM));
    }
    @Test
    @Transactional
    public void getNonExistingProductest() throws Exception {
        // Get the productest
        restProductestMockMvc.perform(get("/api/productests/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProductest() throws Exception {
        // Initialize the database
        productestRepository.saveAndFlush(productest);

        int databaseSizeBeforeUpdate = productestRepository.findAll().size();

        // Update the productest
        Productest updatedProductest = productestRepository.findById(productest.getId()).get();
        // Disconnect from session so that the updates on updatedProductest are not directly saved in db
        em.detach(updatedProductest);
        updatedProductest
            .tensanpham(UPDATED_TENSANPHAM);

        restProductestMockMvc.perform(put("/api/productests")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedProductest)))
            .andExpect(status().isOk());

        // Validate the Productest in the database
        List<Productest> productestList = productestRepository.findAll();
        assertThat(productestList).hasSize(databaseSizeBeforeUpdate);
        Productest testProductest = productestList.get(productestList.size() - 1);
        assertThat(testProductest.getTensanpham()).isEqualTo(UPDATED_TENSANPHAM);
    }

    @Test
    @Transactional
    public void updateNonExistingProductest() throws Exception {
        int databaseSizeBeforeUpdate = productestRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProductestMockMvc.perform(put("/api/productests")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productest)))
            .andExpect(status().isBadRequest());

        // Validate the Productest in the database
        List<Productest> productestList = productestRepository.findAll();
        assertThat(productestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProductest() throws Exception {
        // Initialize the database
        productestRepository.saveAndFlush(productest);

        int databaseSizeBeforeDelete = productestRepository.findAll().size();

        // Delete the productest
        restProductestMockMvc.perform(delete("/api/productests/{id}", productest.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Productest> productestList = productestRepository.findAll();
        assertThat(productestList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
