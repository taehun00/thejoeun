### 👥 5명 역할 분담 

| 담당자 | 역할 | 주요 테이블 |
|--------|------|-------------|
| A | 사용자 및 반려동물 관리 | `USER`, `PET`, `PET_TYPE` | ✔ 테이블 생성 완료
| B | 사료 및 브랜드 관리 | `FOOD`, `FOOD_BRAND`, `FOOD_INGREDIENT`|
| C | 질환 정보 및 매핑 | `DISEASE`, `PET_DISEASE` |
| D | 추천 및 즐겨찾기 기능 | `FOOD_RECOMMEND`, `FAVORITE_FOOD` |
| E | 리뷰 및 영양소 관리 | `REVIEW`, `NUTRIENT`, `FOOD_NUTRIENT` |  ✔ 테이블 생성 완료

### --1. table (users) + sequence (user_seq)
| 컬럼명       | 데이터 타입       | 제약 조건               | 설명 |
|--------------|-------------------|--------------------------|------|
| `userid`     | `NUMBER`          | `PRIMARY KEY`            | 사용자 고유 ID |
| `email`      | `VARCHAR2(200)`   | `NOT NULL`, `UNIQUE`     | 이메일 주소 |
| `nickname`   | `VARCHAR2(100)`   | `NOT NULL`               | 닉네임 |
| `password`   | `VARCHAR2(100)`   | `NOT NULL`               | 비밀번호 |
| `createdat` | `VARCHAR2(200)`   | `NOT NULL`               | 가입일 |

---
userid      email                   nickname        password        createdate
101         'iis07007@naver.com'    '성태훈'         '1234'          '2025-11-03'
---


### --2. table (pettype) + sequence (pet_type_seq)
| 컬럼명        | 데이터 타입       | 제약 조건        | 설명 |
|---------------|-------------------|------------------|------|
| `pettypeid` | `NUMBER`          | `PRIMARY KEY`    | 반려동물 종류 ID |
| `typename`   | `VARCHAR2(100)`   | `NOT NULL`       | 종류 이름 (강아지, 고양이 등) |

---
pettypeid       typename
1               '고양이'
---

### --3. table (pet) + sequence (pet_seq)
| 컬럼명        | 데이터 타입       | 제약 조건                                | 설명 |
|---------------|-------------------|------------------------------------------|------|
| `petid`       | `NUMBER`          | `PRIMARY KEY`                            | 반려동물 고유 ID |
| `userid`       | `NUMBER`          | `FOREIGN KEY REFERENCES user(userid)`    | 사용자 ID |
| `petname`     | `VARCHAR2(100)`   | `NOT NULL`                               | 반려동물 이름 |
| `birthdate`    | `VARCHAR2(100)`   | —                                        | 생년월일 |
| `pettypeid`  | `NUMBER`          | `FOREIGN KEY REFERENCES pet_type(pet_type_id)` | 반려동물 종류 ID |

---
petid       userid      petname     birthdate       pettypeid
1001        101         '겨울이'     '2022-06-12'    1
---