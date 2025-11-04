### 👥 5명 역할 분담 

| 담당자 |          역할          |               주요 테이블              |
|--------|------------------------|-----------------------------------------|
|   A    | 사용자 및 반려동물 관리 | `USER`, `PET`, `PET_TYPE`               |
|   B    | 사료 및 브랜드 관리     | `FOOD`, `FOOD_BRAND`, 'FOOD_INGREDIENT' |
|   C    | 질환 정보 및 매핑       | `DISEASE`, `PET_DISEASE`                |
|   D    | 추천 및 즐겨찾기 기능   | `FOOD_RECOMMEND`, `FAVORITE_FOOD`       |
|   E    | 리뷰 및 영양소 관리     | `REVIEW`, `NUTRIENT`, 'FOOD_NUTRIENT'   |

---
운동정보 (1단계 CRUD 파트/ 입력시 _(언더바) 및 줄일 수 있는 필드명은 줄일 예정.)
| 필드명             | 타입           | 설명 |
|--------------------|----------------|------|
| `exercise_id`      | INT (PK)       | 운동 고유 ID |
| `exercise_type`    | VARCHAR(50)    | 운동 종류 (예: 산책, 수영, 노즈워크 등) |
| `description`      | VARCHAR(255)   | 운동에 대한 간단 설명 |
| `avg_calories_30min` | FLOAT        | 평균 칼로리 소모량 (소형견 기준, 30분 기준) |
| `recommended_duration_min` | INT   | 권장 운동 시간 (분) |
| `suitable_for`     | VARCHAR(100)   | 추천 대상 (예: 소형견, 노령견 등) |
| `intensity_level`  | VARCHAR(20)    | 운동 강도 (예: 저강도, 중강도, 고강도) |
| `created_at`       | DATETIME       | 등록일 |
| `updated_at`       | DATETIME       | 수정일 |

```
insert into exerciseinfo(execid,
                         exectype,
                         description,
                         avgkcal30min,
                         exectargetmin,
                         suitablefor,
                         intensitylevel,
                         createdat,
                         updatedat) 
values ( 1,
        '산책',
        '가장 기본적인 야외 운동으로 스트레스 해소와 사회성 향상에 효과적입니다.',
        80.0,
        30,
        '모든 견종, 노령견 포함',
        '저강도',
        2010/11/3,
        2025/11/3);
```

---
### --10. table (food_recommend) + sequence (recommend_seq)
#### 일단은 전체적인 흐름(?)만 정해놨고요, 추후에 프로젝트 진행하면서 조정을 더 해야 할 것 같습니다. 
####  ㄴ보완할 점 있으면 카톡 남겨주세요~.

|    컬럼명        |    데이터 타입    |                  제약 조건                      |     설명     |
|------------------|-------------------|--------------------------------------------------|--------------|
| `userid`         | `NUMBER`          | `FOREIGN KEY REFERENCES user(userid)`            | 사용자 ID    |
| `foodid`         | `NUMBER`          | `FOREIGN KEY REFERENCES food(foodid)`            | 추천 사료 ID |
---

```
테이블(SQL / 추후에 변경사항 있으면 변경할 예정.)
create sequence recommend_seq;

create table foodrecommend (
   userid             number foreign key     references user(user id),
   foodid             number foreign key     references food(food id),
);

(임시) 
유저 id(userid)       : 123
추천사료 id(foodid)   : 15

```


---
### --11. table (favoritefood)
|   컬럼명    |     데이터 타입   |                    제약 조건                            |       설명      |
|-------------|-------------------|----------------------------------------------------------|-----------------|
| `userid`    | `NUMBER`          | `FOREIGN KEY REFERENCES user(userid)`                    | 사용자 ID       |
| `foodid`    | `NUMBER`          | `FOREIGN KEY REFERENCES food(foodid)`                    | 사료 ID         |

---

```
테이블 (SQL/ 추후에 변경사항 있으면 변경할 예정.)
create table favoritefood (
   userid      number            foreign key references  user(userid),
   foodid      number            foreign key references  food(foodid),
);

(임시)
사용자 id(    userid)           : 123
사료   id(    foodid)           : 1234

```







