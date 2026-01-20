package com.pawject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pawject.domain.Petdisease;


public interface PetdiseaseReopsitory  extends JpaRepository<Petdisease, Long> {

}
