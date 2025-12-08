<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file="../inc/header.jsp" %> 
<<<<<<< HEAD
=======
<script>
	$(function(){
		let result = '${success}';
		console.log(result);
		if(result == '글쓰기에 실패했습니다.'){
			alert(result); history.go(-1);
		}else if(result.length !=0){
			alert(result);
		}
	});
</script>


>>>>>>> cb4a38b2ec94f84ae8d5b1165d16b0247b5b119c
   <div class="container card  my-5 p-4">
      <h3 class="card-header"> 운동챌린지게시판 글쓰기</h3>
      <!-- POSTID, ETITLE , ECONTENT  -->
	  <form action="${pageContext.request.contextPath}/upload.execboard"  
	  		method="post"  encType="multipart/form-data" > 
	      <input type="hidden"   name="postId"  value="1"> 
<<<<<<< HEAD
		 
		  <!-- 외래키로 가지고 올 것들. / placeholder="내용을 입력해주세요" ← 사용해야 될 수도 있으니 임시보관 -->
<!-- 		  <div class="mb-3 mt-3">
		    <label for="postid" class="form-label">게시글아이디:</label>
<<<<<<< HEAD
=======
		  <!-- 외래키로 가지고 올 것들. / placeholder="내용을 입력해주세요" ← 사용해야 될 수도 있으니 임시보관 -->
<!-- 		  <div class="mb-3 mt-3">
		    <label for="postid" class="form-label">게시글아이디:</label>
>>>>>>> cb4a38b2ec94f84ae8d5b1165d16b0247b5b119c
		    <input type="text" class="form-control" id="postid" 
		    		placeholder="게시글아이디를 입력해주세요." name="postid">
		  </div> -->		
 		  
 		  <div class="mb-3 mt-3">
<<<<<<< HEAD
=======
		    <input type="text" class="form-control" id="postid"  name="postid">
		  </div>
		  <div class="mb-3 mt-3">
>>>>>>> 8229c01e4a164a1e7969c039b4ee1bd00d7c3dde
		    <label for="execid" class="form-label">운동아이디:</label>
		    <input type="text" class="form-control" id="execid"  name="execid">
		  </div>
		  
<!--  게시판 기능테스트때는 풀어서 사용하고, 합본때에네는 잠그고 사용하시면 됩니다.-->	  
		  <div class="mb-3">
		    <label for="userid" class="form-label">사용자아이디:</label>
<<<<<<< HEAD
		    <input type="text" class="form-control" id="userid" 
		    		placeholder="사용자아이디를 입력해주세요.(※회원가입후에 이용해주세요.)" name="userid">
		  </div>  
=======
		    <input type="text" class="form-control" id="userid" name="userid">
		  </div>
>>>>>>> 8229c01e4a164a1e7969c039b4ee1bd00d7c3dde
=======
		    <label for="execid" class="form-label">운동아이디:</label>
		    <input type="text" class="form-control" placeholder="운동아이디를 입력해주세요." 
		    		id="execid"  name="execid">
		  </div>
<!--  게시판 기능테스트때는 풀어서 사용하고, 합본때에네는 잠그고 사용하시면 됩니다 -->
		  <div class="mb-3">
		    <label for="userid" class="form-label">사용자아이디:</label>
		    <input type="text" class="form-control" placeholder="사용자아이디를 입력해주세요."
		    	   id="userid" name="userid">
		  </div> 
>>>>>>> cb4a38b2ec94f84ae8d5b1165d16b0247b5b119c
		  <!-- 외래키로 가지고 올 것들. -->

		  <div class="mb-3 mt-3">
		    <label for="etitle" class="form-label">제목:</label>
<<<<<<< HEAD
		    <input type="text" class="form-control" id="etitle" placeholder="제목을 입력해주세요" name="etitle">
		  </div>
		  <div class="mb-3">
		    <label for="econtent" class="form-label">내용:</label>
		    <textarea class="form-control" id="econtent" placeholder="내용을 입력해주세요" name="econtent"></textarea>
=======
		    <input type="text" class="form-control" id="etitle" placeholder="제목을 입력해주세요." name="etitle">
		  </div>
		  <div class="mb-3">
		    <label for="econtent" class="form-label">내용:</label>
		    <textarea class="form-control" id="econtent" placeholder="내용을 입력해주세요." name="econtent"></textarea>
>>>>>>> cb4a38b2ec94f84ae8d5b1165d16b0247b5b119c
		  </div> 
		   
		  <div class="mb-3">
		    <label for="eimg" class="form-label">이미지:</label>
<<<<<<< HEAD
		    <input type="file" class="form-control" id="eimg" placeholder="이미지를 선택해주세요" name="file">
=======
		    <input type="file" class="form-control" id="eimg" placeholder="이미지를 선택해주세요." name="file">
>>>>>>> cb4a38b2ec94f84ae8d5b1165d16b0247b5b119c
		  </div>
		  
		  <div class="mb-3  text-end">
		  	<button type="submit" class="btn btn-terra">글쓰기</button>  
		  	<a href="${pageContext.request.contextPath}/list.execboard"  class="btn btn-navy">목록보기</a>
		  </div>
		  
    	 <input  type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />	 
     </form> 
   </div>
   
<%@include file="../inc/footer.jsp" %>

<!-- [ ExecBoard - boardwrite.jsp ]  -->