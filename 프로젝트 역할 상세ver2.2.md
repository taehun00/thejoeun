## 🐾 1단계 포트폴리오용 테이블  (JSP + Oracle)

### 📦 총 12개 테이블 구성

| 테이블명             | 설명 |
|----------------------|------|
| `USER`               | 사용자 정보 |
| `PET`                | 반려동물 정보 |
| `PET_TYPE`           | 반려동물 종류 (강아지, 고양이 등) |
| `FOOD`               | 사료 정보 |
| `FOOD_BRAND`         | 사료 브랜드 |
| `DISEASE`            | 질환 정보 |
| `PET_DISEASE`        | 반려동물-질환 매핑 |
| `FOOD_NUTRIENT`      | 사료-영양소 매핑 |
| `NUTRIENT`           | 영양소 정보 |
| `FOOD_RECOMMEND`     | 추천 기록 (추천 로직 기초용) |
| `FAVORITE_FOOD`      | 즐겨찾기 사료 |
| `REVIEW`             | 사료 리뷰 |

---

### 🧩 테이블 상세 구조 

#### 1. `USER`
- `userid` (PK), `email`, `nickname`, `password`, `created_at`

#### 2. `PET`
- `pet_id` (PK), `userid` (FK), `pet_name`, `birthdate`, `pet_type_id` (FK)

#### 3. `PET_TYPE`
- `pet_type_id` (PK), `type_name` (예: 강아지, 고양이)

#### 4. `FOOD`
- `food_id` (PK), `food_name`, `brand_id` (FK), `description`, `target_pet_type_id` (FK)

#### 5. `FOOD_BRAND`
- `brand_id` (PK), `brand_name`, `country`

#### 6. `DISEASE`
- `disease_id` (PK), `disease_name`, `symptoms`, `risk_level`

#### 7. `PET_DISEASE`
- `pet_id` (FK), `disease_id` (FK), `diagnosed_at`  
- 복합 PK: (`pet_id`, `disease_id`)

#### 8. `NUTRIENT`
- `nutrient_id` (PK), `nutrient_name`, `unit`

#### 9. `FOOD_NUTRIENT`
- `food_id` (FK), `nutrient_id` (FK), `amount`  
- 복합 PK: (`food_id`, `nutrient_id`)

#### 10. `FOOD_RECOMMEND`
- `recommend_id` (PK), `userid` (FK), `food_id` (FK), `reason`, `recommended_at`

#### 11. `FAVORITE_FOOD`
- `userid` (FK), `food_id` (FK), `added_at`  
- 복합 PK: (`userid`, `food_id`)

#### 12. `REVIEW`
- `review_id` (PK), `userid` (FK), `food_id` (FK), `rating`, `comment`, `created_at`

#### 13. `FOOD_INGREDIENT`
- 주원료 등 이용된 재료 정보
 

### 👥 5명 역할 분담 

| 담당자 | 역할 | 주요 테이블 |
|--------|------|-------------|
| A | 사용자 및 반려동물 관리 | `USER`, `PET`, `PET_TYPE` |
| B | 사료 및 브랜드 관리 | `FOOD`, `FOOD_BRAND`, 'FOOD_INGREDIENT' |
| C | 질환 정보 및 매핑 | `DISEASE`, `PET_DISEASE` |
| D | 추천 및 즐겨찾기 기능 | `FOOD_RECOMMEND`, `FAVORITE_FOOD` |
| E | 리뷰 및 영양소 관리 | `REVIEW`, `NUTRIENT`, 'FOOD_NUTRIENT' |
 


 

### --1. table (user) + sequence (user_seq)
| 컬럼명       | 데이터 타입       | 제약 조건               | 설명 |
|--------------|-------------------|--------------------------|------|
| `userid`     | `NUMBER`          | `PRIMARY KEY`            | 사용자 고유 ID |
| `email`      | `VARCHAR2(200)`   | `NOT NULL`, `UNIQUE`     | 이메일 주소 |
| `nickname`   | `VARCHAR2(100)`   | `NOT NULL`               | 닉네임 |
| `password`   | `VARCHAR2(100)`   | `NOT NULL`               | 비밀번호 |
| `created_at` | `VARCHAR2(200)`   | `NOT NULL`               | 가입일 |

---

### --2. table (pet_type) + sequence (pet_type_seq)
| 컬럼명        | 데이터 타입       | 제약 조건        | 설명 |
|---------------|-------------------|------------------|------|
| `pet_type_id` | `NUMBER`          | `PRIMARY KEY`    | 반려동물 종류 ID |
| `type_name`   | `VARCHAR2(100)`   | `NOT NULL`       | 종류 이름 (강아지, 고양이 등) |

---

### --3. table (pet) + sequence (pet_seq)
| 컬럼명        | 데이터 타입       | 제약 조건                                | 설명 |
|---------------|-------------------|------------------------------------------|------|
| `pet_id`       | `NUMBER`          | `PRIMARY KEY`                            | 반려동물 고유 ID |
| `userid`       | `NUMBER`          | `FOREIGN KEY REFERENCES user(userid)`    | 사용자 ID |
| `pet_name`     | `VARCHAR2(100)`   | `NOT NULL`                               | 반려동물 이름 |
| `birthdate`    | `VARCHAR2(100)`   | —                                        | 생년월일 |
| `pet_type_id`  | `NUMBER`          | `FOREIGN KEY REFERENCES pet_type(pet_type_id)` | 반려동물 종류 ID |

---

### --4. table (food_brand) + sequence (food_brand_seq)
| 컬럼명      | 데이터 타입       | 제약 조건        | 설명 |
|-------------|-------------------|------------------|------|
| `brand_id`   | `NUMBER`          | `PRIMARY KEY`    | 브랜드 ID |
| `brand_name` | `VARCHAR2(100)`   | `NOT NULL`       | 브랜드 이름 |
| `country`    | `VARCHAR2(100)`   | —                | 제조 국가 |

