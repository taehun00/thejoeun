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
    REFERENCES pettype(pettypeid)
);

CREATE SEQUENCE pet_seq;

INSERT INTO pet VALUES (pet_seq.NEXTVAL, 1, '겨울이', '페르시안', '2022-06-12', 1, SYSDATE);

-- B 파트

CREATE TABLE foodbrand(
brandid NUMBER PRIMARY KEY,
brandname VARCHAR2(100) NOT NULL, country VARCHAR2(100)
);

CREATE TABLE food (

  foodid NUMBER PRIMARY KEY,
  foodname VARCHAR2(100) NOT NULL,
  brandid NUMBER,
  description VARCHAR2(500) NOT NULL,
  mainingredient varchar(200) NOT NULL,
  subingredient VARCHAR2(200),
  pettypeid NUMBER,
  FOREIGN KEY (brandid) REFERENCES foodbrand(brandid),
  FOREIGN KEY (pettypeid) REFERENCES pettype(pettypeid)
);

CREATE SEQUENCE foodseq START WITH 1 INCREMENT BY 1;

-- FOOD BRAND

INSERT INTO foodbrand (brandid, brandname, country) VALUES (1, '네밥이아니야', '미국');
INSERT INTO foodbrand (brandid, brandname, country) VALUES (2, '명냥스티드', '캐나다');
INSERT INTO foodbrand (brandid, brandname, country) VALUES (3, '모모와밥상', '일본');
INSERT INTO foodbrand (brandid, brandname, country) VALUES (4, '밥쌈없다', '네덜란드');
INSERT INTO foodbrand (brandid, brandname, country) VALUES (5, '식탁의정체', '대한민국');
INSERT INTO foodbrand (brandid, brandname, country) VALUES (6, '츄츄는고양이였다', '호주');
INSERT INTO foodbrand (brandid, brandname, country) VALUES (7, '푸드랑탐탐', '뉴질랜드');

SELECT * FROM foodbrand;
COMMIT;


-- FOOD INSERT

INSERT INTO food (foodid, foodname, brandid, description, mainingredient, subingredient, pettypeid)
VALUES (foodseq.NEXTVAL, '처방식 신장케어 연어 앤 귀리', 1, '신장 건강을 위한 연어와 귀리의 조화, 물 많이 마시게 될지도?', '연어', '귀리', 1);

INSERT INTO food (foodid, foodname, brandid, description, mainingredient, subingredient, pettypeid)
VALUES (foodseq.NEXTVAL, '처방식 심장케어 연어 앤 치아씨드', 1 ,'심장 튼튼 프로젝트, 연어와 치아씨드로 펄떡펄떡!', '연어', '치아씨드', 2);
INSERT INTO food VALUES (foodseq.NEXTVAL, '처방식 알러지케어 오리 앤 감자', 1, '알러지 방어막, 오리와 감자가 든든하게 지켜줍니다.', '오리', '감자', 2);
INSERT INTO food VALUES (foodseq.NEXTVAL, '처방식 체중조절 칠면조 앤 고구마', 1, '다이어트 파트너, 칠면조와 고구마로 살은 빼고 맛은 살리고!', '칠면조', '고구마', 2);
INSERT INTO food VALUES (foodseq.NEXTVAL, '처방식 관절케어 닭고기 앤 브로콜리', 1, '관절을 위한 닭고기와 브로콜리, 뛰는 게 즐거워질지도?', '닭고기', '브로콜리', 2);

INSERT INTO food VALUES (foodseq.NEXTVAL, '시니어 오리 앤 감자', 2, '우아한 노년을 위한 오리와 감자, 품격 있는 한 끼!', '오리', '감자', 1);
INSERT INTO food VALUES (foodseq.NEXTVAL, '라이트 치킨 앤 귀리', 2, '에너지 폭발을 위한 치킨과 귀리, 집사도 놀랄 활력!', '치킨', '귀리', 1);
INSERT INTO food VALUES (foodseq.NEXTVAL, '인도어 청어 앤 고구마', 2, '숲속 체질에 맞춘 청어와 고구마, 자연을 담은 레시피!', '청어', '고구마', 1);
INSERT INTO food VALUES (foodseq.NEXTVAL, '라이트 연어 앤 고구마', 2, '짧은 다리에도 근육은 필요해요—연어와 고구마로 탄탄하게!', '연어', '고구마', 2);
INSERT INTO food VALUES (foodseq.NEXTVAL, '시니어 참치 앤 감자', 2, '웃는 얼굴을 위한 참치와 감자, 기분도 사료도 촉촉하게!', '참치', '감자', 2);
INSERT INTO food VALUES (foodseq.NEXTVAL, '처방식 알러지케어 연어 앤 귀리', 2, '알러지 걱정 없는 연어와 귀리, 예민한 아이에게 딱!', '연어', '귀리', 1);
INSERT INTO food VALUES (foodseq.NEXTVAL, '시니어 참치 앤 현미', 2, '노년을 위한 참치와 현미, 부드럽고 소화 잘 되는 한 끼!', '참치', '현미', 1);

