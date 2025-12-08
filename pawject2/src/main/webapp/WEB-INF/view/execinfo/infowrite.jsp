<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file="../inc/header.jsp" %> 
<div class="container card  my-5 p-4">
 	<h3 class="card-header"> 운동정보 글쓰기</h3>
     <!-- POSTID, ETITLE , ECONTENT  -->
	  <form action="${pageContext.request.contextPath}/write.execinfo"  
	  		method="post"  encType="multipart/form-data" > 
	      <input type="hidden"   name="userId"  value="1"> 
		  <input  type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />	 
	<!-- 	  <div class="mb-3 mt-3">
			    <label for="execid" class="form-label">운동아이디:</label>
			    <input type="text" class="form-control" placeholder="운동아이디 입력해주세요." id="execid"  name="execid">
			  </div>-->		  
		  <div class="mb-3 mt-3">
		    <label for="exectype" class="form-label">운동유형:</label>
		    <input type="text" class="form-control" placeholder="운동유형을 입력해주세요." id="exectype"  name="exectype">
		  </div>
		  <div class="mb-3">
		    <label for="description" class="form-label">설명:</label>
		    <textarea class="form-control" id="description" placeholder="내용을 입력해주세요." name="description"></textarea>
		  </div>
		  	  <div class="mb-3">
		    <label for="avgkcal30min" class="form-label">평균칼로리소모량(30분기준):</label>
		    <input type="text" class="form-control"  placeholder="평균칼로리소모량을 입력해주세요." id="avgkcal30min" name="avgkcal30min">
		  </div>
		  <div class="mb-3 mt-3">
		    <label for="exectargetmin" class="form-label">권장운동시간:</label>
		    <input type="text" class="form-control" id="exectargetmin" placeholder="제목을 입력해주세요." name="exectargetmin">
		  </div>
		  <div class="mb-3 mt-3">
		    <label for="suitablefor" class="form-label">운동추천동물:</label>
		    <input type="text" class="form-control" placeholder="추천동물을 입력해주세요." id="suitablefor"  name="suitablefor">
		  </div>
		  <div class="mb-3">
		    <label for="intensitylevel" class="form-label">운동강도:</label>
		    <input type="text" class="form-control" id="intensitylevel" placeholder="운동강도를 선택해주세요." name="intensitylevel">
		  </div>
		  
		  <div class="mb-3  text-end">
		  	<button type="submit" class="btn btn-terra">글쓰기</button>  
		  	<a href="${pageContext.request.contextPath}/list.execinfo"  class="btn btn-olive">목록보기</a>
		  </div>
	    </form> 
</div>
   
<%@include file="../inc/footer.jsp" %>

<!-- [ ExecInfo - infowrite.jsp ]  -->