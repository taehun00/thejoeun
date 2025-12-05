<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

  <div class="container card  my-5 p-4">
     <h3 class="card-header"> 운동정보 글수정  </h3>
  <form action="${pageContext.request.contextPath}/edit.execinfo" 
  		method="post" encType="multipart/form-data" > 
     <!--  <input type="hidden"   name="app_user_id"  value="">  -->
     <input type="hidden"   name="execId"  value="${dto.execid}">
     <input  type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />	
	  <div class="mb-3 mt-3">
	    <label for="execid" class="form-label">운동아이디:</label>
	    <input type="text" class="form-control" id="execid" 
	    	  placeholder="운동아이디 입력해주세요." name="execid" readonly value="${dto.execid}">
	  </div>
	  <div class="mb-3 mt-3">
	    <label for="exectype" class="form-label">운동유형:</label>
	    <input type="text" class="form-control" id="exectype" 
	    	  placeholder="운동유형을 입력해주세요." name="exectype"  value="${dto.exectype}">
	  </div>
	  <div class="mb-3">
	    <label for="description" class="form-label">설명:</label>
	    <textarea class="form-control" id="description" 
	    		placeholder="내용을 입력해주세요." name="description">${dto.description}</textarea>
	  </div>
	  <div class="mb-3 mt-3">
	    <label for="avgkcal30min" class="form-label">평균칼로리소모량(30분기준):</label>
	    <input type="text" class="form-control" id="avgkcal30min" 
	    	  placeholder="평균칼로리소모량을 입력해주세요." name="avgkcal30min"  value="${dto.avgkcal30min}">
	  </div>
	  <div class="mb-3 mt-3">
	    <label for="exectargetmin" class="form-label">권장운동시간:</label>
	    <input type="text" class="form-control" id="exectargetmin" 
	    	  placeholder="권장운동시간을 입력해주세요." name="exectargetmin"  value="${dto.exectargetmin}">
	  </div>
	  <div class="mb-3 mt-3">
	    <label for="suitablefor" class="form-label">운동추천동물:</label>
	    <input type="text" class="form-control" id="suitablefor" 
	    	  placeholder="추천동물을 입력해주세요." name="suitablefor"  value="${dto.suitablefor}">
	  </div>
	  <div class="mb-3 mt-3">
	    <label for="intensitylevel" class="form-label">운동강도:</label>
	    <input type="text" class="form-control" id="intensitylevel" 
	    	  placeholder="운동강도를 입력해주세요." name="intensitylevel"  value="${dto.intensitylevel}">
	  </div>
	   
	  <div class="mb-3  text-end">
	  	<button type="submit" class="btn btn-mint">글수정</button>
	  	<a href="javascript:history.go(-1)"  class="btn btn-lavender">BACK</a>
	  </div>
	  
 </form>
  </div>
   
<%@include file="../inc/footer.jsp" %>

<!-- [ mbtiBoard - list.jsp ]  -->