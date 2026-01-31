# 🐾 Pawject v1
**반려동물 건강 & 사료 종합 플랫폼 (Prototype)**

> Paw + Project = 반려동물을 위한 개발 여정  
> **JSP/Servlet 기반 MVC2 게시판 프로토타입**으로 서비스 아이디어를 실제 흐름(화면/DB/기능)으로 구현한 1차 버전

---

## 🎯 Project Goal (v1)
**Pawject v1은 확장 가능한 ‘기반 설계 + MVP 구현’에 집중한 프로젝트입니다.**

- JSP/Servlet 기반 **MVC2 구조로 게시판 프로토타입 구현**
- CRUD 중심으로 **데이터 흐름/권한/화면 구조 설계**
- 멤버·사료·건강·리뷰·운동 기능을 구현하여 서비스 형태 구체화
- 이후 버전(v2~v4) 확장을 고려한 **UI/DB 구조 기반 마련**

---

## 👥 Team / Period
**박현주 · 성태훈 · 신준용 · 최상욱 · 한승현** 

- **기간**: 2025.11.03 ~ 2025.11.07  
- **인원**: 5명  

---

## 🔗 GitHub
- **Repository**: https://github.com/taehun00/thejoeun  
- **Project Path**: https://github.com/taehun00/thejoeun/tree/master/pawject1  

---

## 🛠 Tech Stack
### Backend
- Java 11 (JDK 11)
- JSP / Servlet (MVC2 Pattern)

### Database
- Oracle 11g

### Frontend
- HTML5 / CSS3
- JavaScript (ES11)
- jQuery 3.7
- Bootstrap 5

### Tools
- Git / GitHub

---

## 🧩 Key Features
> Pawject v1은 **게시판 기능을 기반으로 서비스 기능을 쌓는 구조**로 설계되었습니다.

### ✅ 멤버 관리
- 회원가입 / 로그인
- 회원탈퇴 / 정보수정

### ✅ 사료 게시판
- 사료 데이터 등록 / 조회 / 수정 / 삭제 (CRUD)

### ✅ 건강 정보 게시판
- 건강 정보 등록 / 조회 / 수정 / 삭제 (CRUD)

### ✅ 리뷰 게시판
- 사용자 리뷰 등록 / 조회 / 수정 / 삭제 (CRUD)

### ✅ 운동 정보 게시판
- 운동 정보 등록 / 조회 / 수정 / 삭제 (CRUD)

---

## 🗂 Architecture (MVC2)
Pawject v1은 JSP/Servlet 기반의 전형적인 MVC2 구조로 구현했습니다.

- **Controller (Servlet)** : 요청 처리 / 서비스 호출 / View 라우팅
- **Service** : 비즈니스 로직
- **DAO** : DB 접근 및 SQL 처리
- **View (JSP)** : 출력 화면(UI)

---

## ✨ What We Focused On
짧은 기간의 팀 프로젝트였기 때문에 **마감을 준수하며 아래 기준을 지키는 것**을 목표로 했습니다.

- **데이터 흐름이 보이는 기능 구현**  
  (화면 → 요청 → 처리 → DB → 응답)
- 단순 CRUD가 아닌 **“게시판 묶음 구조”**로 확장 가능 설계
- 추후 버전업을 위한 기초 설계 기반 확보
  - 기능 확장(검색/페이징/필터)
  - 아키텍처 전환(Spring, REST)
  - 프론트 전환(React)

---

## 🧱 Version Roadmap (v1~v4)
> Pawject는 단일 프로젝트가 아니라 **버전업 기반 확장 프로젝트**입니다.

| Version | 핵심 목표 |
|---|---|
| **v1** | 게시판 MVP 구현 + 데이터/화면 구조 설계 |
| v2 | 기능 고도화 (검색/페이징/정렬 등) |
| v3 | 외부 API 연동 및 서비스 확장 |
| v4 | Spring Boot + React + JWT 기반 아키텍처 전환 |

---

## 📌 Result
- MVC2 기반 CRUD 게시판 구조를 실전 형태로 구현
- 핵심 도메인(회원/사료/건강/리뷰/운동) 흐름을 1차 버전에서 확정
- 이후 버전(v2~v4) 확장을 위한 기반 코드 및 DB 설계를 확보

---

## 🙋‍♂️ Contact
- Maintainer: **성태훈**
- GitHub: https://github.com/taehun00/thejoeun