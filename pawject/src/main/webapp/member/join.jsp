<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../inc/header.jsp" %>
<!-- header -->


	<div class="container mt-5">
		<h3>회원가입</h3>
		
		<!-- action method name -->
		
			<form action="join_process.jsp" method="post">
			
			  <div class="mb-3 mt-3">
			    <label for="email" class="form-label">Email:</label>
			    <input type="email" class="form-control" id="email" placeholder="메일을 입력해 주세요" required name="email">
			  </div>
			
			  <div class="mb-3">
			    <label for="nickname" class="form-label">Nickname:</label>
			    <input type="text" class="form-control" id="nickname" placeholder="닉네임을 입력해 주세요" name="nickname">
			  </div>
			
			  <div class="mb-3">
			    <label for="password" class="form-label">Password:</label>
			    <input type="password" class="form-control" id="password" placeholder="비밀번호를 입력해 주세요" name="password">
			  </div>
			
			  <button type="submit" class="btn btn-beige">Submit</button>
			
			</form>
		
		
	</div>





<!-- footer -->
<%@include file="../inc/footer.jsp" %>
