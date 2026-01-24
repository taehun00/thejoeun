<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ page isELIgnored="false" %>
  
  <%@include file="../inc/header.jsp"%>


	<div class="container card my-5 p-4">
	<h3 class="card-header"> Food 정보수정</h3>
	  
   <form action="<%=request.getContextPath()%>/edit.jys?id=${dto.foodid}" method="post">
	<input type="hidden" name="id" value="${dto.foodid}">
	
		
		<div class="mb-3">
  <label class="form-check-label" for="mbti">동물: </label>
  <select name="pettypeid" id="pettypeid" class="form-control" >
    <option value="1" ${dto.pettypename == '고양이' ? 'selected' : ''}>고양이</option>
	<option value="2" ${dto.pettypename == '강아지' ? 'selected' : ''}>강아지</option>
  </select>
</div>

<div class="mb-3 mt-3">
  <label for="title" class="form-label">브랜드이름:</label>
  <input type="text" class="form-control" id="brandname" name="brandname"  value="${dto.brandname}">
</div>

<div class="mb-3 mt-3">
  <label for="title" class="form-label">사료이름:</label>
  <input type="text" class="form-control" id="foodname" name="foodname" value="${dto.foodname}">
</div>

<div class="mb-3 mt-3">
  <label for="title" class="form-label">주재료:</label>
  <input type="text" class="form-control" id="mainingredient" name="mainingredient" value="${dto.mainingredient}">
</div>

<div class="mb-3 mt-3">
  <label for="title" class="form-label">부재료:</label>
  <input type="text" class="form-control" id="subingredient" name="subingredient" value="${dto.subingredient}" >
</div>

<div class="mb-3 mt-3">
  <label for="title" class="form-label">제조국:</label>
  <input type="text" class="form-control" id="country" name="country"  value="${dto.country}">
</div>

<div class="mb-3">
  <label for="content" class="form-label">설명:</label>
  <textarea class="form-control" id="description" name="description" >${dto.description}</textarea>
</div>
  <button type="submit" class="btn btn-primary">정보수정완료</button>
  <a href="javascript:history.go(-1)" class="btn btn-danger">BACK</a>
</form>
</div>

<%@include file="../inc/footer.jsp" %>
   