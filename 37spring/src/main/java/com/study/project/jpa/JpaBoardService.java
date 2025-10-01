//Entity 연동관계
//2. Service Set 기본제공 API -> JpaBoardRepository클래스 확인: JpaRepository<JpaBoardEntity, Integer>

package com.study.project.jpa;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JpaBoardService {
   
   private final JpaBoardRepository repository;
   public JpaBoardService(JpaBoardRepository repository) {
      this.repository = repository;
   }
   public List<JpaBoardEntity> findAll() {
	// TODO Auto-generated method stub
	return repository.findAll();
   }
   
   public JpaBoardEntity save(JpaBoardDTO boardDto) {
	      // TODO Auto-generated method stub
	      
	      Date date = new Date(System.currentTimeMillis());
	      JpaBoardEntity entity = new JpaBoardEntity();
	      
	      entity.setUserId(boardDto.getUserId());
	      entity.setUserName(boardDto.getUserId());
	      entity.setBoardSubject(boardDto.getBoardSubject());
	      entity.setBoardContent(boardDto.getBoardContent());
	      entity.setRegDate(date);
	      entity.setUptDate(null);
	      entity.setViewCnt(0);
	      
	      
	      
	      return repository.save(entity);
	   }
   
   public JpaBoardEntity findById(Integer num) {
	// TODO Auto-generated method stub
	   
	   Optional<JpaBoardEntity> bo = repository.findById(num);
	   
	   bo.orElse(new JpaBoardEntity());
	   bo.orElseThrow(() //orElseThrow(() 
				-> new RuntimeException("해당하는 글이 없습니다."));
	// return repository.findById(num).orElse(null); //orElse(null) 
	   
	JpaBoardEntity board = repository.findById(num).orElseThrow(() //orElseThrow(() 
			-> new RuntimeException("해당하는 글이 없습니다."));
	return board;
   }
   
   // 업데이트수정
   //항목: Dirty Checking
   //db접근: SELECT 후 UPDATE
   //트랜젝션: 자동 Dirty Checking
   //반환값: 수정된 엔티티 (장점: 엔티티 그대로 반환 가능, 간단)
   //주의:조회가 필수, 데이터 존재 여부 확인 필요
   @Transactional
   public JpaBoardEntity boardUpdate(JpaBoardDTO boardDto) {
      // TODO Auto-generated method stub
      
      JpaBoardEntity oneData = repository.findById(boardDto.getBoardNum()).orElse(null);
      oneData.setUserId(boardDto.getUserId());
      oneData.setUserName(boardDto.getUserName());
      oneData.setBoardSubject(boardDto.getBoardSubject());
      oneData.setBoardContent(boardDto.getBoardContent());
   
      //findByID where 적용   select ->> set하면 자동 update (hibernate) 한건변경 시
//      update board
//      set user_id = :userId
//      where board_num = 10
      
      return oneData;
   }
   
   //항목 JPQL Update
   //db접근:바로 UPDATE
   //트랜젝션: @Modifying 필요
   //반환값: 업데이트된 행 수 (int) (대량 업데이트 빠름, 조회 생략 가능)
   //주의: 영속성 컨텍스트 동기화 주의, 엔티티 객체 업데이트 반영 안됨
   @Transactional
   public int boardUpdate1(JpaBoardDTO boardDto) {
      // TODO Auto-generated method stub
      int boardNum = boardDto.getBoardNum();
      String userId = boardDto.getUserId();
      String userName = boardDto.getUserName();
      String boardSubject = boardDto.getBoardSubject();
      String boardContent = boardDto.getBoardContent();
      
      
      return repository.boardUpdate1(boardNum, userId, userName, boardSubject, boardContent);
   }
   
   //삭제
   @Transactional
   public void deletSet(int num) {
	// TODO Auto-generated method stub
	repository.deleteById(num);
   }
   
   @Transactional
   public int deleteQuery(int num) {
	// TODO Auto-generated method stub
	return repository.deleteQuery(num);
   }

 //Containing  like '%keyword%'
   //StartsWith like '%keyword'
   //EndsWith like 'keyword%'
   //Entity 변수 변수 And Or
   public List<JpaBoardEntity> searchSet(JpaSearchDTO searchDto) {
	      // TODO Auto-generated method stub
	      
	      List<JpaBoardEntity> searchList = new ArrayList<JpaBoardEntity>();
	      Sort sort = Sort.by(Sort.Direction.DESC, "regDate");
	      
	      if("name".equals(searchDto.getSearchType())) {
	         searchList = repository.findAllByUserNameContaining(searchDto.getSearchTxt(), sort);
	      }else if("subject".equals(searchDto.getSearchType())) {
	         searchList = repository.findAllByBoardSubjectContaining(searchDto.getSearchTxt(), sort);
	      }else if("subCont".equals(searchDto.getSearchType())) {
	         searchList = repository.findAllByBoardSubjectOrBoardContent(searchDto.getSearchTxt(), searchDto.getSearchTxt(), sort);
	      }else {
	         searchList = repository.findAll();
	      }
	            
	            
	            
	            /*StartsWith
	            EndsWith*/
	      
	      
	      return searchList;
	   }   

}