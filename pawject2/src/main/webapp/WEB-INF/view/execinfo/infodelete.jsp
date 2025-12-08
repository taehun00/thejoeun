<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file="../inc/header.jsp" %>

   <div class="container card  my-5 p-4 ">
      <h3 class="card-header"> 운동정보 글삭제</h3>
	  <%-- <form action="${pageContext.request.contextPath}/delete.do?id=${param.id}"     method="post">  --%> 
	  <form action="${pageContext.request.contextPath}/delete.execinfo"  method="post">  
	      <input type="hidden"   name="execid"  value="${param.execid}"> 
		  <input  type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />			  
<<<<<<< HEAD
		  
		  <div class="my-3">
		    <label for="execid" class="form-label">운동아이디:</label>
		    <input type="text" class="form-control" 
		    	id="execid"  placeholder="운동아이디를 입력해주세요" name="execid">
=======
		  <div class="my-3">
		    <label for="execid" class="form-label">운동아이디:</label>
		    <input type="text" class="form-control" 
		    	id="execid"  placeholder="운동아이디를 입력해주세요." name="execid">
>>>>>>> cb4a38b2ec94f84ae8d5b1165d16b0247b5b119c
		  </div> 
		  <div class="my-3">
		    <label for="exectype" class="form-label">운동유형:</label>
		    <input type="text" class="form-control" 
<<<<<<< HEAD
		    	id="exectype"  placeholder="운동유형을 입력해주세요" name="exectype">
=======
		    	id="exectype"  placeholder="운동유형을 입력해주세요." name="exectype">
>>>>>>> cb4a38b2ec94f84ae8d5b1165d16b0247b5b119c
		  </div> 
		  
		  
		  <div class="my-3  text-end">
		  	<button type="submit" class="btn btn-roseRed">글삭제</button>
<<<<<<< HEAD
		  	<a href="javascript:history.go(-1)"  class="btn btn-rosePink">BACK</a>
=======
		  	<a href="javascript:history.go(-1)"  class="btn btn-navy">BACK</a>
>>>>>>> cb4a38b2ec94f84ae8d5b1165d16b0247b5b119c
		  </div>
	 </form>
   </div>
   
<%@include file="../inc/footer.jsp" %>

<!-- [ mbtiBoard - list.jsp ]  -->