INSERT INTO food VALUES (foodseq.NEXTVAL, '키튼 참치 앤 현미', 3, '부드러운 털을 위한 참치와 현미, 고양이계의 실크로드!', '참치', '현미', 2);
INSERT INTO food VALUES (foodseq.NEXTVAL, '인도어 참치 앤 감자', 3, '활동량 많은 아이를 위한 참치와 감자, 산책 후에도 에너지 충전!', '참치', '감자', 2);
INSERT INTO food VALUES (foodseq.NEXTVAL, '처방식 피부케어 연어 앤 치아씨드', 3, '피부 고민 해결사, 연어와 치아씨드로 반짝반짝!', '연어', '치아씨드', 2);
INSERT INTO food VALUES (foodseq.NEXTVAL, '라이트 닭고기 앤 치아씨드', 3, '활동량 많은 아이를 위한 닭고기와 치아씨드, 가볍고 든든하게!', '닭고기', '치아씨드', 2);
INSERT INTO food VALUES (foodseq.NEXTVAL, '헬시웨이트 연어 앤 감자', 3, '체중 조절을 위한 연어와 감자, 맛있게 건강하게!', '연어', '감자', 2);

INSERT INTO food VALUES (foodseq.NEXTVAL, '라이트 오리 앤 귀리', 4, '체중 관리, 오리와 귀리로 맛있게 슬림하게!', '오리', '귀리', 2);
INSERT INTO food VALUES (foodseq.NEXTVAL, '시니어 연어 앤 감자', 4, '노년을 위한 연어와 감자, 뇌도 입맛도 만족!', '연어', '감자', 2);
INSERT INTO food VALUES (foodseq.NEXTVAL, '키튼 치킨 앤 현미', 4, '귀여운 성장기, 치킨과 현미로 작지만 강하게!', '치킨', '현미', 2);
INSERT INTO food VALUES (foodseq.NEXTVAL, '인도어 청어 앤 감자', 4, '든든한 하루를 위한 청어와 감자, 묵직한 매력에 영양까지!', '청어', '감자', 2);

INSERT INTO food VALUES (foodseq.NEXTVAL, '시니어 닭고기 앤 치아씨드', 5, '부드러운 털을 위한 닭고기와 치아씨드, 털복숭이의 선택!', '닭고기', '치아씨드', 1);
INSERT INTO food VALUES (foodseq.NEXTVAL, '시니어 청어 앤 귀리', 5, '든든한 하루를 위한 청어와 귀리, 묵직한 매력에 영양까지!', '청어', '귀리', 2);
INSERT INTO food VALUES (foodseq.NEXTVAL, '키튼 오리 앤 귀리', 5, '귀여운 키튼 시절을 위한 오리와 귀리, 작지만 영양은 꽉!', '오리', '귀리', 2);
INSERT INTO food VALUES (foodseq.NEXTVAL, '키튼 오리 앤 현미', 5, '작은 입에도 잘 맞는 오리와 현미, 키튼 시절을 위한 영양 설계!', '오리', '현미', 2);

INSERT INTO food VALUES (foodseq.NEXTVAL, '키튼 치킨 앤 청어', 6, '우아한 성장기, 치킨과 청어로 품격 있는 한 끼!', '치킨', '청어', 2);
INSERT INTO food VALUES (foodseq.NEXTVAL, '시니어 연어 앤 치아씨드', 6, '예민한 입맛을 위한 연어와 치아씨드, 까다로운 고양이도 OK!', '연어', '치아씨드', 1);
INSERT INTO food VALUES (foodseq.NEXTVAL, '처방식 요로케어 닭고기 앤 크랜베리', 6, '요로 건강을 위한 닭고기와 크랜베리, 화장실 걱정 끝!', '닭고기', '크랜베리', 1);
INSERT INTO food VALUES (foodseq.NEXTVAL, '인도어 참치 앤 브로콜리', 6, '실내 생활에 맞춘 참치와 브로콜리, 속 편한 하루를 위한 선택!', '참치', '브로콜리', 1);

INSERT INTO food VALUES (foodseq.NEXTVAL, '인도어 참치 앤 고구마', 7, '실크 같은 털을 위한 참치와 고구마, 미용실 갈 필요 없어요!', '참치', '고구마', 1);
INSERT INTO food VALUES (foodseq.NEXTVAL, '헬시웨이트 닭고기 앤 브로콜리', 7, '귀여운 체형을 위한 닭고기와 브로콜리, 균형 잡힌 귀여움!', '닭고기', '브로콜리', 1);
INSERT INTO food VALUES (foodseq.NEXTVAL, '키튼 연어 앤 현미', 7, '짧은 다리에도 에너지 충전, 연어와 현미로 점프력 상승!', '연어', '현미', 2);
INSERT INTO food VALUES (foodseq.NEXTVAL, '인도어 칠면조 앤 고구마', 7, '민감한 피부를 위한 칠면조와 고구마, 부드럽게 케어!', '칠면조', '고구마', 2);


-- C파트

create sequence exerciseinfo_seq;

create table exerciseinfo(
    execid         int           primary key,
    exectype       VARCHAR2(50),
    description    VARCHAR2(255),
    avgkcal30min   FLOAT,
    exectargetmin  int,
    suitablefor    VARCHAR2(100),
    intensitylevel VARCHAR2(100),
    createdat      DATE  DEFAULT SYSDATE,
    updatedat      DATE  DEFAULT SYSDATE
 );

insert into exerciseinfo (execid, exectype, description, avgkcal30min, exectargetmin, suitablefor, intensitylevel ) 
values(exerciseinfo_seq.nextval, '산책', '기본적인 야외활동/스트레스 해소', 80.0, 30, '모든 견종, 노령견 포함', '저강도' );

insert into exerciseinfo (execid, exectype, description, avgkcal30min, exectargetmin, suitablefor, intensitylevel ) 
values(exerciseinfo_seq.nextval,
       '노즈워크', 
       '간식을 숨겨두고 냄새로 찾게 하는 놀이로, 정신 자극과 집중력 향상에 좋습니다.', 
       60.0,
       20, 
       '실내 생활견, 고양이도 가능',
       '저강도');
       
