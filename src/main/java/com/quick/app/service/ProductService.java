package com.quick.app.service; //todo tu them

import com.quick.app.domain.Productest;

import com.quick.app.repository.ProductestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class ProductService
{
  private final Logger log = LoggerFactory.getLogger(ProductService.class);
  @Autowired
  private ProductestRepository repo;
  public List<Productest>listAll(String keyword){
    if(keyword!=null){
      log.debug("fdsfds");
      String lowercaseKeyword = keyword.toLowerCase(Locale.ENGLISH);
      return repo.searchProduct(lowercaseKeyword);
//      return repo.searchProduct(keyword);
    }
    return repo.findAll();
  }
}
