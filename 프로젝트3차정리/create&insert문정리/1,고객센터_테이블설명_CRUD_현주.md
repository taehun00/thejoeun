
##프로젝트 신규 테이블 정리 

**고객센터 1:1 문의 - CSQUESTION**

1. DESC  CSQUESTION

| 컬럼명        | 데이터 타입         | 제약 조건                 | 설명 |
|--------------|-------------------|--------------------------|------|
| `QUESTIONID` | `NUMBER`          | `PRIMARY KEY`            | 채팅방 고유 ID |
| `USERID`     | `NUMBER`          | `NOT NULL`               | 사용자 ID |
| `TITLE`      | `VARCHAR2`        | `NOT NULL`               | 관리자 ID |
| `CATEGORY`   | `NUMBER`          | `NOT NULL`               | 분류 |
| `CONTENT`    | `CLOB`            | `NOT NULL`               | 상담 진행 여부 |
| `STATUS`     | `NUMBER`          | `NUMBER(1) DEFAULT 0`    | 상담일 |
| `CREATEDAT`  | `DATE`            | `DEFAULT SYSDATE`        | 종료일 |


이름         널?       유형            
---------- -------- ------------- 
QUESTIONID NOT NULL NUMBER              --질문번호 시퀀스 이용
USERID     NOT NULL NUMBER              --질문하는 유저 아이디
CATEGORY            VARCHAR2(50)        --분류(계정, 서비스, 이벤트, 기타)
TITLE      NOT NULL VARCHAR2(200)       --제목
CONTENT    NOT NULL CLOB                --문의내용
STATUS              NUMBER(1)           --답변상태(0=답변 대기/1=답변 완료)
CREATEDAT           DATE                --작성일


2. CREATE 외래키 없는 버전 

CREATE TABLE CSQUESTION(
  QUESTIONID NUMBER PRIMARY KEY,
  USERID NUMBER NOT NULL,
  CATEGORY VARCHAR2(50),
  TITLE VARCHAR2(200) NOT NULL,
  CONTENT CLOB NOT NULL,
  STATUS NUMBER(1) DEFAULT 0,   
  CREATEDAT DATE DEFAULT SYSDATE
);

CREATE SEQUENCE CSQUESTION_SEQ 
START WITH 1 INCREMENT BY 1
NOCACHE;

3. CRUD 구문 정리

--INSERT
INSERT INTO CSQUESTION(QUESTIONID, USERID, CATEGORY, TITLE, CONTENT)
        VALUES(CSQUESTION_SEQ.NEXTVAL, 1, '기타', '디자인이 너무 이상해요', '이게 최선인가요 쩜 보기 그래요');

--UPDATE(유저 직접 수정X 답변 상태만 변경)
UPDATE CSQUESTION SET STATUS=1 WHERE QUESTIONID=1;

--DELETE(관리자 권한으로 광고글일 경우 삭제)
DELETE FROM CSQUESTION WHERE QUESTIONID=1; 

--SELECT(전체글/특정글/특정유저/미답변)
SELECT * FROM CSQUESTION;
SELECT * FROM CSQUESTION WHERE QUESTIONID=1;
SELECT * FROM CSQUESTION WHERE USERID=1;
SELECT * FROM CSQUESTION WHERE STATUS=0;



**고객센터 1:1 답변 - CSANSWER**

1. DESC CSANSWER

| 컬럼명        | 데이터 타입         | 제약 조건                 | 설명 |
|--------------|-------------------|--------------------------|------|
| `ANSWERID`   | `NUMBER`          | `PRIMARY KEY`            | 답변 고유 ID |
| `QUESTIONID` | `NUMBER`          | `NOT NULL`               | 질문 ID |
| `ADMINID`    | `NUMBER`          | `NOT NULL`               | 운영자 ID |
| `CONTENT`    | `CLOB`            | `NOT NULL`               | 답변 내용 |
| `CREATEDAT`  | `DATE`            | `DEFAULT SYSDATE`        | 생성일 |



이름         널?       유형     
---------- -------- ------ 
ANSWERID   NOT NULL NUMBER      --답변 ID 시퀀스 이용
QUESTIONID NOT NULL NUMBER      --질문 ID(FK-CSQUESTION)
ADMINID    NOT NULL NUMBER      --운영자 ID(FK-USERS)
CONTENT    NOT NULL CLOB        --답변 내용
CREATEDAT           DATE        --생성일


