<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../inc/header.jsp" %>

  <div class="container card  my-5 p-4">
     <h3 class="card-header"> 운동정보 글수정  </h3>
<<<<<<< HEAD
  <form action="${pageContext.request.contextPath}/updateEdit.execboard" 
  		method="post" encType="multipart/form-data" > 
     <!--  <input type="hidden"   name="app_user_id"  value="">  -->
     <input type="hidden"   name="execId"  value="${dto.execid}">
=======
  <form action="${pageContext.request.contextPath}/edit.execinfo" 
  		method="post" encType="multipart/form-data" > 
     <!--  <input type="hidden"   name="app_user_id"  value="">  -->
     <input type="hidden"   name="execid"  value="${dto.execid}">
>>>>>>> cb4a38b2ec94f84ae8d5b1165d16b0247b5b119c
     <input  type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />	
   	  <div class="mb-3 mt-3">
	    <label for="execid" class="form-label">운동아이디:</label>
	    <input type="text" class="form-control" id="execid" 
<<<<<<< HEAD

=======
>>>>>>> cb4a38b2ec94f84ae8d5b1165d16b0247b5b119c
	    	  placeholder="운동아이디 입력해주세요." name="execid" readonly value="${dto.execid}">
	  </div> 

	  <div class="mb-3 mt-3">
	    <label for="exectype" class="form-label">운동유형:</label>
	    <input type="text" class="form-control" id="exectype" 
<<<<<<< HEAD
	    	  placeholder="운동유형을 입력해주세요" name="exectype"  value="${dto.exectype}">
=======
	    	  placeholder="운동유형을 입력해주세요." name="exectype"  value="${dto.exectype}">
>>>>>>> cb4a38b2ec94f84ae8d5b1165d16b0247b5b119c
	  </div>
	  <div class="mb-3">
	    <label for="description" class="form-label">설명:</label>
	    <textarea class="form-control" id="description" 
<<<<<<< HEAD
	    		placeholder="내용을 입력해주세요" name="description">${dto.description}</textarea>
=======
	    		placeholder="내용을 입력해주세요." name="description">${dto.description}</textarea>
>>>>>>> cb4a38b2ec94f84ae8d5b1165d16b0247b5b119c
	  </div>
	  <div class="mb-3 mt-3">
	    <label for="avgkcal30min" class="form-label">평균칼로리소모량(30분기준):</label>
	    <input type="text" class="form-control" id="avgkcal30min" 
<<<<<<< HEAD
	    	  placeholder="평균칼로리소모량을 입력해주세요" name="avgkcal30min"  value="${dto.avgkcal30min}">
=======
	    	  placeholder="평균칼로리소모량을 입력해주세요." name="avgkcal30min"  value="${dto.avgkcal30min}">
>>>>>>> cb4a38b2ec94f84ae8d5b1165d16b0247b5b119c
	  </div>
	  <div class="mb-3 mt-3">
	    <label for="exectargetmin" class="form-label">권장운동시간:</label>
	    <input type="text" class="form-control" id="exectargetmin" 
<<<<<<< HEAD
	    	  placeholder="권장운동시간을 입력해주세요" name="exectargetmin"  value="${dto.exectargetmin}">
=======
	    	  placeholder="권장운동시간을 입력해주세요." name="exectargetmin"  value="${dto.exectargetmin}">
>>>>>>> cb4a38b2ec94f84ae8d5b1165d16b0247b5b119c
	  </div>
	  <div class="mb-3 mt-3">
	    <label for="suitablefor" class="form-label">운동추천동물:</label>
	    <input type="text" class="form-control" id="suitablefor" 
<<<<<<< HEAD
	    	  placeholder="추천동물을 입력해주세요" name="suitablefor"  value="${dto.suitablefor}">
=======
	    	  placeholder="추천동물을 입력해주세요." name="suitablefor"  value="${dto.suitablefor}">
>>>>>>> cb4a38b2ec94f84ae8d5b1165d16b0247b5b119c
	  </div>
	  <div class="mb-3 mt-3">
	    <label for="intensitylevel" class="form-label">운동강도:</label>
	    <input type="text" class="form-control" id="intensitylevel" 
<<<<<<< HEAD
	    	  placeholder="운동강도를 입력해주세요" name="intensitylevel"  value="${dto.intensitylevel}">
=======
	    	  placeholder="운동강도를 입력해주세요." name="intensitylevel"  value="${dto.intensitylevel}">
>>>>>>> cb4a38b2ec94f84ae8d5b1165d16b0247b5b119c
	  </div>
	   
	  <div class="mb-3  text-end">
	  	<button type="submit" class="btn btn-mint">글수정</button>
	  	<a href="javascript:history.go(-1)"  class="btn btn-lavender">BACK</a>
	  </div>
	  
 </form>
  </div>
   
<%@include file="../inc/footer.jsp" %>

<!-- [ mbtiBoard - list.jsp ]  -->