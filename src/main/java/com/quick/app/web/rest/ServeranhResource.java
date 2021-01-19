//package com.quick.app.web.rest;
//
//import com.quick.app.domain.Serveranh;
//import com.quick.app.repository.ServeranhRepository;
//import com.quick.app.web.rest.errors.BadRequestAlertException;
//
//import io.github.jhipster.web.util.HeaderUtil;
//import io.github.jhipster.web.util.ResponseUtil;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.ResponseEntity;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.*;
//
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.util.List;
//import java.util.Optional;
//
///**
// * REST controller for managing {@link com.quick.app.domain.Serveranh}.
// */
//@RestController
//@RequestMapping("/api")
//@Transactional
//public class ServeranhResource {
//
//    private final Logger log = LoggerFactory.getLogger(ServeranhResource.class);
//
//    private static final String ENTITY_NAME = "serveranh";
//
//    @Value("${jhipster.clientApp.name}")
//    private String applicationName;
//
//    private final ServeranhRepository serveranhRepository;
//
//    public ServeranhResource(ServeranhRepository serveranhRepository) {
//        this.serveranhRepository = serveranhRepository;
//    }
//
//    /**
//     * {@code POST  /serveranhs} : Create a new serveranh.
//     *
//     * @param serveranh the serveranh to create.
//     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new serveranh, or with status {@code 400 (Bad Request)} if the serveranh has already an ID.
//     * @throws URISyntaxException if the Location URI syntax is incorrect.
//     */
//    @PostMapping("/serveranhs")
//    public ResponseEntity<Serveranh> createServeranh(@RequestBody Serveranh serveranh) throws URISyntaxException {
//        log.debug("REST request to save Serveranh : {}", serveranh);
//        if (serveranh.getId() != null) {
//            throw new BadRequestAlertException("A new serveranh cannot already have an ID", ENTITY_NAME, "idexists");
//        }
//        Serveranh result = serveranhRepository.save(serveranh);
//        return ResponseEntity.created(new URI("/api/serveranhs/" + result.getId()))
//            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
//            .body(result);
//    }
//
//    /**
//     * {@code PUT  /serveranhs} : Updates an existing serveranh.
//     *
//     * @param serveranh the serveranh to update.
//     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated serveranh,
//     * or with status {@code 400 (Bad Request)} if the serveranh is not valid,
//     * or with status {@code 500 (Internal Server Error)} if the serveranh couldn't be updated.
//     * @throws URISyntaxException if the Location URI syntax is incorrect.
//     */
//    @PutMapping("/serveranhs")
//    public ResponseEntity<Serveranh> updateServeranh(@RequestBody Serveranh serveranh) throws URISyntaxException {
//        log.debug("REST request to update Serveranh : {}", serveranh);
//        if (serveranh.getId() == null) {
//            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
//        }
//        Serveranh result = serveranhRepository.save(serveranh);
//        return ResponseEntity.ok()
//            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, serveranh.getId().toString()))
//            .body(result);
//    }
//
//    /**
//     * {@code GET  /serveranhs} : get all the serveranhs.
//     *
//     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of serveranhs in body.
//     */
//    @GetMapping("/serveranhs")
//    public List<Serveranh> getAllServeranhs() {
//        log.debug("REST request to get all Serveranhs");
//        return serveranhRepository.findAll();
//    }
//
//    /**
//     * {@code GET  /serveranhs/:id} : get the "id" serveranh.
//     *
//     * @param id the id of the serveranh to retrieve.
//     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the serveranh, or with status {@code 404 (Not Found)}.
//     */
//    @GetMapping("/serveranhs/{id}")
//    public ResponseEntity<Serveranh> getServeranh(@PathVariable Long id) {
//        log.debug("REST request to get Serveranh : {}", id);
//        Optional<Serveranh> serveranh = serveranhRepository.findById(id);
//        return ResponseUtil.wrapOrNotFound(serveranh);
//    }
//
//    /**
//     * {@code DELETE  /serveranhs/:id} : delete the "id" serveranh.
//     *
//     * @param id the id of the serveranh to delete.
//     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
//     */
//    @DeleteMapping("/serveranhs/{id}")
//    public ResponseEntity<Void> deleteServeranh(@PathVariable Long id) {
//        log.debug("REST request to delete Serveranh : {}", id);
//        serveranhRepository.deleteById(id);
//        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
//    }
//}

package com.quick.app.web.rest;

import com.quick.app.domain.ImageServer;
import com.quick.app.domain.Serveranh;
import com.quick.app.repository.ServeranhRepository;
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

import javax.activation.MimetypesFileTypeMap;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

