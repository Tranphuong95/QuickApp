package com.quick.app.web.rest;

import com.quick.app.domain.Fileanh;
import com.quick.app.domain.Serveranh;
import com.quick.app.repository.FileanhRepository;
import com.quick.app.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

/**
 * REST controller for managing {@link com.quick.app.domain.Fileanh}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class FileanhResource {

    private final Logger log = LoggerFactory.getLogger(FileanhResource.class);

    private static final String ENTITY_NAME = "fileanh";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FileanhRepository fileanhRepository;

    public FileanhResource(FileanhRepository fileanhRepository) {
        this.fileanhRepository = fileanhRepository;
    }

    /**
     * {@code POST  /fileanhs} : Create a new fileanh.
     *
     * @param fileanh the fileanh to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fileanh, or with status {@code 400 (Bad Request)} if the fileanh has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
//    @PostMapping("/fileanhs")
//    public ResponseEntity<Fileanh> createFileanh(@RequestBody Fileanh fileanh) throws URISyntaxException {
//        log.debug("REST request to save Fileanh : {}", fileanh);
//        if (fileanh.getId() != null) {
//            throw new BadRequestAlertException("A new fileanh cannot already have an ID", ENTITY_NAME, "idexists");
//        }
//        Fileanh result = fileanhRepository.save(fileanh);
//        return ResponseEntity.created(new URI("/api/fileanhs/" + result.getId()))
//            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
//            .body(result);
//    }

    @PostMapping("/fileanhs") //todo add 19/1
    public ResponseEntity<Map> createImageServer(@RequestParam MultipartFile img, @RequestParam String pikachu)
    {
        try
        {
            byte[] a = img.getBytes();
            String b = img.getContentType();
            log.debug("REST request to save ImageServer : {}", b);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        Random random = new Random();
        Long id = random.nextLong();
        Fileanh fileanh = new Fileanh();

        try
        {
            String imageContentType = img.getContentType();

            String[] tails =  img.getOriginalFilename().split("\\.");
            String tail = tails[tails.length-1]; //todo ten đuôi ảnh

            UUID uuid= UUID.randomUUID();
            String nameImage =tails[0];

            String name= nameImage +"&"+ uuid + "." + tail;

            fileanh.setId(id);
            fileanh.setImage(img.getBytes());
            fileanh.setImageContentType(imageContentType);
            fileanh.setUuid(uuid);
            fileanh.setName(name);
            Fileanh result = fileanhRepository.save(fileanh);


            Map map = new HashMap();
            map.put("link", "/api/fileanhs/" + result.getName());
            return ResponseEntity.ok()
              .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
              .body(map);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;


    }

    /**
     * {@code PUT  /fileanhs} : Updates an existing fileanh.
     *
     * @param fileanh the fileanh to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fileanh,
     * or with status {@code 400 (Bad Request)} if the fileanh is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fileanh couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/fileanhs")
    public ResponseEntity<Fileanh> updateFileanh(@RequestBody Fileanh fileanh) throws URISyntaxException {
        log.debug("REST request to update Fileanh : {}", fileanh);
        if (fileanh.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Fileanh result = fileanhRepository.save(fileanh);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fileanh.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /fileanhs} : get all the fileanhs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fileanhs in body.
     */
    @GetMapping("/fileanhs")
    public List<Fileanh> getAllFileanhs() {
        log.debug("REST request to get all Fileanhs");
        return fileanhRepository.findAll();
    }

    /**
     * {@code GET  /fileanhs/:id} : get the "id" fileanh.
     *
     * @param id the id of the fileanh to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fileanh, or with status {@code 404 (Not Found)}.
     */
//    @GetMapping("/fileanhs/{id}")
//    public ResponseEntity<Fileanh> getFileanh(@PathVariable Long id) {
//        log.debug("REST request to get Fileanh : {}", id);
//        Optional<Fileanh> fileanh = fileanhRepository.findById(id);
//        return ResponseUtil.wrapOrNotFound(fileanh);
//    }

    @GetMapping("/fileanhs/{id}") //todo add 19/1r
    public ResponseEntity<byte[]> getFileanh(@PathVariable String id)
    {
        log.debug("REST request to get Fileanh : {}", id);
        Optional<Fileanh> fileanh = fileanhRepository.findByName(id);
        HttpHeaders headerUtil = HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fileanh.get().getName());
        log.warn("zzzz");

        if (fileanh.get().getImageContentType().equals("image/png"))
            headerUtil.setContentType(MediaType.IMAGE_PNG);
        else if (fileanh.get().getImageContentType().equals("image/jpeg"))
            headerUtil.setContentType(MediaType.IMAGE_JPEG);
        else if (fileanh.get().getImageContentType().equals("image/gif"))
            headerUtil.setContentType(MediaType.IMAGE_GIF);
        else
            headerUtil.setContentType(MediaType.IMAGE_PNG);

        log.warn("yyyy");
        return ResponseEntity.ok()
          .headers(headerUtil)
          .body(fileanh.get().getImage());
    }

    /**
     * {@code DELETE  /fileanhs/:id} : delete the "id" fileanh.
     *
     * @param id the id of the fileanh to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/fileanhs/{id}")
    public ResponseEntity<Void> deleteFileanh(@PathVariable Long id) {
        log.debug("REST request to delete Fileanh : {}", id);
        fileanhRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
