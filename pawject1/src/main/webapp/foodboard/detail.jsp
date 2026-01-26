<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file="../inc/header.jsp" %>

   <div class="container card  my-5 p-4">
      <h3 class="card-header"> FOOD 상세보기</h3>
	  <div class="mb-3">
  <label class="form-check-label" for="mbti">동물: </label>
  <select name="pettypeid" id="pettypeid" class="form-control" disabled>
    <option value="1" ${dto.pettypename == '고양이' ? 'selected' : ''}>고양이</option>
	<option value="2" ${dto.pettypename == '강아지' ? 'selected' : ''}>강아지</option>
  </select>
</div>

<div class="mb-3 mt-3">
  <label for="title" class="form-label">브랜드이름:</label>
  <input type="text" class="form-control" id="brandname" name="brandname" readonly value="${dto.brandname}">
</div>

<div class="mb-3 mt-3">
  <label for="title" class="form-label">사료이름:</label>
  <input type="text" class="form-control" id="foodname" name="foodname" readonly value="${dto.foodname}">
</div>

<div class="mb-3 mt-3">
  <label for="title" class="form-label">주재료:</label>
  <input type="text" class="form-control" id="mainingredient" name="mainingredient" readonly value="${dto.mainingredient}">
</div>

<div class="mb-3 mt-3">
  <label for="title" class="form-label">부재료:</label>
  <input type="text" class="form-control" id="subingredient" name="subingredient" readonly value="${dto.subingredient}" >
</div>

<div class="mb-3 mt-3">
  <label for="title" class="form-label">제조국:</label>
  <input type="text" class="form-control" id="country" name="country" readonly value="${dto.country}">
</div>

<div class="mb-3">
  <label for="content" class="form-label">설명:</label>
  <textarea class="form-control" id="description" name="description" readonly >${dto.description}</textarea>
</div>
 <p>foodid: ${dto.foodid}</p> 
		  
	 	  <c:if test="${not empty sessionScope.email}">	<!-- 이메일 체크 --> <!-- 로그인유저 인증 --> 
			  <div class="mb-3  text-end">
			  	<a href="<%=request.getContextPath()%>/editView.jys?id=${dto.foodid}"  class="btn btn-success  from-control">글수정</a>
			  </div>
			  <div class="mb-3  text-end">
			  	<a href="<%=request.getContextPath()%>/deleteView.jys?id=${dto.foodid}"  class="btn btn-danger from-control">글삭제</a>
			  </div>
		  </c:if> 
		  
		  <div class="mb-3  text-end">
		  	<a href="<%=request.getContextPath()%>/list.jys" class="btn btn-primary from-control">목록보기</a>
		  </div>
	 </div>

   
<%@include file="../inc/footer.jsp" %>
