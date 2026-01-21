package com.pawject.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="FOOD")
@Getter @Setter
public class Food {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "food_seq")
	@SequenceGenerator(name = "food_seq", sequenceName = "FOOD_SEQ", allocationSize = 1)
	private Long foodid;
	
	@Column(nullable = false, length = 100)
	private String foodname;
	@Column(nullable = false)
//	private Integer brandid;
//	@Column(length = 500)
	private String description;
	@Column(nullable = false, length = 200)
	private String mainingredient;
	@Column(length = 200)
	private String subingredient;
	@Column(nullable = false)
	private Integer pettypeid;
	@Column(nullable = false, length = 50)
	private String category;
	@Column(length = 50)
	private String petagegroup;
	@Column(length = 1)
	private String isgrainfree;
	@Column
	private Integer calorie;
	@Column(length = 20)
	private String foodtype;
	@Column(length = 300)
	private String foodimg;
	
	@Column(nullable = false)
	private LocalDateTime createdat;
	
	@Column(nullable = false)
	private LocalDateTime updatedat;
	
	@PrePersist
	void onCreate() {
		this.createdat=LocalDateTime.now();
		this.updatedat=LocalDateTime.now();
	}
	@PreUpdate
	void onUpdate() {
		this.updatedat=LocalDateTime.now();
	}
	
	@ManyToOne
	@JoinColumn(name="BRANDID", nullable=false)
	private FoodBrand brand;
	
	@OneToMany(mappedBy = "food", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<FoodNutrient> foodnutrient = new ArrayList<>();
	
	@OneToMany(mappedBy = "food")
	private List<Review> review = new ArrayList<>();
	
	@OneToMany(mappedBy = "food")
	private List<Tester> tester = new ArrayList<>();
	
	//pettype 테이블 있어야됨
//	@ManyToOne
//	@JoinColumn(name="PETTYPEID", nullable=false)
//	private Pettype pettype;

}
/*
 * --2.푸드 (FK) BRANDID : BFAND(BRANDID)/PETTYPEID : PETS(PETTYPEID) 
CREATE TABLE FOOD (
    FOODID          NUMBER          PRIMARY KEY,
    FOODNAME        VARCHAR2(100)   NOT NULL,
    BRANDID         NUMBER          NOT NULL,
    DESCRIPTION     VARCHAR2(500),
    MAININGREDIENT  VARCHAR2(200)   NOT NULL,
    SUBINGREDIENT   VARCHAR2(200),
    PETTYPEID       NUMBER          NOT NULL,
    CATEGORY        VARCHAR2(50)    NOT NULL,
    PETAGEGROUP     VARCHAR2(50),
    ISGRAINFREE     CHAR(1)         CHECK (UPPER(ISGRAINFREE) IN ('Y','N')),
    CALORIE         NUMBER(5,1),
    FOODTYPE        VARCHAR2(20),
    FOODIMG         VARCHAR2(300),
    CREATEDAT       DATE DEFAULT SYSDATE,
    UPDATEDAT       DATE DEFAULT NULL,

    CONSTRAINT FK_FOOD_BRAND FOREIGN KEY (BRANDID)
        REFERENCES FOODBRAND(BRANDID),

    CONSTRAINT FK_FOOD_PETTYPE FOREIGN KEY (PETTYPEID)
        REFERENCES PETTYPE(PETTYPEID)
);

CREATE SEQUENCE food_seq
START WITH 1 INCREMENT BY 1
NOCACHE;
 */