---

### --5. table (food) + sequence (food_seq)
| 컬럼명              | 데이터 타입       | 제약 조건                                              | 설명 |
|---------------------|-------------------|--------------------------------------------------------|------|
| `food_id`            | `NUMBER`          | `PRIMARY KEY`                                          | 사료 ID |
| `food_name`          | `VARCHAR2(100)`   | `NOT NULL`                                             | 사료 이름 |
| `brand_id`           | `NUMBER`          | `FOREIGN KEY REFERENCES food_brand(brand_id)`          | 브랜드 ID |
| `description`        | `VARCHAR2(500)`   | —                                                      | 설명 |
| `target_pet_type_id` | `NUMBER`          | `FOREIGN KEY REFERENCES pet_type(pet_type_id)`         | 대상 반려동물 종류 |

---

### --6. table (disease) + sequence (disease_seq)
| 컬럼명        | 데이터 타입       | 제약 조건        | 설명 |
|---------------|-------------------|------------------|------|
| `disease_id`   | `NUMBER`          | `PRIMARY KEY`    | 질환 ID |
| `disease_name` | `VARCHAR2(100)`   | `NOT NULL`       | 질환 이름 |
| `symptoms`     | `VARCHAR2(500)`   | —                | 주요 증상 |
| `risk_level`   | `VARCHAR2(50)`    | —                | 위험도 (낮음/중간/높음) |

---

### --7. table (pet_disease)
| 컬럼명        | 데이터 타입       | 제약 조건                                                        | 설명 |
|---------------|-------------------|------------------------------------------------------------------|------|
| `pet_id`       | `NUMBER`          | `FOREIGN KEY REFERENCES pet(pet_id)`                            | 반려동물 ID |
| `disease_id`   | `NUMBER`          | `FOREIGN KEY REFERENCES disease(disease_id)`                    | 질환 ID |
| `diagnosed_at` | `VARCHAR2(200)`   | —                                                                | 진단일 |
| **복합키**     |                   | `PRIMARY KEY (pet_id, disease_id)`                              | 반려동물-질환 매핑 |

---

### --8. table (nutrient) + sequence (nutrient_seq)
| 컬럼명         | 데이터 타입       | 제약 조건        | 설명 |
|----------------|-------------------|------------------|------|
| `nutrient_id`   | `NUMBER`          | `PRIMARY KEY`    | 영양소 ID |
| `nutrient_name` | `VARCHAR2(100)`   | `NOT NULL`       | 영양소 이름 |
| `unit`          | `VARCHAR2(50)`    | —                | 단위 (g, mg 등) |

---

### --9. table (food_nutrient)
| 컬럼명         | 데이터 타입       | 제약 조건                                                        | 설명 |
|----------------|-------------------|------------------------------------------------------------------|------|
| `food_id`       | `NUMBER`          | `FOREIGN KEY REFERENCES food(food_id)`                          | 사료 ID |
| `nutrient_id`   | `NUMBER`          | `FOREIGN KEY REFERENCES nutrient(nutrient_id)`                  | 영양소 ID |
| `amount`        | `NUMBER`          | —                                                                | 포함량 |
| **복합키**      |                   | `PRIMARY KEY (food_id, nutrient_id)`                            | 사료-영양소 매핑 |

---

### --10. table (food_recommend) + sequence (recommend_seq)
| 컬럼명          | 데이터 타입       | 제약 조건                                        | 설명 |
|------------------|-------------------|--------------------------------------------------|------|
| `recommend_id`    | `NUMBER`          | `PRIMARY KEY`                                    | 추천 ID |
| `userid`          | `NUMBER`          | `FOREIGN KEY REFERENCES user(userid)`            | 사용자 ID |
| `food_id`         | `NUMBER`          | `FOREIGN KEY REFERENCES food(food_id)`           | 추천 사료 ID |
| `reason`          | `VARCHAR2(500)`   | —                                                | 추천 사유 |
| `recommended_at`  | `VARCHAR2(200)`   | —                                                | 추천일 |

---

### --11. table (favorite_food)
| 컬럼명      | 데이터 타입       | 제약 조건                                                | 설명 |
|-------------|-------------------|----------------------------------------------------------|------|
| `userid`     | `NUMBER`          | `FOREIGN KEY REFERENCES user(userid)`                   | 사용자 ID |
| `food_id`    | `NUMBER`          | `FOREIGN KEY REFERENCES food(food_id)`                  | 사료 ID |
| `added_at`   | `VARCHAR2(200)`   | —                                                        | 즐겨찾기 등록일 |
| **복합키**   |                   | `PRIMARY KEY (userid, food_id)`                         | 즐겨찾기 매핑 |

---

### --12. table (review) + sequence (review_seq)
| 컬럼명      | 데이터 타입       | 제약 조건                                                | 설명 |
|-------------|-------------------|----------------------------------------------------------|------|
| `review_id`  | `NUMBER`          | `PRIMARY KEY`                                            | 리뷰 ID |
| `userid`     | `NUMBER`          | `FOREIGN KEY REFERENCES user(userid)`                   | 작성자 |
| `food_id`    | `NUMBER`          | `FOREIGN KEY REFERENCES food(food_id)`                  | 사료 ID |
| `rating`     | `NUMBER(1)`       | `CHECK (rating BETWEEN 1 AND 5)`                         | 평점 |
| `comment`    | `VARCHAR2(500)`   | —                                                        | 리뷰 내용 |
| `created_at` | `VARCHAR2(200)`   | —                                                        | 작성일 |
