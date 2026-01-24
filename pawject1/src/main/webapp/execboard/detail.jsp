<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file="../inc/header.jsp" %> 
   <div class="container card  my-5 p-4">
      <h3 class="card-header"> 글상세보기</h3>
	  <div> 
	      <input type="hidden"   name="execid"  value=""> 
		  <div class="mb-3 mt-3">
		    <label for="hit" class="form-label">운동코드</label>
		    <input type="text" class="form-control" id="hit" name="hit"  readonly  
		    	   value="${dto.execid}"> <%-- ${}로 반드시 입력할것! --%>
		  </div> 
		  <div class="mb-3 mt-3">
		    <label for="execetype" class="form-label">운동유형:</label>
		    <input type="text" class="form-control" id="exectype" 
		    		placeholder="내용을 입력해주세요" name="exectype"  readonly 
		    		value="${dto.exectype}">
		  </div>  
		  <div class="mb-3"> 
		    <label for="description" class="form-label">설명:</label>
		    <textarea class="form-control" id="description" placeholder="내용을 입력해주세요"   
		    	readonly name="description">${dto.description}</textarea> <%-- textarea - 공백없이 작성!!(글꼴 인식을 못할수도 있음.) --%>
		  </div> 
  		  <div class="mb-3 mt-3">
		    <label for="avgkcal30min" class="form-label">30분당 평균칼로리소모량</label>
		    <input type="text" class="form-control" id="avgkcal30min" name="avgkcal30min"  readonly  
		    	   value="${dto.avgkcal30min}"> <%-- ${}로 반드시 입력할것! --%>
		  </div> 
		  <div class="mb-3 mt-3">
		    <label for="exectargetmin" class="form-label">권장운동시간:</label>
		    <input type="text" class="form-control" id="exectargetmin" 
		    		placeholder="내용을 입력해주세요" name="exectargetmin"  readonly 
		    		value="${dto.exectargetmin}">
		  </div> 
		  		  <div class="mb-3 mt-3">
		    <label for="suitablefor" class="form-label">추천동물</label>
		    <input type="text" class="form-control" id="suitablefor" name="suitablefor"  readonly  
		    	   value="${dto.suitablefor}"> <%-- ${}로 반드시 입력할것! --%>
		  </div> 
		  <div class="mb-3 mt-3">
		    <label for="intensitylevel" class="form-label">운동강도:</label>
		    <input type="text" class="form-control" id="intensitylevel" 
		    		placeholder="내용을 입력해주세요" name="intensitylevel"  readonly 
		    		value="${dto.intensitylevel}">
		  </div> 
		  
			  <div class="mb-3">
			  	<a href="<%=request.getContextPath()%>/updateForm.hsh?id=${dto.execid}" class="btn btn-warning form-control">글수정</a>
			  </div>
			  <div class="mb-3">
			  	<a href="<%=request.getContextPath()%>/delete.hsh?id=${dto.execid}" class="btn btn-danger form-control">글삭제</a>
			  </div>
		  <c:if   test="${not empty execid}">	  	  
		  </c:if>
		  
		  <div class="mb-3">
		  	<a href="<%=request.getContextPath()%>/execAll.hsh" class="btn btn-secondary form-control">목록보기</a>
		  </div>
	 </div>
   </div> 
<%@include file="../inc/footer.jsp" %>

<!-- [ mbtiBoard - detail.jsp ]  -->