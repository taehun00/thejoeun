package com.pawject.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="FOODBRAND")
@Getter @Setter
public class FoodBrand {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "brand_seq")
	@SequenceGenerator(name = "brand_seq", sequenceName = "BRAND_SEQ", allocationSize = 1)
	private Long brandid;
	
	@Column(nullable = false, length = 100)
	private String brandname;
	
	@Column(length = 100)
	private String country;
	
	@Column(nullable = false, length = 50)
	private String brandtype;
	
	@Column(length = 50)
	private String origin;
	
	@Column(length = 500)
	private String brandinfo;
	
	@OneToMany(mappedBy = "brand")
	private List<Food> food = new ArrayList<>();
	
	@OneToMany(mappedBy = "brand")
	private List<Review> review = new ArrayList<>();
	
}
/* 사용 메서드 - brnadSelectAll 하나
 * CREATE TABLE FOODBRAND (
    BRANDID        NUMBER           PRIMARY KEY,
    BRANDNAME      VARCHAR2(100)    NOT NULL,
    COUNTRY        VARCHAR2(100),
    BRANDTYPE      VARCHAR2(50)     NOT NULL,   
    ORIGIN         VARCHAR2(50),             
    BRANDINFO      VARCHAR2(500)

);
 */
