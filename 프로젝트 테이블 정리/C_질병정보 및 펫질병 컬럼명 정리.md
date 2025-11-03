### 👥 5명 역할 분담 

| 담당자 | 역할 | 주요 테이블 |
|--------|------|-------------|
| A | 사용자 및 반려동물 관리 | `USERS`, `PET`, `PETTYPE` |
| B | 사료 및 브랜드 관리 | `FOOD`, `FOODBRAND`, `FOODINGREDIENT`|
| C | 질환 정보 및 매핑 | `DISEASE`, `PETDISEASE` |
| D | 추천 및 즐겨찾기 기능 | `FOODRECOMMEND`, `FAVORITEFOOD` |
| E | 리뷰 및 영양소 관리 | `REVIEW`, `NUTRIENT`, `FOODNUTRIENT`, 'NUTRIENTRANGE' |

---
### --6. table (disease) + sequence (disease_seq)

| 컬럼명     | 데이터 타입       | 제약 조건             | 설명                           |
|------------|-------------------|------------------------|--------------------------------|
| `disno`    | `NUMBER`          | `PRIMARY KEY`, `NOT NULL` | 질병 고유 번호                 |
| `disname`  | `VARCHAR2(50)`    | `NOT NULL`, `UNIQUE`  | 질병 이름                      |
| `disex`    | `VARCHAR2(150)`   | `NULL`                 | 질병 설명                      |
| `kindpet`  | `VARCHAR2(200)`   | `NOT NULL`             | 해당 질병에 걸리는 반려동물 종류 |
| `infval`   | `VARCHAR2(200)`   | `NULL`                 | 감염 경로 또는 감염 값         |
| `mannote`  | `VARCHAR2(200)`   | `NULL`                 | 관리 시 참고 사항              |

# 🧬 DISEASE 테이블 데이터

| DISNO | DISNAME | DISEX                                      | KINDPET  | INFVAL   | MANNOTE |
|-------|---------|-------                                     |--------- |--------  |---------|
| 1     | 단계 구분 | 기관 내강이 0% ∼ 100% 좁아진 정도로 심각성 평가 | 해당 없음 | 해당 없음 | 해당 없음 |
| 2     | 단계 구분 | 기관 내강이 0% ∼ 100% 좁아진 정도로 심각성 평가 | 해당 없음 | 해당 없음 | 해당 없음 |



---


---

### --7. table (pet_disease)

| 컬럼명       | 데이터 타입       | 제약 조건             | 설명               |
|--------------|-------------------|------------------------|--------------------|
| `disid`      | `VARCHAR2(20)`    | `PRIMARY KEY`, `NOT NULL` | 반려동물 질병 ID   |
| `disname`    | `VARCHAR2(50)`    | `NOT NULL`             | 질병 이름          |
| `Fedm`       | `NUMBER(10)`      | `NULL`                 | 사료 관련 수치     |
| `Pdm`        | `NUMBER(10)`      | `NULL`                 | 펫닥터 진단 수치   |
| `Proteindm`  | `NUMBER(10)`      | `NULL`                 | 단백질 관련 수치   |

# 🧬 PETDISEASE 테이블 데이터
|DISID       |DISNAME     |FEDM        |PDM         |PROTEINDM|
|-------     |---------   |-------     |---------   |--------  |
|PET-D-048   |체리아이     |80          |1            |25       |

---
