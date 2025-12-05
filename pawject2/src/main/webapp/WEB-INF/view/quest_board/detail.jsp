<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file="../inc/header.jsp" %> 

   <script>
   $(function(){
	   let result = '${success}';
	   if(result == '비밀번호를 확인해주세요'){  alert( result  );  history.go(-1); }
	   else  if(result.length  != 0 ){  alert(result); }  //아까 처음 값이없을때 공백
   });
   
   </script>
   <div class="container card  my-5 p-4">
      <h3 class="card-header"> 반려동물 질환 글 수정 <%-- ${dto} --%></h3>
	  <div> 
	      <input type="hidden"   name="appUserid"  value=""> 
		  <div class="mb-3 mt-3">
		    <label for="bhit" class="form-label">조회수</label>
		    <input type="text" class="form-control" id="bhit" name="bhit"  readonly   value="${dto.bhit}">
		  </div> 
		  <div class="mb-3 mt-3">
		    <label for="disname" class="form-label">질환명:</label>
		    <input type="text" class="form-control" id="disname" 
		    		placeholder="질환명을  입력해주세요" name="disname"  readonly  value="${dto.disname}">
		  </div>  
		  <div class="mb-3">
		    <label for="disex" class="form-label">질환명 설명:</label>
		    <textarea class="form-control" id="disex" placeholder="내용을 입력해주세요"   
		    	readonly name="disex">${dto.disex}</textarea>
		  </div> 
		  
		  <div class="mb-3">
		    <label for="kindpet" class="form-label">반려동물 종:</label>
		    <textarea class="form-control" id="kindpet" placeholder="내용을 입력해주세요"   
		    	readonly name="kindpet">${dto.kindpet}</textarea>
		  </div> 
		  
		  <div class="mb-3">
		    <label for="infval" class="form-label">질환명관련 수치:</label>
		    <textarea class="form-control" id="infval" placeholder="내용을 입력해주세요"   
		    	readonly name="infval">${dto.infval}</textarea>
		  </div> 
		  
		  <div class="mb-3">
		    <label for="mannote" class="form-label">질환명관련 주의사항:</label>
		    <textarea class="form-control" id="mannote" placeholder="내용을 입력해주세요"   
		    	readonly name="mannote">${dto.mannote}</textarea>
		  </div> 
		  
		 <%--  <div class="mb-3">
		    <label for="bfile" class="form-label">파일:</label> 
		    <img src="${pageContext.request.contextPath}/upload/${dto.bfile}" alt=""  class="w-50"/>
		  </div>  --%>		  
		  
			<div class="mb-3">
			<a href="${pageContext.request.contextPath}/edit.quest?disno=${dto.disno}" class="btn btn-success form-control">글수정</a>
			</div>
			<div class="mb-3">
			<a href="${pageContext.request.contextPath}/delete.quest?disno=${dto.disno}" class="btn btn-secondary form-control">글삭제</a>
			</div>  
		  <div class="mb-3">
		  	<a href="${pageContext.request.contextPath}/list.quest" class="btn btn-primary form-control">목록보기</a>
		  </div>
	 </div>
   </div> 
<%@include file="../inc/footer.jsp" %>

<!-- [ mbtiBoard - list.jsp ]  -->