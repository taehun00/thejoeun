### 👥 5명 역할 분담 

| 담당자 | 역할 | 주요 테이블 |
|--------|------|-------------|
| A | 사용자 및 반려동물 관리 | `USER`, `PET`, `PET_TYPE` |
| B | 사료 및 브랜드 관리 | `FOOD`, `FOOD_BRAND`, 'FOOD_INGREDIENT' |
| C | 질환 정보 및 매핑 | `DISEASE`, `PET_DISEASE` |
| D | 추천 및 즐겨찾기 기능 | `FOOD_RECOMMEND`, `FAVORITE_FOOD` |
| E | 리뷰 및 영양소 관리 | `REVIEW`, `NUTRIENT`, 'FOOD_NUTRIENT' |

---

### --10. table (food_recommend) + sequence (recommend_seq)
#### 일단은 전체적인 흐름(?)만 정해놨고요, 추후에 프로젝트 진행하면서 조정을 더 해야 할 것 같습니다. 
####  ㄴ보완할 점 있으면 카톡 남겨주세요~.

| 컬럼명          | 데이터 타입       | 제약 조건                                        | 설명 |
|------------------|-------------------|--------------------------------------------------|------|
| `recommend_id`    | `NUMBER`          | `PRIMARY KEY`                                    | 추천 ID |
| `userid`          | `NUMBER`          | `FOREIGN KEY REFERENCES user(userid)`            | 사용자 ID |
| `food_id`         | `NUMBER`          | `FOREIGN KEY REFERENCES food(food_id)`           | 추천 사료 ID |
| `reason`          | `VARCHAR2(500)`   | —                                                | 추천 사유 |
| `recommended_at`  | `VARCHAR2(200)`   | —                                                | 추천일 |

---

```
테이블(SQL )
create sequence recommend seq;

create table foodrecommend (
   recommend id      number primary key,
   user id           number foreign key     references user(user id),
   food id           number foreign key     references food(food id),
   reason            varchar2(500), 
   recommended at varchar2(200)  
);

(임시 컬럼명) 
추천 id    : 5
유저 id    : 123
추천사료 id : 15
추천사유    : ~해서 ~하기 때문에 추천한다.
추천일      : xxxx년 xx월 xx일

```
---
### --11. table (favorite_food)
| 컬럼명      | 데이터 타입       | 제약 조건                                                | 설명 |
|-------------|-------------------|----------------------------------------------------------|------|
| `userid`     | `NUMBER`          | `FOREIGN KEY REFERENCES user(userid)`                   | 사용자 ID |
| `food_id`    | `NUMBER`          | `FOREIGN KEY REFERENCES food(food_id)`                  | 사료 ID |
| `added_at`   | `VARCHAR2(200)`   | —                                                        | 즐겨찾기 등록일 |
| **복합키**   |                   | `PRIMARY KEY (userid, food_id)`                         | 즐겨찾기 매핑 |
---

```
테이블 (SQL)
create table favorite food (
   user id    number            foreign key references  user(user id),
   food id    number            foreign key references  food(food id),
   added at   varchar2(200),
   hit                          primary key(userid, foodid)
);

(임시 컬럼명)
사용자 id      : 123
사료   id      : 1234
즐겨찾기 등록일  : xxxx년 xx월 xx일
즐겨찾기 매핑    : ~~사료
```


