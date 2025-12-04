<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file="../inc/header.jsp" %>
	<script>
	$(function(){
		let result = '${success}';
		if(result.length== '비밀번호를 확인해주세요'){ alert( result ); history.go(-1); }
		else if(result.length > 0){ alert(result) } //아까 처음 갋이없을때 공백
	});
	
	</script>
   <div class="container card  my-5 p-4">
      <h3 class="card-header"> MBTI  QUEST 상세보기  <%-- ${dto} --%></h3>
	  <div> 
	      <input type="hidden"   name="app_user_id"  value=""> 
		  <div class="mb-3 mt-3">
		    <label for="hit" class="form-label">조회수</label>
		    <input type="text" class="form-control" id="hit" name="hit"  readonly   value="${dto.bhit}">
		  </div> 
		  <div class="mb-3 mt-3">
		    <label for="title" class="form-label">TITLE:</label>
		    <input type="text" class="form-control" id="title" 
		    		placeholder="내용을 입력해주세요" name="title"  readonly  value="${dto.btitle}">
		  </div>  
		  <div class="mb-3">
		    <label for="content" class="form-label">CONTENT:</label>
		    <textarea class="form-control" id="content" placeholder="내용을 입력해주세요"   
		    	readonly name="content">${dto.bcontent}</textarea>
		  </div> 
		  <div class="mb-3">
		    <label for="bfile" class="form-label">파일:</label>
		  	<img src="${pageContext.request.contextPath}/upload/${dto.bfile}" alt=""/>
		  </div> 
		 
			<div class="mb-3">
			<a href="${pageContext.request.contextPath}/edit.quest?id=${dto.id}" class="btn btn-success form-control">글수정</a>
			</div>
			<div class="mb-3">
			<a href="${pageContext.request.contextPath}/delete.quest?id=${dto.id}" class="btn btn-secondary form-control">글삭제</a>
			</div> 
		  <div class="mb-3">
		  	<a href="#" class="btn btn-primary form-control">목록보기</a>
		  </div>
	 </div>
   </div> 
<%@include file="../inc/footer.jsp" %>

<!-- [ mbtiBoard - list.jsp ]  -->