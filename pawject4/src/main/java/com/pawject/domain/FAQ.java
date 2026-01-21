package com.pawject.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="FAQ")
@Getter @Setter
public class FAQ {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "faq_seq")
	@SequenceGenerator(name = "faq_seq", sequenceName = "FAQ_SEQ", allocationSize = 1)
	private Long faqid;

	@Column(length = 50)
	private String category;
	
	@Column(nullable = false, length = 500)
	private String question;
	
	@Column(length = 1000)   //명시 안하면 기본 255 들어감
	private String answer;
	
	@Column(length = 500)
	private String keywords;

	@Column(nullable = false)
	private Integer isactive;
	
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
}
/*
 * 
--10. FAQ 외래키X
CREATE TABLE FAQ (
  FAQID NUMBER PRIMARY KEY,
  CATEGORY VARCHAR2(50),
  QUESTION VARCHAR2(500) NOT NULL,
  ANSWER CLOB NOT NULL,
  KEYWORDS VARCHAR2(500),
  ISACTIVE NUMBER(1) DEFAULT 1 NOT NULL,
  CREATEDAT DATE DEFAULT SYSDATE,
  UPDATEDAT DATE
);

CREATE SEQUENCE FAQ_SEQ
START WITH 1 INCREMENT BY 1
NOCACHE;
 */