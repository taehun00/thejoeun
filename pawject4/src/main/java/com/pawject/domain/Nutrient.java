package com.pawject.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "NUTRIENT")
@Getter @Setter
public class Nutrient {
	@Id
	private Long nutrientid;
	@Column(nullable = false, length = 100)
	private String nutrientname;
	@Column(length = 50)
	private String unit;
	
	
	@OneToMany(mappedBy = "nutrient")
	private List<FoodNutrient> foodnutrient = new ArrayList<>();
	
	@OneToMany(mappedBy = "nutrient")
	private List<NutrientRange> nutrientRange = new ArrayList<>();
	

}
/*
 * --3. 영양소 외래키X
CREATE TABLE NUTRIENT(
    NUTRIENTID NUMBER PRIMARY KEY,
    NUTRIENTNAME VARCHAR2(100) NOT NULL,
    UNIT VARCHAR2(50) 
);

 */