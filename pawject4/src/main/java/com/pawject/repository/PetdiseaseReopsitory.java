package com.pawject.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pawject.domain.Petdisease;

@Repository
public interface PetdiseaseReopsitory  extends JpaRepository<Petdisease, Long> {

	//펫타입 고정 페이징
	Page<Petdisease> findByPettypeid(Long pettypeid, Pageable pageable);
	
	//검색 - 펫타입은 고정
	@Query("""
		    select p
		    from Petdisease p
		    where p.pettypeid = :pettypeid
		      and (
		            :keyword is null
		            or trim(:keyword) = ''
		            or lower(p.disname) like lower(concat('%', :keyword, '%'))
		      )
		    """)
		Page<Petdisease> searchKeyword(
		        @Param("pettypeid") Long pettypeid,
		        @Param("keyword") String keyword,
		        Pageable pageable
		);
	
}
