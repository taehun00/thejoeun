<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../inc/header.jsp" %>

<<<<<<< HEAD
<div class="container my-5">
  <div class="card shadow-sm">
    <div class="card-header bg-light">
      <h3 class="mb-0">USER 상세 정보</h3>
    </div>
    <div class="card-body d-flex align-items-start">
      <!-- 프로필 이미지 -->
      <div class="me-4">
        <img src="${pageContext.request.contextPath}/upload/${user.ufile}" 
             alt="프로필" class="rounded-circle border shadow-sm" style="width:120px; height:120px;">
      </div>
      <!-- 유저 정보 -->
      <ul class="list-group list-group-flush flex-fill">
        <li class="list-group-item"><strong>UserNo:</strong> ${user.userId}</li>
        <li class="list-group-item"><strong>Email:</strong> ${user.email}</li>
        <li class="list-group-item"><strong>Mobile:</strong> ${user.mobile}</li>
        <li class="list-group-item"><strong>Date:</strong> ${user.createdAt}</li>
      </ul>
    </div>

    <!-- 닉네임 수정 폼 -->
    <div class="card-body border-top mt-3">
      <form action="${pageContext.request.contextPath}/security/updateAdmin" method="post" class="row g-3">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <input type="hidden" name="userId" value="${user.userId}" />
        <div class="col-md-6">
          <label for="nickname" class="form-label">Nickname:</label>
          <input type="text" class="form-control" id="nickname" name="nickname" value="${user.nickname}" />
        </div>
        <div class="col-md-6 d-flex align-items-end">
          <button type="submit" class="btn btn-mustard">닉네임 수정</button>
        </div>
      </form>
    </div>

    <div class="card-footer text-end">
      <a href="${pageContext.request.contextPath}/security/listPage" class="btn btn-sage">목록으로</a>
    </div>
  </div>
=======
<div class="container card my-5 p-4">
    <h3 class="card-header">USER 상세 정보</h3>
    <div class="card-body">
        <img src="${pageContext.request.contextPath}/upload/${user.ufile}" alt="프로필" style="width:120px" />

        <p><strong>UserNo:</strong> ${user.userId}</p>
        <p><strong>Email:</strong> ${user.email}</p>
        <p><strong>Mobile:</strong> ${user.mobile}</p>
        <p><strong>Date:</strong> ${user.createdAt}</p>
    </div>
    
    <!-- 닉네임 수정 폼 -->
    <div class="mt-3">
        <form action="${pageContext.request.contextPath}/security/updateAdmin" method="post">
        	<input  type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
            <input type="hidden" name="userId" value="${user.userId}" />
            <div class="mb-3">
                <label for="nickname" class="form-label">Nickname:</label>
                <input type="text" class="form-control" id="nickname" name="nickname" value="${user.nickname}" />
            </div>
            <button type="submit" class="btn btn-primary">닉네임 수정</button>
        </form>
    </div>
    
    <div class="mt-3">
        <a href="${pageContext.request.contextPath}/security/listPage"  class="btn btn-danger">목록으로</a>
    </div>
>>>>>>> cb4a38b2ec94f84ae8d5b1165d16b0247b5b119c
</div>

<%@ include file="../inc/footer.jsp" %>
