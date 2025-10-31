

# 🐾 **반려동물 건강 기반 맞춤 사료 추천 플랫폼 (가제:PetHealth Universe)**

> 반려동물의 질환·영양 상태를 기준으로 사료를 추천하고
> 건강 정보와 커뮤니티·모바일 기능으로 확장되는 통합 플랫폼.

---

## 📘 **개발 단계별 개요**

| 단계      | 기술 스택                             | 주요 기능                       | 초점          |
| ------- | --------------------------------- | --------------------------- | ----------- |
| **1단계** | JSP + Oracle                      | 반려동물·사료·질환정보 CRUD, 추천 로직 기초 | DB 기반 구조 설계 |
| **2단계** | Spring MVC + MyBatis + JSTL       | 관리자 시스템 구축 (데이터 관리/승인 기능)   | 운영자 중심 관리   |
| **3단계** | Spring Boot + JPA + Thymeleaf     | 사용자용 추천 서비스 구현 (필터링/검색/통계)  | 사용자 중심 기능   |
| **4단계** | Node + React                      | 커뮤니티, 후기 게시판, 사용자 피드        | UX 확장       |
| **5단계** | Spring Boot + JWT + Redis + React | 중앙 인증 서버 + 통합 API           | 인증/보안       |
| **6단계** | Flutter                           | 모바일 UX 최적화 앱                | 접근성/확장성     |

---

## 💡 **1단계: JSP + Oracle (기초 CRUD 단계)**

> DB 설계, 기본 CRUD, 추천 구조를 구현하는 단계
> 5명 팀원이 각 기능 모듈을 분담하여 구현

| 담당자                | 주요 기능               | 핵심 테이블                                          | 주요 JSP 페이지                                                     | 주요 구현 내용                                   | 난이도      |
| :----------------- | :------------------ | :---------------------------------------------- | :------------------------------------------------------------- | :----------------------------------------- | :------- |
| 🧍‍♂️① 사용자·반려동물 담당(태훈) | 사용자 및 반려동물 정보 관리    | `User`, `Pet`                                   | `userList.jsp`, `userForm.jsp`, `petForm.jsp`, `petDetail.jsp` | 사용자 등록, 반려동물 등록, 질환 선택(드롭다운), 정보 수정/삭제     | ⚙️ 중간    |
| 💊② 질환별 영양가이드 담당(상욱)   | 질환 및 권장 영양 수치 관리    | `DiseaseGuide`                                  | `guideList.jsp`, `guideForm.jsp`                               | 질환명, 권장 단백질/인/칼로리 수치, 건강 가이드 문구 CRUD       | 🟢 쉬움~중간 |
| 🍖③ 사료 정보 담당(준용)       | 사료 데이터 관리           | `Food`                                          | `foodList.jsp`, `foodForm.jsp`, `foodDetail.jsp`               | 사료명, 단백질/인/칼로리/알러지 정보 CRUD + 검색 필터         | ⚙️ 중간    |
| 🎯④ 추천 담당 (현주)     | 질환 기반 사료 추천 + 결과 저장 | `Recommendation` + JOIN(`DiseaseGuide`, `Food`) | `filterForm.jsp`, `recommendList.jsp`, `recommendResult.jsp`   | 질환 선택 기반 SQL JOIN 필터링, 결과 리스트 출력, 추천 저장 기능 | 🔥 어려움   |
| 📚⑤ 건강정보 제공 담당(승현)     | 건강·영양 관련 정보 콘텐츠 제공  | `HealthInfo`                                    | `infoList.jsp`, `infoForm.jsp`, `infoDetail.jsp`               | 건강 상식, 질환 관리법, 사료 영양 설명 등 정보성 콘텐츠 CRUD     | ⚙️ 중간    |

---

## ⚙️ **핵심 테이블 요약**

```sql
USER(user_id, name, email)
PET(pet_id, user_id, name, species, age, disease_name)
DISEASE_GUIDE(disease_id, disease_name, protein_min, protein_max, phosphorus_min, phosphorus_max, guide_message)
FOOD(food_id, name, protein, phosphorus, calorie, allergy_tag)
RECOMMENDATION(rec_id, pet_id, food_id, match_score, created_at)
HEALTH_INFO(info_id, category, title, content, thumbnail, created_at)

```
 

### --1. table (user) + sequence (user_seq)
| 컬럼명       | 데이터 타입       | 제약 조건        | 설명 |
|--------------|-------------------|------------------|------|
| `user_id`    | `NUMBER`          | `PRIMARY KEY`    | 사용자 고유 ID |
| `name`       | `VARCHAR2(100)`   | `NOT NULL`       | 사용자 이름 |
| `email`      | `VARCHAR2(200)`   | `NOT NULL`       | 이메일 주소 |

---
### --2-1. table (pet) + sequence (pet_seq)
| 컬럼명       | 데이터 타입       | 제약 조건        | 설명 |
|--------------|-------------------|------------------|------|
| `pet_id`     | `NUMBER`          | `PRIMARY KEY`    | 반려동물 고유 ID |
| `user_id`    | `NUMBER`          | `NOT NULL`       | 사용자 ID (`user` 테이블 참조) |
| `name`       | `VARCHAR2(100)`   | `NOT NULL`       | 반려동물 이름 |
| `species`    | `VARCHAR2(50)`    | `NOT NULL`       | 반려동물 종 |
| `age`        | `NUMBER`          | —                | 반려동물 나이 |
| —            | —                 | `FOREIGN KEY`    | `user_id`는 `user(user_id)` 참조 |

