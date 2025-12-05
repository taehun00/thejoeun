<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../inc/header.jsp" %>

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
        <a href="${pageContext.request.contextPath}/security/list"  class="btn btn-danger">목록으로</a>
    </div>
</div>

<%@ include file="../inc/footer.jsp" %>
