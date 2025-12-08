<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../inc/header.jsp" %>
<<<<<<< HEAD
=======
<script>
	$(function(){
		let result = '${success}';
		console.log(result);
		if(result == '사용자아이디를 확인해주세요.'){
			alert(result); history.go(-1);
		}else if(result.length !=0){
			alert(result);
		}
	});
</script>
>>>>>>> cb4a38b2ec94f84ae8d5b1165d16b0247b5b119c

  <div class="container card  my-5 p-4">
     <h3 class="card-header"> 운동챌린지게시판 글수정  </h3>
  <form action="${pageContext.request.contextPath}/updateEdit.execboard" 
  		method="post" encType="multipart/form-data" > 
     <!--  <input type="hidden"   name="app_user_id"  value="">  -->
     <input type="hidden"   name="postId"  value="${dto.postid}">
     <input  type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />	
	  
	  
	  <div class="mb-3 mt-3">
	    <label for="postid" class="form-label">게시글아이디:</label>
	    <input type="text" class="form-control" id="postid" 
<<<<<<< HEAD
	    	  placeholder="게시글아이디를 입력해주세요" name="postid"  value="${dto.postid}">
=======
	    	  placeholder="게시글아이디를 입력해주세요." name="postid"  readonly value="${dto.postid}">
>>>>>>> cb4a38b2ec94f84ae8d5b1165d16b0247b5b119c
	  </div>
	  <div class="mb-3 mt-3">
	    <label for="execid" class="form-label">운동아이디:</label>
	    <input type="text" class="form-control" id="execid" 
<<<<<<< HEAD
	    	  placeholder="운동아이디 입력해주세요" name="execid"  value="${dto.execid}">
=======
	    	  placeholder="운동아이디 입력해주세요." name="execid"  readonly value="${dto.execid}">
>>>>>>> cb4a38b2ec94f84ae8d5b1165d16b0247b5b119c
	  </div>
	  <div class="mb-3 mt-3">
	    <label for="userid" class="form-label">사용자아이디:</label>
	    <input type="text" class="form-control" id="userid" 
<<<<<<< HEAD

	    	  placeholder="사용자아이디 입력해주세요." name="userid"  readonly  value="${dto.userid}">

	  </div>
	  
	  
	  <div class="mb-3 mt-3">
	    <label for="etitle" class="form-label">제목:</label>
	    <input type="text" class="form-control" id="etitle" 
	    	  placeholder="제목을 입력해주세요" name="etitle"  value="${dto.etitle}">
=======
	    	  placeholder="사용자아이디 입력해주세요." name="userid"  readonly  value="${dto.userid}">

	  </div>
	  <div class="mb-3 mt-3">
	    <label for="etitle" class="form-label">제목:</label>
	    <input type="text" class="form-control" id="etitle" 
	    	  placeholder="제목을 입력해주세요." name="etitle"  value="${dto.etitle}">
>>>>>>> cb4a38b2ec94f84ae8d5b1165d16b0247b5b119c
	  </div>
	  <div class="mb-3">
	    <label for="econtent" class="form-label">내용:</label>
	    <textarea class="form-control" id="econtent" 
<<<<<<< HEAD
	    		placeholder="내용을 입력해주세요" name="econtent">${dto.econtent}</textarea>
	  </div> 
	  <div class="mb-3">
	    <label for="eimg" class="form-label">이미지:</label>
	 	<input type="file" class="form-control" id="eimg" placeholder="파일을 입력해주세요" name="file"> 	 
 	 </div>
	  
	  <div class="mb-3  text-end">
	  	<button type="submit" class="btn btn-primary">글수정</button>
	  	<a href="javascript:history.go(-1)"  class="btn btn-danger">BACK</a>
=======
	    		placeholder="내용을 입력해주세요." name="econtent">${dto.econtent}</textarea>
	  </div> 
	  <div class="mb-3">
	    <label for="file" class="form-label">이미지:</label>
	 	<input type="file" class="form-control" id="eimg" placeholder="파일을 입력해주세요." name="file"> 	 
 	 </div>
	  
	  <div class="mb-3  text-end">
	  	<button type="submit" class="btn btn-lavender">글수정</button>
	  	<a href="javascript:history.go(-1)"  class="btn btn-navy">BACK</a>
>>>>>>> cb4a38b2ec94f84ae8d5b1165d16b0247b5b119c
	  </div>
	  
 </form>
  </div>
   
<%@include file="../inc/footer.jsp" %>

<!-- [ mbtiBoard - list.jsp ]  -->