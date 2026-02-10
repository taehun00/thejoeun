# 🐾 Pawject v4
**반려동물 건강 & 사료 종합 플랫폼 (Architecture Upgrade)**

> Paw + Project = 반려동물을 위한 개발 여정  
> **React 기반 프론트/백 분리 아키텍처로 전환**하고,  
> JWT 인증 + Redis 캐싱 + CI/CD까지 적용해 **운영/확장 중심 구조로 고도화한 최종 버전**

---

## 🎯 Project Goal (v4)
**Pawject v4는 플랫폼을 ‘서비스형 아키텍처’로 완성하는 단계입니다.**

- React 기반 프론트/백 분리 아키텍처로 전환 → **확장성 & 유지보수성 확보**
- API 중심 구조로 재편하여 기능 간 연동성 강화 → **신규 기능 개발 속도 향상**
- **JWT 인증 + Redis 캐싱** 적용으로 보안성과 성능을 동시에 확보
- 사용자 중심 UI/UX 개편으로 콘텐츠 탐색 효율/참여도를 높여 경쟁력 강화

---

## 👥 Team / Period
### ✔ Role Assignment
- **성태훈(팀장)** : 계정/권한 및 사용자 기능 총괄 (유저관리·좋아요·신고)
- **박현주** : 체험단/질환정보 도메인 기능 담당 (게시판 설계·리뉴얼)
- **한승현** : 커뮤니티/프로모션 UI 담당 (운동 챌린지·SNS 피드(추후업데이트예정)·광고 배너)

    </br>
    
- **기간**: 2026.01.19 ~ 2026.01.31  
---

## 🔗 Links
- **Repository**: https://github.com/taehun00/thejoeun  
- **Project Path**: https://github.com/taehun00/thejoeun/tree/master/pawject4  
- **Figma(PPT)**: (업데이트 예정)
- **구글시트**: https://docs.google.com/spreadsheets/d/1tKH45UxPa-RrMnF8XNpTcCXr2Naq1IjctwE3coVSyS8/edit?gid=0#gid=0



### 🎥 Demo Video (v4)
> 썸네일 클릭 시 시연 영상으로 이동합니다.

<table>
  <tr>
    <th>체험단/잘환정보</th>
    <th>좋아요/신고</th>
    <th>운동챌린지/광고배너</th>
  </tr>
  <tr>
    <td align="center">
      <a href="https://youtu.be/pZOSRqIKZ6s?si=lzPm4VY2YeDzePXH" target="_blank" rel="noopener noreferrer">
        <img src="https://img.youtube.com/vi/pZOSRqIKZ6s/0.jpg" width="200" />
      </a>
      <p><strong>박현주</strong></p>
    </td>
    <td align="center">
      <a href="https://www.youtube.com/watch?v=TF3cFZ7jMKw&t=2s" target="_blank" rel="noopener noreferrer">
        <img src="https://img.youtube.com/vi/TF3cFZ7jMKw/0.jpg" width="200" />
      </a>
      <p><strong>성태훈</strong></p>
    </td>
    <td align="center">
      <a href="https://www.youtube.com/watch?v=L5Sabniz1DY" target="_blank" rel="noopener noreferrer">
        <img src="https://img.youtube.com/vi/L5Sabniz1DY/maxresdefault.jpg" width="200" />
      </a>
      <p><strong>한승현</strong></p>
    </td>
  </tr>
</table>


## 🛠 Tech Stack (v4)
### Backend
- Java 11 (JDK 11)
- **Spring Boot 3.4 (MVC, Security)**
- **JPA**
- **MyBatis 3.5**
- RESTful API

### Database / Cache
- Oracle 11g
- **Redis (캐싱 및 세션 관리)**

### Frontend
- **React**
- **Ant Design (Antd)**
- **Chart.js**

### Others
- Node.js
- External API
- **JWT 인증**

### DevOps
- **AWS (EC2, S3, RDS)**
- **GitHub Actions 기반 CI/CD 파이프라인 구축**

---

## ✅ Main Features (v4)

### ✅ 1) React 전환 (UI 리뉴얼)
- 기존 게시판 UI를 React 기반으로 재구성
- 공통 UI(헤더/네비게이션) 구성
- 메인페이지 리뉴얼

### ✅ 2) 인증/보안 (JWT)
- Spring Security 기반 인증 구조를 **JWT 토큰 방식으로 전환**
- API 중심 로그인/인가 체계 구축

### ✅ 3) 신규/확장 기능
- **체험단 게시판** 신규 개발
- **운동챌린지 게시판** 신규 개발(피드형 UI)
- **질병정보 게시판** 기존 기능 리빌드(DB 리뉴얼 + 구조 재설계 및 운영 기능 확장)
- **커뮤니티 운영 기능** 추가(댓글 / 좋아요 / 신고)
- **광고 배너 기능** 추가(운영 수익 모델 기반)
- 기존 데이터 구조 개편 및 기능 확장

---

## 🗂 Architecture (v4 핵심 구조)
- React SPA + Spring Boot REST API 기반 **프론트/백 분리**
- JWT 기반 인증 흐름
- Redis 캐싱으로 성능 최적화
- CI/CD 파이프라인 구축으로 배포 자동화

---

## ✨ What’s New (v3 → v4 개선점)
v4는 기능 추가보다 **아키텍처 전환과 운영 안정성 강화**에 초점을 맞췄습니다.

- Thymeleaf 기반 구조 → **React SPA 전환**
- 서버 렌더링 중심 → **API 중심 구조**
- 보안/인증 구조 강화: **JWT 적용**
- 성능 개선: **Redis 캐싱**
- 배포/운영 체계 강화: **AWS + GitHub Actions CI/CD**

---

## 🧱 Version Roadmap (v1~v4)
| Version | 핵심 목표 |
|---|---|
| v1 | 게시판 MVP 구현 + 데이터/화면 구조 설계 |
| v2 | 검색/페이징/AJAX/업로드 기반 실사용 서비스화 |
| v3 | 외부 API 연동 + 고객센터/검색/운영 확장으로 종합 플랫폼화 |
| **v4** | React 전환 + JWT/Redis/CI-CD 적용으로 운영형 아키텍처 완성 |

---

## 📌 Result
- 프론트/백 분리 아키텍처 기반으로 확장성과 유지보수성 확보
- JWT 인증 및 Redis 캐싱 적용으로 보안성과 응답 성능 강화
- 체험단(신규) / 질병정보(DB 리뉴얼) / 운동챌린지(피드형 리뉴얼) 기능 확장으로 서비스 경쟁력 강화
- 댓글 / 좋아요 / 신고 기능 적용으로 커뮤니티 운영 기능 강화
- CI/CD 파이프라인 구축으로 배포 효율 및 운영 안정성 향상
- 광고 배너 기능 반영으로 운영 확장성(수익 모델) 기반 마련
