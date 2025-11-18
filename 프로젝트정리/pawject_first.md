### 👥 5명 역할 분담 

| 담당자 | 역할 | 주요 테이블 |
|--------|------|-------------|
| A 태훈 | 사용자 및 반려동물 관리 | `USERS`, `PET`, `PETTYPE` |
| B *** | 사료 및 브랜드 관리 | `FOOD`, `FOODBRAND`, `FOODINGREDIENT`|
| C 상욱 | 질환 정보 및 매핑 | `DISEASE`, `PETDISEASE` |
| D 승현 | 운동 정보 | `EXERCISEINFO` |
| E 현주 | 리뷰 및 영양소 관리 | `REVIEW`, `NUTRIENT`, `FOODNUTRIENT`, `NUTRIENTRANGE` |

### --1. table (users) + sequence (user_seq)
| 컬럼명       | 데이터 타입       | 제약 조건               | 설명 |
|--------------|-------------------|--------------------------|------|
| `userid`     | `NUMBER`          | `PRIMARY KEY`            | 사용자 고유 ID |
| `email`      | `VARCHAR2(200)`   | `NOT NULL`, `UNIQUE`     | 이메일 주소 |
| `nickname`   | `VARCHAR2(100)`   | `NOT NULL`               | 닉네임 |
| `password`   | `VARCHAR2(100)`   | `NOT NULL`               | 비밀번호 |
| `createdat`  | `DATE`            | `NOT NULL`               | 가입일 |

```
userid      email                   nickname        password        createdate
101         'iis07007@naver.com'    '성태훈'         '1234'          '2025-11-03'
```


### --2. table (pettype)
| 컬럼명           | 데이터 타입         | 제약 조건         | 설명 |
|-----------------|-------------------|------------------|------|
| `pettypeid`     | `NUMBER`          | `PRIMARY KEY`    | 반려동물 종류 ID |
| `pettypename`   | `VARCHAR2(100)`   | `NOT NULL`       | 종류 이름 (강아지, 고양이 등) |

이건 고정★★★★
``` 
pettypeid       typename
1               '고양이'
2               '강아지'
```

### --3. table (pet) + sequence (pet_seq)
| 컬럼명         | 데이터 타입         | 제약 조건                                      | 설명 |
|---------------|-------------------|-----------------------------------------------|------|
| `petid`       | `NUMBER`          | `PRIMARY KEY`                                 | 반려동물 고유 ID |
| `userid`      | `NUMBER`          | `FOREIGN KEY REFERENCES user(userid)`         | 사용자 ID |
| `petname`     | `VARCHAR2(100)`   | `NOT NULL`                                    | 반려동물 이름 |
| `petbreed`    | `VARCHAR2(100)`   | `NOT NULL`                                    | 반려동물 종 |
| `birthdate`   | `VARCHAR2(100)`   | —                                             | 생년월일 |
| `pettypeid`   | `NUMBER`          | `FOREIGN KEY REFERENCES pettype(pet_type_id)` | 반려동물 종류 ID |
| `createdat`   | `DATE`            | `NOT NULL`                                    | 반려동물등록일 |

```
petid       userid      petname      petbreed         birthdate       pettypeid
1001        101         '겨울이'     '페르시안'       '2022-06-12'     1
```

### --4. table (foodbrand)  + sequence (foodbrandseq)
| 컬럼명       | 데이터 타입         | 제약 조건         | 설명 |
|-------------|-------------------|------------------|------|
| `brandid`   | `NUMBER`          | `PRIMARY KEY`    | 브랜드 ID |
| `brandname` | `VARCHAR2(100)`   | `NOT NULL`       | 브랜드 이름 |
| `country`   | `VARCHAR2(100)`   | —                | 제조 국가 |


```
브랜드ID      브랜드이름          제조국
'1'	       '츄츄는고양이였다'	'대한민국'
'2'	       '밥쌈없다'           '미국'
```


