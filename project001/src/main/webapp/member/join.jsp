<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../inc/header.jsp" %>
<!-- header -->
<!-- header -->
<!-- header -->

<div class="container mt-5">
 	<h3>WELCOME! 회원가입</h3>
 	<form action="join_process.jsp" method="post">
	  <div class="mb-3 mt-3">
	    <label for="email" class="form-label">Email:</label>
	    <input type="email" class="form-control" id="email" required placeholder="Enter email" name="email">
	  </div>
	  <div class="mb-3">
	    <label for="password" class="form-label">Password:</label>
	    <input type="password" class="form-control" id="password" placeholder="Enter password" name="password">
	  </div>
	  <div class="mb-3">
	    <label class="form-check-label">MBTI TYPE : 
	    </label>
	    <select name="mbti_type_id" id="mbti"	class="form-control">
	    	<option value="1">ISTJ</option>
	    	<option value="2">ISFJ</option>
	    	<option value="3">INFJ</option>
	    </select>
	  </div>
	  <button type="submit" class="btn btn-primary">Submit</button>
	</form>
</div>
<!-- footer -->
<!-- footer -->
<!-- footer -->

<%@ include file="../inc/footer.jsp" %>