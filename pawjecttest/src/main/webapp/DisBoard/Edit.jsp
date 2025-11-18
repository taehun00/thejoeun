<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../inc/header.jsp" %>

   <div class="container card  my-5 p-4">
      <h3 class="card-header"> 질환 내용 수정</h3>
	  <form action="<%=request.getContextPath() %>/Pawjectedit.swc?id=${dto.disno}"  method="post"> 
	      <!-- <input type="hidden"   name="app_user_id"  value="">  -->
	      <input type="hidden"   name="app_user_id"  value="${dto.disno}">
		  <div class="mb-3 mt-3">
		    <label for="title" class="form-label">DISNAME(질환명):</label>
		    <input type="text" class="form-control" id="title" placeholder="DISNAME(질환명)내용을 입력해주세요" name="disname" value ="${dto.disname}">
		  </div>  
		  
		  <div class="mb-3">
		    <label for="content" class="form-label">DISEX(질환 설명):</label>
		    <textarea class="form-control" id="content" placeholder="질환 설명 내용을 입력해주세요" name="disex">${dto.disex}</textarea>
		  </div> 
		  
		  <div class="mb-3">
		    <label for="content" class="form-label">KINDPET(대표 품종):</label>
		    <textarea class="form-control" id="content" placeholder="대표 품종 내용을 입력해주세요" name="kindpet">${dto.kindpet}</textarea>
		  </div> 
		  
		  <div class="mb-3">
		    <label for="content" class="form-label">INFVAL(수치화 정보):</label>
		    <textarea class="form-control" id="content" placeholder="수치화 정보 내용을 입력해주세요" name="infval">${dto.infval}</textarea>
		  </div> 
		  
		  
		  <div class="mb-3">
		    <label for="content" class="form-label">MANNOTE(관리 및 주의점):</label>
		    <textarea class="form-control" id="content" placeholder="관리 및 주의점 내용을 입력해주세요" name="mannote">${dto.mannote}</textarea>
		  </div> 
		  
		  
		  
		  
		  <div class="mb-3  text-end">
		  	<button type="submit" class="btn btn-primary">글수정</button>
		  	<a href = "javascript:history.go(-1)" class = "btn btn-danger">Back</a>
		  </div>
	 </form>
   </div>
   
<%@include file="../inc/footer.jsp" %>