### --5. table (food) + sequence (foodseq)
| 컬럼명               | 데이터 타입         | 제약 조건                                               | 설명 |
|---------------------|-------------------|--------------------------------------------------------|------|
| `foodid`            | `NUMBER`          | `PRIMARY KEY`                                          | 사료 ID |
| `foodname`          | `VARCHAR2(100)`   | `NOT NULL`                                             | 사료 이름 |
| `brandid`           | `NUMBER`          | `FOREIGN KEY REFERENCES foodbrand(brandid)`            | 브랜드 ID |
| `description`       | `VARCHAR2(500)`   | —                                                      | 설명 |
| `pettypeid`         | `NUMBER`          | `FOREIGN KEY REFERENCES pettype(pettypeid)`            | 대상 반려동물 종류 |

```
푸드ID          푸드네임             브랜드ID                             설명                                       대상반려동물 id
'1'	         '키튼 치킨 앤 청어'	   '1'	    '성장기 고양이를 위한 치킨과 청어 기반의 고단백 건식 사료입니다.'	         '2'
'2'	         '어덜트 연어 앤 현미'	   '2'	    '성묘 고양이의 활력과 소화 건강을 위한 연어와 현미 습식 사료입니다.'	    '2'
```


### --13. table (foodingredient) + sequence (foodingredientseq)
| 컬럼명                | 데이터 타입         | 제약 조건                                             | 설명 |
|----------------------|-------------------|------------------------------------------------------|-----------|
| `foodingredientid`   | `NUMBER`          | `PRIMARY KEY`                                        | 푸드재료 ID |
| `foodid`             | `NUMBER`          | `FOREIGN KEY REFERENCES food(foodid)`                | 사료 ID    |
| `mainingredient`     | `VARCHAR2(200)`   | —                                                    | 주 재료    |
| `subingredient`      | `VARCHAR2(200)`   | —                                                    | 부 재료    |   

```
푸드재료ID   푸드ID      주재료      부재료
'1'	        '1'	    '치킨'	'청어'
'2'	        '2'	    '연어'	'현미'
```

### --6. table (disease) + sequence (disease_seq)

| 컬럼명     | 데이터 타입        | 제약 조건                 | 설명                          |
|------------|-------------------|---------------------------|-------------------------------|
| `disno`    | `NUMBER`          | `PRIMARY KEY`, `NOT NULL` | 질병 고유 번호                 |
| `disname`  | `VARCHAR2(50)`    | `NOT NULL`, `UNIQUE`      | 질병 이름                      |
| `disex`    | `VARCHAR2(150)`   | `NULL`                    | 질병 설명                      |
| `kindpet`  | `VARCHAR2(200)`   | `NOT NULL`                | 해당 질병에 걸리는 반려동물 종류 |
| `infval`   | `VARCHAR2(200)`   | `NULL`                    | 감염 관련 정보 또는 감염값       |
| `mannote`  | `VARCHAR2(200)`   | `NULL`                    | 관리 시 참고 사항               |

# 🧬 DISEASE 테이블 데이터

| DISNO | DISNAME | DISEX | KINDPET  | INFVAL   | MANNOTE |
|-------|---------|-------|--------- |--------  |---------|
| PET-D-048      | 고관절 이형성증 (CHD) | 기관 내강이 0% ∼ 100% 좁아진 정도로 심각성 평가 | 리트리버, 셰퍼드 등 대형견 | 발생률: 특정 대형견 품종에서 **15% ∼ 50%**까지 보고됨. | 🚨 OFA/PennHIP 평가: 유전적 소양을 생후 4개월부터 평가하여 관리 방향 설정. |
| PET-D-049     | 슬개골 탈구(PL) | 기관 내강이 0% ∼ 100% 좁아진 정도로 심각성 평가 | 말티즈, 푸들, 포메라니안 등 소형견 | 국내 유병률: 소형견에서 60% ∼ 70% 이상 보고됨. | 등급 구분: 4단계 (Grade I ∼ IV) 로 구분되며, 보통 Grade II 이상에서 수술적 교정을 고려. |




### --7. table (pet_disease)


