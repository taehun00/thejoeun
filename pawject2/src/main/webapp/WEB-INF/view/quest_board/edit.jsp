<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../inc/header.jsp" %>

  <div class="container card  my-5 p-4">
     <h3 class="card-header"> 반려동물 질환 글 수정</h3>
  <form action="${pageContext.request.contextPath}/edit.quest"  
  		method="post"  > 
     <input type="hidden"   name="disno"  value="${dto.disno}">  
          <input  type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
	  <div class="mb-3 mt-3">
	    <label for="disname" class="form-label">질환명:</label>
	    <input type="text" class="form-control" id="disname" 
	    	  placeholder="질환명을 입력해주세요" name="disname"  value="${dto.disname}">
	  </div>
	  <div class="mb-3">
	    <label for="bpass" class="form-label">PASS:</label>
	    <input type="password" class="form-control" id="bpass" 
	    	placeholder="비밀번호를 입력해주세요" name="bpass">
	  </div>
	  <div class="mb-3">
	    <label for="disex" class="form-label">질환명 설명:</label>
	    <textarea class="form-control" id="disex"
	    	 placeholder="질환명을 입력해주세요" name="disex">${dto.disex}</textarea>
	  </div> 
	  
	  <div class="mb-3">
	    <label for="kindpet" class="form-label">반려동물 종:</label>
	    <textarea class="form-control" id="kindpet"
	    	 placeholder="반려동물종을 입력해주세요" name="kindpet">${dto.kindpet}</textarea>
	  </div> 
	  
	  <div class="mb-3">
	    <label for="infval" class="form-label">질환명관련 수치:</label>
	    <textarea class="form-control" id="infval"
	    	 placeholder="질환명관련 수치 입력해주세요" name="infval">${dto.infval}</textarea>
	  </div> 
	  
	  <div class="mb-3">
	    <label for="mannote" class="form-label">질환명관련 주의사항:</label>
	    <textarea class="form-control" id="mannote"
	    	 placeholder="질환명관련 주의사항 입력해주세요" name="mannote">${dto.mannote}</textarea>
	  </div> 
	  
	 <%--  <div class="mb-3">
	    <label for="kindpet" class="form-label">반려동물 종:</label>
	    <input type="file" class="form-control" id="kindpet" placeholder="파일을 입력해주세요" name="kindpet">
	    
	  	<input type="text" class="form-control" id="bfile"   readonly  name="bfile"  value="${dto.kindpet}">
	  </div>	  --%> 
	  
	  
	  <div class="mb-3  text-end">
	  <!-- 로그인한 사람만 글 수정 -->
	  <sec:authorize access="isAuthenticated()"> 
	  	<button type="submit" class="btn btn-primary">글수정</button>
	  </sec:authorize>
	  	<a href="javascript:history.go(-1)"  class="btn btn-danger">BACK</a>
	  </div>
 </form>
  </div>
   
<%@include file="../inc/footer.jsp" %>

<!-- [ mbtiBoard - list.jsp ]  -->