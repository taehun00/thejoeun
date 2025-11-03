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
| `recommendid`     | `NUMBER`          | `PRIMARY KEY`                                    | 추천 ID |
| `userid`          | `NUMBER`          | `FOREIGN KEY REFERENCES user(userid)`            | 사용자 ID |
| `foodid`          | `NUMBER`          | `FOREIGN KEY REFERENCES food(food_id)`           | 추천 사료 ID |
| `reason`          | `VARCHAR2(500)`   | —                                                | 추천 사유 |
| `recommendedat`   | `VARCHAR2(200)`   | —                                                | 추천일 |

---

```
테이블(SQL )
create sequence recommend seq;

create table foodrecommend (
   recommendid        number primary key,
   userid             number foreign key     references user(user id),
   foodid             number foreign key     references food(food id),
   reason             varchar2(500), 
   recommendedat      varchar2(200)  
);

(임시) 
추천 id(recommendid) : 5
유저 id(userid)      : 123
추천사료 id(foodid)   : 15
추천사유(reason)      : ~해서 ~하기 때문에 추천한다.
추천일(recommendedat) : xxxx년 xx월 xx일

```
---
### --11. table (favorite_food)
| 컬럼명      | 데이터 타입       | 제약 조건                                                | 설명 |
|-------------|-------------------|----------------------------------------------------------|------|
| `userid`     | `NUMBER`          | `FOREIGN KEY REFERENCES user(userid)`                   | 사용자 ID |
| `foodid`    | `NUMBER`          | `FOREIGN KEY REFERENCES food(foodid)`                  | 사료 ID |
| `addedat`   | `VARCHAR2(200)`   | —                                                        | 즐겨찾기 등록일 |
| **복합키**   |                   | `PRIMARY KEY (userid, foodid)`                         | 즐겨찾기 매핑 |
---

```
테이블 (SQL)
create table favoritefood (
   favoriteid  number            primary key,  --추가된 부분/ 추후에 조정가능
   userid      number            foreign key references  user(user id),
   foodid      number            foreign key references  food(food id),
   addedat     varchar2(200), 
   note        varchar2(500)     --추가된 부분/ 추후에 조정가능
);

(임시)
사용자 id(    userid)         : 123
사료   id(    foddid)         : 1234
즐겨찾기 등록일(sysdate, date)  : xxxx년 xx월 xx일
즐겨찾기 매핑(  userid, foodid) : ~~사료
즐겨찾기 id(   favoriteid)     : 15                             --추가된 부분/ 추후에 조정가능
유저코멘트(     note)           : 우리집 강아지가 이 간식을 좋아해요. --추가된 부분/ 추후에 조정가능
```