---

### --2-2. table (pet_disease) + sequence (pet_disease_seq)
| 컬럼명       | 데이터 타입       | 제약 조건        | 설명 |
|--------------|-------------------|------------------|------|
| `pet_disease_id` | `NUMBER`      | `PRIMARY KEY`    | 고유 ID |
| `pet_id`     | `NUMBER`          | `NOT NULL`       | 반려동물 ID (`pet` 테이블 참조) |
| `disease_id` | `NUMBER`          | `NOT NULL`       | 질환 ID (`disease_guide` 테이블 참조) |
| —            | —                 | `FOREIGN KEY`    | `pet_id`는 `pet(pet_id)` 참조, `disease_id`는 `disease_guide(disease_id)` 참조 |
 

### --3. table (disease_guide) + sequence (disease_guide_seq)
| 컬럼명           | 데이터 타입       | 제약 조건        | 설명 |
|------------------|-------------------|------------------|------|
| `disease_id`     | `NUMBER`          | `PRIMARY KEY`    | 질환 고유 ID |
| `disease_name`   | `VARCHAR2(100)`   | `NOT NULL`       | 질환명 |
| `protein_min`    | `NUMBER`          | —                | 최소 단백질 수치 |
| `protein_max`    | `NUMBER`          | —                | 최대 단백질 수치 |
| `phosphorus_min` | `NUMBER`          | —                | 최소 인 수치 |
| `phosphorus_max` | `NUMBER`          | —                | 최대 인 수치 |
| `guide_message`  | `VARCHAR2(500)`   | —                | 건강 가이드 문구 |

---

### --4. table (food) + sequence (food_seq)
| 컬럼명       | 데이터 타입       | 제약 조건        | 설명 |
|--------------|-------------------|------------------|------|
| `food_id`    | `NUMBER`          | `PRIMARY KEY`    | 사료 고유 ID |
| `name`       | `VARCHAR2(200)`   | `NOT NULL`       | 사료명 |
| `protein`    | `NUMBER`          | —                | 단백질 함량 |
| `phosphorus` | `NUMBER`          | —                | 인 함량 |
| `calorie`    | `NUMBER`          | —                | 칼로리 |
| `allergy_tag`| `VARCHAR2(200)`   | —                | 알러지 태그 (쉼표 구분 문자열) |

---

### --5. table (recommendation) + sequence (recommendation_seq)
| 컬럼명       | 데이터 타입       | 제약 조건        | 설명 |
|--------------|-------------------|------------------|------|
| `rec_id`     | `NUMBER`          | `PRIMARY KEY`    | 추천 결과 고유 ID |
| `pet_id`     | `NUMBER`          | `NOT NULL`       | 반려동물 ID (`pet` 테이블 참조) |
| `food_id`    | `NUMBER`          | `NOT NULL`       | 사료 ID (`food` 테이블 참조) |
| `match_score`| `NUMBER`          | —                | 질환-사료 적합도 점수 |
| `created_at` | `DATE`            | `DEFAULT SYSDATE`| 추천 생성일 |
| —            | —                 | `FOREIGN KEY`    | `pet_id`는 `pet(pet_id)` 참조, `food_id`는 `food(food_id)` 참조 |

---

### --6. table (health_info) + sequence (health_info_seq)
| 컬럼명       | 데이터 타입       | 제약 조건        | 설명 |
|--------------|-------------------|------------------|------|
| `info_id`    | `NUMBER`          | `PRIMARY KEY`    | 건강정보 고유 ID |
| `category`   | `VARCHAR2(100)`   | —                | 정보 카테고리 |
| `title`      | `VARCHAR2(200)`   | `NOT NULL`       | 정보 제목 |
| `content`    | `CLOB`            | `NOT NULL`       | 정보 내용 |
| `thumbnail`  | `VARCHAR2(300)`   | —                | 썸네일 이미지 경로 |
| `created_at` | `DATE`            | `DEFAULT SYSDATE`| 작성일 |
 





## ✅ **요약 포인트**

* 모든 데이터는 **학습·시연용 더미 데이터(dummy data)** 로 구성됨
  → 실제 브랜드나 임상정보는 포함하지 않음

* 건강정보(`HealthInfo`)는 이용자가 작성하는 게시판이 아니라
  **운영자가 직접 제공하는 정리형 정보 페이지**

* 사용자는 입력한 질환에 따라 → 필터링할 조건 자동 추천

* 사용자는 **질환이나 사료 성분 조건(단백질·인·칼로리 등)을 직접 선택**해
  **수동 필터링 방식으로 원하는 사료를 탐색**

* 추천 로직은 `DiseaseGuide`와 `Food` 테이블을 **JOIN하여 조건 매칭**하는 구조

* 각 팀원은 **1개 CRUD 모듈**을 담당하여 JSP + Oracle 기반으로 구현

* **추천 담당**이 필터링 로직과 결과 출력까지 통합 담당,
  사용자의 선택 조건을 반영해 동적으로 결과를 생성



