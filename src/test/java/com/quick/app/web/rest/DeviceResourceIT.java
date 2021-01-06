package com.quick.app.web.rest;

import com.quick.app.QuicklyappApp;
import com.quick.app.domain.Device;
import com.quick.app.repository.DeviceRepository;

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
 * Integration tests for the {@link DeviceResource} REST controller.
 */
@SpringBootTest(classes = QuicklyappApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class DeviceResourceIT {

    private static final String DEFAULT_TENSANPHAM = "AAAAAAAAAA";
    private static final String UPDATED_TENSANPHAM = "BBBBBBBBBB";

    private static final String DEFAULT_KICHTHUOCMATTHUNG = "AAAAAAAAAA";
    private static final String UPDATED_KICHTHUOCMATTHUNG = "BBBBBBBBBB";

    private static final String DEFAULT_KICHTHUOCTHANTHUNG = "AAAAAAAAAA";
    private static final String UPDATED_KICHTHUOCTHANTHUNG = "BBBBBBBBBB";

    private static final String DEFAULT_PHUKIEN = "AAAAAAAAAA";
    private static final String UPDATED_PHUKIEN = "BBBBBBBBBB";

    private static final String DEFAULT_CHATLIEU = "AAAAAAAAAA";
    private static final String UPDATED_CHATLIEU = "BBBBBBBBBB";

    private static final String DEFAULT_BAOHANH = "AAAAAAAAAA";
    private static final String UPDATED_BAOHANH = "BBBBBBBBBB";

    private static final String DEFAULT_DIACHI = "AAAAAAAAAA";
    private static final String UPDATED_DIACHI = "BBBBBBBBBB";

    private static final String DEFAULT_HOTLINE = "AAAAAAAAAA";
    private static final String UPDATED_HOTLINE = "BBBBBBBBBB";

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDeviceMockMvc;

    private Device device;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Device createEntity(EntityManager em) {
        Device device = new Device()
            .tensanpham(DEFAULT_TENSANPHAM)
            .kichthuocmatthung(DEFAULT_KICHTHUOCMATTHUNG)
            .kichthuocthanthung(DEFAULT_KICHTHUOCTHANTHUNG)
            .phukien(DEFAULT_PHUKIEN)
            .chatlieu(DEFAULT_CHATLIEU)
            .baohanh(DEFAULT_BAOHANH)
            .diachi(DEFAULT_DIACHI)
            .hotline(DEFAULT_HOTLINE);
        return device;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Device createUpdatedEntity(EntityManager em) {
        Device device = new Device()
            .tensanpham(UPDATED_TENSANPHAM)
            .kichthuocmatthung(UPDATED_KICHTHUOCMATTHUNG)
            .kichthuocthanthung(UPDATED_KICHTHUOCTHANTHUNG)
            .phukien(UPDATED_PHUKIEN)
            .chatlieu(UPDATED_CHATLIEU)
            .baohanh(UPDATED_BAOHANH)
            .diachi(UPDATED_DIACHI)
            .hotline(UPDATED_HOTLINE);
        return device;
    }

    @BeforeEach
    public void initTest() {
        device = createEntity(em);
    }

    @Test
    @Transactional
    public void createDevice() throws Exception {
        int databaseSizeBeforeCreate = deviceRepository.findAll().size();
        // Create the Device
        restDeviceMockMvc.perform(post("/api/devices")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(device)))
            .andExpect(status().isCreated());

        // Validate the Device in the database
        List<Device> deviceList = deviceRepository.findAll();
        assertThat(deviceList).hasSize(databaseSizeBeforeCreate + 1);
        Device testDevice = deviceList.get(deviceList.size() - 1);
        assertThat(testDevice.getTensanpham()).isEqualTo(DEFAULT_TENSANPHAM);
        assertThat(testDevice.getKichthuocmatthung()).isEqualTo(DEFAULT_KICHTHUOCMATTHUNG);
        assertThat(testDevice.getKichthuocthanthung()).isEqualTo(DEFAULT_KICHTHUOCTHANTHUNG);
        assertThat(testDevice.getPhukien()).isEqualTo(DEFAULT_PHUKIEN);
        assertThat(testDevice.getChatlieu()).isEqualTo(DEFAULT_CHATLIEU);
        assertThat(testDevice.getBaohanh()).isEqualTo(DEFAULT_BAOHANH);
        assertThat(testDevice.getDiachi()).isEqualTo(DEFAULT_DIACHI);
        assertThat(testDevice.getHotline()).isEqualTo(DEFAULT_HOTLINE);
    }

    @Test
    @Transactional
    public void createDeviceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = deviceRepository.findAll().size();

        // Create the Device with an existing ID
        device.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDeviceMockMvc.perform(post("/api/devices")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(device)))
            .andExpect(status().isBadRequest());

        // Validate the Device in the database
        List<Device> deviceList = deviceRepository.findAll();
        assertThat(deviceList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDevices() throws Exception {
        // Initialize the database
        deviceRepository.saveAndFlush(device);

        // Get all the deviceList
        restDeviceMockMvc.perform(get("/api/devices?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(device.getId().intValue())))
            .andExpect(jsonPath("$.[*].tensanpham").value(hasItem(DEFAULT_TENSANPHAM)))
            .andExpect(jsonPath("$.[*].kichthuocmatthung").value(hasItem(DEFAULT_KICHTHUOCMATTHUNG)))
            .andExpect(jsonPath("$.[*].kichthuocthanthung").value(hasItem(DEFAULT_KICHTHUOCTHANTHUNG)))
            .andExpect(jsonPath("$.[*].phukien").value(hasItem(DEFAULT_PHUKIEN)))
            .andExpect(jsonPath("$.[*].chatlieu").value(hasItem(DEFAULT_CHATLIEU)))
            .andExpect(jsonPath("$.[*].baohanh").value(hasItem(DEFAULT_BAOHANH)))
            .andExpect(jsonPath("$.[*].diachi").value(hasItem(DEFAULT_DIACHI)))
            .andExpect(jsonPath("$.[*].hotline").value(hasItem(DEFAULT_HOTLINE)));
    }
    
    @Test
    @Transactional
    public void getDevice() throws Exception {
        // Initialize the database
        deviceRepository.saveAndFlush(device);

        // Get the device
        restDeviceMockMvc.perform(get("/api/devices/{id}", device.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(device.getId().intValue()))
            .andExpect(jsonPath("$.tensanpham").value(DEFAULT_TENSANPHAM))
            .andExpect(jsonPath("$.kichthuocmatthung").value(DEFAULT_KICHTHUOCMATTHUNG))
            .andExpect(jsonPath("$.kichthuocthanthung").value(DEFAULT_KICHTHUOCTHANTHUNG))
            .andExpect(jsonPath("$.phukien").value(DEFAULT_PHUKIEN))
            .andExpect(jsonPath("$.chatlieu").value(DEFAULT_CHATLIEU))
            .andExpect(jsonPath("$.baohanh").value(DEFAULT_BAOHANH))
            .andExpect(jsonPath("$.diachi").value(DEFAULT_DIACHI))
            .andExpect(jsonPath("$.hotline").value(DEFAULT_HOTLINE));
    }
    @Test
    @Transactional
    public void getNonExistingDevice() throws Exception {
        // Get the device
        restDeviceMockMvc.perform(get("/api/devices/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDevice() throws Exception {
        // Initialize the database
        deviceRepository.saveAndFlush(device);

        int databaseSizeBeforeUpdate = deviceRepository.findAll().size();

        // Update the device
        Device updatedDevice = deviceRepository.findById(device.getId()).get();
        // Disconnect from session so that the updates on updatedDevice are not directly saved in db
        em.detach(updatedDevice);
        updatedDevice
            .tensanpham(UPDATED_TENSANPHAM)
            .kichthuocmatthung(UPDATED_KICHTHUOCMATTHUNG)
            .kichthuocthanthung(UPDATED_KICHTHUOCTHANTHUNG)
            .phukien(UPDATED_PHUKIEN)
            .chatlieu(UPDATED_CHATLIEU)
            .baohanh(UPDATED_BAOHANH)
            .diachi(UPDATED_DIACHI)
            .hotline(UPDATED_HOTLINE);

        restDeviceMockMvc.perform(put("/api/devices")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedDevice)))
            .andExpect(status().isOk());

        // Validate the Device in the database
        List<Device> deviceList = deviceRepository.findAll();
        assertThat(deviceList).hasSize(databaseSizeBeforeUpdate);
        Device testDevice = deviceList.get(deviceList.size() - 1);
        assertThat(testDevice.getTensanpham()).isEqualTo(UPDATED_TENSANPHAM);
        assertThat(testDevice.getKichthuocmatthung()).isEqualTo(UPDATED_KICHTHUOCMATTHUNG);
        assertThat(testDevice.getKichthuocthanthung()).isEqualTo(UPDATED_KICHTHUOCTHANTHUNG);
        assertThat(testDevice.getPhukien()).isEqualTo(UPDATED_PHUKIEN);
        assertThat(testDevice.getChatlieu()).isEqualTo(UPDATED_CHATLIEU);
        assertThat(testDevice.getBaohanh()).isEqualTo(UPDATED_BAOHANH);
        assertThat(testDevice.getDiachi()).isEqualTo(UPDATED_DIACHI);
        assertThat(testDevice.getHotline()).isEqualTo(UPDATED_HOTLINE);
    }

    @Test
    @Transactional
    public void updateNonExistingDevice() throws Exception {
        int databaseSizeBeforeUpdate = deviceRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDeviceMockMvc.perform(put("/api/devices")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(device)))
            .andExpect(status().isBadRequest());

        // Validate the Device in the database
        List<Device> deviceList = deviceRepository.findAll();
        assertThat(deviceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDevice() throws Exception {
        // Initialize the database
        deviceRepository.saveAndFlush(device);

        int databaseSizeBeforeDelete = deviceRepository.findAll().size();

        // Delete the device
        restDeviceMockMvc.perform(delete("/api/devices/{id}", device.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Device> deviceList = deviceRepository.findAll();
        assertThat(deviceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
