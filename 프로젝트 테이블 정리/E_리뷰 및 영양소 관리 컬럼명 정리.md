### 👥 5명 역할 분담 

| 담당자 | 역할 | 주요 테이블 |
|--------|------|-------------|
| A | 사용자 및 반려동물 관리 | `USER`, `PET`, `PET_TYPE` |
| B | 사료 및 브랜드 관리 | `FOOD`, `FOOD_BRAND`, `FOOD_INGREDIENT`|
| C | 질환 정보 및 매핑 | `DISEASE`, `PET_DISEASE` |
| D | 추천 및 즐겨찾기 기능 | `FOOD_RECOMMEND`, `FAVORITE_FOOD` |
| E | 리뷰 및 영양소 관리 | `REVIEW`, `NUTRIENT`, `FOODNUTRIENT` + `NUTRIENTRANGE`|  ✔ 테이블 생성 


### --12. table (review) + sequence (review_seq) 리뷰 게시판(사료를 브랜드-제품명 순으로 필터링 후 간단한 한줄평 남기는 게시판이 어떨까요?)
| 컬럼명      | 데이터 타입       | 제약 조건                                                | 설명 |
|-------------|-------------------|----------------------------------------------------------|------|
| `reviewid`  | `NUMBER`          | `PRIMARY KEY`                                            | 리뷰 ID |
| `userid`     | `NUMBER`          | `FOREIGN KEY REFERENCES user(userid)`                   | 작성자 |
| `password`   | `VARCHAR2(50)`     | `not null`                                                | 글 비밀번호|
| `brandid`    | `NUMBER`          | `FOREIGN KEY REFERENCES food_brand(brandid)`            | 브랜드 ID |
| `foodid`    | `NUMBER`          | `FOREIGN KEY REFERENCES food(foodid)`                  | 사료 ID |
| `foodimg`    | `VARCHAR2(300)`   | `우선 null값 채우기`                                      | 사료 이미지 |
| `rating`     | `NUMBER(1)`       | `CHECK (rating BETWEEN 1 AND 5)`                         | 평점 |
| `title`     | `VARCHAR2(100)`       |                                                      | 제목 |
| `review_comment`    | `VARCHAR2(500)`   | —                                                 | 리뷰 내용 |
| `createdat` | `DATE`   | —                                                        | 작성일 |

```
 리뷰id     작성자닉네임    글비밀번호      브랜드id    사료id      사료이미지      평점        제목            리뷰 내용                              작성일
  '1'        'user1'       '1111'        '2'        '03'        null        *****      잘먹어요           '기호성이 너무 좋아서 우리 애가 돼지가 됐어요'      '2025-10-30'
```



### --9. table (foodnutrient) 단순 사료 라벨 데이터
| 컬럼명         | 데이터 타입       | 제약 조건                                                        | 설명 |
|----------------|-------------------|------------------------------------------------------------------|------|
| `foodid`       | `NUMBER`          | `FOREIGN KEY REFERENCES food(foodid)`                          | 사료 ID |
| `nutrientid`   | `NUMBER`          | `FOREIGN KEY REFERENCES nutrient(nutrientid)`                  | 영양소 ID |
| `amount`        | `NUMBER`          | —                                                                | 포함량 |
| **복합키**      |                   | `PRIMARY KEY (foodid, nutrientid)`/미구현                            | 사료-영양소 매핑 |

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

### --14. table (NUTRIENTRANGE) 영양소 기준 설정 - 영양소에 대한 구체적인 기준 예시와 직관적 설명이 필요해 보여서 추가했습니다

| 컬럼명         | 데이터 타입       | 제약 조건        | 설명 |
|----------------|-------------------|------------------|------|
| `rangeid`    |    `NUMBER`          | `PRIMARY KEY`    | 구간 ID |
| `pettypeid`    | `NUMBER`          | `FOREIGN KEY REFERENCES PET_TYPE(pettypeid)`    | 고양이/강아지 구분 ID |
| `nutrientid`   | `NUMBER`          | `FOREIGN KEY REFERENCES nutrient(nutrientid)`    | 영양소 ID |
| `minvalue`      | `NUMBER`    | `NOT NULL` | 최소값|
| `maxvalue`      | `NUMBER`    | `NOT NULL`      | 최대값 |
| `rangelabel`   | `VARCHAR2(50)`    | `NOT NULL`       | 중,저,고 등 직관적 구간 설명 |

```
구간ID   펫타입ID     영양소ID        최소값       최대값      설명
'101'     '1'           '6'         '15'        '25'      '고양이건사료_저단백'
'102'     '1'           '6'         '26'        '40'      '고양이건사료_중단백'
'103'     '1'           '6'         '41'        '55'      '고양이건사료_고단백'
```
>고양이건사료_저단백 부분은 편의를 위한 예시로 넣어놓은 거고요 
>추후 저단백만 남기고 다른 테이블과 조인하여 '고양이' '건사료' '저단백'으로 출력하면 될듯 합니다 
>그리고 저희 건사료만 다루는거 어때여 습식까지 가면 일이 2배....

>영양소 테이블들 조인 예시
```
SELECT FOODID, NUTRIENTNAME, AMOUNT, UNIT, RANGELABEL 
FROM  FOODNUTRIENT A 
JOIN NUTRIENT B ON(A.NUTRIENTID = B.NUTRIENTID) 
JOIN NUTRIENTRANGE C ON(B.NUTRIENTID = C.NUTRIENTID 
AND A.AMOUNT BETWEEN C.MINVALUE AND C.MAXVALUE );

출력: 3(foodid 나중에 사료테이블과 연계하면 사료명 출력),   단백질   30%   고양이건사료_중단백
```
