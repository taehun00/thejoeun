<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file="../inc/header.jsp" %> 
   <div class="container card  my-5 p-4">
      <h3 class="card-header"> 반려동물 질환 글쓰기</h3>
      <!-- APP_USER_ID , BTITLE , BCONTENT , BPASS  -->
	  <form action="${pageContext.request.contextPath}/write.quest"  
	  		method="post"    > 
	      <input type="hidden"   name="appUserid"  value="1"> 
		  <div class="mb-3 mt-3">
		    <label for="disname" class="form-label">질환명:</label>
		    <input type="text" class="form-control" id="disname" placeholder="질환명을  입력해주세요" name="disname">
		  </div>
		  <div class="mb-3">
		    <label for="bpass" class="form-label">PASS:</label>
		    <input type="password" class="form-control" id="bpass" placeholder="비밀번호를 입력해주세요" name="bpass">
		  </div>
		  <div class="mb-3">
		    <label for="disex" class="form-label">질환명 설명:</label>
		    <textarea class="form-control" id="disex" placeholder="질환명을 설명해주세요" name="disex"></textarea>
		  </div>  
		  
		  <div class="mb-3">
		    <label for="kindpet" class="form-label">반려동물 종:</label>
		    <textarea class="form-control" id="kindpet" placeholder="반려동물종을 입력해주세요" name="kindpet"></textarea>
		  </div> 
		  
		  <div class="mb-3">
		    <label for="infval" class="form-label">질환명관련 수치:</label>
		    <textarea class="form-control" id="infval" placeholder="질환명관련 수치 입력해주세요" name="infval"></textarea>
		  </div> 
		  
		  <div class="mb-3">
		    <label for="mannote" class="form-label">질환명관련 주의사항:</label>
		    <textarea class="form-control" id="mannote" placeholder="질환명관련 주의사항 입력해주세요" name="mannote"></textarea>
		  </div> 
		  
		 
		  
		  <!-- <div class="mb-3">
		    <label for="file" class="form-label">FILE:</label>
		    <input type="file" class="form-control" id="file" placeholder="파일을 입력해주세요" name="file">
		  </div> -->
		  <div class="mb-3  text-end">
		  	<button type="submit" class="btn btn-danger">글쓰기</button>  
		  	<a href="${pageContext.request.contextPath}/list.quest"  class="btn btn-primary">목록보기</a>
		  </div>
   </form>
   
  </div>
<%@include file="../inc/footer.jsp" %>

<!-- [ mbtiBoard - list.jsp ]  -->