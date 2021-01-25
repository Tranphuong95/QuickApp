package com.quick.app.web.rest;

import com.quick.app.QuicklyappApp;
import com.quick.app.domain.Imagex;
import com.quick.app.repository.ImagexRepository;

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
 * Integration tests for the {@link ImagexResource} REST controller.
 */
@SpringBootTest(classes = QuicklyappApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ImagexResourceIT {

    private static final byte[] DEFAULT_IMAGE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_IMAGE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGE_CONTENT_TYPE = "image/png";

    @Autowired
    private ImagexRepository imagexRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restImagexMockMvc;

    private Imagex imagex;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Imagex createEntity(EntityManager em) {
        Imagex imagex = new Imagex()
            .image(DEFAULT_IMAGE)
            .imageContentType(DEFAULT_IMAGE_CONTENT_TYPE);
        return imagex;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Imagex createUpdatedEntity(EntityManager em) {
        Imagex imagex = new Imagex()
            .image(UPDATED_IMAGE)
            .imageContentType(UPDATED_IMAGE_CONTENT_TYPE);
        return imagex;
    }

    @BeforeEach
    public void initTest() {
        imagex = createEntity(em);
    }

    @Test
    @Transactional
    public void createImagex() throws Exception {
        int databaseSizeBeforeCreate = imagexRepository.findAll().size();
        // Create the Imagex
        restImagexMockMvc.perform(post("/api/imagexes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(imagex)))
            .andExpect(status().isCreated());

        // Validate the Imagex in the database
        List<Imagex> imagexList = imagexRepository.findAll();
        assertThat(imagexList).hasSize(databaseSizeBeforeCreate + 1);
        Imagex testImagex = imagexList.get(imagexList.size() - 1);
        assertThat(testImagex.getImage()).isEqualTo(DEFAULT_IMAGE);
        assertThat(testImagex.getImageContentType()).isEqualTo(DEFAULT_IMAGE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createImagexWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = imagexRepository.findAll().size();

        // Create the Imagex with an existing ID
        imagex.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restImagexMockMvc.perform(post("/api/imagexes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(imagex)))
            .andExpect(status().isBadRequest());

        // Validate the Imagex in the database
        List<Imagex> imagexList = imagexRepository.findAll();
        assertThat(imagexList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllImagexes() throws Exception {
        // Initialize the database
        imagexRepository.saveAndFlush(imagex);

        // Get all the imagexList
        restImagexMockMvc.perform(get("/api/imagexes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(imagex.getId().intValue())))
            .andExpect(jsonPath("$.[*].imageContentType").value(hasItem(DEFAULT_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].image").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGE))));
    }
    
    @Test
    @Transactional
    public void getImagex() throws Exception {
        // Initialize the database
        imagexRepository.saveAndFlush(imagex);

        // Get the imagex
        restImagexMockMvc.perform(get("/api/imagexes/{id}", imagex.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(imagex.getId().intValue()))
            .andExpect(jsonPath("$.imageContentType").value(DEFAULT_IMAGE_CONTENT_TYPE))
            .andExpect(jsonPath("$.image").value(Base64Utils.encodeToString(DEFAULT_IMAGE)));
    }
    @Test
    @Transactional
    public void getNonExistingImagex() throws Exception {
        // Get the imagex
        restImagexMockMvc.perform(get("/api/imagexes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateImagex() throws Exception {
        // Initialize the database
        imagexRepository.saveAndFlush(imagex);

        int databaseSizeBeforeUpdate = imagexRepository.findAll().size();

        // Update the imagex
        Imagex updatedImagex = imagexRepository.findById(imagex.getId()).get();
        // Disconnect from session so that the updates on updatedImagex are not directly saved in db
        em.detach(updatedImagex);
        updatedImagex
            .image(UPDATED_IMAGE)
            .imageContentType(UPDATED_IMAGE_CONTENT_TYPE);

        restImagexMockMvc.perform(put("/api/imagexes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedImagex)))
            .andExpect(status().isOk());

        // Validate the Imagex in the database
        List<Imagex> imagexList = imagexRepository.findAll();
        assertThat(imagexList).hasSize(databaseSizeBeforeUpdate);
        Imagex testImagex = imagexList.get(imagexList.size() - 1);
        assertThat(testImagex.getImage()).isEqualTo(UPDATED_IMAGE);
        assertThat(testImagex.getImageContentType()).isEqualTo(UPDATED_IMAGE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingImagex() throws Exception {
        int databaseSizeBeforeUpdate = imagexRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restImagexMockMvc.perform(put("/api/imagexes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(imagex)))
            .andExpect(status().isBadRequest());

        // Validate the Imagex in the database
        List<Imagex> imagexList = imagexRepository.findAll();
        assertThat(imagexList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteImagex() throws Exception {
        // Initialize the database
        imagexRepository.saveAndFlush(imagex);

        int databaseSizeBeforeDelete = imagexRepository.findAll().size();

        // Delete the imagex
        restImagexMockMvc.perform(delete("/api/imagexes/{id}", imagex.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Imagex> imagexList = imagexRepository.findAll();
        assertThat(imagexList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
