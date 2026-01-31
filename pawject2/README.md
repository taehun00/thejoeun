# 🐾 Pawject v2
**반려동물 건강 & 사료 종합 플랫폼 (Service Upgrade)**

> Paw + Project = 반려동물을 위한 개발 여정  
> **v1 프로토타입을 Spring Framework 기반으로 확장**하여  
> 검색/페이징/AJAX/다중 이미지 업로드까지 포함한 **실사용 가능한 서비스 형태**로 고도화한 버전

---

## 🎯 Project Goal (v2)
**Pawject v2는 v1의 초기 구조를 기반으로 ‘실사용 가능한 운영형 서비스’로 개선한 버전입니다.**

- v1을 Spring 기반으로 확장하여 핵심 게시판 기능 고도화
- **검색 · 페이징 · 다중 이미지 업로드 · AJAX 운영 기능** 추가
- 데이터 구조 및 UI 일관성 강화 → 사용자 경험 & 운영 효율 동시 확보
- **법적 리스크를 고려한 데이터/이미지 처리 체계** 적용으로 안정적 운영 기반 마련

---

## 👥 Team / Period
**박현주 · 성태훈 · 최상욱 · 한승현**

- **기간**: 2025.12.01 ~ 2025.12.07  
- **인원**: 4명  

---

## 🔗 GitHub
- **Repository**: https://github.com/taehun00/thejoeun  
- **Project Path**: https://github.com/taehun00/thejoeun/tree/master/pawject2

---

## 🎬 Demo / Docs

- **Figma(PPT)**: https://www.figma.com/deck/j626h6S3cxnQN7z0ZsQCNT/PAWJECT_ver2?node-id=10-129&t=LYEOVxYYTiaQjsJA-1

### 🎥 Demo Video
> 썸네일 클릭 시 시연 영상으로 이동합니다.

| 멤버 | 리뷰·사료 | 질환정보 | 운동 |
|---|---|---|---|
| [![멤버](https://img.youtube.com/vi/solt_Vr0Hm0/0.jpg)](https://www.youtube.com/watch?v=solt_Vr0Hm0&t=2s) | [![리뷰+사료](https://img.youtube.com/vi/1OgiHFzOJ4M/0.jpg)](https://www.youtube.com/watch?si=IoxqWmDDXXLwLkd4&v=1OgiHFzOJ4M&feature=youtu.be) | [![질환정보](https://img.youtube.com/vi/ZcX2HGLvasw/0.jpg)](https://www.youtube.com/watch?v=ZcX2HGLvasw&feature=youtu.be) | [![운동](https://img.youtube.com/vi/eN79WDRs4wI/0.jpg)](https://www.youtube.com/watch?v=eN79WDRs4wI) |
---

## 🛠 Tech Stack (v2)
### Backend
- Java 11 (JDK 11)
- **Spring Boot 2.7 (MVC, Security)**
- **MyBatis 3.5**
- Thymeleaf
- RESTful API

### Database
- Oracle 11g

### Frontend
- HTML5 / CSS3
- Bootstrap 5
- JavaScript / jQuery
- **AJAX**

### Others / Collaboration
- Git / GitHub
- Figma
- External API
- GitHub 기반 협업 및 버전 관리

---

## ✅ Main Features (v2)
> Pawject v2는 “CRUD + 검색/페이징 + 운영 편의 기능”을 중심으로 고도화했습니다.

### ✅ 1) 멤버 관리
- 회원가입 / 로그인 / 탈퇴 / 정보수정
- **펫정보 관리**
- **회원 검색**
- **페이징 처리**

### ✅ 2) 사료 게시판 (관리자)
- 사료 데이터 CRUD
- **빠른 삭제 기능**
- **검색 / 페이징**

### ✅ 3) 리뷰 게시판
- 리뷰 CRUD
- **검색 / 페이징**
- **다중 이미지 첨부(업로드)**

### ✅ 4) 건강 정보 게시판
- 건강 정보 CRUD
- **검색 / 페이징**

### ✅ 5) 운동 정보 게시판
- 운동 정보 CRUD
- **검색 / 페이징**

---

## 🗂 Architecture
- Spring Boot MVC 기반 구조
- Spring Security 적용(인증/인가)
- MyBatis 기반 DB 접근
- Thymeleaf 기반 뷰 렌더링
- 일부 기능 RESTful API 구성

---

## ✨ What’s New (v1 → v2 개선점)
v2는 단순한 “프레임워크 변경”이 아니라 **운영 가능한 서비스로의 확장**에 초점을 맞췄습니다.

- MVC2(JSP/Servlet) → **Spring Boot 기반으로 전환**
- 단순 CRUD → **검색/페이징을 포함한 실사용 흐름 완성**
- 리뷰 게시판 **다중 이미지 업로드 지원**
- AJAX 운영 기능 도입으로 UX 개선
- 데이터/이미지 처리에서 **법적 리스크 대응 고려**

---

## 🧱 Version Roadmap (v1~v4)
| Version | 핵심 목표 |
|---|---|
| v1 | 게시판 MVP 구현 + 데이터/화면 구조 설계 |
| **v2** | 검색/페이징/AJAX/업로드 기반 실사용 서비스화 |
| v3 | 외부 API 연동 및 서비스 확장 |
| v4 | Spring Boot + React + JWT 기반 아키텍처 전환 |

---

## 📌 Result
- v1 프로토타입을 Spring 기반으로 확장하여 서비스 구조 고도화
- 운영/사용 관점에서 필요한 검색·페이징·업로드 기능을 반영해 완성도 개선
- 데이터 및 UI 일관성 강화로 유지보수성과 사용자 경험을 동시에 확보