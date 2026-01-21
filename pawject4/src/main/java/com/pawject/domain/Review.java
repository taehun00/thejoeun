package com.pawject.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
@Table(name = "REVIEW")
@Getter @Setter
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_seq")
	@SequenceGenerator(name = "review_seq", sequenceName = "REVIEW_SEQ", allocationSize = 1)
	private Long reviewid;

	@Column(nullable = false)
	private Integer userid;
//	@Column
//	private Integer brandid;
//	@Column
//	private Integer foodid;
	@Column
	private String rating;
	@Column(length = 100)
	private String title;
	@Column(length = 500)
	private String reviewcomment;
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
	
	//user 만들어야됨
//	@ManyToOne
//	@JoinColumn(name = "USERID")
//	private User user;
	
	@ManyToOne
	@JoinColumn(name = "FOODID")
	private Food food;
	
	@ManyToOne
	@JoinColumn(name = "BRANDID")
	private FoodBrand brand;
	
	@OneToMany(mappedBy = "review")
	private List<ReviewImg> reviewimg = new ArrayList<>();
}

/*
 * --6. 리뷰 (FK) USERID : USERS(USERID) / BRANDID : BRAND(BRANDID) /FOODID : FOOD(FOODID)
CREATE TABLE REVIEW (
    REVIEWID    NUMBER PRIMARY KEY,
    USERID      NUMBER NOT NULL,
    BRANDID     NUMBER,
    FOODID      NUMBER,
    RATING      NUMBER(1) CHECK (RATING BETWEEN 1 AND 5),
    TITLE       VARCHAR2(100), 
    REVIEWCOMMENT VARCHAR2(500),
    CREATEDAT   DATE DEFAULT SYSDATE,
    UPDATEDAT   DATE DEFAULT NULL,
    
    CONSTRAINT fk_review_user FOREIGN KEY (USERID)
        REFERENCES USERS(USERID),
        
    CONSTRAINT fk_review_brand FOREIGN KEY (BRANDID)
        REFERENCES FOODBRAND(BRANDID),
        
    CONSTRAINT fk_review_food FOREIGN KEY (FOODID)
        REFERENCES FOOD(FOODID)
);
 **/
