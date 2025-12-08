<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file="../inc/header.jsp" %>

<div class="container my-5">
  <div class="card shadow-sm">
    <div class="card-header bg-light">
      <h3 class="mb-0">회원 탈퇴</h3>
    </div>
    <div class="card-body">
      <p class="fs-5 text-danger">
        진심으로 탈퇴하시겠습니까?
      </p>
      <form action="${pageContext.request.contextPath}/security/deleteMember" method="post" class="d-flex justify-content-end gap-2">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <input type="hidden" name="userId" value="${dto.userId}">
        <input type="hidden" name="email" value="<sec:authentication property="principal.username" />">
        
        <button type="submit" class="btn btn-burgundy">유저탈퇴</button>
        <a href="javascript:history.go(-1)" class="btn btn-sage">취소</a>
      </form>
    </div>
  </div>
</div>

   
<%@include file="../inc/footer.jsp" %>

