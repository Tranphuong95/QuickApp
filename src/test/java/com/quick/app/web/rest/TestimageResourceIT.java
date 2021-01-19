package com.quick.app.web.rest;

import com.quick.app.QuicklyappApp;
import com.quick.app.domain.Testimage;
import com.quick.app.repository.TestimageRepository;

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

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link TestimageResource} REST controller.
 */
@SpringBootTest(classes = QuicklyappApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TestimageResourceIT {

    private static final byte[] DEFAULT_IMAGE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_IMAGE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGE_CONTENT_TYPE = "image/png";

    @Autowired
    private TestimageRepository testimageRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTestimageMockMvc;

    private Testimage testimage;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Testimage createEntity(EntityManager em) {
        Testimage testimage = new Testimage()
            .image(DEFAULT_IMAGE)
            .imageContentType(DEFAULT_IMAGE_CONTENT_TYPE);
        return testimage;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Testimage createUpdatedEntity(EntityManager em) {
        Testimage testimage = new Testimage()
            .image(UPDATED_IMAGE)
            .imageContentType(UPDATED_IMAGE_CONTENT_TYPE);
        return testimage;
    }

    @BeforeEach
    public void initTest() {
        testimage = createEntity(em);
    }

    @Test
    @Transactional
    public void createTestimage() throws Exception {
        int databaseSizeBeforeCreate = testimageRepository.findAll().size();
        // Create the Testimage
        restTestimageMockMvc.perform(post("/api/testimages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(testimage)))
            .andExpect(status().isCreated());

        // Validate the Testimage in the database
        List<Testimage> testimageList = testimageRepository.findAll();
        assertThat(testimageList).hasSize(databaseSizeBeforeCreate + 1);
        Testimage testTestimage = testimageList.get(testimageList.size() - 1);
        assertThat(testTestimage.getImage()).isEqualTo(DEFAULT_IMAGE);
        assertThat(testTestimage.getImageContentType()).isEqualTo(DEFAULT_IMAGE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createTestimageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = testimageRepository.findAll().size();

        // Create the Testimage with an existing ID
        testimage.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTestimageMockMvc.perform(post("/api/testimages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(testimage)))
            .andExpect(status().isBadRequest());

        // Validate the Testimage in the database
        List<Testimage> testimageList = testimageRepository.findAll();
        assertThat(testimageList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTestimages() throws Exception {
        // Initialize the database
        testimageRepository.saveAndFlush(testimage);

        // Get all the testimageList
        restTestimageMockMvc.perform(get("/api/testimages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(testimage.getId().intValue())))
            .andExpect(jsonPath("$.[*].imageContentType").value(hasItem(DEFAULT_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].image").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGE))));
    }
    
    @Test
    @Transactional
    public void getTestimage() throws Exception {
        // Initialize the database
        testimageRepository.saveAndFlush(testimage);

        // Get the testimage
        restTestimageMockMvc.perform(get("/api/testimages/{id}", testimage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(testimage.getId().intValue()))
            .andExpect(jsonPath("$.imageContentType").value(DEFAULT_IMAGE_CONTENT_TYPE))
            .andExpect(jsonPath("$.image").value(Base64Utils.encodeToString(DEFAULT_IMAGE)));
    }
    @Test
    @Transactional
    public void getNonExistingTestimage() throws Exception {
        // Get the testimage
        restTestimageMockMvc.perform(get("/api/testimages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTestimage() throws Exception {
        // Initialize the database
        testimageRepository.saveAndFlush(testimage);

        int databaseSizeBeforeUpdate = testimageRepository.findAll().size();

        // Update the testimage
        Testimage updatedTestimage = testimageRepository.findById(testimage.getId()).get();
        // Disconnect from session so that the updates on updatedTestimage are not directly saved in db
        em.detach(updatedTestimage);
        updatedTestimage
            .image(UPDATED_IMAGE)
            .imageContentType(UPDATED_IMAGE_CONTENT_TYPE);

        restTestimageMockMvc.perform(put("/api/testimages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTestimage)))
            .andExpect(status().isOk());

        // Validate the Testimage in the database
        List<Testimage> testimageList = testimageRepository.findAll();
        assertThat(testimageList).hasSize(databaseSizeBeforeUpdate);
        Testimage testTestimage = testimageList.get(testimageList.size() - 1);
        assertThat(testTestimage.getImage()).isEqualTo(UPDATED_IMAGE);
        assertThat(testTestimage.getImageContentType()).isEqualTo(UPDATED_IMAGE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingTestimage() throws Exception {
        int databaseSizeBeforeUpdate = testimageRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTestimageMockMvc.perform(put("/api/testimages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(testimage)))
            .andExpect(status().isBadRequest());

        // Validate the Testimage in the database
        List<Testimage> testimageList = testimageRepository.findAll();
        assertThat(testimageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTestimage() throws Exception {
        // Initialize the database
        testimageRepository.saveAndFlush(testimage);

        int databaseSizeBeforeDelete = testimageRepository.findAll().size();

        // Delete the testimage
        restTestimageMockMvc.perform(delete("/api/testimages/{id}", testimage.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Testimage> testimageList = testimageRepository.findAll();
        assertThat(testimageList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
