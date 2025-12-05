<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="../inc/header.jsp" %>

	<div class="container mt-5">
	    <h3>펫 삭제 확인</h3>
	    <p>정말로 "${pet.petName}" 펫 정보를 삭제하시겠습니까?</p>
	
	    <form action="${pageContext.request.contextPath}/pet/delete" method="post">
	        <input type="hidden" name="petId" value="${pet.petId}">
	        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	        <button type="submit" class="btn btn-danger">삭제</button>
	        <a href="${pageContext.request.contextPath}/pet/detail?petId=${pet.petId}" class="btn btn-secondary">취소</a>
	    </form>
	</div>

<%@include file="../inc/footer.jsp" %>