2. CREATE 외래키 없는 버전 

CREATE TABLE CSANSWER(
  ANSWERID NUMBER PRIMARY KEY,
  QUESTIONID NUMBER NOT NULL,
  ADMINID NUMBER NOT NULL,
  CONTENT CLOB NOT NULL,
  CREATEDAT DATE DEFAULT SYSDATE
);

CREATE SEQUENCE CSANSWER_SEQ 
START WITH 1 INCREMENT BY 1
NOCACHE;

3. CRUD 구문 정리

--INSERT
INSERT INTO CSANSWER(ANSWERID, QUESTIONID, ADMINID, CONTENT)
        VALUES(CSANSWER_SEQ.NEXTVAL, 1, 100, '디자이너를 잡아오겠습니다');

--UPDATE(사용X)
UPDATE CSANSWER SET CONTENT='디자이너를 신속히 잡아오겠습니다' WHERE ANSWERID=1;

--DELETE(사용X-데이터 관리 차원)
DELETE FROM CSANSWER WHERE ANSWERID=1; 

--SELECT(전체글/특정글)
SELECT * FROM CSANSWER;
SELECT * FROM CSANSWER WHERE ANSWERID=1;


**챗봇 답변용 DB - FAQ**

1. DESC CHATMESSAGE

| 컬럼명        | 데이터 타입         | 제약 조건                 | 설명 |
|--------------|-------------------|--------------------------|------|
| `FAQID`      | `NUMBER`          | `PRIMARY KEY`            | 질문답변 고유 ID |
| `CATEGORY`   | `NUMBER`          | `NOT NULL`               | 분류 |
| `QUESTION`   | `NUMBER`          | `NOT NULL`               | 질문내용 |
| `ANSWER`     | `NUMBER(1)`       | `NOT NULL`               | 답변내용 |
| `KEYWORDS`   | `VARCHAR2(2000)`  |                          | 키워드 |
| `ISACTIVE`   | `NUMBER`          |                          | 사용여부 |
| `CREATEDAT`  | `DATE`            | `DEFAULT SYSDATE`        | 생성일 |
| `UPDATEDAT`  | `DATE`            |                          | 수정일 |


이름        널?       유형            
--------- -------- ------------- 
FAQID     NOT NULL NUMBER               --질문답변 고유번호(시퀀스 이용)
CATEGORY           VARCHAR2(50)         --분류(계정, 서비스, 이벤트, 기타)
QUESTION  NOT NULL VARCHAR2(500)        --질문
ANSWER    NOT NULL CLOB                 --답변내용
KEYWORDS           VARCHAR2(500)        --매칭키워드
ISACTIVE  NOT NULL NUMBER(1)            --사용여부(1=사용중/0=미사용)
CREATEDAT          DATE                 --생성일
UPDATEDAT          DATE                 --수정일



2. CREATE 외래키 없는 버전
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


3. CRUD 구문 정리
--SELECT(전체 조회/특정 답변/활성화-비활성화/카테고리별 활성화-비활성화 답변)
SELECT * FROM FAQ ORDER BY FAQID;
SELECT * FROM FAQ WHERE FAQID=1;
SELECT * FROM FAQ WHERE ISACTIVE=1 ORDER BY FAQID;
SELECT * FROM FAQ WHERE ISACTIVE=1 AND CATEGORY='계정' ORDER BY FAQID;

--INSERT
INSERT INTO FAQ(FAQID, CATEGORY, QUESTION, ANSWER, KEYWORDS, ISACTIVE)
VALUES (FAQ_SEQ.NEXTVAL, '계정', '펫 등록을 하고 싶어요', '로그인 후 마이페이지의 펫 등록 기능을 이용해 주세요', '펫, 등록, 추가', 1);

--UPDATE(전체수정 이용X/활성화 변경)
UPDATE FAQ SET CATEGORY='기타', QUESTION='관리자에게 문의하고 싶어요', ANSWER='고객센터 채팅 기능을 이용해 주세요. 채팅 상담 이용 시간은 평일 09:00~18:00 입니다', KEYWORDS='관리자, 문의, 채팅, 고객센터', ISACTIVE='1', UPDATEDAT=SYSDATE;

UPDATE FAQ SET  ISACTIVE='0' WHERE FAQID=2;

--


--DELETE(이용X)
DELETE FROM FAQ WHERE FAQID=1;


