package com.quick.app.repository;

import com.quick.app.domain.Message;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.ListIterator;

/**
 * Spring Data  repository for the Message entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MessageRepository extends JpaRepository<Message, Long>
{
//  truy vấn , tìm cái gì đó


  public List<Message> findByMessage(String message); //findByMessage


  //test thu ben duoi
  public List<Message> findTop10ByIdBefore(Long id);


  public List<Message> findTop10ByMessageAfter(String messageAfter);


  //  tìm bản ghi có id lớn hơn id nhập vào , message lớn hơn message nh vào
  public List<Message> findByIdAfterAndMessageAfter(Long id, String mess);


  @Query("SELECT m FROM Message as m WHERE m.id > ?1 AND m.message > ?2")
  public List<Message> findByIdAfterAndMessageAfter2(Long id, String message);
  @Query("SELECT message From Message WHERE id=(SELECT MAX(id) FROM Message )")
  public List<Message> findTop10ById(Long id);

  //test
//  @Query(value="SELECT  m FROM Message as m  ORDER BY m.id Limit=10" ,nativeQuery=true)
//  public List<Message>findTop10ByMaxId(Long id);
  //test
  @Query(value="SELECT * FROM Message ORDER BY id DESC Limit 10", nativeQuery = true)
  public List<Message>findTop10ByMaxId(Long id);

}