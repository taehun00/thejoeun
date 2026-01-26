<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="../inc/header.jsp"%>

<div class="container card  my-5">
	<h3 class="card-header"> 글수정</h3>
	  <form action="<%=request.getContextPath()%>/update.hsh?id=${dto.execid}"  method="post"> 
		  <div class="mb-3 mt-3">
		    <label for="exectype" class="form-label">운동유형</label>
		    <input type="text" class="form-control" id="exectype" name="exectype" value="${dto.exectype}" > <%-- ${}로 반드시 입력할것! --%>
		  </div>  
		  <div class="mb-3"> 
		    <label for="description" class="form-label">설명:</label>
		    <textarea class="form-control" id="description" placeholder="내용을 입력해주세요"   
		    	     name="description">${dto.description}</textarea> <%-- textarea - 공백없이 작성!!(글꼴 인식을 못할수도 있음.) --%>
		  </div>  
		  <div class="mb-3 mt-3">
		    <label for="avgkcal30min" class="form-label">30분당 평균칼로리소모량:</label>
		    <input type="text" class="form-control" id="avgkcal30min" 
		    		placeholder="내용을 입력해주세요" name="avgkcal30min" value="${dto.avgkcal30min}">
		  </div> 
		  <div class="mb-3 mt-3">
		    <label for="exectargetmin" class="form-label">권장운동시간:</label>
		    <input type="text" class="form-control" id="exectargetmin" 
		    		placeholder="내용을 입력해주세요" name="exectargetmin" value="${dto.exectargetmin}">
		  </div> 
		  		  <div class="mb-3 mt-3">
		    <label for="suitablefor" class="form-label">추천동물:</label>
		    <input type="text" class="form-control" id="suitablefor" 
		    		placeholder="내용을 입력해주세요" name="suitablefor" value="${dto.suitablefor}">
		  </div> 
		  		  <div class="mb-3 mt-3">
		    <label for="intensitylevel" class="form-label">운동강도:</label>
		    <input type="text" class="form-control" id="intensitylevel" 
		    		placeholder="내용을 입력해주세요" name="intensitylevel" value="${dto.intensitylevel}">
		  </div> 
		  <div class="mb-3  text-end">
		  	<button type="submit" class="btn btn-warning">글쓰기</button>
		  	<a href="<%=request.getContextPath()%>/execAll.hsh" class="btn btn-secondary">목록보기</a>
		  </div>
	 </form>
</div>

<%@include file="../inc/footer.jsp"%>

<!-- MbtiBoard - edit,jsp -->