insert into exerciseinfo (execid, exectype, description, avgkcal30min, exectargetmin, suitablefor, intensitylevel ) 
values(exerciseinfo_seq.nextval,
       '수영', 
       '관절에 부담이 적고 전신 근육을 사용하는 고강도 운동', 
       120.0, 
       25, 
       '중형견 이상, 관절 약한 반려동물', 
       '고강도'
 );
  
insert into exerciseinfo (execid, exectype, description, avgkcal30min, exectargetmin, suitablefor, intensitylevel ) 
values(exerciseinfo_seq.nextval,
        '터그놀이', 
        '줄다리기 형태의 놀이로, 근력과 집중력을 동시에 향상', 
        70.0,
        15, 
        '활동적인 소형견, 고양이도 가능',
        '중강도'
        );
  
 
insert into exerciseinfo (execid, exectype, description, avgkcal30min, exectargetmin, suitablefor, intensitylevel ) 
values(exerciseinfo_seq.nextval,
        '레이저 포인터 추적', 
        '고양이에게 인기 있는 실내 운동, 사냥 본능을 자극', 
        50.0, 
        10, 
        '고양이 전용, 실내 생활 반려동물', 
        '중강도'
    );


-- D 파트

-- 질환 보드 테이블
create table DISEASE(
    disno number   primary key,
    disname varchar2(50),
    disex   varchar2(150),
    kindpet varchar2(200),
    infval  varchar2(200),
    mannote varchar2(200)
    );

create sequence disease_seq;
create sequence disno_seq;

insert into disease 
    values(disno_seq.nextval
    ,'고관절 이형성증 (CHD)'
    ,'대퇴골과 골반 연결 부위 비정상 발달로 통증 및 관절염 유발.'
    ,'리트리버, 셰퍼드 등 대형견'
    ,'발생률: 특정 대형견 품종에서 **15% ∼ 50%**까지 보고됨.'
    ,'🚨 OFA/PennHIP 평가: 유전적 소양을 생후 4개월부터 평가하여 관리 방향 설정.');
    
    insert into disease 
    values(disno_seq.nextval,
    '슬개골 탈구 (PL)'
    ,'무릎의 슬개골이 정상 위치에서 벗어남.'
    ,'말티즈, 푸들, 포메라니안 등 소형견'
    ,'국내 유병률: 소형견에서 60% ∼ 70% 이상 보고됨.'
    ,'등급 구분: 4단계 (Grade I ∼ IV) 로 구분되며, 보통 Grade II 이상에서 수술적 교정을 고려.');
    
    insert into disease 
    values(disno_seq.nextval,
    '비대성 심근증 (HCM)'
    ,'심장 근육이 두꺼워져 심장 기능 저하. 고양이 최다 발병 심장병.'
    ,'메인쿤, 랙돌, 스핑크스'
    ,'메인쿤 발생률: 20% ∼ 30% 유전적 소인 높음.'
    ,'벽 두께: 좌심실 후벽 및 중격의 두께가 6 mm 이상일 때 진단 (품종별 기준 다름).');
    
    insert into disease 
    values(disno_seq.nextval,
    '추간판 탈출증 (IVDD)'
    ,'척추 디스크 돌출로 신경 압박.'
    ,'닥스훈트, 웰시코기 등 장단족 견종'
    ,'닥스훈트 발생률: 전체 견종 중 약 20% ∼ 25% 차지 (유전적으로 취약).'
    ,'등급 구분: 5단계 (Grade I ∼ V) 로 구분되며, Grade III 이상은 마비 증상 위험.');
    
    insert into disease 
    values(disno_seq.nextval,
    '기관 허탈 (기관지 붕괴)'
    ,'기관 연골 약화로 기관이 납작하게 좁아짐.'
    ,'요크셔테리어, 포메라니안, 치와와'
    ,'발병 연령: 주로 4 ∼ 14세에 나타나며, 소형견 기관 질환의 **약 80%**를 차지.'
    ,'단계 구분: 기관 내강이 0% ∼ 100% 좁아진 정도로 심각성 평가.');
    

    
    insert into disease 
    values(disno_seq.nextval,
    '진행성 망막 위축 (PRA)'
    ,'망막 시세포 점진적 손상으로 실명.'
    ,'푸들, 코카 스패니얼, 닥스훈트'
    ,'실명 진행: 초기(야맹증)부터 최종 실명까지 수개월 ∼ 2년 내외 소요 (품종별 다름).'
    ,'유전자 검사: 특정 품종은 DNA 검사로 발병 유전자 보유 여부를 99% 정확도로 확인 가능.'
    );
    
    insert into disease 
    values(disno_seq.nextval,
    '다낭성 신장 질환 (PKD)'
    ,'신장에 낭종(물혹)이 생겨 신부전 유발.'
    ,'페르시안, 엑조틱, 히말라얀'
    ,'페르시안 유병률: 지역 및 혈통에 따라 **최대 30% ∼ 50%**에서 유전자 검사 양성.'
    ,'낭종 크기: 신장 초음파 상 1 mm 크기의 낭종부터 확인 가능하며 크기 증가에 따라 신기능 저하.'
    );
    
    
    insert into disease 
    values(disno_seq.nextval,
    '뇌수두증 (Hydrocephalus)'
    ,'뇌척수액 과다 축적으로 뇌 압박.'
    ,'치와와, 말티즈, 포메라니안 등 소형견'
    ,'진단 기준: MRI/CT 상 뇌실 지수(VH Ratio)가 0.4 이상일 때 의심.'
    ,'수술 성공률: 션트 수술 성공률은 보고에 따라 **50% ∼ 90%**로 다양.'
    );
    
    insert into disease 
    values(disno_seq.nextval,
    '폰 빌레브란트 병 (vWD)'
    ,'혈액 응고 인자 부족으로 출혈 경향.'
    ,'도베르만, 셰틀랜드 쉽독, 푸들'
    ,'도베르만 유병률: **약 60%**의 개체가 유전자 변이를 보유하며, **최소 10%**가 출혈 증상을 보임.'
    ,'응고 인자 수치: vWF 인자 농도가 50% 이하일 때 임상 증상 위험 증가.'
    );
    
    insert into disease 
    values(disno_seq.nextval,
    '체리아이 (Cherry Eye)'
    ,'제3안검 눈물샘이 밖으로 돌출.'
    ,'불독, 비글, 코카 스패니얼, 시츄'
    ,'재발률: 단순 봉합술 시 재발률이 **5% ∼ 40%**로 보고되며, 수술 방법에 따라 다름.'
    ,'돌출 지속 시간: 수 시간 이내에 복원 수술을 받는 것이 눈물샘 기능 유지에 유리.'
    );

