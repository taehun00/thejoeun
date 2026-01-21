package com.pawject.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "CSQUESTION")
@Getter @Setter
public class CSQuestion {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "csquestion_seq")
	@SequenceGenerator(name = "csquestion_seq", sequenceName = "CSQUESTION_SEQ", allocationSize = 1)
	private Long questionid;

	@Column(nullable = false)
	private Integer userid;
	@Column(length = 50)
	private String category;
	@Column(nullable = false, length = 200)
	private String title;
	@Column(nullable = false, length = 1000)
	private String content;
	@Column
	private String status;
	@Column(nullable = false)
	private LocalDateTime createdat;
	
	@PrePersist
	void onCreate() {
		this.createdat=LocalDateTime.now();
	}
	
	@OneToMany(mappedBy = "csq")
	private List<CSAnswer> caq = new ArrayList<>();
	
	//user 만들어야됨
//	@ManyToOne
//	@JoinColumn(name = "USERID")
//	private User user;
	
}
/*
 * CREATE TABLE CSQUESTION(
  QUESTIONID NUMBER PRIMARY KEY,
  USERID NUMBER NOT NULL,
  CATEGORY VARCHAR2(50),
  TITLE VARCHAR2(200) NOT NULL,
  CONTENT CLOB NOT NULL,
  STATUS NUMBER(1) DEFAULT 0,   
  CREATEDAT DATE DEFAULT SYSDATE,
  
   CONSTRAINT fk_csquestion_user FOREIGN KEY (USERID)
        REFERENCES USERS(USERID) 
  
);

 */