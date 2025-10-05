//Entity연동관계 3

package com.study.project.jpa;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

//jpa는 기존 인터페이스 상속 문법과 다름

public interface JpaBoardRepository extends JpaRepository<JpaBoardEntity, Integer>{
	
	//int boardUpdate1(int boardNum, String userId, String userName);
	
	//벌크 방식 table JPQL
	   @Modifying //insert, update, delete 꼭 붙여야합니다.
	   @Query("""
	          update JpaBoardEntity jbe
	          set jbe.userId = :userId
	             , jbe.userName = :userName
	             , jbe.boardSubject  = :boardSubject
	             , jbe.boardContent = :boardContent
	          where jbe.boardNum = :boardNum 
	         """)
	   int boardUpdate1(@Param("boardNum") int boardNum
	         , @Param("userId") String userId
	         , @Param("userName")String userName
	         , @Param("boardSubject")String boardSubject
	         , @Param("boardContent")String boardContent
	         );
	   
	   //삭제
	   @Modifying
	   @Query("""
		         delete from JpaBoardEntity jbe
		         where jbe.boardNum = :num         
		         """)
	   
	   int deleteQuery(@Param("num") int num);
	   
	   List<JpaBoardEntity> findAllByUserNameContaining(Object searchTxt, Sort sort);

	   List<JpaBoardEntity> findAllByBoardSubjectContaining(Object searchTxt, Sort sort);

	   List<JpaBoardEntity> findAllByBoardSubjectOrBoardContent(Object searchTxt, Object searchTxt2, Sort sort);


}