-- E 파트

--리뷰 테이블
CREATE TABLE REVIEW (
    REVIEWID NUMBER PRIMARY KEY,
    USERID NUMBER,
    PASSWORD VARCHAR2(50) NOT NULL,
    BRANDID NUMBER,
    FOODID NUMBER,
    FOODIMG VARCHAR2(300),
    RATING NUMBER(1) CHECK (RATING BETWEEN 1 AND 5),
    TITLE VARCHAR2(100), 
    REVIEWCOMMENT VARCHAR2(500),
    CREATEDAT DATE DEFAULT SYSDATE
);

CREATE SEQUENCE REVIEW_SEQ;

--샘플 생성
INSERT INTO REVIEW VALUES(1, 1, '1111', 1, 3, NULL, 5, '잘먹어요', '기호성이 너무 좋아서 우리 애가 돼지가 됐어요', SYSDATE ); 
INSERT INTO REVIEW VALUES(2, 2, '1111', 2, 2, NULL, 4, '완전 금사료', '애기는 좋아하는데 제 지갑이 싫어해서 별점 하나 뺍니다 할인 없나요', SYSDATE ); 
--------------------------------------------------------------------------------
--라벨영양소 테이블 : 각각의 사료에 대한 단순 영양소 데이터 입력
CREATE TABLE FOODNUTRIENT(
    FOODID NUMBER,
    NUTRIENTID NUMBER,
    AMOUNT NUMBER
);

-- [1] 네밥이아니야_처방식 신장케어 연어 앤 귀리 (고양이)
INSERT INTO foodnutrient VALUES (1, 6, 22);  -- 단백질 (저단백)
INSERT INTO foodnutrient VALUES (1, 1, 0.4); -- 인 (저인)
INSERT INTO foodnutrient VALUES (1, 2, 0.18);-- 나트륨 (저나트륨)
INSERT INTO foodnutrient VALUES (1, 5, 10);  -- 지방 (낮음)
INSERT INTO foodnutrient VALUES (1, 4, 32);  -- 탄수화물 (보통)
INSERT INTO foodnutrient VALUES (1, 7, 0.07);-- 마그네슘
INSERT INTO foodnutrient VALUES (1, 8, 6);   -- 구리

-- [2] 네밥이아니야_처방식 심장케어 연어 앤 치아씨드 (강아지)
INSERT INTO foodnutrient VALUES (2, 6, 30);  -- 단백질 (적정)
INSERT INTO foodnutrient VALUES (2, 1, 0.8); -- 인 (보통)
INSERT INTO foodnutrient VALUES (2, 2, 0.18);-- 나트륨 (낮음)
INSERT INTO foodnutrient VALUES (2, 5, 13);  -- 지방 (보통)
INSERT INTO foodnutrient VALUES (2, 4, 30);  -- 탄수화물 (보통)
INSERT INTO foodnutrient VALUES (2, 7, 0.08);-- 마그네슘
INSERT INTO foodnutrient VALUES (2, 8, 8);   -- 구리

-- [3] 네밥이아니야_처방식 알러지케어 오리 앤 감자 (강아지)
INSERT INTO foodnutrient VALUES (3, 6, 32);  -- 단백질 (중단백)
INSERT INTO foodnutrient VALUES (3, 1, 0.8); -- 인 (보통)
INSERT INTO foodnutrient VALUES (3, 2, 0.25);-- 나트륨 (보통)
INSERT INTO foodnutrient VALUES (3, 5, 14);  -- 지방 (보통)
INSERT INTO foodnutrient VALUES (3, 4, 28);  -- 탄수화물 (균형형)
INSERT INTO foodnutrient VALUES (3, 7, 0.09);-- 마그네슘
INSERT INTO foodnutrient VALUES (3, 8, 9);   -- 구리

-- [4] 네밥이아니야_처방식 체중조절 칠면조 앤 고구마 (강아지)
INSERT INTO foodnutrient VALUES (4, 6, 35);  -- 단백질 (고단백)
INSERT INTO foodnutrient VALUES (4, 1, 0.6); -- 인 (보통)
INSERT INTO foodnutrient VALUES (4, 2, 0.25);-- 나트륨 (보통)
INSERT INTO foodnutrient VALUES (4, 5, 8);   -- 지방 (저지방)
INSERT INTO foodnutrient VALUES (4, 4, 38);  -- 탄수화물 (조금 높음)
INSERT INTO foodnutrient VALUES (4, 7, 0.07);-- 마그네슘
INSERT INTO foodnutrient VALUES (4, 8, 9);   -- 구리

