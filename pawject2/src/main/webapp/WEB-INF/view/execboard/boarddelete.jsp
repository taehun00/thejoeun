<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<<<<<<< HEAD
    
<%@include file="../inc/header.jsp" %>
=======
<%@include file="../inc/header.jsp" %>
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

   <div class="container card  my-5 p-4 ">
      <h3 class="card-header"> 운동챌린지게시판 글삭제</h3>
	  <%-- <form action="${pageContext.request.contextPath}/delete.do?id=${param.id}"     method="post">  --%> 
	  <form action="${pageContext.request.contextPath}/delete.execboard"  method="post">  
	      <input type="hidden"   name="postid"  value="${param.postid}"> 
		  <input  type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />			  
		  
<!--  		  <div class="my-3">
		    <label for="postid" class="form-label">게시글아이디:</label>
<<<<<<< HEAD
		    <input type="text" class="form-control"
		    	id="postid"  placeholder="게시글아이디를 입력해주세요." name="postid">
		  </div> -->
          <div class="my-3">
<<<<<<< HEAD
=======
		    <input type="text" class="form-control" 
		    	id="postid"  placeholder="게시글아이디를 입력해주세요" name="postid">
		  </div> 
		  <div class="my-3">
>>>>>>> 8229c01e4a164a1e7969c039b4ee1bd00d7c3dde
		    <label for="execid" class="form-label">운동아이디:</label>
		    <input type="text" class="form-control" 
		    	id="execid"  placeholder="운동아이디를 입력해주세요" name="execid">
=======
		    <label for="execid" class="form-label">운동아이디:</label>
		    <input type="text" class="form-control" 
		    	id="execid"  placeholder="운동아이디를 입력해주세요." name="execid">
>>>>>>> cb4a38b2ec94f84ae8d5b1165d16b0247b5b119c
		  </div> 
		  <div class="my-3">
		    <label for="userid" class="form-label">사용자아이디:</label>
		    <input type="text" class="form-control" 
<<<<<<< HEAD
		    	id="userid"  placeholder="사용자아이디를 입력해주세요" name="userid">
=======
		    	id="userid"  placeholder="사용자아이디를 입력해주세요." name="userid">
>>>>>>> cb4a38b2ec94f84ae8d5b1165d16b0247b5b119c
		  </div> 
		  
		  <div class="my-3  text-end">
		  	<button type="submit" class="btn btn-roseRed">글삭제</button>
		  	<a href="javascript:history.go(-1)"  class="btn btn-mint">BACK</a>
		  </div>
	 </form>
   </div>
   
<%@include file="../inc/footer.jsp" %>

<!-- [ mbtiBoard - list.jsp ]  -->