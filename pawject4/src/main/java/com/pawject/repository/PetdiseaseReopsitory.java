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

	//페이징+검색 -> 컨트롤러에서 처리
	Page<Petdisease> findByDisnameContaining(
		    String keyword, Pageable pageable
		);
	
	
	
}
