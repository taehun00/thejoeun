<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../inc/header.jsp" %>
<!-- header -->


	<div class="container mt-5">
		<h3>삭제</h3>
	</div>


		<form action="${pageContext.request.contextPath}/delete.rv?reviewid=<%=request.getParameter("reviewid")%>" method="post" class="mx-auto;">
		<div class="container mt-5">
		  
		    <div class="mb-3">
		      <label for="password" class="form-label fw-bold"></label>
		      <input type="hidden" name="userid" value="${dto.userid}">    <!--  hidden 빼먹으면 입력창 생겨버림!! -->
		      <input type="password" class="form-control text-center" id="password" placeholder="비밀번호를 입력해 주세요" name="password"
		      style="width:400px; margin: 0 auto;">
		    </div>
		
		    <div class="d-flex justify-content-center gap-3 mt-3">
		      <button type="button" class="btn btn-beige"
        			onclick="location.href='${pageContext.request.contextPath}/list.rv'">목록보기</button>
		      <button type="submit" class="btn btn-beige px-4">삭제</button>
		    </div>

		</div>
		</form>
		
	
<!-- footer -->
<%@include file="../inc/footer.jsp" %>
