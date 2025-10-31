<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>PetCare Project Template</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
  <style>
:root {
  --beige-main: #FFF8D6;   
  --beige-accent: #FFEB99; 
  --beige-dark: #E6C972;   
  --beige-bg: #FFFEFA;    
  --text-main: #554A2F;    
}

body {
  background-color: var(--beige-bg);
  color: var(--text-main);
  font-family: 'Noto Sans KR', sans-serif;
}

/* ====== 네비게이션 ====== */
.navbar {
  background-color: var(--beige-main);
  box-shadow: 0 2px 4px rgba(0,0,0,0.04);
}
.navbar-brand {
  font-weight: 700;
  color: var(--text-main);
}
.nav-link {
  color: var(--text-main);
  font-weight: 500;
}
.nav-link:hover {
  color: var(--beige-dark);
}

/* ====== 배너 ====== */
.hero {
  background: linear-gradient(135deg, var(--beige-main) 0%, var(--beige-accent) 100%);
  color: var(--text-main);
  padding: 80px 0;
  text-align: center;
}
.hero h1 {
  font-weight: 700;
  font-size: 2.2rem;
}
.hero p {
  color: #6B5C3A;
}

/* ====== 카드/게시판 ====== */
.content {
  padding: 60px 0;
}
.card {
  border: none;
  border-radius: 12px;
  background-color: #FFFFFF;
  box-shadow: 0 3px 8px rgba(0,0,0,0.04);
  transition: transform 0.2s;
}
.card:hover {
  transform: scale(1.02);
}

/* ====== 버튼 ====== */
.btn-beige {
  background-color: var(--beige-accent);
  color: #4F422A;
  border-radius: 50px;
  padding: 8px 24px;
  border: none;
  font-weight: 600;
  transition: 0.2s;
}
.btn-beige:hover {
  background-color: var(--beige-dark);
  color: white;
}

/* ====== 푸터 ====== */
footer {
  background-color: var(--beige-main);
  color: var(--text-main);
  text-align: center;
  padding: 20px 0;
  margin-top: 60px;
  font-size: 0.9rem;
  border-top: 1px solid #FFEFB0;
}
  </style>
</head>
<body>

  <!-- 네비게이션 -->
  <nav class="navbar navbar-expand-lg">
    <div class="container">
      <a class="navbar-brand" href="#">🐾 사이트 이름</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse justify-content-end" id="navbarNav">
        <ul class="navbar-nav">
          <li class="nav-item"><a class="nav-link" href="#">사료추천</a></li>
          <li class="nav-item"><a class="nav-link" href="#">건강정보</a></li>
          <li class="nav-item"><a class="nav-link" href="#">회원가입</a></li>
          <li class="nav-item"><a class="nav-link" href="#">로그인</a></li>
        </ul>
      </div>
    </div>
  </nav>

  <!-- 헤더 내용 -->
  <section class="hero">
    <div class="container">
      <h1>당신의 반려동물에게 최적의 한 끼를 찾아드립니다.</h1>
      <p class="mt-3">건강 데이터를 기반으로 한 맞춤형 사료 가이드</p>
    </div>
  </section>

    <div>
        <h3>내용</h3>

    </div>


  <!-- 푸터 -->
  <footer>
    footer
  </footer>

</body>
</html>
