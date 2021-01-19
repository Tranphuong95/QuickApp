package com.quick.app.web.rest;

import com.quick.app.QuicklyappApp;
import com.quick.app.domain.Testuuid;
import com.quick.app.repository.TestuuidRepository;

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
 * Integration tests for the {@link TestuuidResource} REST controller.
 */
@SpringBootTest(classes = QuicklyappApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TestuuidResourceIT {

    @Autowired
    private TestuuidRepository testuuidRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTestuuidMockMvc;

    private Testuuid testuuid;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Testuuid createEntity(EntityManager em) {
        Testuuid testuuid = new Testuuid();
        return testuuid;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Testuuid createUpdatedEntity(EntityManager em) {
        Testuuid testuuid = new Testuuid();
        return testuuid;
    }

    @BeforeEach
    public void initTest() {
        testuuid = createEntity(em);
    }

    @Test
    @Transactional
    public void createTestuuid() throws Exception {
        int databaseSizeBeforeCreate = testuuidRepository.findAll().size();
        // Create the Testuuid
        restTestuuidMockMvc.perform(post("/api/testuuids")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(testuuid)))
            .andExpect(status().isCreated());

        // Validate the Testuuid in the database
        List<Testuuid> testuuidList = testuuidRepository.findAll();
        assertThat(testuuidList).hasSize(databaseSizeBeforeCreate + 1);
        Testuuid testTestuuid = testuuidList.get(testuuidList.size() - 1);
    }

    @Test
    @Transactional
    public void createTestuuidWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = testuuidRepository.findAll().size();

        // Create the Testuuid with an existing ID
        testuuid.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTestuuidMockMvc.perform(post("/api/testuuids")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(testuuid)))
            .andExpect(status().isBadRequest());

        // Validate the Testuuid in the database
        List<Testuuid> testuuidList = testuuidRepository.findAll();
        assertThat(testuuidList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTestuuids() throws Exception {
        // Initialize the database
        testuuidRepository.saveAndFlush(testuuid);

        // Get all the testuuidList
        restTestuuidMockMvc.perform(get("/api/testuuids?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(testuuid.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getTestuuid() throws Exception {
        // Initialize the database
        testuuidRepository.saveAndFlush(testuuid);

        // Get the testuuid
        restTestuuidMockMvc.perform(get("/api/testuuids/{id}", testuuid.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(testuuid.getId().intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingTestuuid() throws Exception {
        // Get the testuuid
        restTestuuidMockMvc.perform(get("/api/testuuids/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTestuuid() throws Exception {
        // Initialize the database
        testuuidRepository.saveAndFlush(testuuid);

        int databaseSizeBeforeUpdate = testuuidRepository.findAll().size();

        // Update the testuuid
        Testuuid updatedTestuuid = testuuidRepository.findById(testuuid.getId()).get();
        // Disconnect from session so that the updates on updatedTestuuid are not directly saved in db
        em.detach(updatedTestuuid);

        restTestuuidMockMvc.perform(put("/api/testuuids")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTestuuid)))
            .andExpect(status().isOk());

        // Validate the Testuuid in the database
        List<Testuuid> testuuidList = testuuidRepository.findAll();
        assertThat(testuuidList).hasSize(databaseSizeBeforeUpdate);
        Testuuid testTestuuid = testuuidList.get(testuuidList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingTestuuid() throws Exception {
        int databaseSizeBeforeUpdate = testuuidRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTestuuidMockMvc.perform(put("/api/testuuids")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(testuuid)))
            .andExpect(status().isBadRequest());

        // Validate the Testuuid in the database
        List<Testuuid> testuuidList = testuuidRepository.findAll();
        assertThat(testuuidList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTestuuid() throws Exception {
        // Initialize the database
        testuuidRepository.saveAndFlush(testuuid);

        int databaseSizeBeforeDelete = testuuidRepository.findAll().size();

        // Delete the testuuid
        restTestuuidMockMvc.perform(delete("/api/testuuids/{id}", testuuid.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Testuuid> testuuidList = testuuidRepository.findAll();
        assertThat(testuuidList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
