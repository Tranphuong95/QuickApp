package com.quick.app.web.rest;

import com.quick.app.QuicklyappApp;
import com.quick.app.domain.Serveranh;
import com.quick.app.repository.ServeranhRepository;

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
 * Integration tests for the {@link ServeranhResource} REST controller.
 */
@SpringBootTest(classes = QuicklyappApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ServeranhResourceIT {

    private static final byte[] DEFAULT_IMAGE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_IMAGE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGE_CONTENT_TYPE = "image/png";

    private static final UUID DEFAULT_UUID = UUID.randomUUID();
    private static final UUID UPDATED_UUID = UUID.randomUUID();

    @Autowired
    private ServeranhRepository serveranhRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restServeranhMockMvc;

    private Serveranh serveranh;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Serveranh createEntity(EntityManager em) {
        Serveranh serveranh = new Serveranh()
            .image(DEFAULT_IMAGE)
            .imageContentType(DEFAULT_IMAGE_CONTENT_TYPE)
            .uuid(DEFAULT_UUID);
        return serveranh;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Serveranh createUpdatedEntity(EntityManager em) {
        Serveranh serveranh = new Serveranh()
            .image(UPDATED_IMAGE)
            .imageContentType(UPDATED_IMAGE_CONTENT_TYPE)
            .uuid(UPDATED_UUID);
        return serveranh;
    }

    @BeforeEach
    public void initTest() {
        serveranh = createEntity(em);
    }

    @Test
    @Transactional
    public void createServeranh() throws Exception {
        int databaseSizeBeforeCreate = serveranhRepository.findAll().size();
        // Create the Serveranh
        restServeranhMockMvc.perform(post("/api/serveranhs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(serveranh)))
            .andExpect(status().isCreated());

        // Validate the Serveranh in the database
        List<Serveranh> serveranhList = serveranhRepository.findAll();
        assertThat(serveranhList).hasSize(databaseSizeBeforeCreate + 1);
        Serveranh testServeranh = serveranhList.get(serveranhList.size() - 1);
        assertThat(testServeranh.getImage()).isEqualTo(DEFAULT_IMAGE);
        assertThat(testServeranh.getImageContentType()).isEqualTo(DEFAULT_IMAGE_CONTENT_TYPE);
        assertThat(testServeranh.getUuid()).isEqualTo(DEFAULT_UUID);
    }

    @Test
    @Transactional
    public void createServeranhWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = serveranhRepository.findAll().size();

        // Create the Serveranh with an existing ID
        serveranh.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restServeranhMockMvc.perform(post("/api/serveranhs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(serveranh)))
            .andExpect(status().isBadRequest());

        // Validate the Serveranh in the database
        List<Serveranh> serveranhList = serveranhRepository.findAll();
        assertThat(serveranhList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllServeranhs() throws Exception {
        // Initialize the database
        serveranhRepository.saveAndFlush(serveranh);

        // Get all the serveranhList
        restServeranhMockMvc.perform(get("/api/serveranhs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(serveranh.getId().intValue())))
            .andExpect(jsonPath("$.[*].imageContentType").value(hasItem(DEFAULT_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].image").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGE))))
            .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())));
    }
    
    @Test
    @Transactional
    public void getServeranh() throws Exception {
        // Initialize the database
        serveranhRepository.saveAndFlush(serveranh);

        // Get the serveranh
        restServeranhMockMvc.perform(get("/api/serveranhs/{id}", serveranh.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(serveranh.getId().intValue()))
            .andExpect(jsonPath("$.imageContentType").value(DEFAULT_IMAGE_CONTENT_TYPE))
            .andExpect(jsonPath("$.image").value(Base64Utils.encodeToString(DEFAULT_IMAGE)))
            .andExpect(jsonPath("$.uuid").value(DEFAULT_UUID.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingServeranh() throws Exception {
        // Get the serveranh
        restServeranhMockMvc.perform(get("/api/serveranhs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateServeranh() throws Exception {
        // Initialize the database
        serveranhRepository.saveAndFlush(serveranh);

        int databaseSizeBeforeUpdate = serveranhRepository.findAll().size();

        // Update the serveranh
        Serveranh updatedServeranh = serveranhRepository.findById(serveranh.getId()).get();
        // Disconnect from session so that the updates on updatedServeranh are not directly saved in db
        em.detach(updatedServeranh);
        updatedServeranh
            .image(UPDATED_IMAGE)
            .imageContentType(UPDATED_IMAGE_CONTENT_TYPE)
            .uuid(UPDATED_UUID);

        restServeranhMockMvc.perform(put("/api/serveranhs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedServeranh)))
            .andExpect(status().isOk());

        // Validate the Serveranh in the database
        List<Serveranh> serveranhList = serveranhRepository.findAll();
        assertThat(serveranhList).hasSize(databaseSizeBeforeUpdate);
        Serveranh testServeranh = serveranhList.get(serveranhList.size() - 1);
        assertThat(testServeranh.getImage()).isEqualTo(UPDATED_IMAGE);
        assertThat(testServeranh.getImageContentType()).isEqualTo(UPDATED_IMAGE_CONTENT_TYPE);
        assertThat(testServeranh.getUuid()).isEqualTo(UPDATED_UUID);
    }

    @Test
    @Transactional
    public void updateNonExistingServeranh() throws Exception {
        int databaseSizeBeforeUpdate = serveranhRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restServeranhMockMvc.perform(put("/api/serveranhs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(serveranh)))
            .andExpect(status().isBadRequest());

        // Validate the Serveranh in the database
        List<Serveranh> serveranhList = serveranhRepository.findAll();
        assertThat(serveranhList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteServeranh() throws Exception {
        // Initialize the database
        serveranhRepository.saveAndFlush(serveranh);

        int databaseSizeBeforeDelete = serveranhRepository.findAll().size();

        // Delete the serveranh
        restServeranhMockMvc.perform(delete("/api/serveranhs/{id}", serveranh.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Serveranh> serveranhList = serveranhRepository.findAll();
        assertThat(serveranhList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
