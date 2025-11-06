<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>PetCare Project Template</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
  
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/inc/header.css">
  

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
        
      <%
		String email = (String)session.getAttribute("email");
		Integer sid= (Integer)session.getAttribute("userid");
      %>
        
         <%if(email!=null){%>
        
          <li class="nav-item"><a class="nav-link" href="#">사료추천</a></li>
          <li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/list.rv">리뷰</a></li>
          <li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/member/mypage.jsp?userid=<%=sid%>"><%=email%></a></li>
          <li class="nav-item"><a class="nav-link" href="#">로그아웃</a></li>
        
         <%}else{%>
        
          <li class="nav-item"><a class="nav-link" href="#">사료추천</a></li>
          <li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/list.rv">리뷰</a></li>
          <li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/member/join.jsp">회원가입</a></li>
          <li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/member/login.jsp">로그인</a></li>
          
          <% }%>
          
        </ul>
      </div>
    </div>
  </nav>

  <!-- 헤더 배너 -->
  <section class="hero">
    <div class="container">
      <h1>당신의 반려동물에게 최적의 한 끼를 찾아드립니다.</h1>
      <p class="mt-3">따뜻한 마음으로 건강한 사료를 추천합니다.</p>
      
    <!-- <button class="btn-beige mt-3">시작하기</button> --> 
    
    
    </div>
  </section>