/**
 * REST controller for managing {@link com.quick.app.domain.Serveranh}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ServeranhResource
{

  private final Logger log = LoggerFactory.getLogger(ServeranhResource.class);

  private static final String ENTITY_NAME = "serveranh";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final ServeranhRepository serveranhRepository;

  public ServeranhResource(ServeranhRepository serveranhRepository)
  {
    this.serveranhRepository = serveranhRepository;
  }

  /**
   * {@code POST  /serveranhs} : Create a new serveranh.
   *
   * @param serveranh the serveranh to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new serveranh, or with status {@code 400 (Bad Request)} if the serveranh has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
//    @PostMapping("/serveranhs")
//    public ResponseEntity<Serveranh> createServeranh(@RequestBody Serveranh serveranh) throws URISyntaxException {
//        log.debug("REST request to save Serveranh : {}", serveranh);
//        if (serveranh.getId() != null) {
//            throw new BadRequestAlertException("A new serveranh cannot already have an ID", ENTITY_NAME, "idexists");
//        }
//        Serveranh result = serveranhRepository.save(serveranh);
//        return ResponseEntity.created(new URI("/api/serveranhs/" + result.getId()))
//          .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
//          .body(result);
//    }
  @PostMapping("/serveranhs") //todo add 18/1
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
    Serveranh serveranh = new Serveranh();

    try
    {
      String imageContentType = img.getContentType();

      serveranh.setId(id);
      serveranh.setImage(img.getBytes());
      serveranh.setImageContentType(imageContentType);
      Serveranh result = serveranhRepository.save(serveranh);

      String[] tails =  img.getOriginalFilename().split("\\.");
      String tail = tails[tails.length-1]; //todo ten đuôi ảnh

      String nameImage =tails[0];

      Map map = new HashMap();
      map.put("link", "/api/serveranhs/" + result.getUuid());
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
   * {@code PUT  /serveranhs} : Updates an existing serveranh.
   *
   * @param serveranh the serveranh to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated serveranh,
   * or with status {@code 400 (Bad Request)} if the serveranh is not valid,
   * or with status {@code 500 (Internal Server Error)} if the serveranh couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/serveranhs")
  public ResponseEntity<Serveranh> updateServeranh(@RequestBody Serveranh serveranh) throws URISyntaxException
  {
    log.debug("REST request to update Serveranh : {}", serveranh);
    if (serveranh.getId() == null)
    {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    Serveranh result = serveranhRepository.save(serveranh);
    return ResponseEntity.ok()
      .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, serveranh.getId().toString()))
      .body(result);
  }

  /**
   * {@code GET  /serveranhs} : get all the serveranhs.
   *
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of serveranhs in body.
   */
  @GetMapping("/serveranhs")
  public List<Serveranh> getAllServeranhs()
  {
    log.debug("REST request to get all Serveranhs");
    return serveranhRepository.findAll();
  }

  /**
   * {@code GET  /serveranhs/:id} : get the "id" serveranh.
   *
   * @param id the id of the serveranh to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the serveranh, or with status {@code 404 (Not Found)}.
   */
//    @GetMapping("/serveranhs/{id}")
//    public ResponseEntity<Serveranh> getServeranh(@PathVariable Long id) {
//        log.debug("REST request to get Serveranh : {}", id);
//        Optional<Serveranh> serveranh = serveranhRepository.findById(id);
//        return ResponseUtil.wrapOrNotFound(serveranh);
//    }
  @GetMapping("/serveranhs/{id}") //todo add 18/1
  public ResponseEntity<byte[]> getServeranh(@PathVariable UUID id)
  {
    log.debug("REST request to get Serveranh : {}", id);
    Optional<Serveranh> serveranh = serveranhRepository.findByUuid(id);
    HttpHeaders headerUtil = HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, serveranh.get().getUuid().toString());
//        headerUtil.setContentType(MediaType.IMAGE_JPEG);
    log.warn("zzzz");

      if (serveranh.get().getImageContentType().equals("image/png"))
        headerUtil.setContentType(MediaType.IMAGE_PNG);
      else if (serveranh.get().getImageContentType().equals("image/jpeg"))
        headerUtil.setContentType(MediaType.IMAGE_JPEG);
      else if (serveranh.get().getImageContentType().equals("image/gif"))
        headerUtil.setContentType(MediaType.IMAGE_GIF);
      else
        headerUtil.setContentType(MediaType.IMAGE_PNG);

    log.warn("yyyy");
    return ResponseEntity.ok()
      .headers(headerUtil)
      .body(serveranh.get().getImage());
  }

  /**
   * {@code DELETE  /serveranhs/:id} : delete the "id" serveranh.
   *
   * @param id the id of the serveranh to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/serveranhs/{id}")
  public ResponseEntity<Void> deleteServeranh(@PathVariable Long id)
  {
    log.debug("REST request to delete Serveranh : {}", id);
    serveranhRepository.deleteById(id);
    return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
  }
}

