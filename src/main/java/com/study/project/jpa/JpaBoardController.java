//Entity 연동관계
//1. url요청

package com.study.project.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//지금 jpa, 레스트풀, 마이바티스 사용한다
@RestController // JSON 응답으로 변환되어 브라우저/클라이언트에 전달됨
@RequestMapping("/jpa/board")
public class JpaBoardController {
   
   private final JpaBoardService service;
   public JpaBoardController(JpaBoardService service) {
      this.service = service;
   }
   
   @GetMapping
   public List<JpaBoardEntity> findAll(){
      
      List<JpaBoardEntity> list = service.findAll();//1 service로부터 데이터 가져옴
      
      return list;//2 그 데이터를 호출한 쪽(컨트롤러 → 스프링 → 클라이언트)으로 반환
   }
   
   @GetMapping("/{num}")
   public JpaBoardEntity findById(@PathVariable Integer num) {

      return service.findById(num);

   }
   
   //등록
   @PostMapping
   public Map<String, Object> save(@RequestBody JpaBoardDTO boardDto){
      
      JpaBoardEntity insert = service.save(boardDto);
      
      Map<String, Object> status = new HashMap<String, Object>();
      status.put("status", insert.getBoardNum() == 0 ? false : true);
      
      return status;   
   }
   
   //update 수정
   //set방식 엔티티 객체 기반 수정
   //단건
   @PutMapping("/set")
   public Map<String, Object> boardUpdate(@RequestBody JpaBoardDTO boardDto){
      
      JpaBoardEntity update = service.boardUpdate(boardDto);
//      JpaBoardEntity update = service.boardUpdate1(boardDto);
      
      
      Map<String, Object> status = new HashMap<String, Object>();
      status.put("status", update.getBoardNum() == 0 ? false : true);
      
      return status;
   }
   
   //수정
   //JPQL bulk 수정 기반
   @PutMapping("/query")
   public Map<String, Object> boardUpdate1(@RequestBody JpaBoardDTO boardDto){
      
      int update = service.boardUpdate1(boardDto);
      
      
      Map<String, Object> status = new HashMap<String, Object>();
      status.put("status", update == 0 ? false : true);
      
      return status;
   }
   
   //삭제
   @DeleteMapping("/set/{num}")
   public Map<String, Object> deleteSet(@PathVariable int num){
      
      Map<String, Object> status = new HashMap<String, Object>();
      try {
         service.deletSet(num);
         status.put("status", true);
      }catch (Exception e) {
         // TODO: handle exception
         status.put("status", false);
      }
      
      return status;
   }
   
   @DeleteMapping("/query/{num}")
   public Map<String, Object> deleteQuery(@PathVariable int num){
      
      int delete = service.deleteQuery(num);
      Map<String, Object> status = new HashMap<String, Object>();
      status.put("status", delete == 0 ? false : true);
      
      return status;
   }
   
   //검색 (타입 검색만됨 날짜X)
   @PostMapping("/search/set")
   public List<JpaBoardEntity> searchSet(@RequestBody JpaSearchDTO searchDto){
      
      List<JpaBoardEntity> searchList = service.searchSet(searchDto);
      
      return searchList;
   }
   
}

