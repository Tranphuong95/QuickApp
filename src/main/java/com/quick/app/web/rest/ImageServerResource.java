package com.quick.app.web.rest;

import com.quick.app.domain.ImageServer;
import com.quick.app.repository.ImageServerRepository;
import com.quick.app.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import org.apache.commons.compress.utils.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.util.*;
import java.util.List;

/**
 * REST controller for managing {@link com.quick.app.domain.ImageServer}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ImageServerResource
{

  private final Logger log = LoggerFactory.getLogger(ImageServerResource.class);

  private static final String ENTITY_NAME = "imageServer";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final ImageServerRepository imageServerRepository;

  public ImageServerResource(ImageServerRepository imageServerRepository)
  {
    this.imageServerRepository = imageServerRepository;
  }

  /**
   * {@code POST  /image-servers} : Create a new imageServer.
   *
   * @param imageServer the imageServer to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new imageServer, or with status {@code 400 (Bad Request)} if the imageServer has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/image-servers")
  public ResponseEntity<Map> createImageServer(@RequestParam MultipartFile img)
  {
    log.debug("REST request to save ImageServer : {}", img);
    Random  random = new Random();
    Long id = random.nextLong();

    ImageServer imageServer = new ImageServer();

    try
    {
      imageServer.setId(id);
      imageServer.setImage(img.getBytes());
      ImageServer result = imageServerRepository.save(imageServer);
      Map map = new HashMap();
      map.put("link","/api/image-servers/" + result.getId());
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
//  @PostMapping("/image-servers")
//  public ResponseEntity<ImageServer> createImageServer(@RequestBody ImageServer imageServer) throws URISyntaxException
//  {
//    log.debug("REST request to save ImageServer : {}", imageServer);
//    if (imageServer.getId() != null)
//    {
//      throw new BadRequestAlertException("A new imageServer cannot already have an ID", ENTITY_NAME, "idexists");
//    }
//    ImageServer result = imageServerRepository.save(imageServer);
//
//    return ResponseEntity.created(new URI("/api/image-servers/" + result.getId()))
//      .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
//      .body(result);
//  }


  /**
   * {@code PUT  /image-servers} : Updates an existing imageServer.
   *
   * @param imageServer the imageServer to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated imageServer,
   * or with status {@code 400 (Bad Request)} if the imageServer is not valid,
   * or with status {@code 500 (Internal Server Error)} if the imageServer couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/image-servers")
  public ResponseEntity<ImageServer> updateImageServer(@RequestBody ImageServer imageServer) throws URISyntaxException
  {
    log.debug("REST request to update ImageServer : {}", imageServer);
    if (imageServer.getId() == null)
    {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    ImageServer result = imageServerRepository.save(imageServer);
    return ResponseEntity.ok()
      .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, imageServer.getId().toString()))
      .body(result);
  }

  /**
   * {@code GET  /image-servers} : get all the imageServers.
   *
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of imageServers in body.
   */
  @GetMapping("/image-servers")
  public List<ImageServer> getAllImageServers()
  {
    log.debug("REST request to get all ImageServers");
    return imageServerRepository.findAll();
  }

  /**
   * {@code GET  /image-servers/:id} : get the "id" imageServer.
   *
   * @param id the id of the imageServer to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the imageServer, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/image-servers/{id}")
  public ResponseEntity<byte[]> getImageServer(@PathVariable Long id)
  {
    log.debug("REST request to get ImageServer : {}", id);
    Optional<ImageServer> imageServer = imageServerRepository.findById(id);
    HttpHeaders headerUtil = HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, imageServer.get().getId().toString());
    headerUtil.setContentType(MediaType.IMAGE_JPEG);
    return ResponseEntity.ok()
      .headers(headerUtil)
      .body(imageServer.get().getImage());
  }


//    @GetMapping("/image-servers/{id}") //todo ban goc
//    public ResponseEntity<ImageServer> getImageServer(@PathVariable Long id) {
//        log.debug("REST request to get ImageServer : {}", id);
//        Optional<ImageServer> imageServer = imageServerRepository.findById(id);
//        return ResponseUtil.wrapOrNotFound(imageServer);
//    }

  /**
   * {@code DELETE  /image-servers/:id} : delete the "id" imageServer.
   *
   * @param id the id of the imageServer to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/image-servers/{id}")
  public ResponseEntity<Void> deleteImageServer(@PathVariable Long id)
  {
    log.debug("REST request to delete ImageServer : {}", id);
    imageServerRepository.deleteById(id);
    return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
  }
}



//package com.quick.app.web.rest;
//
//import com.quick.app.domain.ImageServer;
//import com.quick.app.repository.ImageServerRepository;
//import com.quick.app.web.rest.errors.BadRequestAlertException;
//
//import io.github.jhipster.web.util.HeaderUtil;
//import org.apache.commons.compress.utils.IOUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.imageio.ImageIO;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.security.MessageDigest;
//import java.util.*;
//import java.util.List;
//
///**
// * REST controller for managing {@link com.quick.app.domain.ImageServer}.
// */
//@RestController
//@RequestMapping("/api")
//@Transactional
//public class ImageServerResource
//{
//
//  private final Logger log = LoggerFactory.getLogger(ImageServerResource.class);
//
//  private static final String ENTITY_NAME = "imageServer";
//
//  @Value("${jhipster.clientApp.name}")
//  private String applicationName;
//
//  private final ImageServerRepository imageServerRepository;
//
//  public ImageServerResource(ImageServerRepository imageServerRepository)
//  {
//    this.imageServerRepository = imageServerRepository;
//  }
//
//  /**
//   * {@code POST  /image-servers} : Create a new imageServer.
//   *
//   * @param imageServer the imageServer to create.
//   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new imageServer, or with status {@code 400 (Bad Request)} if the imageServer has already an ID.
//   * @throws URISyntaxException if the Location URI syntax is incorrect.
//   */
//  @PostMapping("/image-servers")
//  public ResponseEntity<Map> createImageServer(@RequestParam MultipartFile img)
//  {
//    log.debug("REST request to save ImageServer : {}", img);
//    Random  random = new Random();
//    UUID id = UUID.randomUUID();
//
//
//    ImageServer imageServer = new ImageServer();
//
//    try
//    {
//      imageServer.setId(id);
//      imageServer.setImage(img.getBytes());
//      ImageServer result = imageServerRepository.save(imageServer);
//      Map map = new HashMap();
////      map.put("link","/api/image-servers/" + result.getId());
//      map.put("link","/api/image-servers/" + result.getId());
//      return ResponseEntity.ok()
//        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
//        .body(map);
//    }
//    catch (IOException e)
//    {
//      e.printStackTrace();
//    }
//    return null;
//
//
//  }
////  @PostMapping("/image-servers")
////  public ResponseEntity<ImageServer> createImageServer(@RequestBody ImageServer imageServer) throws URISyntaxException
////  {
////    log.debug("REST request to save ImageServer : {}", imageServer);
////    if (imageServer.getId() != null)
////    {
////      throw new BadRequestAlertException("A new imageServer cannot already have an ID", ENTITY_NAME, "idexists");
////    }
////    ImageServer result = imageServerRepository.save(imageServer);
////
////    return ResponseEntity.created(new URI("/api/image-servers/" + result.getId()))
////      .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
////      .body(result);
////  }
//
//
//  /**
//   * {@code PUT  /image-servers} : Updates an existing imageServer.
//   *
//   * @param imageServer the imageServer to update.
//   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated imageServer,
//   * or with status {@code 400 (Bad Request)} if the imageServer is not valid,
//   * or with status {@code 500 (Internal Server Error)} if the imageServer couldn't be updated.
//   * @throws URISyntaxException if the Location URI syntax is incorrect.
//   */
//  @PutMapping("/image-servers")
//  public ResponseEntity<ImageServer> updateImageServer(@RequestBody ImageServer imageServer) throws URISyntaxException
//  {
//    log.debug("REST request to update ImageServer : {}", imageServer);
//    if (imageServer.getId() == null)
//    {
//      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
//    }
//    ImageServer result = imageServerRepository.save(imageServer);
//    return ResponseEntity.ok()
//      .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, imageServer.getId().toString()))
//      .body(result);
//  }
//
//  /**
//   * {@code GET  /image-servers} : get all the imageServers.
//   *
//   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of imageServers in body.
//   */
//  @GetMapping("/image-servers")
//  public List<ImageServer> getAllImageServers()
//  {
//    log.debug("REST request to get all ImageServers");
//    return imageServerRepository.findAll();
//  }
//
//  /**
//   * {@code GET  /image-servers/:id} : get the "id" imageServer.
//   *
//   * @param id the id of the imageServer to retrieve.
//   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the imageServer, or with status {@code 404 (Not Found)}.
//   */
//  @GetMapping("/image-servers/{id}")
//  public ResponseEntity<byte[]> getImageServer(@PathVariable Long id)
//  {
//    log.debug("REST request to get ImageServer : {}", id);
//    Optional<ImageServer> imageServer = imageServerRepository.findById(id);
//    HttpHeaders headerUtil = HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, imageServer.get().getId().toString());
//    headerUtil.setContentType(MediaType.IMAGE_JPEG);
//    return ResponseEntity.ok()
//      .headers(headerUtil)
//      .body(imageServer.get().getImage());
//  }
//
//
////    @GetMapping("/image-servers/{id}")
////    public ResponseEntity<ImageServer> getImageServer(@PathVariable Long id) {
////        log.debug("REST request to get ImageServer : {}", id);
////        Optional<ImageServer> imageServer = imageServerRepository.findById(id);
////        return ResponseUtil.wrapOrNotFound(imageServer);
////    }
//
//  /**
//   * {@code DELETE  /image-servers/:id} : delete the "id" imageServer.
//   *
//   * @param id the id of the imageServer to delete.
//   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
//   */
//  @DeleteMapping("/image-servers/{id}")
//  public ResponseEntity<Void> deleteImageServer(@PathVariable Long id)
//  {
//    log.debug("REST request to delete ImageServer : {}", id);
//    imageServerRepository.deleteById(id);
//    return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
//  }
//}
//
//
