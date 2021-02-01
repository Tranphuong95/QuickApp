package com.quick.app.web.rest;

import com.quick.app.QuicklyappApp;
import com.quick.app.domain.Testsdiachi;
import com.quick.app.repository.TestsdiachiRepository;

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
 * Integration tests for the {@link TestsdiachiResource} REST controller.
 */
@SpringBootTest(classes = QuicklyappApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TestsdiachiResourceIT {

    private static final String DEFAULT_TINH = "AAAAAAAAAA";
    private static final String UPDATED_TINH = "BBBBBBBBBB";

    private static final Long DEFAULT_MATINH = 1L;
    private static final Long UPDATED_MATINH = 2L;

    private static final String DEFAULT_QUANHUYEN = "AAAAAAAAAA";
    private static final String UPDATED_QUANHUYEN = "BBBBBBBBBB";

    private static final Long DEFAULT_MAQUANHUYEN = 1L;
    private static final Long UPDATED_MAQUANHUYEN = 2L;

    private static final String DEFAULT_PHUONGXA = "AAAAAAAAAA";
    private static final String UPDATED_PHUONGXA = "BBBBBBBBBB";

    private static final Long DEFAULT_MAPHUONGXA = 1L;
    private static final Long UPDATED_MAPHUONGXA = 2L;

    @Autowired
    private TestsdiachiRepository testsdiachiRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTestsdiachiMockMvc;

    private Testsdiachi testsdiachi;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Testsdiachi createEntity(EntityManager em) {
        Testsdiachi testsdiachi = new Testsdiachi()
            .tinh(DEFAULT_TINH)
            .matinh(DEFAULT_MATINH)
            .quanhuyen(DEFAULT_QUANHUYEN)
            .maquanhuyen(DEFAULT_MAQUANHUYEN)
            .phuongxa(DEFAULT_PHUONGXA)
            .maphuongxa(DEFAULT_MAPHUONGXA);
        return testsdiachi;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Testsdiachi createUpdatedEntity(EntityManager em) {
        Testsdiachi testsdiachi = new Testsdiachi()
            .tinh(UPDATED_TINH)
            .matinh(UPDATED_MATINH)
            .quanhuyen(UPDATED_QUANHUYEN)
            .maquanhuyen(UPDATED_MAQUANHUYEN)
            .phuongxa(UPDATED_PHUONGXA)
            .maphuongxa(UPDATED_MAPHUONGXA);
        return testsdiachi;
    }

    @BeforeEach
    public void initTest() {
        testsdiachi = createEntity(em);
    }

    @Test
    @Transactional
    public void createTestsdiachi() throws Exception {
        int databaseSizeBeforeCreate = testsdiachiRepository.findAll().size();
        // Create the Testsdiachi
        restTestsdiachiMockMvc.perform(post("/api/testsdiachis")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(testsdiachi)))
            .andExpect(status().isCreated());

        // Validate the Testsdiachi in the database
        List<Testsdiachi> testsdiachiList = testsdiachiRepository.findAll();
        assertThat(testsdiachiList).hasSize(databaseSizeBeforeCreate + 1);
        Testsdiachi testTestsdiachi = testsdiachiList.get(testsdiachiList.size() - 1);
        assertThat(testTestsdiachi.getTinh()).isEqualTo(DEFAULT_TINH);
        assertThat(testTestsdiachi.getMatinh()).isEqualTo(DEFAULT_MATINH);
        assertThat(testTestsdiachi.getQuanhuyen()).isEqualTo(DEFAULT_QUANHUYEN);
        assertThat(testTestsdiachi.getMaquanhuyen()).isEqualTo(DEFAULT_MAQUANHUYEN);
        assertThat(testTestsdiachi.getPhuongxa()).isEqualTo(DEFAULT_PHUONGXA);
        assertThat(testTestsdiachi.getMaphuongxa()).isEqualTo(DEFAULT_MAPHUONGXA);
    }

    @Test
    @Transactional
    public void createTestsdiachiWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = testsdiachiRepository.findAll().size();

        // Create the Testsdiachi with an existing ID
        testsdiachi.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTestsdiachiMockMvc.perform(post("/api/testsdiachis")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(testsdiachi)))
            .andExpect(status().isBadRequest());

        // Validate the Testsdiachi in the database
        List<Testsdiachi> testsdiachiList = testsdiachiRepository.findAll();
        assertThat(testsdiachiList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTestsdiachis() throws Exception {
        // Initialize the database
        testsdiachiRepository.saveAndFlush(testsdiachi);

        // Get all the testsdiachiList
        restTestsdiachiMockMvc.perform(get("/api/testsdiachis?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(testsdiachi.getId().intValue())))
            .andExpect(jsonPath("$.[*].tinh").value(hasItem(DEFAULT_TINH)))
            .andExpect(jsonPath("$.[*].matinh").value(hasItem(DEFAULT_MATINH.intValue())))
            .andExpect(jsonPath("$.[*].quanhuyen").value(hasItem(DEFAULT_QUANHUYEN)))
            .andExpect(jsonPath("$.[*].maquanhuyen").value(hasItem(DEFAULT_MAQUANHUYEN.intValue())))
            .andExpect(jsonPath("$.[*].phuongxa").value(hasItem(DEFAULT_PHUONGXA)))
            .andExpect(jsonPath("$.[*].maphuongxa").value(hasItem(DEFAULT_MAPHUONGXA.intValue())));
    }
    
    @Test
    @Transactional
    public void getTestsdiachi() throws Exception {
        // Initialize the database
        testsdiachiRepository.saveAndFlush(testsdiachi);

        // Get the testsdiachi
        restTestsdiachiMockMvc.perform(get("/api/testsdiachis/{id}", testsdiachi.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(testsdiachi.getId().intValue()))
            .andExpect(jsonPath("$.tinh").value(DEFAULT_TINH))
            .andExpect(jsonPath("$.matinh").value(DEFAULT_MATINH.intValue()))
            .andExpect(jsonPath("$.quanhuyen").value(DEFAULT_QUANHUYEN))
            .andExpect(jsonPath("$.maquanhuyen").value(DEFAULT_MAQUANHUYEN.intValue()))
            .andExpect(jsonPath("$.phuongxa").value(DEFAULT_PHUONGXA))
            .andExpect(jsonPath("$.maphuongxa").value(DEFAULT_MAPHUONGXA.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingTestsdiachi() throws Exception {
        // Get the testsdiachi
        restTestsdiachiMockMvc.perform(get("/api/testsdiachis/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTestsdiachi() throws Exception {
        // Initialize the database
        testsdiachiRepository.saveAndFlush(testsdiachi);

        int databaseSizeBeforeUpdate = testsdiachiRepository.findAll().size();

        // Update the testsdiachi
        Testsdiachi updatedTestsdiachi = testsdiachiRepository.findById(testsdiachi.getId()).get();
        // Disconnect from session so that the updates on updatedTestsdiachi are not directly saved in db
        em.detach(updatedTestsdiachi);
        updatedTestsdiachi
            .tinh(UPDATED_TINH)
            .matinh(UPDATED_MATINH)
            .quanhuyen(UPDATED_QUANHUYEN)
            .maquanhuyen(UPDATED_MAQUANHUYEN)
            .phuongxa(UPDATED_PHUONGXA)
            .maphuongxa(UPDATED_MAPHUONGXA);

        restTestsdiachiMockMvc.perform(put("/api/testsdiachis")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTestsdiachi)))
            .andExpect(status().isOk());

        // Validate the Testsdiachi in the database
        List<Testsdiachi> testsdiachiList = testsdiachiRepository.findAll();
        assertThat(testsdiachiList).hasSize(databaseSizeBeforeUpdate);
        Testsdiachi testTestsdiachi = testsdiachiList.get(testsdiachiList.size() - 1);
        assertThat(testTestsdiachi.getTinh()).isEqualTo(UPDATED_TINH);
        assertThat(testTestsdiachi.getMatinh()).isEqualTo(UPDATED_MATINH);
        assertThat(testTestsdiachi.getQuanhuyen()).isEqualTo(UPDATED_QUANHUYEN);
        assertThat(testTestsdiachi.getMaquanhuyen()).isEqualTo(UPDATED_MAQUANHUYEN);
        assertThat(testTestsdiachi.getPhuongxa()).isEqualTo(UPDATED_PHUONGXA);
        assertThat(testTestsdiachi.getMaphuongxa()).isEqualTo(UPDATED_MAPHUONGXA);
    }

    @Test
    @Transactional
    public void updateNonExistingTestsdiachi() throws Exception {
        int databaseSizeBeforeUpdate = testsdiachiRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTestsdiachiMockMvc.perform(put("/api/testsdiachis")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(testsdiachi)))
            .andExpect(status().isBadRequest());

        // Validate the Testsdiachi in the database
        List<Testsdiachi> testsdiachiList = testsdiachiRepository.findAll();
        assertThat(testsdiachiList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTestsdiachi() throws Exception {
        // Initialize the database
        testsdiachiRepository.saveAndFlush(testsdiachi);

        int databaseSizeBeforeDelete = testsdiachiRepository.findAll().size();

        // Delete the testsdiachi
        restTestsdiachiMockMvc.perform(delete("/api/testsdiachis/{id}", testsdiachi.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Testsdiachi> testsdiachiList = testsdiachiRepository.findAll();
        assertThat(testsdiachiList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
