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
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "NUTRIENTRANGE")
@Getter @Setter
public class NutrientRange {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nutrientrange_seq")
	@SequenceGenerator(name = "nutrientrange_seq", sequenceName = "NUTRIENTRANGE_SEQ", allocationSize = 1)
	private Long rangeid;
	
	@Column(nullable = false)
	private Integer pettypeid;
//	@Column(nullable = false)
//	private Integer nutrientid;
	@Column(nullable = false)
	private Integer minvalue;
	@Column(nullable = false)
	private Integer maxvalue;
	@Column(nullable = false, length = 50)
	private String rangelabel;

	@ManyToOne
	@JoinColumn(name = "NUTRIENTID", nullable = false)
	private Nutrient nutrient;
	
	//pettype 테이블 있어야됨
//	@ManyToOne
//	@JoinColumn(name="PETTYPEID", nullable=false)
//	private Pettype pettype;

}

/*
 * --5.영양소범위 (FK) NUTRIENTID : NUTRIENT(NUTRIENTID), PETTYPE(PETTYPEID)		
CREATE TABLE NUTRIENTRANGE (
    RANGEID      NUMBER          PRIMARY KEY,
    PETTYPEID    NUMBER          NOT NULL,
    NUTRIENTID   NUMBER          NOT NULL,
    MINVALUE     NUMBER          NOT NULL,
    MAXVALUE     NUMBER          NOT NULL,
    RANGELABEL   VARCHAR2(50)    NOT NULL,

    CONSTRAINT FK_NR_PETTYPE FOREIGN KEY (PETTYPEID)
        REFERENCES PETTYPE(PETTYPEID),

    CONSTRAINT FK_NR_NUTRIENT FOREIGN KEY (NUTRIENTID)
        REFERENCES NUTRIENT(NUTRIENTID)
);

 */