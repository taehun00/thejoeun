<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file="../inc/header.jsp" %>

   <div class="container card  my-5 p-4">
      <h3 class="card-header"> FOOD 정보작성</h3>
	  <form action="<%=request.getContextPath()%>/write.jys"  method="post"> 
	      <input type="hidden"   name="userid"  value=""> 
	      
	      
        <div class="mb-3">
		<label class="form-check-label"  for="mbti">동물: </label>  
		<select   name="pettypeid"  id="pettypeid"  class="form-control">
		<option value="1">고양이</option>
		<option value="2">강아지</option>
		</select>
			
			
		</div>
		
		<div class="mb-3 mt-3">
		    <label for="title" class="form-label">브랜드이름:</label>
		    <input type="text" class="form-control" 
		    		id="brandname" placeholder="내용을 입력해주세요" name="brandname">
		  </div>
		  <div class="mb-3 mt-3">
		    <label for="title" class="form-label">사료이름:</label>
		    <input type="text" class="form-control" 
		    		id="foodname" placeholder="내용을 입력해주세요" name="foodname">
		  </div>
		  <div class="mb-3 mt-3">
		    <label for="title" class="form-label">주재료:</label>
		    <input type="text" class="form-control" 
		    		id="mainingredient" placeholder="내용을 입력해주세요" name="mainingredient">
		  </div>
		  <div class="mb-3 mt-3">
		    <label for="title" class="form-label">부재료:</label>
		    <input type="text" class="form-control" 
		    		id="subingredient" placeholder="내용을 입력해주세요" name="subingredient">
		  </div>
		  <div class="mb-3 mt-3">
		    <label for="title" class="form-label">제조국:</label>
		    <input type="text" class="form-control" 
		    		id="country" placeholder="내용을 입력해주세요" name="country">
		  </div>
		  <div class="mb-3">
		    <label for="content" class="form-label">설명:</label>
		    <textarea class="form-control" 
		    id="description" placeholder="내용을 입력해주세요" name="description"></textarea>
		  </div> 

		  <div class="mb-3  text-end">
		  
		  	<button type="submit" class="btn btn-primary">게시글작성완료</button>
		  	<a href="<%=request.getContextPath()%>/list.jys" class="btn btn-primary">목록보기</a>
		  </div>
	 </form>
   </div>
   
<%@include file="../inc/footer.jsp" %>
