### 👥 5명 역할 분담 

| 담당자 | 역할 | 주요 테이블 |
|--------|------|-------------|
| A | 사용자 및 반려동물 관리 | `USER`, `PET`, `PET_TYPE` |
| B | 사료 및 브랜드 관리 | `FOOD`, `FOOD_BRAND`, `FOOD_INGREDIENT`|
| C | 질환 정보 및 매핑 | `DISEASE`, `PET_DISEASE` |
| D | 추천 및 즐겨찾기 기능 | `FOOD_RECOMMEND`, `FAVORITE_FOOD` |
| E | 리뷰 및 영양소 관리 | `REVIEW`, `NUTRIENT`, `FOOD_NUTRIENT` |  


### --4. table (foodbrand)  + sequence (food_brand_seq)
| 컬럼명      | 데이터 타입       | 제약 조건        | 설명 |
|-------------|-------------------|------------------|------|
| `brandid`   | `NUMBER`          | `PRIMARY KEY`    | 브랜드 ID |
| `brandname` | `VARCHAR2(100)`   | `NOT NULL`       | 브랜드 이름 |
| `country`   | `VARCHAR2(100)`   | —                | 제조 국가 |


```
브랜드ID      브랜드이름       제조국
'1'	       '츄츄는고양이였다'	'대한민국'
'2'	       '밥쌈없다'        '미국'
```


### --5. table (food) + sequence (foodseq)
| 컬럼명              | 데이터 타입       | 제약 조건                                              | 설명 |
|---------------------|-------------------|--------------------------------------------------------|------|
| `foodid`            | `NUMBER`          | `PRIMARY KEY`                                          | 사료 ID |
| `foodname`          | `VARCHAR2(100)`   | `NOT NULL`                                             | 사료 이름 |
| `brandid`           | `NUMBER`          | `FOREIGN KEY REFERENCES foodbrand(brandid)`          | 브랜드 ID |
| `description`       | `VARCHAR2(500)`   | —                                                      | 설명 |
| `targetpettypeid`   | `NUMBER`          | `FOREIGN KEY REFERENCES pettype(pettypeid)`         | 대상 반려동물 종류 |

```
푸드ID          푸드네임            브랜드ID                 설명                                            대상반려동물 id
'1'	         '키튼 치킨 앤 청어'	     '1'	    '성장기 고양이를 위한 치킨과 청어 기반의 고단백 건식 사료입니다.'	       '2'
'2'	         '어덜트 연어 앤 현미'	   '2'	    '성묘 고양이의 활력과 소화 건강을 위한 연어와 현미 습식 사료입니다.'	    '2'
```


### --13. table (foodingredient) + sequence (food_ingredient_seq)
| 컬럼명      | 데이터 타입       | 제약 조건        | 설명 |
|----------------------|-------------------|------------------------------------------------------|-----------|
| `foodingredientid`   | `NUMBER`          | `PRIMARY KEY`                                        | 푸드재료 ID |
| `foodid`             | `NUMBER`          | `FOREIGN KEY REFERENCES food(foodid)`       | 사료 ID    |
| `mainingredient`     | `VARCHAR2(200)`   | —                                                    | 주 재료    |
| `subingredient`      | `VARCHAR2(200)`   | —                                                    | 부 재료    |   

```
푸드재료ID   푸드ID   주재료  부재료
'1'	        '1'	    '치킨'	'청어'
'2'	        '2'	    '연어'	'현미'
```




---
사료정보 테이블 조인 예시

```
SELECT 
  f.foodid,
  f.foodname,
  fb.brandname,
  f.description,
  f.targetpettypeid,
  fi.mainingredient,
  fi.subingredient
FROM food f
JOIN foodbrand fb ON f.brandid = fb.brandid
JOIN foodingredient fi ON f.foodid = fi.foodid;
```

---

```
사료ID      사료네임           브랜드네임                  설명                                            대상반려동물ID 주재료 부재료
'1'	  '키튼 치킨 앤 청어'	    '츄츄는고양이였다'	'성장기 고양이를 위한 치킨과 청어 기반의 고단백 건식 사료입니다.'	      '2'   	치킨	청어
'2'	  '어덜트 연어 앤 현미'	     '밥쌈없다'	     '성묘 고양이의 활력과 소화 건강을 위한 연어와 현미 습식 사료입니다.'	    '2'	    연어	현미
```

대상반려동물ID(pettypeid) 를 대상반려동물이름(typename) : `개`, `고양이` 로 교체 하는 것이 자연스러워보입니다

 