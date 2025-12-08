<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib  prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Pet Food & Wellness</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
  <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
  
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/butten.css">
     <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/review.css">
</head>
<body>


<div class="p-5 bg-primary text-white text-center">
<p class="mb-0 fs-4 ">🐾&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;🐾</p>
<h1 class="fw-bold">Pet Food & Wellness</h1>
<p class="mb-0 fs-4">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;🐾</p>
<p class="mb-0">반려동물을 위한 사료·건강 데이터 통합 서비스</p>
</div>

<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <div class="container-fluid">
    <a class="navbar-brand fw-bold" href="">HOME</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navMenu">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navMenu">

        <ul class="navbar-nav ms-auto align-items-center">
		 


          <!-- 로그인 사용자 메뉴 -->
          <sec:authorize access="isAuthenticated()">
          
           <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/reviewlist.fn">사료 리뷰</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/list.execboard">운동 챌린지</a>
          </li>
                   
 
            <li class="nav-item">
              <a class="nav-link fw-bold text-info" href="${pageContext.request.contextPath}/security/mypage">
                <sec:authentication property="principal.dto.email" />
              </a>
            </li>
            <li class="nav-item">
              <form action="${pageContext.request.contextPath}/security/logout" method="post" class="d-inline">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button type="submit" class="btn btn-sm btn-danger ms-2">로그아웃</button>
              </form>
            </li>
          </sec:authorize>

          <!-- 비로그인 사용자 메뉴 -->
          <sec:authorize access="isAnonymous()">
          
          <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/reviewlist.fn">사료 리뷰</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/list.execboard">운동 챌린지</a>
          </li>
          
            <li class="nav-item">
              <a class="nav-link" href="${pageContext.request.contextPath}/security/login">LOGIN</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="${pageContext.request.contextPath}/security/join">JOIN</a>
            </li>
          </sec:authorize>


      <ul class="navbar-nav ms-auto">

<<<<<<< HEAD
        <li class="nav-item">
          <a class="nav-link" href="${pageContext.request.contextPath}/foodlist.fn">사료 관리</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="${pageContext.request.contextPath}/reviewlist.fn">사료 리뷰</a>
        </li>

        <li class="nav-item">
          <a class="nav-link" href="${pageContext.request.contextPath}/list.execboard">운동 챌린지게시판</a>
        </li>
		
		<!-- 로그인한 사용자만 보이는 메뉴 -->
        <sec:authorize access="isAuthenticated()">
          <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/security/mypage">
              <sec:authentication property="principal.dto.email" />
            </a>
          </li>
          <li class="nav-item">
            <form action="${pageContext.request.contextPath}/security/logout" method="post">
              <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
              <input type="submit" value="로그아웃" class="btn btn-danger" />
            </form>
          </li>
        </sec:authorize>

        <!-- 비로그인 사용자만 보이는 메뉴 -->
        <sec:authorize access="isAnonymous()">
          <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/security/login">LOGIN</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/security/join">JOIN</a>
          </li>
        </sec:authorize>
        
=======
>>>>>>> 281a9d3c0e920c5175bf5c2aae3eabb3cec26ae5
        <sec:authorize access="hasRole('ADMIN')">
		  <li class="nav-item">
		    <a class="nav-link fw-bold text-warning" href="${pageContext.request.contextPath}/security/listPage">
		      관리자 USER
		    </a>
		 </li>
		 <li>   
		    <a class="nav-link fw-bold text-warning" href="${pageContext.request.contextPath}/pet/listPetPage">
		      관리자 PET
		    </a>
		  </li>
		  <li class="nav-item">
		      <a class="nav-link fw-bold text-warning" href="${pageContext.request.contextPath}/foodlist.fn">사료 관리</a>
		  </li>
		  
		<li class="nav-item">
          <a class="nav-link fw-bold text-warning" href="${pageContext.request.contextPath}/list.quest">질환 리스트</a>
        </li>
		</sec:authorize>
        
		
      </ul>
    </div>

  </div>
</nav>
<!-- 	header		 -->
<!-- 	header		 -->
<!-- 	header		 -->
    