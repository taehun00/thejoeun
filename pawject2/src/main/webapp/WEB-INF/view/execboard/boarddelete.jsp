<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file="../inc/header.jsp" %>

   <div class="container card  my-5 p-4 ">
      <h3 class="card-header"> 운동챌린지게시판 글삭제</h3>
	  <%-- <form action="${pageContext.request.contextPath}/delete.do?id=${param.id}"     method="post">  --%> 
	  <form action="${pageContext.request.contextPath}/delete.execboard"  method="post">  
	      <input type="hidden"   name="userId"  value="${param.userid}"> 
		  <input  type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />			  
		  
		  <div class="my-3">
		    <label for="postid" class="form-label">게시글아이디:</label>
		    <input type="text" class="form-control" 
		    	id="postid"  placeholder="게시글아이디를 입력해주세요." name="postid">
		  </div> 
		  <div class="my-3">
		    <label for="execid" class="form-label">운동아이디:</label>
		    <input type="text" class="form-control" 
		    	id="execid"  placeholder="운동아이디를 입력해주세요." name="execid">
		  </div> 
		  <div class="my-3">
		    <label for="userid" class="form-label">사용자아이디:</label>
		    <input type="text" class="form-control" 
		    	id="userid"  placeholder="사용자아이디를 입력해주세요." name="userid">
		  </div> 
		  
		  
		  <div class="my-3  text-end">
		  	<button type="submit" class="btn btn-roseRed">글삭제</button>
		  	<a href="javascript:history.go(-1)"  class="btn btn-rosePink">BACK</a>
		  </div>
	 </form>
   </div>
   
<%@include file="../inc/footer.jsp" %>

<!-- [ mbtiBoard - list.jsp ]  -->