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
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PETDISEASE")
@Getter @Setter
public class Petdisease {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "petdisease_seq")
	@SequenceGenerator(name = "petdisease_seq", sequenceName = "PETDISEASE_SEQ",  allocationSize = 1 )
	private Long disno;
	
	@Column(nullable = false)
	private Integer adminid;
	
	@Column(nullable = false)
	private Integer pettypeid;
	
	@Column(nullable = false, length = 100)
	private String disname;
	
	@Column(nullable = false, length = 1000)
	private String disexplain;
	
	@Column(length = 500)
	private String recommend;
	
	@Column(nullable = false , name="CREATEDAT")
	private LocalDateTime createdat; // 생성일시
	@Column(nullable = false , name="UPDATEDAT")
	private LocalDateTime updatedat; // 수정일시
	
	@PrePersist
	void onCreate() {
		this.createdat = LocalDateTime.now();
		this.updatedat = LocalDateTime.now();
	}
	
	@PreUpdate
	void onUpdate() { 
		this.updatedat = LocalDateTime.now();
	}

}
/**
 *-- (FK) USERID : USERS(USERID), PETTYPEID : PETTYPE(PETTYPEID)
CREATE TABLE PETDISEASE (
    disno       NUMBER PRIMARY KEY,         -- 글 번호
    adminid      NUMBER NOT NULL,           -- 작성자(운영자-users.userid 참조)
    pettypeid     NUMBER NOT NULL,            -- 반려동물 종류(pettype.pettypeid)
    disname     VARCHAR2(100) NOT NULL,              -- 질환명
    disexplain      VARCHAR2(1000) NOT NULL,              -- 질환 설명
    recommend   VARCHAR2(500),            -- 추천 먹거리(저단백, 저인 등등)
    createdat   DATE DEFAULT SYSDATE,       -- 작성일자
    updatedat   DATE DEFAULT SYSDATE,       -- 수정일
    CONSTRAINT fk_disease_user FOREIGN KEY (userid)
        REFERENCES USERS(userid)         
    CONSTRAINT fk_disease_pettype FOREIGN KEY (pettypeid)
        REFERENCES PETTYPE(pettypeid)    
);

-- 시퀀스
create sequence petdisease_seq;

 */