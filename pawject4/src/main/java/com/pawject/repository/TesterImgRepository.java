package com.pawject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pawject.domain.Testerimg;
@Repository
public interface TesterImgRepository extends JpaRepository<Testerimg, Long> {
	List<Testerimg> findByTester_TesteridIn(List<Long> testerids);

}

/**
 */
 