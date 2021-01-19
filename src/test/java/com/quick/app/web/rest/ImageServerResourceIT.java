package com.quick.app.web.rest;

import com.quick.app.QuicklyappApp;
import com.quick.app.domain.ImageServer;
import com.quick.app.repository.ImageServerRepository;

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
 * Integration tests for the {@link ImageServerResource} REST controller.
 */
@SpringBootTest(classes = QuicklyappApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ImageServerResourceIT {

    private static final byte[] DEFAULT_IMAGE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_IMAGE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGE_CONTENT_TYPE = "image/png";

    @Autowired
    private ImageServerRepository imageServerRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restImageServerMockMvc;

    private ImageServer imageServer;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ImageServer createEntity(EntityManager em) {
        ImageServer imageServer = new ImageServer()
            .image(DEFAULT_IMAGE)
            .imageContentType(DEFAULT_IMAGE_CONTENT_TYPE);
        return imageServer;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ImageServer createUpdatedEntity(EntityManager em) {
        ImageServer imageServer = new ImageServer()
            .image(UPDATED_IMAGE)
            .imageContentType(UPDATED_IMAGE_CONTENT_TYPE);
        return imageServer;
    }

    @BeforeEach
    public void initTest() {
        imageServer = createEntity(em);
    }

    @Test
    @Transactional
    public void createImageServer() throws Exception {
        int databaseSizeBeforeCreate = imageServerRepository.findAll().size();
        // Create the ImageServer
        restImageServerMockMvc.perform(post("/api/image-servers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(imageServer)))
            .andExpect(status().isCreated());

        // Validate the ImageServer in the database
        List<ImageServer> imageServerList = imageServerRepository.findAll();
        assertThat(imageServerList).hasSize(databaseSizeBeforeCreate + 1);
        ImageServer testImageServer = imageServerList.get(imageServerList.size() - 1);
        assertThat(testImageServer.getImage()).isEqualTo(DEFAULT_IMAGE);
        assertThat(testImageServer.getImageContentType()).isEqualTo(DEFAULT_IMAGE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createImageServerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = imageServerRepository.findAll().size();

        // Create the ImageServer with an existing ID
        imageServer.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restImageServerMockMvc.perform(post("/api/image-servers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(imageServer)))
            .andExpect(status().isBadRequest());

        // Validate the ImageServer in the database
        List<ImageServer> imageServerList = imageServerRepository.findAll();
        assertThat(imageServerList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllImageServers() throws Exception {
        // Initialize the database
        imageServerRepository.saveAndFlush(imageServer);

        // Get all the imageServerList
        restImageServerMockMvc.perform(get("/api/image-servers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(imageServer.getId().intValue())))
            .andExpect(jsonPath("$.[*].imageContentType").value(hasItem(DEFAULT_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].image").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGE))));
    }
    
    @Test
    @Transactional
    public void getImageServer() throws Exception {
        // Initialize the database
        imageServerRepository.saveAndFlush(imageServer);

        // Get the imageServer
        restImageServerMockMvc.perform(get("/api/image-servers/{id}", imageServer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(imageServer.getId().intValue()))
            .andExpect(jsonPath("$.imageContentType").value(DEFAULT_IMAGE_CONTENT_TYPE))
            .andExpect(jsonPath("$.image").value(Base64Utils.encodeToString(DEFAULT_IMAGE)));
    }
    @Test
    @Transactional
    public void getNonExistingImageServer() throws Exception {
        // Get the imageServer
        restImageServerMockMvc.perform(get("/api/image-servers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateImageServer() throws Exception {
        // Initialize the database
        imageServerRepository.saveAndFlush(imageServer);

        int databaseSizeBeforeUpdate = imageServerRepository.findAll().size();

        // Update the imageServer
        ImageServer updatedImageServer = imageServerRepository.findById(imageServer.getId()).get();
        // Disconnect from session so that the updates on updatedImageServer are not directly saved in db
        em.detach(updatedImageServer);
        updatedImageServer
            .image(UPDATED_IMAGE)
            .imageContentType(UPDATED_IMAGE_CONTENT_TYPE);

        restImageServerMockMvc.perform(put("/api/image-servers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedImageServer)))
            .andExpect(status().isOk());

        // Validate the ImageServer in the database
        List<ImageServer> imageServerList = imageServerRepository.findAll();
        assertThat(imageServerList).hasSize(databaseSizeBeforeUpdate);
        ImageServer testImageServer = imageServerList.get(imageServerList.size() - 1);
        assertThat(testImageServer.getImage()).isEqualTo(UPDATED_IMAGE);
        assertThat(testImageServer.getImageContentType()).isEqualTo(UPDATED_IMAGE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingImageServer() throws Exception {
        int databaseSizeBeforeUpdate = imageServerRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restImageServerMockMvc.perform(put("/api/image-servers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(imageServer)))
            .andExpect(status().isBadRequest());

        // Validate the ImageServer in the database
        List<ImageServer> imageServerList = imageServerRepository.findAll();
        assertThat(imageServerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteImageServer() throws Exception {
        // Initialize the database
        imageServerRepository.saveAndFlush(imageServer);

        int databaseSizeBeforeDelete = imageServerRepository.findAll().size();

        // Delete the imageServer
        restImageServerMockMvc.perform(delete("/api/image-servers/{id}", imageServer.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ImageServer> imageServerList = imageServerRepository.findAll();
        assertThat(imageServerList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
