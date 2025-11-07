<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../inc/header.jsp" %>

<div class="container card my-5 p-4">
    <h3 class="card-header">👤 사용자 정보</h3>

    <table class="table table-bordered table-hover mt-3">
        <caption>회원 정보</caption>
        <thead>
            <tr>
                <th scope="col">이메일</th>
                <th scope="col">닉네임</th>
                <th scope="col">가입일</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>${dto.email}</td>
                <td>${dto.nickname}</td>
                <td>${dto.createdat}</td>
				                
            </tr>
        </tbody>
    </table>

    <div class="mt-4 text-end">
    	<a href="${pageContext.request.contextPath}/petinfo.pet?userid=${dto.userid}" class="btn btn-success">펫정보</a>
        <a href="<%= request.getContextPath() %>/logout.u" class="btn btn-outline-danger">로그아웃</a>
    </div>
</div>

<%@ include file="../inc/footer.jsp" %>
