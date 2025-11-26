-------------------------------------------
-- users 테이블, 시퀀스
CREATE TABLE users (
  userid     NUMBER PRIMARY KEY,
  email      VARCHAR2(200) NOT NULL UNIQUE,
  nickname   VARCHAR2(100) NOT NULL,
  password   VARCHAR2(100) NOT NULL,
  createdat  DATE NOT NULL
);

CREATE SEQUENCE users_seq;

-- users 데이터 예시없이 회원가입 하시먼 됩니다.
------------------------------------------
-- pettype 테이블, 최종데이터
CREATE TABLE pettype (
  pettypeid NUMBER PRIMARY KEY,
  pettypename  VARCHAR2(100) NOT NULL
);

INSERT INTO pettype (pettypeid, pettypename) VALUES (1, '고양이');
INSERT INTO pettype (pettypeid, pettypename) VALUES (2, '강아지');

-----------------------------------------
-- pet 테이블, 시퀀스, 임시데이터
CREATE TABLE pet (
  petid      NUMBER PRIMARY KEY,
  userid     NUMBER NOT NULL,
  petname    VARCHAR2(100) NOT NULL,
  petbreed   VARCHAR2(100) NOT NULL,
  birthdate  VARCHAR2(100),
  pettypeid  NUMBER NOT NULL,
  createdat  DATE NOT NULL,
  
  CONSTRAINT fk_pet_user FOREIGN KEY (userid)
    REFERENCES users(userid),
    
  CONSTRAINT fk_pet_type FOREIGN KEY (pettypeid)
    REFERENCES pettypename(pettypeid)
);

CREATE SEQUENCE pet_seq;

INSERT INTO pet VALUES (pet_seq.NEXTVAL, 1, '겨울이', '페르시안', '2022-06-12', 1, SYSDATE);