package com.pawject.dto.petdisease;

import com.pawject.domain.Petdisease;
import com.pawject.dto.tester.TesterRequestDto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PetdiseaseResponseDto {

	private int disno;
	private int adminid;
	private Long pettypeid;
	private String disname;
	private String disexplain;
	private String recommend;
	private String createdat;
	private String updatedat;
	
	public static PetdiseaseResponseDto form(Petdisease petdis) {
	    return PetdiseaseResponseDto.builder()
	        .pettypeid(petdis.getPettypeid())
	        .disname(petdis.getDisname())
	        .disexplain(petdis.getDisexplain())
	        .recommend(petdis.getRecommend())
	        .build();
	}
}

/**
 *-- (FK) USERID : USERS(USERID), PETTYPEID : PETTYPE(PETTYPEID)
CREATE TABLE PETDISEASE (
    disno       NUMBER PRIMARY KEY,         -- 글 번호
    adminid      NUMBER NOT NULL,           -- 작성자(운영자-users.userid 참조)
    pettypeid     NUMBER,            -- 반려동물 종류(pettype.pettypeid)
    disname     VARCHAR2(100),              -- 질환명
    disexplain      VARCHAR2(500),              -- 질환 설명
    recommend   VARCHAR2(100),              -- 추천 먹거리(저단백, 저인 등등)
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