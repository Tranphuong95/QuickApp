package com.quick.app.web.rest;

import com.quick.app.QuicklyappApp;
import com.quick.app.domain.Editor;
import com.quick.app.repository.EditorRepository;

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
 * Integration tests for the {@link EditorResource} REST controller.
 */
@SpringBootTest(classes = QuicklyappApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class EditorResourceIT {

    private static final String DEFAULT_EDITOR = "AAAAAAAAAA";
    private static final String UPDATED_EDITOR = "BBBBBBBBBB";

    @Autowired
    private EditorRepository editorRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEditorMockMvc;

    private Editor editor;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Editor createEntity(EntityManager em) {
        Editor editor = new Editor()
            .editor(DEFAULT_EDITOR);
        return editor;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Editor createUpdatedEntity(EntityManager em) {
        Editor editor = new Editor()
            .editor(UPDATED_EDITOR);
        return editor;
    }

    @BeforeEach
    public void initTest() {
        editor = createEntity(em);
    }

    @Test
    @Transactional
    public void createEditor() throws Exception {
        int databaseSizeBeforeCreate = editorRepository.findAll().size();
        // Create the Editor
        restEditorMockMvc.perform(post("/api/editors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(editor)))
            .andExpect(status().isCreated());

        // Validate the Editor in the database
        List<Editor> editorList = editorRepository.findAll();
        assertThat(editorList).hasSize(databaseSizeBeforeCreate + 1);
        Editor testEditor = editorList.get(editorList.size() - 1);
        assertThat(testEditor.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createEditorWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = editorRepository.findAll().size();

        // Create the Editor with an existing ID
        editor.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEditorMockMvc.perform(post("/api/editors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(editor)))
            .andExpect(status().isBadRequest());

        // Validate the Editor in the database
        List<Editor> editorList = editorRepository.findAll();
        assertThat(editorList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEditors() throws Exception {
        // Initialize the database
        editorRepository.saveAndFlush(editor);

        // Get all the editorList
        restEditorMockMvc.perform(get("/api/editors?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(editor.getId().intValue())))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR)));
    }
    
    @Test
    @Transactional
    public void getEditor() throws Exception {
        // Initialize the database
        editorRepository.saveAndFlush(editor);

        // Get the editor
        restEditorMockMvc.perform(get("/api/editors/{id}", editor.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(editor.getId().intValue()))
            .andExpect(jsonPath("$.editor").value(DEFAULT_EDITOR));
    }
    @Test
    @Transactional
    public void getNonExistingEditor() throws Exception {
        // Get the editor
        restEditorMockMvc.perform(get("/api/editors/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEditor() throws Exception {
        // Initialize the database
        editorRepository.saveAndFlush(editor);

        int databaseSizeBeforeUpdate = editorRepository.findAll().size();

        // Update the editor
        Editor updatedEditor = editorRepository.findById(editor.getId()).get();
        // Disconnect from session so that the updates on updatedEditor are not directly saved in db
        em.detach(updatedEditor);
        updatedEditor
            .editor(UPDATED_EDITOR);

        restEditorMockMvc.perform(put("/api/editors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedEditor)))
            .andExpect(status().isOk());

        // Validate the Editor in the database
        List<Editor> editorList = editorRepository.findAll();
        assertThat(editorList).hasSize(databaseSizeBeforeUpdate);
        Editor testEditor = editorList.get(editorList.size() - 1);
        assertThat(testEditor.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingEditor() throws Exception {
        int databaseSizeBeforeUpdate = editorRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEditorMockMvc.perform(put("/api/editors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(editor)))
            .andExpect(status().isBadRequest());

        // Validate the Editor in the database
        List<Editor> editorList = editorRepository.findAll();
        assertThat(editorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEditor() throws Exception {
        // Initialize the database
        editorRepository.saveAndFlush(editor);

        int databaseSizeBeforeDelete = editorRepository.findAll().size();

        // Delete the editor
        restEditorMockMvc.perform(delete("/api/editors/{id}", editor.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Editor> editorList = editorRepository.findAll();
        assertThat(editorList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