-- [5] 네밥이아니야_처방식 관절케어 닭고기 앤 브로콜리 (강아지)
INSERT INTO foodnutrient VALUES (5, 6, 33);  -- 단백질 (중~고단백)
INSERT INTO foodnutrient VALUES (5, 1, 0.9); -- 인 (보통~높음)
INSERT INTO foodnutrient VALUES (5, 2, 0.25);-- 나트륨 (보통)
INSERT INTO foodnutrient VALUES (5, 5, 14);  -- 지방 (보통)
INSERT INTO foodnutrient VALUES (5, 4, 30);  -- 탄수화물 (균형)
INSERT INTO foodnutrient VALUES (5, 7, 0.09);-- 마그네슘
INSERT INTO foodnutrient VALUES (5, 8, 10);  -- 구리


-- [6] 명냥스티드_시니어 오리 앤 감자 (고양이)
INSERT INTO foodnutrient VALUES (6, 6, 36);
INSERT INTO foodnutrient VALUES (6, 1, 0.9);
INSERT INTO foodnutrient VALUES (6, 2, 0.25);
INSERT INTO foodnutrient VALUES (6, 5, 16);
INSERT INTO foodnutrient VALUES (6, 4, 26);
INSERT INTO foodnutrient VALUES (6, 7, 0.09);
INSERT INTO foodnutrient VALUES (6, 8, 9);

-- [7] 명냥스티드_라이트 치킨 앤 귀리 (고양이)
INSERT INTO foodnutrient VALUES (7, 6, 29);
INSERT INTO foodnutrient VALUES (7, 1, 0.7);
INSERT INTO foodnutrient VALUES (7, 2, 0.3);
INSERT INTO foodnutrient VALUES (7, 5, 14);
INSERT INTO foodnutrient VALUES (7, 4, 31);
INSERT INTO foodnutrient VALUES (7, 7, 0.1);
INSERT INTO foodnutrient VALUES (7, 8, 10);

-- [8] 명냥스티드_인도어 청어 앤 고구마 (고양이)
INSERT INTO foodnutrient VALUES (8, 6, 41);
INSERT INTO foodnutrient VALUES (8, 1, 1.0);
INSERT INTO foodnutrient VALUES (8, 2, 0.4);
INSERT INTO foodnutrient VALUES (8, 5, 18);
INSERT INTO foodnutrient VALUES (8, 4, 22);
INSERT INTO foodnutrient VALUES (8, 7, 0.11);
INSERT INTO foodnutrient VALUES (8, 8, 12);

-- [9] 명냥스티드_라이트 연어 앤 고구마 (강아지)
INSERT INTO foodnutrient VALUES (9, 6, 32);
INSERT INTO foodnutrient VALUES (9, 1, 0.8);
INSERT INTO foodnutrient VALUES (9, 2, 0.25);
INSERT INTO foodnutrient VALUES (9, 5, 13);
INSERT INTO foodnutrient VALUES (9, 4, 34);
INSERT INTO foodnutrient VALUES (9, 7, 0.08);
INSERT INTO foodnutrient VALUES (9, 8, 9);

-- [10] 명냥스티드_시니어 참치 앤 감자 (강아지)
INSERT INTO foodnutrient VALUES (10, 6, 27);
INSERT INTO foodnutrient VALUES (10, 1, 0.6);
INSERT INTO foodnutrient VALUES (10, 2, 0.2);
INSERT INTO foodnutrient VALUES (10, 5, 10);
INSERT INTO foodnutrient VALUES (10, 4, 38);
INSERT INTO foodnutrient VALUES (10, 7, 0.07);
INSERT INTO foodnutrient VALUES (10, 8, 7);


-- [11] 명냥스티드_처방식 알러지케어 연어 앤 귀리 (고양이)
INSERT INTO foodnutrient VALUES (11, 6, 31);
INSERT INTO foodnutrient VALUES (11, 1, 0.7);
INSERT INTO foodnutrient VALUES (11, 2, 0.25);
INSERT INTO foodnutrient VALUES (11, 5, 13);
INSERT INTO foodnutrient VALUES (11, 4, 30);
INSERT INTO foodnutrient VALUES (11, 7, 0.09);
INSERT INTO foodnutrient VALUES (11, 8, 9);

-- [12] 명냥스티드_시니어 참치 앤 현미 (고양이)
INSERT INTO foodnutrient VALUES (12, 6, 28);
INSERT INTO foodnutrient VALUES (12, 1, 0.6);
INSERT INTO foodnutrient VALUES (12, 2, 0.22);
INSERT INTO foodnutrient VALUES (12, 5, 11);
INSERT INTO foodnutrient VALUES (12, 4, 34);
INSERT INTO foodnutrient VALUES (12, 7, 0.08);
INSERT INTO foodnutrient VALUES (12, 8, 8);

-- [13] 모모와밥상_키튼 참치 앤 현미 (고양이)
INSERT INTO foodnutrient VALUES (13, 6, 38);
INSERT INTO foodnutrient VALUES (13, 1, 0.9);
INSERT INTO foodnutrient VALUES (13, 2, 0.3);
INSERT INTO foodnutrient VALUES (13, 5, 18);
INSERT INTO foodnutrient VALUES (13, 4, 25);
INSERT INTO foodnutrient VALUES (13, 7, 0.1);
INSERT INTO foodnutrient VALUES (13, 8, 11);

-- [14] 모모와밥상_인도어 참치 앤 감자 (강아지)
INSERT INTO foodnutrient VALUES (14, 6, 31);
INSERT INTO foodnutrient VALUES (14, 1, 0.8);
INSERT INTO foodnutrient VALUES (14, 2, 0.3);
INSERT INTO foodnutrient VALUES (14, 5, 13);
INSERT INTO foodnutrient VALUES (14, 4, 32);
INSERT INTO foodnutrient VALUES (14, 7, 0.09);
INSERT INTO foodnutrient VALUES (14, 8, 9);

