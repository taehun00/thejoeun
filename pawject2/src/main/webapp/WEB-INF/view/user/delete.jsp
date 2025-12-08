<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file="../inc/header.jsp" %>

<<<<<<< HEAD
<div class="container my-5">
  <div class="card shadow-sm">
    <div class="card-header bg-light">
      <h3 class="mb-0">회원 탈퇴</h3>
    </div>
    <div class="card-body">
      <p class="fs-5 text-danger">
        진심으로 탈퇴하시겠습니까?
      </p>
      <form action="${pageContext.request.contextPath}/security/deleteMember" method="post" class="d-flex justify-content-end gap-2">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <input type="hidden" name="userId" value="${dto.userId}">
        <input type="hidden" name="email" value="<sec:authentication property="principal.username" />">
        
        <button type="submit" class="btn btn-burgundy">유저탈퇴</button>
        <a href="javascript:history.go(-1)" class="btn btn-sage">취소</a>
      </form>
    </div>
  </div>
</div>

   
<%@include file="../inc/footer.jsp" %>
=======
   <div class="container card  my-5 p-4 ">
      <h3 class="card-header"> MBTI 탈퇴</h3>
	  <form action="${pageContext.request.contextPath}/security/deleteMember"  method="post">  
	  	  <input  type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
	      <input type="hidden" name="userId" value="${dto.userId}">
		  <input type="hidden" name="email" value="<sec:authentication property="principal.username" />">
			<pre>진심으로 탈퇴</pre>
		  <div class="my-3  text-end">
		  	<button type="submit" class="btn btn-primary">유저탈퇴</button>
		  	<a href="javascript:history.go(-1)"  class="btn btn-danger">BACK</a>
		  </div>
	 </form>
   </div>
   
<%@include file="../inc/footer.jsp" %>

<!-- [ mbtiBoard - list.jsp ]  
    1. 빈칸 알림창  - 빈칸입니다. 알림창 + 커서가게
	2. spring 에서  ajax 사용법  
	3. 로그인중복 - dao / service / controller  
-->
>>>>>>> cb4a38b2ec94f84ae8d5b1165d16b0247b5b119c
