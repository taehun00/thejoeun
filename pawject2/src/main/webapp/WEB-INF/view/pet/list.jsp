<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../inc/header.jsp" %>

<div class="container card my-5 p-3">
    <h3 class="card-header">PET DETAIL</h3>

    <table class="table table-striped table-bordered table-hover align-middle">
        <colgroup>
            <col style="width:20%">
            <col style="width:80%">
        </colgroup>
        <tbody class="table-info">
            <tr>
                <th scope="row">사진</th>
                <td>
                    <img src="${pageContext.request.contextPath}/upload/${pet.pfile}" 
                         alt="펫 사진" style="width:200px; height:auto;" />
                </td>
            </tr>
            <tr>
                <th scope="row">이름</th>
                <td>${pet.petName}</td>
            </tr>
            <tr>
                <th scope="row">품종</th>
                <td>${pet.petBreed}</td>
            </tr>
            <tr>
                <th scope="row">생일</th>
                <td>${pet.birthDate}</td>
            </tr>
            <tr>
                <th scope="row">타입</th>
                <td>
                    <c:choose>
                        <c:when test="${pet.petTypeId == 1}">고양이</c:when>
                        <c:when test="${pet.petTypeId == 2}">강아지</c:when>
                        <c:otherwise>기타</c:otherwise>
                    </c:choose>
                </td>
            </tr>
            <tr>
                <th scope="row">등록일</th>
                <td>${pet.createdAt}</td>
            </tr>
        </tbody>
    </table>

    <div class="text-end">
        <a href="${pageContext.request.contextPath}/user/mypage" class="btn btn-secondary">목록으로</a>
        <a href="${pageContext.request.contextPath}/pet/update/user?petId=${pet.petId}" class="btn btn-primary">수정하기</a>
        <a href="${pageContext.request.contextPath}/pet/delete/${pet.petId}" class="btn btn-danger">삭제하기</a>
    </div>
</div>

<%@ include file="../inc/footer.jsp" %>