| 컬럼명       | 데이터 타입       | 제약 조건                 | 설명             |
|--------------|-------------------|---------------------------|------------------|
| `pdid`       | `NUMBER`          | `PRIMARY KEY`, `NOT NULL` | 진단 고유번호     |
| `disid`      | `VARCHAR2(20)`    | FK `NOT NULL`             | 반려동물 질병 ID  |
| `petid`      | `VARCHAR2(50)`    | FK `NOT NULL`             | 동물id           |


# 🧬 PETDISEASE 테이블 데이터
| PDID      | DISNO (FK)    |petid(FK)     | 
|-------    |-------        |---------     | 
|  1        |PET-D-048      | 1            | 
|  2        |PET-D-049      | 1            | 

> 어떤동물이 어떤질병에 걸렸다라는것말하고 싶은것.
>  초코가 심장병이 있어요.
>  초코가 피부병이 있어요.

---

### --8. table (pet_disease_date)
| 컬럼명       | 데이터 타입        | 제약 조건                | 설명              |
|--------------|-------------------|---------------------------|------------------|
| `검사ID`      | `NUMBER`          | `PRIMARY KEY`, `NOT NULL` | 검사 고유 번호    |
| `Fedm`       | `NUMBER(10)`      | `NULL`                    | 철 관련 수치      |
| `Pdm`        | `NUMBER(10)`      | `NULL`                    | 인 관련 수치      |
| `Proteindm`  | `NUMBER(10)`      | `NULL`                    | 단백질 관련 수치  |

# 🧬 PETDISEASE 테이블 데이터


|검사ID     | PDID       |FEDM        |PDM          |PROTEINDM  | DATE      |
|-------   |-------      |-------     |---------    |--------   |--------   |
| 1        |  1          |80          |1            |25         |2025-11-03 |
| 2        |  1          |80          |1            |25         |2025-11-04 |


### -- 운동정보 (exerciseinfo) + sequence(exerciseinfo_seq)
###운동정보 (1단계 CRUD 파트/ 입력시 _(언더바) 및 줄일 수 있는 필드명은 줄일 예정.)
| 필드명             | 타입           | 설명 |
|--------------------|----------------|------|
| `exercise_id`      | INT (PK)       | 운동 고유 ID |
| `exercise_type`    | VARCHAR(50)    | 운동 종류 (예: 산책, 수영, 노즈워크 등) |
| `description`      | VARCHAR(255)   | 운동에 대한 간단 설명 |
| `avg_calories_30min` | FLOAT        | 평균 칼로리 소모량 (소형견 기준, 30분 기준) |
| `recommended_duration_min` | INT    | 권장 운동 시간 (분) |
| `suitable_for`     | VARCHAR(100)   | 추천 대상 (예: 소형견, 노령견 등) |
| `intensity_level`  | VARCHAR(20)    | 운동 강도 (예: 저강도, 중강도, 고강도) |
| `created_at`       | DATETIME       | 등록일 |
| `updated_at`       | DATETIME       | 수정일 |

```
운동코드(execid)   운동유형(exectype)  운동설명(description)             30분당 평균소모칼로리(avgkcal30min)
     1                  산책           '기본적인 야외활동/스트레스 해소'            80.0

운동권장시간(exectargetmin)           운동추천동물(suitablefor)         운동강도(intensitylevel)
     30                               '모든 견종, 노령견 포함'                   '저강도'

등록일(createdat)                    글수정일(updatedat) 
   디폴트값                                디폴트값
```


### --12. table (review) + sequence (review_seq) 리뷰 게시판(사료를 브랜드-제품명 순으로 필터링 후 간단한 한줄평 남기는 게시판이 어떨까요?)
| 컬럼명              | 데이터 타입       | 제약 조건                                              | 설명 |
|---------------------|-------------------|--------------------------------------------------------|------|
| `reviewid`          | `NUMBER`          | `PRIMARY KEY`                                          | 리뷰 ID |
| `userid`            | `NUMBER`          | `FOREIGN KEY REFERENCES user(userid)`                  | 작성자 |
| `password`          | `VARCHAR2(50)`    | `not null`                                             | 글 비밀번호|
| `brandid`           | `NUMBER`          | `FOREIGN KEY REFERENCES food_brand(brandid)`           | 브랜드 ID |
| `foodid`            | `NUMBER`          | `FOREIGN KEY REFERENCES food(foodid)`                  | 사료 ID |
| `foodimg`           | `VARCHAR2(300)`   | `우선 null값 채우기`                                    | 사료 이미지 |
| `rating`            | `NUMBER(1)`       | `CHECK (rating BETWEEN 1 AND 5)`                       | 평점 |
| `title`             | `VARCHAR2(100)`   |                                                        | 제목 |
| `review_comment`    | `VARCHAR2(500)`   | —                                                      | 리뷰 내용 |
| `createdat`         | `DATE`            | —                                                      | 작성일 |

