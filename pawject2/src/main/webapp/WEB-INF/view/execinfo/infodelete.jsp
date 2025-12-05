<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../inc/header.jsp" %>
<!-- <script>
	$(function(){
		let result = '${success}';
		console.log(result);
		if(resut == '운동아이디를 확인해주세요.'){
			alert(result); history.go(-1);
		}else if(result.length !=0){
			alert(result);
		}
	});
</script> --> 

   <div class="container card  my-5 p-4 ">
      <h3 class="card-header"> 운동정보 글삭제</h3>
	  <%-- <form action="${pageContext.request.contextPath}/delete.do?id=${param.id}"     method="post">  --%> 
	  <form action="${pageContext.request.contextPath}/delete.execinfo"  method="post">  
	      <input type="hidden"   name="execid"  value="${param.execid}"> 
		  <input  type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />			  
		  
		  <div class="my-3">
		    <label for="execid" class="form-label">운동아이디:</label>
		    <input type="text" class="form-control" 
		    	id="execid"  placeholder="운동아이디를 입력해주세요." name="execid">
		  </div> 
		  <div class="my-3">
		    <label for="exectype" class="form-label">운동유형:</label>
		    <input type="text" class="form-control" 
		    	id="exectype"  placeholder="운동유형을 입력해주세요." name="exectype">
		  </div> 
		  
		  
		  <div class="my-3  text-end">
		  	<button type="submit" class="btn btn-roseRed">글삭제</button>
		  	<a href="javascript:history.go(-1)"  class="btn btn-navy">BACK</a>
		  </div>
	 </form>
   </div>
   
<%@include file="../inc/footer.jsp" %>

<!-- [ mbtiBoard - list.jsp ]  -->