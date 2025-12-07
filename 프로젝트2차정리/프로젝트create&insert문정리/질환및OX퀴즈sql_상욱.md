select table_name from user_tables;
DROP TABLE DISEASE;
DROP TABLE DISEASE_OX;
SELECT * FROM DISEASE;


## 질환 리스트
-- 질환 테이블 생성
CREATE TABLE DISEASE (
    disno       NUMBER PRIMARY KEY,         -- 글 번호
    appUserid   NUMBER,                     -- 작성자 ID
    disname     VARCHAR2(100),              -- 질환명
    disex       VARCHAR2(500),              -- 질환 설명
    kindpet     VARCHAR2(100),              -- 반려동물 종류
    infval      VARCHAR2(500),              -- 질환 관련 수치
    mannote     VARCHAR2(500),              -- 주의사항
    bhit        NUMBER DEFAULT 0,           -- 조회수
    createdat   DATE DEFAULT SYSDATE,       -- 작성일자
    bip         VARCHAR2(50),               -- 작성자 IP
    bpass       VARCHAR2(250)               -- 비밀번호
);

-- 시퀀스
create sequence disease_seq;

-- INSERT
insert into disease 
    (DISNO , DISNAME ,DISEX, KINDPET,INFVAL,MANNOTE)
    values(disno_seq.nextval,
    '체리아이 (Cherry Eye)'
    ,'제3안검 눈물샘이 밖으로 돌출.'
    ,'불독, 비글, 코카 스패니얼, 시츄'
    ,'재발률: 단순 봉합술 시 재발률이 **5% ∼ 40%**로 보고되며, 수술 방법에 따라 다름.'
    ,'돌출 지속 시간: 수 시간 이내에 복원 수술을 받는 것이 눈물샘 기능 유지에 유리.'
    );

## 질환 OX 퀴즈
-- 질환 OX 퀴즈 테이블 생성
CREATE TABLE disease_ox (
    oxno        NUMBER           PRIMARY KEY,
    disno       NUMBER           NOT NULL,
    oxquestion  VARCHAR2(300)    NOT NULL,
    oxanswer    CHAR(1)          NOT NULL CHECK (oxanswer IN ('O','X')),
    oxcomment   VARCHAR2(400),
    oxbhit      NUMBER           NOT NULL,
    createdat   DATE             NOT NULL,
    CONSTRAINT fk_disease_ox FOREIGN KEY(disno)
        REFERENCES disease(disno)
);

-- 시퀀스
CREATE SEQUENCE disease_ox_seq;

-- OX 문제 등록 (INSERT)
INSERT INTO disease_ox (
    oxno, disno, oxquestion, oxanswer, oxcomment, oxbhit,createdat
) VALUES (
    disease_ox_seq.NEXTVAL,  #{disno},  #{oxquestion},  #{oxanswer},  #{oxcomment},#{oxbhit},#{sysdate} 
);   

INSERT INTO disease_ox (
    oxno, disno, oxquestion, oxanswer, oxcomment, oxbhit,createdat
) VALUES (
    disease_ox_seq.NEXTVAL, 151,
    '피부 알레르기(피부염)는 음식 알레르기로만 발생한다.', 'X',
    '피부 알레르기는 음식 외에도 환경 알레르기(꽃가루, 집먼지 등)로도 발생합니다.',
    0,sysdate
);