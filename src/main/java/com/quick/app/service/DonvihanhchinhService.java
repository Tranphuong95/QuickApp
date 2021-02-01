package com.quick.app.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class DonvihanhchinhService
{
  private final Logger log= LoggerFactory.getLogger(DonvihanhchinhService.class);
  private DonvihanhchinhService repo;
  public DonvihanhchinhService(){
    Object objectadress = getClass().getResourceAsStream("filejson/donvihanhchinh.json");
    log.debug("REST request to save adress : {}", objectadress);

  }
}
