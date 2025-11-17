### 👥 5명 역할 분담 

| 담당자 |          역할          |               주요 테이블              |
|--------|------------------------|-----------------------------------------|
|   A    | 사용자 및 반려동물 관리 | `USER`, `PET`, `PET_TYPE`               |
|   B    | 사료 및 브랜드 관리     | `FOOD`, `FOOD_BRAND`, 'FOOD_INGREDIENT' |
|   C    | 질환 정보 및 매핑       | `DISEASE`, `PET_DISEASE`                |
|   D    | 추천 및 즐겨찾기 기능   | `FOOD_RECOMMEND`, `FAVORITE_FOOD`       |
|   E    | 리뷰 및 영양소 관리     | `REVIEW`, `NUTRIENT`, 'FOOD_NUTRIENT'   |

---
### -- 운동정보 (exerciseinfo) + sequence(exerciseinfo_seq)
###운동정보 (1단계 CRUD 파트/ 입력시 _(언더바) 및 줄일 수 있는 필드명은 줄일 예정.)
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

create sequence exerciseinfo_seq;

insert into exerciseinfo(execid,
                         exectype,
                         description,
                         avgkcal30min,
                         exectargetmin,
                         suitablefor,
                         intensitylevel,
                         createdat,
                         updatedat) 
values ( exerciseinfo_seq.nextval,
        '산책',
        '기본적인 야외활동/스트레스 해소'      
        80.0,
        30,
        '모든 견종, 노령견 포함',
        '저강도',
        디폴트값,
        디폴트값
        );
```
```
(임시)
운동코드(execid)   운동유형(exectype)  운동설명(description)             30분당 평균소모칼로리(avgkcal30min)
     1                  산책           '기본적인 야외활동/스트레스 해소'            80.0

운동권장시간(exectargetmin)           운동추천동물(suitablefor)         운동강도(intensitylevel)
     30                               '모든 견종, 노령견 포함'                   '저강도'

등록일(createdat)                    글수정일(updatedat) 
   디폴트값                                디폴트값
```










