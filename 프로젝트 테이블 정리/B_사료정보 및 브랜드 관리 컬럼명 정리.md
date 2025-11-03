### 👥 5명 역할 분담 

| 담당자 | 역할 | 주요 테이블 |
|--------|------|-------------|
| A | 사용자 및 반려동물 관리 | `USER`, `PET`, `PET_TYPE` |
| B | 사료 및 브랜드 관리 | `FOOD`, `FOOD_BRAND`|
| C | 질환 정보 및 매핑 | `DISEASE`, `PET_DISEASE` |
| D | 추천 및 즐겨찾기 기능 | `FOOD_RECOMMEND`, `FAVORITE_FOOD` |
| E | 리뷰 및 영양소 관리 | `REVIEW`, `NUTRIENT`, `FOOD_NUTRIENT` 'NUTRIENTRANGE' |


### --4. table (foodbrand)  + sequence (foodbrandseq)
| 컬럼명      | 데이터 타입       | 제약 조건        | 설명 |
|-------------|-------------------|------------------|------|
| `brandid`   | `NUMBER`          | `PRIMARY KEY`    | 브랜드 ID |
| `brandname` | `VARCHAR2(100)`   | `NOT NULL`       | 브랜드 이름 |
| `country`   | `VARCHAR2(100)`   | —                | 제조 국가 |


```
브랜드ID      브랜드이름       제조국
'1'	       '네밥이아니야'	     '미국'
'2'	       '명냥스티드'	     '캐나다'
```


### --5. table (food) + sequence (foodseq)
| 컬럼명              | 데이터 타입       | 제약 조건                                              | 설명 |
|---------------------|-------------------|----------------------------------------------------|------|
| `foodid`            | `NUMBER`          | `PRIMARY KEY`                                       | 사료 ID |
| `foodname`          | `VARCHAR2(100)`   | `NOT NULL`                                          | 사료 이름 |
| `brandid`           | `NUMBER`          | `FOREIGN KEY REFERENCES foodbrand(brandid)`         | 브랜드 ID |
| `description`       | `VARCHAR2(500)`   | `NOT NULL`                                          | 설명 |
| `mainingredient`     | `VARCHAR2(200)`   | `NOT NULL`                                         | 주 재료    |
| `subingredient`      | `VARCHAR2(200)`   | —                                                  | 부 재료    |   
| `targetpettypeid`   | `NUMBER`          | `FOREIGN KEY REFERENCES pettype(pettypeid)`         | 대상 반려동물 종류 |

```
푸드ID          푸드네임                 브랜드ID                      설명                            주재료     부재료   대상반려동물 id
'5'	   '처방식 관절케어 닭고기 앤 브로콜리' 	'1'	    '관절을 위한 닭고기와 브로콜리, 뛰는 게 즐거워질지도?'	'닭고기'	'브로콜리'	     '2'
'6'	   '시니어 오리 앤 감자'	              '2'	    '우아한 노년을 위한 오리와 감자, 품격 있는 한 끼!'	    '오리'	  '감자'	       '1'


---
사료정보 테이블 조인 예시

```
SELECT 
  f.foodid,
  f.foodname,
  fb.brandname,
  f.description,
  f.targetpettypeid,
  f.mainingredient,
  f.subingredient,
  fb.country
FROM food f
JOIN foodbrand fb ON f.brandid = fb.brandid;

```

---

```
사료ID      사료네임                   브랜드네임                            설명                              대상반려동물ID 주재료 부재료   제조국
'1'	'처방식 신장케어 연어 앤 귀리'	   '네밥이아니야'	    '신장 건강을 위한 연어와 귀리의 조화, 물 많이 마시게 될지도?'	      1	        연어	귀리	  미국
'2'	'처방식 심장케어 연어 앤 치아씨드'	'네밥이아니야'	   '심장 튼튼 프로젝트, 연어와 치아씨드로 펄떡펄떡!'	               2	       연어	치아씨드	미국
```

대상반려동물ID(pettypeid) 를 대상반려동물이름(typename) : `개`, `고양이` 로 교체 하는 것이 자연스러워보입니다

 