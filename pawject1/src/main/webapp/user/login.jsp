<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../inc/header.jsp" %>


<div class="container mt-5">
	<h3> 로그인 </h3>
	<form action="${pageContext.request.contextPath}/login.u" method="post">

	  <div class="mb-3 mt-3">
	    <label for="email" class="form-label">Email:</label>
	    <input type="email" class="form-control" id="email" required placeholder="Enter email" name="email">
	  </div>
	  <div class="mb-3">
	    <label for="password" class="form-label">Password:</label>
	    <input type="password" class="form-control" id="password" placeholder="Enter password" name="password">
	  </div>




	
	<div class="d-flex justify-content-end gap-3 mt-3">
		  <button type="submit" class="btn btn-beige">로그인</button>
		<a href="${pageContext.request.contextPath}/joinView.u" class="btn btn-beige">회원가입</a>
	</div>

	</form>
</div>
	


<%@ include file="../inc/footer.jsp" %>