package com.pawject.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "REVIEWIMG")
@Getter 
@Setter
public class ReviewImg {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,  generator = "reviewimg_seq")
	@SequenceGenerator(name = "reviewimg_seq", sequenceName = "REVIEWIMG_SEQ")
	private Long reviewimgid;
	
//	@Column
//	private Integer reviewid;
	@Column(nullable = false, length = 300)
	private String reviewimgname;
	@Column(nullable = false)
	private LocalDateTime createdat;
	
	@PrePersist
	void onCreate() {
		this.createdat=LocalDateTime.now();
	}
	
	@ManyToOne
	@JoinColumn(name = "REVIEWID")
	private Review review;
}
/*
 * --7. 리뷰이미지  (FK)	REVIEWID : REVIEW(REVIEWID)	
CREATE TABLE REVIEWIMG (
    REVIEWIMGID NUMBER PRIMARY KEY,
    REVIEWID NUMBER, 
    REVIEWIMGNAME VARCHAR2(300) NOT NULL,
    CREATEDAT DATE DEFAULT SYSDATE,
    FOREIGN KEY (REVIEWID) REFERENCES REVIEW(REVIEWID)
);

CREATE SEQUENCE REVIEWIMG_SEQ
START WITH 1 INCREMENT BY 1
NOCACHE;
 */