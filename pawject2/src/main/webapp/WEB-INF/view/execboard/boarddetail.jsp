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
      <h3 class="card-header"> 글상세보기  <%-- ${dto} --%></h3>
	  <div> 
	      <input type="hidden"   name="postId"  value=""> 
		  
		  <!-- 외래키로 가지고 올 것들. / placeholder="내용을 입력해주세요" ← 사용해야 될 수도 있으니 임시보관 -->
		  <div class="mb-3 mt-3">
		    <label for="postid" class="form-label">게시글아이디:</label>
<<<<<<< HEAD
		    <input type="text" class="form-control" id="postid"  name="postid">
		  </div>
		  <div class="mb-3 mt-3">
		    <label for="execid" class="form-label">운동아이디:</label>
		    <input type="text" class="form-control" id="execid"  name="execid">
		  </div>
 	  	  <div class="mb-3">
		    <label for="userid" class="form-label">사용자아이디:</label>
		    <input type="text" class="form-control" id="userid" name="userid">
=======
		    <input type="text" class="form-control" id="postid"  name="postid"
		    		readonly value="${dto.postid}">
		  </div>
		  <div class="mb-3 mt-3">
		    <label for="execid" class="form-label">운동아이디:</label>
		    <input type="text" class="form-control" id="execid"  name="execid"
		    		readonly value="${dto.execid}">
		  </div>
 	  	  <div class="mb-3">
		    <label for="userid" class="form-label">사용자아이디:</label>
		    <input type="text" class="form-control" id="userid" name="userid"
		    		readonly value="${dto.userid}">
>>>>>>> cb4a38b2ec94f84ae8d5b1165d16b0247b5b119c
		  </div>
		  <!-- 외래키로 가지고 올 것들. -->		   
		  <div class="mb-3 mt-3">
		    <label for="etitle" class="form-label">제목:</label>
<<<<<<< HEAD
		    <input type="text" class="form-control" id="etitle" placeholder="제목을 입력해주세요" name="etitle" readonly  value="${dto.etitle}">
		  </div>
		  <div class="mb-3">
		    <label for="econtent" class="form-label">내용:</label>
		    <textarea class="form-control" id="econtent" placeholder="내용을 입력해주세요" name="econtent">${econtent}</textarea>
		  </div> 
		  
		  <div class="mb-3">
		    <label for="eimg" class="form-label">파일:</label>
		  	<img src="${pageContext.request.contextPath}/upload/${dto.eimg}" alt=""/>
		  </div> 
		 
			<div class="mb-3">
			<a href="${pageContext.request.contextPath}/edit.execboard?postid=${dto.postid}" class="btn btn-lavender form-control">글수정</a>
			</div>
			<div class="mb-3">
			<a href="${pageContext.request.contextPath}/delete.execboard?postid=${dto.postid}" class="btn btn-roseRed form-control">글삭제</a>
			</div> 
		  <div class="mb-3">
		  	<a href="${pageContext.request.contextPath}/list.execboard?postid=${dto.postid}" class="btn btn-mustard form-control">목록보기</a>
		  </div>
=======
		    <input type="text" class="form-control" id="etitle" placeholder="제목을 입력해주세요." name="etitle" readonly  value="${dto.etitle}">
		  </div>
		  <div class="mb-3 mt-3">
		    <label for="econtent" class="form-label">내용:</label>
		    <input type="text" class="form-control" id="econtent" placeholder="네용을 입력해주세요." name="econtent" readonly  value="${dto.econtent}">
		  </div>
		   <div class="mb-3">
			  <label for="file" class="form-label">이미지:</label>
			  <img src="${pageContext.request.contextPath}/upload/${dto.eimg}"  alt="eimg"
			        style="width:100px;" />
			</div>	
			<sec:authorize access="isAuthenticated()">
				<div class="mb-3">
				<a href="${pageContext.request.contextPath}/edit.execboard?postid=${dto.postid}" class="btn btn-lavender form-control">글수정</a>
				</div>
			</sec:authorize>	
			
			<sec:authorize access="isAuthenticated()">
				<div class="mb-3">
				<a href="${pageContext.request.contextPath}/delete.execboard?postid=${dto.postid}" class="btn btn-roseRed form-control">글삭제</a>
				</div> 
			</sec:authorize>
				
			<sec:authorize access="isAuthenticated()">
			  <div class="mb-3">
			  	<a href="${pageContext.request.contextPath}/list.execboard?postid=${dto.postid}" class="btn btn-mustard form-control">목록보기</a>
			  </div>
			</sec:authorize>  
>>>>>>> cb4a38b2ec94f84ae8d5b1165d16b0247b5b119c
	 </div>
   </div> 
<%@include file="../inc/footer.jsp" %>

<!-- [ mbtiBoard - list.jsp ]  -->