<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file="../inc/header.jsp" %>
<<<<<<< HEAD
<%-- 	
	<script>
	$(function(){
		let result = '${success}';
		if(result.length== '비밀번호를 확인해주세요'){ alert( result ); history.go(-1); }
		else if(result.length > 0){ alert(result) } //아까 처음 갋이없을때 공백
	})
	</script> --%> 
  <div class="container card  my-5 p-4">
      <h3 class="card-header"> 운동정보 상세보기  <%-- ${dto} --%></h3>
	  <div> 
	      <input type="hidden"   name="execId"  value=""> 
		  
		  <div class="mb-3 mt-3">
		    <label for="execid" class="form-label">운동아이디:</label>
		    <input type="text" class="form-control" id="execid" 
		    		placeholder="내용을 입력해주세요" name="execid"  readonly  value="${dto.execid}">
		  </div>  
		  		  <div class="mb-3 mt-3">
		    <label for="exectype" class="form-label">운동유형:</label>
		    <input type="text" class="form-control" id="exectype" 
		    		placeholder="내용을 입력해주세요" name="exectype"  readonly  value="${dto.exectype}">
		  </div>  
		  <div class="mb-3">
		    <label for="description" class="form-label">설명:</label>
		    <textarea class="form-control" id="description" placeholder="내용을 입력해주세요"   
		    	readonly name="description">${dto.description}</textarea>
		  </div> 
		  <div class="mb-3 mt-3">
		    <label for="avgkcal30min" class="form-label">평균칼로리소모량(30분기준):</label>
		    <input type="text" class="form-control" id="avgkcal30min" 
		    		placeholder="내용을 입력해주세요" name="avgkcal30min"  readonly  value="${dto.avgkcal30min}">
		  </div>  
		   <div class="mb-3 mt-3">
		    <label for="exectargetmin" class="form-label">권장운동시간:</label>
		    <input type="text" class="form-control" id="exectargetmin" 
		    		placeholder="내용을 입력해주세요" name="exectargetmin"  readonly  value="${dto.exectargetmin}">
		  </div>  
		  <div class="mb-3 mt-3">
		    <label for="suitablefor" class="form-label">운동추천동물:</label>
		    <input type="text" class="form-control" id="suitablefor" 
		    		placeholder="내용을 입력해주세요" name="suitablefor"  readonly  value="${dto.suitablefor}">
		  </div>  
		  <div class="mb-3 mt-3">
		    <label for="intensitylevel" class="form-label">운동강도:</label>
		    <input type="text" class="form-control" id="intensitylevel" 
		    		placeholder="내용을 입력해주세요" name="intensitylevel"  readonly  value="${dto.intensitylevel}">
		  </div>  
		 
			<div class="mb-3">
				<a href="${pageContext.request.contextPath}/edit.execinfo?execid=${dto.execid}" class="btn btn-olive form-control">글수정</a>
			</div>
			<div class="mb-3">
				<a href="${pageContext.request.contextPath}/delete.execinfo?execid=${dto.execid}" class="btn btn-burgundy form-control">글삭제</a>
			</div>			
		  <div class="mb-3">
		  	<a href="${pageContext.request.contextPath}/list.execinfo?execid=${dto.execid}" class="btn btn-sage form-control">목록보기</a>
		  </div>
		  
	 </div>
   </div> 
=======
<script>
$(function(){
	let result = '${success}';
	if(result.length== '비밀번호를 확인해주세요'){ alert( result ); history.go(-1); }
	else if(result.length > 0){ alert(result) } //아까 처음 갋이없을때 공백
})
</script> 
<div class="container card  my-5 p-4">
     <h3 class="card-header"> 운동정보 상세보기  <%-- ${dto} --%></h3>
		 <div> 
		      <input type="hidden"   name="execId"  value=""> 
			  <div class="mb-3 mt-3">
			    <label for="execid" class="form-label">운동아이디:</label>
			    <input type="text" class="form-control" id="execid" 
			    		placeholder="내용을 입력해주세요." name="execid"  readonly  value="${dto.execid}">
			  </div>  
			  		  <div class="mb-3 mt-3">
			    <label for="exectype" class="form-label">운동유형:</label>
			    <input type="text" class="form-control" id="exectype" 
			    		placeholder="내용을 입력해주세요." name="exectype"  readonly  value="${dto.exectype}">
			  </div>  
			  <div class="mb-3">
			    <label for="description" class="form-label">설명:</label>
			    <textarea class="form-control" id="description" placeholder="내용을 입력해주세요"   
			    	readonly name="description">${dto.description}</textarea>
			  </div> 
			  <div class="mb-3 mt-3">
			    <label for="avgkcal30min" class="form-label">평균칼로리소모량(30분기준):</label>
			    <input type="text" class="form-control" id="avgkcal30min" 
			    		placeholder="내용을 입력해주세요." name="avgkcal30min"  readonly  value="${dto.avgkcal30min}">
			  </div>  
			   <div class="mb-3 mt-3">
			    <label for="exectargetmin" class="form-label">권장운동시간:</label>
			    <input type="text" class="form-control" id="exectargetmin" 
			    		placeholder="내용을 입력해주세요." name="exectargetmin"  readonly  value="${dto.exectargetmin}">
			  </div>  
			  <div class="mb-3 mt-3">
			    <label for="suitablefor" class="form-label">운동추천동물:</label>
			    <input type="text" class="form-control" id="suitablefor" 
			    		placeholder="내용을 입력해주세요" name="suitablefor"  readonly  value="${dto.suitablefor}">
			  </div>  
			  <div class="mb-3 mt-3">
			    <label for="intensitylevel" class="form-label">운동강도:</label>
			    <input type="text" class="form-control" id="intensitylevel" 
			    		placeholder="내용을 입력해주세요." name="intensitylevel"  readonly  value="${dto.intensitylevel}">
			  </div>  
			  
			  <sec:authorize access="isAuthenticated() and hasRole('ROLE_ADMIN')">
				  <div class="mb-3">
						<a href="${pageContext.request.contextPath}/edit.execinfo?execid=${dto.execid}" class="btn btn-olive form-control">글수정</a>
				  </div>
			  </sec:authorize>
			  
			  <sec:authorize access="isAuthenticated() and hasRole('ROLE_ADMIN')">
				  <div class="mb-3">
						<a href="${pageContext.request.contextPath}/delete.execinfo?execid=${dto.execid}" class="btn btn-burgundy form-control">글삭제</a>
				  </div>
			  </sec:authorize>
			  		
			  <sec:authorize access="isAuthenticated()">
				  <div class="mb-3">
				  	<a href="${pageContext.request.contextPath}/list.execinfo?execid=${dto.execid}" class="btn btn-sage form-control">목록보기</a>
				  </div>
			  </sec:authorize>
		 </div>
</div> 
>>>>>>> cb4a38b2ec94f84ae8d5b1165d16b0247b5b119c
<%@include file="../inc/footer.jsp" %>

<!-- [ mbtiBoard - list.jsp ]  -->