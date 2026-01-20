package com.pawject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pawject.domain.Tester;

public interface TesterRepository extends JpaRepository<Tester, Long> {
	//삭제여부
	List<Tester> findByDeletedFalse();
	
	// 전체게시글 조회 - Oracle 네이티브 페이징  
	//org.springframework.data.jpa.repository.Query;
	//org.springframework.data.repository.query.Param  ##
    @Query(
    	      value = "SELECT * FROM ( " +
    	              "SELECT p.*, ROWNUM AS rnum " +
    	              "FROM (SELECT * FROM TESTER WHERE DELETED = 0 ORDER BY CREATEDAT DESC) p " + 
    	              ") " +
    	              "WHERE rnum BETWEEN :start AND :end",
    	      nativeQuery = true
    	) 
    List<Tester> findPostsWithPaging(@Param("start") int start, @Param("end") int end); 
}

/**
 */
 