-- [15] 모모와밥상_처방식 피부케어 연어 앤 치아씨드 (강아지)
-- 피부 처방 기준: 단백질 중간(30~35), 지방 약간 높게(15~18, 오메가↑)
INSERT INTO foodnutrient VALUES (15, 6, 33);  -- 단백질 중간
INSERT INTO foodnutrient VALUES (15, 1, 0.8);  -- 인 보통
INSERT INTO foodnutrient VALUES (15, 2, 0.25); -- 나트륨 보통
INSERT INTO foodnutrient VALUES (15, 5, 17);   -- 지방 높음(피부용)
INSERT INTO foodnutrient VALUES (15, 4, 28);   -- 탄수화물 보통
INSERT INTO foodnutrient VALUES (15, 7, 0.08); -- 마그네슘 보통
INSERT INTO foodnutrient VALUES (15, 8, 9);    -- 구리

-- [16] 모모와밥상_라이트 닭고기 앤 치아씨드 (강아지)
INSERT INTO foodnutrient VALUES (16, 6, 34);
INSERT INTO foodnutrient VALUES (16, 1, 0.9);
INSERT INTO foodnutrient VALUES (16, 2, 0.3);
INSERT INTO foodnutrient VALUES (16, 5, 10);
INSERT INTO foodnutrient VALUES (16, 4, 37);
INSERT INTO foodnutrient VALUES (16, 7, 0.08);
INSERT INTO foodnutrient VALUES (16, 8, 8);

-- [17] 모모와밥상_헬시웨이트 연어 앤 감자 (강아지)
INSERT INTO foodnutrient VALUES (17, 6, 32);
INSERT INTO foodnutrient VALUES (17, 1, 0.7);
INSERT INTO foodnutrient VALUES (17, 2, 0.22);
INSERT INTO foodnutrient VALUES (17, 5, 12);
INSERT INTO foodnutrient VALUES (17, 4, 34);
INSERT INTO foodnutrient VALUES (17, 7, 0.09);
INSERT INTO foodnutrient VALUES (17, 8, 9);

-- [18] 밥쌈없다_라이트 오리 앤 귀리 (강아지)
INSERT INTO foodnutrient VALUES (18, 6, 33);
INSERT INTO foodnutrient VALUES (18, 1, 0.8);
INSERT INTO foodnutrient VALUES (18, 2, 0.28);
INSERT INTO foodnutrient VALUES (18, 5, 12);
INSERT INTO foodnutrient VALUES (18, 4, 31);
INSERT INTO foodnutrient VALUES (18, 7, 0.09);
INSERT INTO foodnutrient VALUES (18, 8, 10);

-- [19] 밥쌈없다_시니어 연어 앤 감자 (강아지)
INSERT INTO foodnutrient VALUES (19, 6, 29);
INSERT INTO foodnutrient VALUES (19, 1, 0.7);
INSERT INTO foodnutrient VALUES (19, 2, 0.25);
INSERT INTO foodnutrient VALUES (19, 5, 11);
INSERT INTO foodnutrient VALUES (19, 4, 33);
INSERT INTO foodnutrient VALUES (19, 7, 0.08);
INSERT INTO foodnutrient VALUES (19, 8, 9);

-- [20] 밥쌈없다_키튼 치킨 앤 현미 (강아지)
INSERT INTO foodnutrient VALUES (20, 6, 37);
INSERT INTO foodnutrient VALUES (20, 1, 0.9);
INSERT INTO foodnutrient VALUES (20, 2, 0.3);
INSERT INTO foodnutrient VALUES (20, 5, 17);
INSERT INTO foodnutrient VALUES (20, 4, 25);
INSERT INTO foodnutrient VALUES (20, 7, 0.1);
INSERT INTO foodnutrient VALUES (20, 8, 11);

-- [21] 식탁의정체_시니어 닭고기 앤 치아씨드 (고양이)
INSERT INTO foodnutrient VALUES (21, 6, 33);
INSERT INTO foodnutrient VALUES (21, 1, 0.9);
INSERT INTO foodnutrient VALUES (21, 2, 0.3);
INSERT INTO foodnutrient VALUES (21, 5, 14);
INSERT INTO foodnutrient VALUES (21, 4, 28);
INSERT INTO foodnutrient VALUES (21, 7, 0.09);
INSERT INTO foodnutrient VALUES (21, 8, 10);

-- [22] 식탁의정체_시니어 청어 앤 귀리 (강아지)
INSERT INTO foodnutrient VALUES (22, 6, 30);
INSERT INTO foodnutrient VALUES (22, 1, 0.8);
INSERT INTO foodnutrient VALUES (22, 2, 0.28);
INSERT INTO foodnutrient VALUES (22, 5, 12);
INSERT INTO foodnutrient VALUES (22, 4, 33);
INSERT INTO foodnutrient VALUES (22, 7, 0.09);
INSERT INTO foodnutrient VALUES (22, 8, 8);

