### 👥 5명 역할 분담 

| 담당자 | 역할 | 주요 테이블 |
|--------|------|-------------|
| A | 사용자 및 반려동물 관리 | `USER`, `PET`, `PET_TYPE` |
| B | 사료 및 브랜드 관리 | `FOOD`, `FOOD_BRAND`, `FOOD_INGREDIENT`|
| C | 질환 정보 및 매핑 | `DISEASE`, `PET_DISEASE` |
| D | 추천 및 즐겨찾기 기능 | `FOOD_RECOMMEND`, `FAVORITE_FOOD` |
| E | 리뷰 및 영양소 관리 | `REVIEW`, `NUTRIENT`, `FOOD_NUTRIENT` |  ✔ 테이블 생성 완료


### --12. table (review) + sequence (review_seq) 리뷰 게시판(사료를 브랜드-제품명 순으로 필터링 후 간단한 한줄평 남기는 게시판이 어떨까요?)
| 컬럼명      | 데이터 타입       | 제약 조건                                                | 설명 |
|-------------|-------------------|----------------------------------------------------------|------|
| `reviewid`  | `NUMBER`          | `PRIMARY KEY`                                            | 리뷰 ID |
| `userid`     | `NUMBER`          | `FOREIGN KEY REFERENCES user(userid)`                   | 작성자 |
| `password`   | `VARCHAR2(50)`     | 'not null'                                              | 글 비밀번호|
| `brandid`    | `NUMBER`          | `FOREIGN KEY REFERENCES food_brand(brand_id)`            | 브랜드 ID |
| `foodid`    | `NUMBER`          | `FOREIGN KEY REFERENCES food(food_id)`                  | 사료 ID |
| `foodimg`    | `VARCHAR2(300)`   | `우선 null값 채우기`                                      | 사료 이미지 |
| `rating`     | `NUMBER(1)`       | `CHECK (rating BETWEEN 1 AND 5)`                         | 평점 |
| `reviewcomment`    | `VARCHAR2(500)`   | comment는 등록 불가 컬럼이라 수정                     리뷰 내용 |
| `createdat` |date | DEFAULT SYSDAYE                                                        | 작성일 |

```
 리뷰id     작성자닉네임    글비밀번호      브랜드id    사료id      사료이미지      평점                리뷰 내용                              작성일
  '1'        'user1'       '1111'        '2'        '03'        null         '5'   '기호성이 너무 좋아서 우리 애가 돼지가 됐어요'      '2025-10-30'
```


### --9. table (food_nutrient) 단순 사료 라벨 데이터
| 컬럼명         | 데이터 타입       | 제약 조건                                                        | 설명 |
|----------------|-------------------|------------------------------------------------------------------|------|
| `foodid`       | `NUMBER`          | `FOREIGN KEY REFERENCES food(food_id)`                          | 사료 ID |
| `nutrientid`   | `NUMBER`          | `FOREIGN KEY REFERENCES nutrient(nutrient_id)`                  | 영양소 ID |
| `amount`        | `NUMBER`          | —                                                                | 포함량 |
| **복합키**      |                   | `PRIMARY KEY (food_id, nutrient_id)`/미구현                            | 사료-영양소 매핑 |

```
사료id      영양소id       포함량
 '02'         '6'           '30'
```

### --8. table (nutrient) + sequence (nutrient_seq) 영양소의 단위 
| 컬럼명         | 데이터 타입       | 제약 조건        | 설명 |
|----------------|-------------------|------------------|------|
| `nutrientid`   | `NUMBER`          | `PRIMARY KEY`    | 영양소 ID |
| `nutrientname` | `VARCHAR2(100)`   | `NOT NULL`       | 영양소 이름 |
| `unit`          | `VARCHAR2(50)`    | —                | 단위 (g, mg 등) |
    
```
영양소id    영양소이름      단위
'6'         '단백질'       '%'
``` 

### --14. table (NUTRIENT_RANGE) 영양소 기준 설정 - 영양소에 대한 구체적인 기준 예시와 직관적 설명이 필요해 보여서 추가했습니다

| 컬럼명         | 데이터 타입       | 제약 조건        | 설명 |
|----------------|-------------------|------------------|------|
| `rangeid`    |    `NUMBER`          | `PRIMARY KEY`    | 구간 ID |
| `pettypeid`    | `NUMBER`          | `FOREIGN KEY REFERENCES PET_TYPE(pet_type_id)`    | 고양이/강아지 구분 ID |
| `nutrientid`   | `NUMBER`          | `FOREIGN KEY REFERENCES nutrient(nutrient_id)`    | 영양소 ID |
| `minvalue`      | `NUMBER`    | `NOT NULL` | 최소값|
| `maxvalue`      | `NUMBER`    | `NOT NULL`      | 최대값 |
| `rangelabel`   | `VARCHAR2(50)`    | `NOT NULL`       | 중,저,고 등 직관적 구간 설명 |

```
펫타입ID    구간id     영양소ID        최소값       최대값      설명
'1'     '101'           '6'         '15'        '25'      '고양이건사료_저단백'
'1'     '102'           '6'         '26'        '40'      '고양이건사료_중단백'
'1'     '103'           '6'         '41'        '55'      '고양이건사료_고단백'
```

>영양소 테이블들 조인 예시
```
SELECT FOODID, NUTRIENTNAME, AMOUNT, UNIT, RANGELABEL 
FROM  FOODNUTRIENT A 
JOIN NUTRIENT B ON(A.NUTRIENTID = B.NUTRIENTID) 
JOIN NUTRIENTRANGE C ON(B.NUTRIENTID = C.NUTRIENTID 
AND A.AMOUNT BETWEEN C.MINVALUE AND C.MAXVALUE );

출력: 3(foodid 나중에 사료테이블과 연계하면 사료명 출력),   단백질   30%   고양이건사료_중단백(이 부분은 고양이-강아지 종별, 건사료-습식사료별 기준치가 전부 달라서 습식은 빼는 게 나을 수도 있을 것 같습니다 아니면 테이블을 하나 더 만들어야 하나...? )  
```