```
 리뷰id     작성자닉네임    글비밀번호      브랜드id    사료id      사료이미지      평점        제목            리뷰 내용                              작성일
  '1'        'user1'       '1111'        '2'        '03'        null        *****      잘먹어요           '기호성이 너무 좋아서 우리 애가 돼지가 됐어요'      '2025-10-30'
```



### --9. table (foodnutrient) 단순 사료 라벨 데이터
| 컬럼명          | 데이터 타입      | 제약 조건                                                     | 설명 |
|----------------|-------------------|--------------------------------------------------------------|------|
| `foodid`       | `NUMBER`          | `FOREIGN KEY REFERENCES food(foodid)`                        | 사료 ID |
| `nutrientid`   | `NUMBER`          | `FOREIGN KEY REFERENCES nutrient(nutrientid)`                | 영양소 ID |
| `amount`       | `NUMBER`          | —                                                            | 포함량 |
| **복합키**     |                   | `PRIMARY KEY (foodid, nutrientid)`/미구현                     | 사료-영양소 매핑 |

```
사료id      영양소id       포함량
 '02'         '6'           '30'
```

### --8. table (nutrient) + sequence (nutrient_seq) 영양소의 단위 
| 컬럼명          | 데이터 타입       | 제약 조건         | 설명 |
|-----------------|-------------------|------------------|------|
| `nutrientid`    | `NUMBER`          | `PRIMARY KEY`    | 영양소 ID |
| `nutrientname`  | `VARCHAR2(100)`   | `NOT NULL`       | 영양소 이름 |
| `unit`          | `VARCHAR2(50)`    | —                | 단위 (g, mg 등) |
    
```
영양소id    영양소이름      단위
'6'         '단백질'       '%'
``` 

### --14. table (NUTRIENTRANGE) 영양소 기준 설정 - 영양소에 대한 구체적인 기준 예시와 직관적 설명이 필요해 보여서 추가했습니다

| 컬럼명         | 데이터 타입       | 제약 조건                                       | 설명 |
|----------------|-------------------|------------------------------------------------|------|
| `rangeid`      |    `NUMBER`       | `PRIMARY KEY`                                  | 구간 ID |
| `pettypeid`    | `NUMBER`          | `FOREIGN KEY REFERENCES PETTYPE(pettypeid)`    | 고양이/강아지 구분 ID |
| `nutrientid`   | `NUMBER`          | `FOREIGN KEY REFERENCES nutrient(nutrientid)`  | 영양소 ID |
| `minvalue`     | `NUMBER`          | `NOT NULL`                                     | 최소값|
| `maxvalue`     | `NUMBER`          | `NOT NULL`                                     | 최대값 |
| `rangelabel`   | `VARCHAR2(50)`    | `NOT NULL`                                     | 중,저,고 등 직관적 구간 설명 |

```
구간ID   펫타입ID     영양소ID        최소값       최대값      설명
'101'     '1'           '6'         '15'        '25'      '고양이건사료_저단백'
'102'     '1'           '6'         '26'        '40'      '고양이건사료_중단백'
'103'     '1'           '6'         '41'        '55'      '고양이건사료_고단백'
```


**게시판 시안**
<img src="/프로젝트 테이블 정리/img/review001.png" alt="게시판시안1차리스트">
<img src="/프로젝트 테이블 정리/img/review002.png" alt="게시판시안1차글쓰기">