-- [23] 츄츄는고양이였다_처방식 요로케어 닭고기 앤 크랜베리 (고양이)
-- 요로 처방 기준: 단백질 중간(25~35), 인↓(≤0.6), 마그네슘↓(≤0.08), 나트륨 보통~조금 낮게
INSERT INTO foodnutrient VALUES (23, 6, 30);  -- 단백질 중간
INSERT INTO foodnutrient VALUES (23, 1, 0.5); -- 인 낮음
INSERT INTO foodnutrient VALUES (23, 2, 0.22);-- 나트륨 약간 낮음
INSERT INTO foodnutrient VALUES (23, 5, 13);  -- 지방 보통
INSERT INTO foodnutrient VALUES (23, 4, 30);  -- 탄수화물 균형
INSERT INTO foodnutrient VALUES (23, 7, 0.07);-- 마그네슘 낮음
INSERT INTO foodnutrient VALUES (23, 8, 8);   -- 구리

-- [24] 식탁의정체_키튼 오리 앤 귀리 (강아지)
INSERT INTO foodnutrient VALUES (24, 6, 36);
INSERT INTO foodnutrient VALUES (24, 1, 0.9);
INSERT INTO foodnutrient VALUES (24, 2, 0.3);
INSERT INTO foodnutrient VALUES (24, 5, 17);
INSERT INTO foodnutrient VALUES (24, 4, 25);
INSERT INTO foodnutrient VALUES (24, 7, 0.1);
INSERT INTO foodnutrient VALUES (24, 8, 11);

-- [25] 츄츄는고양이였다_키튼 치킨 앤 청어 (고양이)
INSERT INTO foodnutrient VALUES (25, 6, 38);
INSERT INTO foodnutrient VALUES (25, 1, 0.9);
INSERT INTO foodnutrient VALUES (25, 2, 0.3);
INSERT INTO foodnutrient VALUES (25, 5, 18);
INSERT INTO foodnutrient VALUES (25, 4, 25);
INSERT INTO foodnutrient VALUES (25, 7, 0.1);
INSERT INTO foodnutrient VALUES (25, 8, 11);

-- [26] 츄츄는고양이였다_시니어 연어 앤 치아씨드 (고양이)
INSERT INTO foodnutrient VALUES (26, 6, 31);
INSERT INTO foodnutrient VALUES (26, 1, 0.8);
INSERT INTO foodnutrient VALUES (26, 2, 0.28);
INSERT INTO foodnutrient VALUES (26, 5, 13);
INSERT INTO foodnutrient VALUES (26, 4, 32);
INSERT INTO foodnutrient VALUES (26, 7, 0.09);
INSERT INTO foodnutrient VALUES (26, 8, 9);

-- [27] 푸드랑탐탐_인도어 참치 앤 고구마 (고양이)
INSERT INTO foodnutrient VALUES (27, 6, 34);
INSERT INTO foodnutrient VALUES (27, 1, 0.9);
INSERT INTO foodnutrient VALUES (27, 2, 0.3);
INSERT INTO foodnutrient VALUES (27, 5, 15);
INSERT INTO foodnutrient VALUES (27, 4, 27);
INSERT INTO foodnutrient VALUES (27, 7, 0.09);
INSERT INTO foodnutrient VALUES (27, 8, 10);

-- [28] 푸드랑탐탐_인도어 칠면조 앤 고구마 (강아지)
INSERT INTO foodnutrient VALUES (28, 6, 32);
INSERT INTO foodnutrient VALUES (28, 1, 0.8);
INSERT INTO foodnutrient VALUES (28, 2, 0.25);
INSERT INTO foodnutrient VALUES (28, 5, 12);
INSERT INTO foodnutrient VALUES (28, 4, 33);
INSERT INTO foodnutrient VALUES (28, 7, 0.08);
INSERT INTO foodnutrient VALUES (28, 8, 8);

--------------------------------------------------------------------------------
--영양소 테이블 : 영양소 이름과 단위 매칭
CREATE TABLE NUTRIENT(
    NUTRIENTID NUMBER PRIMARY KEY,
    NUTRIENTNAME VARCHAR2(100) NOT NULL,
    UNIT VARCHAR2(50) 
);

CREATE SEQUENCE NUTRIENT_SEQ;

INSERT INTO NUTRIENT VALUES (1, '인', '%');
INSERT INTO NUTRIENT VALUES (2, '나트륨', '%');
INSERT INTO NUTRIENT VALUES (3, '요오드', 'mg/kg');
INSERT INTO NUTRIENT VALUES (4, '탄수화물', '%');
INSERT INTO NUTRIENT VALUES (5, '지방', '%');
INSERT INTO NUTRIENT VALUES (6, '단백질', '%');
INSERT INTO NUTRIENT VALUES (7, '마그네슘', '%');
INSERT INTO NUTRIENT VALUES (8, '구리', 'mg/kg');
INSERT INTO NUTRIENT VALUES (9, '칼륨', '%');
INSERT INTO NUTRIENT VALUES (10, '칼슘', '%');
INSERT INTO NUTRIENT VALUES (11, '옥살산', 'mg/kg');

--------------------------------------------------------------------------------
--범위 테이블 : 각각의 영양소가 어느 범위에 속하는지
CREATE TABLE NUTRIENTRANGE(
    RANGEID NUMBER PRIMARY KEY,
    PETTYPEID NUMBER,
    NUTRIENTID NUMBER,
    MINVALUE NUMBER NOT NULL,
    MAXVALUE NUMBER NOT NULL,
    RANGELABEL VARCHAR2(50) NOT NULL
);

--고양이 영양소
-- 단백질
INSERT INTO NUTRIENTRANGE VALUES(1, 1, 6, 15, 25, '고양이건사료_저단백');
INSERT INTO NUTRIENTRANGE VALUES(2, 1, 6, 26, 40, '고양이건사료_중단백');
INSERT INTO NUTRIENTRANGE VALUES(3, 1, 6, 41, 55, '고양이건사료_고단백');

