<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../inc/header.jsp" %>

<div class="container mt-5">
    <h3> 회원가입 </h3>
    <form action="${pageContext.request.contextPath}/join.u" method="post">
        <div class="mb-3 mt-3">
            <label for="email" class="form-label">Email:</label>
            <input type="email" class="form-control" id="email" required placeholder="Enter email" name="email">
        </div>
        <div class="mb-3">
            <label for="nickname" class="form-label">Nickname:</label>
            <input type="text" class="form-control" id="nickname" required placeholder="Enter nickname" name="nickname">
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Password:</label>
            <input type="password" class="form-control" id="password" required placeholder="Enter password" name="password">
        </div>

        <button type="submit" class="btn btn-primary">회원가입</button>
        <a href="${pageContext.request.contextPath}/loginView.u" class="btn btn-outline-secondary ms-2">로그인으로 돌아가기</a>
    </form>
</div>

<%@ include file="../inc/footer.jsp" %>