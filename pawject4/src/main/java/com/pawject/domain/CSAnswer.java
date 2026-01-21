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
@Table(name = "CSANSWER")
@Getter @Setter
public class CSAnswer {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "csanswer_seq")
	@SequenceGenerator(name = "csanswer_seq", sequenceName = "CSANSWER_SEQ", allocationSize = 1)
	private Long answerid;
//	@Column(nullable = false)
//	private Integer questionid;
	@Column(nullable = false)
	private Integer adminid;
	@Column(nullable = false)
	private String content;
	@Column(nullable = false)
	private LocalDateTime createdat;
	
	@PrePersist
	void onCreate() {
		this.createdat=LocalDateTime.now();
	}
	
	@ManyToOne
	@JoinColumn(name = "QUESTIONID")
	private CSQuestion csq;
	
	
	//user 만들어야됨
//	@ManyToOne
//	@JoinColumn(name = "ADMINID", referencedColumnName="USERID")
//	private User user;
}
/*
 * --9. 1:1답변 (FK)QUESTIONID : CSQUESTION(QUESTIONID)/(FK)ADMINID : USERS(USERID)
CREATE TABLE CSANSWER(
  ANSWERID NUMBER PRIMARY KEY,
  QUESTIONID NUMBER NOT NULL,
  ADMINID NUMBER NOT NULL,
  CONTENT CLOB NOT NULL,
  CREATEDAT DATE DEFAULT SYSDATE,
      CONSTRAINT fk_csanswer_question FOREIGN KEY (QUESTIONID)
        REFERENCES CSQUESTION(QUESTIONID),
        
        CONSTRAINT fk_csanswer_admin FOREIGN KEY (ADMINID)
        REFERENCES USERS(USERID)
);

CREATE SEQUENCE CSANSWER_SEQ 
START WITH 1 INCREMENT BY 1
NOCACHE;
 */