-- 인
INSERT INTO NUTRIENTRANGE VALUES(4, 1, 1, 0, 0.5, '고양이건사료_저인');
INSERT INTO NUTRIENTRANGE VALUES(5, 1, 1, 0.5, 1.2, '고양이건사료_일반인');
INSERT INTO NUTRIENTRANGE VALUES(6, 1, 1, 1.3, 2.0, '고양이건사료_고인');

-- 나트륨
INSERT INTO NUTRIENTRANGE VALUES(7, 1, 2, 0, 0.2, '고양이건사료_저나트륨');
INSERT INTO NUTRIENTRANGE VALUES(8, 1, 2, 0.2, 0.5, '고양이건사료_일반나트륨');
INSERT INTO NUTRIENTRANGE VALUES(9, 1, 2, 0.6, 1.0, '고양이건사료_고나트륨');

-- 요오드 (mg/kg)
INSERT INTO NUTRIENTRANGE VALUES(10, 1, 3, 0.2, 0.4, '고양이건사료_저요오드');
INSERT INTO NUTRIENTRANGE VALUES(11, 1, 3, 0.4, 2.0, '고양이건사료_일반요오드');

-- 탄수화물
INSERT INTO NUTRIENTRANGE VALUES(12, 1, 4, 0, 15, '고양이건사료_저탄수화물');
INSERT INTO NUTRIENTRANGE VALUES(13, 1, 4, 15, 30, '고양이건사료_일반탄수화물');
INSERT INTO NUTRIENTRANGE VALUES(14, 1, 4, 35, 60, '고양이건사료_고탄수화물');

-- 지방
INSERT INTO NUTRIENTRANGE VALUES(15, 1, 5, 0, 10, '고양이건사료_저지방');
INSERT INTO NUTRIENTRANGE VALUES(16, 1, 5, 11, 20, '고양이건사료_중지방');
INSERT INTO NUTRIENTRANGE VALUES(17, 1, 5, 21, 35, '고양이건사료_고지방');

-- 마그네슘
INSERT INTO NUTRIENTRANGE VALUES(18, 1, 7, 0, 0.08, '고양이건사료_저마그네슘');
INSERT INTO NUTRIENTRANGE VALUES(19, 1, 7, 0.08, 0.12, '고양이건사료_일반마그네슘');
INSERT INTO NUTRIENTRANGE VALUES(20, 1, 7, 0.13, 0.3, '고양이건사료_고마그네슘');

-- 구리 (mg/kg)
INSERT INTO NUTRIENTRANGE VALUES(21, 1, 8, 0, 5, '고양이건사료_저구리');
INSERT INTO NUTRIENTRANGE VALUES(22, 1, 8, 5, 15, '고양이건사료_일반구리');
INSERT INTO NUTRIENTRANGE VALUES(23, 1, 8, 20, 40, '고양이건사료_고구리');


--강아지 영양소 
-- 단백질
INSERT INTO NUTRIENTRANGE VALUES(24, 2, 6, 15, 20, '강아지건사료_저단백');
INSERT INTO NUTRIENTRANGE VALUES(25, 2, 6, 21, 30, '강아지건사료_중단백');
INSERT INTO NUTRIENTRANGE VALUES(26, 2, 6, 31, 50, '강아지건사료_고단백');

-- 인
INSERT INTO NUTRIENTRANGE VALUES(27, 2, 1, 0, 0.4, '강아지건사료_저인');
INSERT INTO NUTRIENTRANGE VALUES(28, 2, 1, 0.4, 1.0, '강아지건사료_일반인');

-- 나트륨
INSERT INTO NUTRIENTRANGE VALUES(29, 2, 2, 0, 0.15, '강아지건사료_저나트륨');
INSERT INTO NUTRIENTRANGE VALUES(30, 2, 2, 0.15, 0.4, '강아지건사료_일반나트륨');

-- 칼륨
INSERT INTO NUTRIENTRANGE VALUES(31, 2, 9, 0, 0.4, '강아지건사료_저칼륨');
INSERT INTO NUTRIENTRANGE VALUES(32, 2, 9, 0.4, 0.9, '강아지건사료_일반칼륨');

-- 탄수화물
INSERT INTO NUTRIENTRANGE VALUES(33, 2, 4, 0, 30, '강아지건사료_저탄수화물');
INSERT INTO NUTRIENTRANGE VALUES(34, 2, 4, 30, 55, '강아지건사료_일반탄수화물');

-- 지방
INSERT INTO NUTRIENTRANGE VALUES(35, 2, 5, 0, 8, '강아지건사료_저지방');
INSERT INTO NUTRIENTRANGE VALUES(36, 2, 5, 9, 16, '강아지건사료_중지방');
INSERT INTO NUTRIENTRANGE VALUES(37, 2, 5, 17, 30, '강아지건사료_고지방');

-- 구리
INSERT INTO NUTRIENTRANGE VALUES(38, 2, 8, 0, 4, '강아지건사료_저구리');
INSERT INTO NUTRIENTRANGE VALUES(39, 2, 8, 4, 20, '강아지건사료_일반구리');

-- 마그네슘
INSERT INTO NUTRIENTRANGE VALUES(40, 2, 7, 0, 0.08, '강아지건사료_저마그네슘');

-- 칼슘
INSERT INTO NUTRIENTRANGE VALUES(41, 2, 10, 0, 0.7, '강아지건사료_저칼슘');
INSERT INTO NUTRIENTRANGE VALUES(42, 2, 10, 0.8, 1.8, '강아지건사료_일반칼슘');

-- 옥살산 (정량 기준 없음, 제한용)
INSERT INTO NUTRIENTRANGE VALUES(43, 2, 11, 0, 0, '강아지건사료_옥살산주의');