package com.quick.app.web.rest;

import com.quick.app.QuicklyappApp;
import com.quick.app.domain.Fileanh;
import com.quick.app.repository.FileanhRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link FileanhResource} REST controller.
 */
@SpringBootTest(classes = QuicklyappApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class FileanhResourceIT {

    private static final byte[] DEFAULT_IMAGE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_IMAGE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGE_CONTENT_TYPE = "image/png";

    private static final UUID DEFAULT_UUID = UUID.randomUUID();
    private static final UUID UPDATED_UUID = UUID.randomUUID();

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private FileanhRepository fileanhRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFileanhMockMvc;

    private Fileanh fileanh;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Fileanh createEntity(EntityManager em) {
        Fileanh fileanh = new Fileanh()
            .image(DEFAULT_IMAGE)
            .imageContentType(DEFAULT_IMAGE_CONTENT_TYPE)
            .uuid(DEFAULT_UUID)
            .name(DEFAULT_NAME);
        return fileanh;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Fileanh createUpdatedEntity(EntityManager em) {
        Fileanh fileanh = new Fileanh()
            .image(UPDATED_IMAGE)
            .imageContentType(UPDATED_IMAGE_CONTENT_TYPE)
            .uuid(UPDATED_UUID)
            .name(UPDATED_NAME);
        return fileanh;
    }

    @BeforeEach
    public void initTest() {
        fileanh = createEntity(em);
    }

    @Test
    @Transactional
    public void createFileanh() throws Exception {
        int databaseSizeBeforeCreate = fileanhRepository.findAll().size();
        // Create the Fileanh
        restFileanhMockMvc.perform(post("/api/fileanhs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fileanh)))
            .andExpect(status().isCreated());

        // Validate the Fileanh in the database
        List<Fileanh> fileanhList = fileanhRepository.findAll();
        assertThat(fileanhList).hasSize(databaseSizeBeforeCreate + 1);
        Fileanh testFileanh = fileanhList.get(fileanhList.size() - 1);
        assertThat(testFileanh.getImage()).isEqualTo(DEFAULT_IMAGE);
        assertThat(testFileanh.getImageContentType()).isEqualTo(DEFAULT_IMAGE_CONTENT_TYPE);
        assertThat(testFileanh.getUuid()).isEqualTo(DEFAULT_UUID);
        assertThat(testFileanh.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createFileanhWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fileanhRepository.findAll().size();

        // Create the Fileanh with an existing ID
        fileanh.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFileanhMockMvc.perform(post("/api/fileanhs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fileanh)))
            .andExpect(status().isBadRequest());

        // Validate the Fileanh in the database
        List<Fileanh> fileanhList = fileanhRepository.findAll();
        assertThat(fileanhList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllFileanhs() throws Exception {
        // Initialize the database
        fileanhRepository.saveAndFlush(fileanh);

        // Get all the fileanhList
        restFileanhMockMvc.perform(get("/api/fileanhs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fileanh.getId().intValue())))
            .andExpect(jsonPath("$.[*].imageContentType").value(hasItem(DEFAULT_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].image").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGE))))
            .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }
    
    @Test
    @Transactional
    public void getFileanh() throws Exception {
        // Initialize the database
        fileanhRepository.saveAndFlush(fileanh);

        // Get the fileanh
        restFileanhMockMvc.perform(get("/api/fileanhs/{id}", fileanh.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(fileanh.getId().intValue()))
            .andExpect(jsonPath("$.imageContentType").value(DEFAULT_IMAGE_CONTENT_TYPE))
            .andExpect(jsonPath("$.image").value(Base64Utils.encodeToString(DEFAULT_IMAGE)))
            .andExpect(jsonPath("$.uuid").value(DEFAULT_UUID.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }
    @Test
    @Transactional
    public void getNonExistingFileanh() throws Exception {
        // Get the fileanh
        restFileanhMockMvc.perform(get("/api/fileanhs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFileanh() throws Exception {
        // Initialize the database
        fileanhRepository.saveAndFlush(fileanh);

        int databaseSizeBeforeUpdate = fileanhRepository.findAll().size();

        // Update the fileanh
        Fileanh updatedFileanh = fileanhRepository.findById(fileanh.getId()).get();
        // Disconnect from session so that the updates on updatedFileanh are not directly saved in db
        em.detach(updatedFileanh);
        updatedFileanh
            .image(UPDATED_IMAGE)
            .imageContentType(UPDATED_IMAGE_CONTENT_TYPE)
            .uuid(UPDATED_UUID)
            .name(UPDATED_NAME);

        restFileanhMockMvc.perform(put("/api/fileanhs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedFileanh)))
            .andExpect(status().isOk());

        // Validate the Fileanh in the database
        List<Fileanh> fileanhList = fileanhRepository.findAll();
        assertThat(fileanhList).hasSize(databaseSizeBeforeUpdate);
        Fileanh testFileanh = fileanhList.get(fileanhList.size() - 1);
        assertThat(testFileanh.getImage()).isEqualTo(UPDATED_IMAGE);
        assertThat(testFileanh.getImageContentType()).isEqualTo(UPDATED_IMAGE_CONTENT_TYPE);
        assertThat(testFileanh.getUuid()).isEqualTo(UPDATED_UUID);
        assertThat(testFileanh.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingFileanh() throws Exception {
        int databaseSizeBeforeUpdate = fileanhRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFileanhMockMvc.perform(put("/api/fileanhs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fileanh)))
            .andExpect(status().isBadRequest());

        // Validate the Fileanh in the database
        List<Fileanh> fileanhList = fileanhRepository.findAll();
        assertThat(fileanhList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFileanh() throws Exception {
        // Initialize the database
        fileanhRepository.saveAndFlush(fileanh);

        int databaseSizeBeforeDelete = fileanhRepository.findAll().size();

        // Delete the fileanh
        restFileanhMockMvc.perform(delete("/api/fileanhs/{id}", fileanh.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Fileanh> fileanhList = fileanhRepository.findAll();
        assertThat(fileanhList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
