<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file="../inc/header.jsp" %> 
   <div class="container card  my-5 p-4">
      <h3 class="card-header"> 운동챌린지게시판 글쓰기</h3>
      <!-- POSTID, ETITLE , ECONTENT  -->
	  <form action="${pageContext.request.contextPath}/upload.execboard"  
	  		method="post"  encType="multipart/form-data" > 
	      <input type="hidden"   name="appUserId"  value="1"> 
		  
		  
		  <div class="mb-3 mt-3">
		    <label for="etitle" class="form-label">제목:</label>
		    <input type="text" class="form-control" id="etitle" placeholder="내용을 입력해주세요" name="etitle">
		  </div>
		  <div class="mb-3">
		    <label for="econtent" class="form-label">내용:</label>
		    <textarea class="form-control" id="econtent" placeholder="내용을 입력해주세요" name="econtent"></textarea>
		  </div>  
		  <div class="mb-3">
		    <label for="eimg" class="form-label">이미지:</label>
		    <input type="eimg" class="form-control" id="eimg" placeholder="파일을 입력해주세요" name="eimg">
		  </div>
		  
		  <div class="mb-3  text-end">
		  	<button type="submit" class="btn btn-primary">글쓰기</button>  
		  	<a href="${pageContext.request.contextPath}/list.quest"  class="btn btn-primary">목록보기</a>
		  </div>
		  
    	 <input  type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />	 
     </form> 
   </div>
   
<%@include file="../inc/footer.jsp" %>

<!-- [ ExecBoard - boardwrite.jsp ]  -->