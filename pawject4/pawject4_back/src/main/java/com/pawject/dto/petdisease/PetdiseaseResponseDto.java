package com.pawject.dto.petdisease;

import com.pawject.domain.Petdisease;
import com.pawject.dto.tester.TesterAdminRequestDto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PetdiseaseResponseDto {

	private Long disno;
	private int adminid;
	private Long pettypeid;
	private String disname;
	private String disexplain;
	private String recommend;
	private String createdat;
	private String updatedat;
	
	public static PetdiseaseResponseDto from(Petdisease p){
	    return PetdiseaseResponseDto.builder()
	            .disno(p.getDisno() == null ? null : p.getDisno().longValue())
	            .adminid(p.getAdmin() == null || p.getAdmin().getUserId() == null ? 0 : p.getAdmin().getUserId().intValue())
	            .pettypeid(p.getPettype() == null ? null : p.getPettype().getPetTypeId())
	            .disname(p.getDisname())
	            .disexplain(p.getDisexplain())
	            .recommend(p.getRecommend())
	            .createdat(p.getCreatedat() == null ? null : p.getCreatedat().toString())
	            .updatedat(p.getUpdatedat() == null ? null : p.getUpdatedat().toString())
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