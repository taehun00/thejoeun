package com.pawject.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "FOODNUTRIENT")
public class FoodNutrient {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "foodnutrient_seq")
	@SequenceGenerator(name = "foodnutrient_seq", sequenceName = "FOODNUTRIENT_SEQ", allocationSize = 1)
	private Long id;
	
//	@Column
//	private Integer foodid;
//	@Column
//	private Integer nutrientid;
	@Column
	private Integer amount;

	
	@ManyToOne
	@JoinColumn(name="FOODID", 	nullable = false)
	private Food food;
	
	@ManyToOne
	@JoinColumn(name="NUTRIENTID", 	nullable = false)
	private Nutrient nutrient;
}
/*
 *--4.라벨영양소 (FK) FOODID : FOOD(FOODID) / NUTRIENTID  : NUTRIENT(NUTRIENTID)
CREATE TABLE FOODNUTRIENT(
    FOODID      NUMBER NOT NULL,
    NUTRIENTID  NUMBER NOT NULL,
    AMOUNT      NUMBER,

    CONSTRAINT PK_FOODNUTRIENT PRIMARY KEY (FOODID, NUTRIENTID),

    CONSTRAINT FK_FN_FOOD FOREIGN KEY (FOODID)
        REFERENCES FOOD(FOODID)
        ON DELETE CASCADE,

    CONSTRAINT FK_FN_NUTRIENT FOREIGN KEY (NUTRIENTID)
        REFERENCES NUTRIENT(NUTRIENTID)
);
 */