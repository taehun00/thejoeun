<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../inc/header.jsp" %>

<<<<<<< HEAD
<div class="container my-5">
  <div class="card shadow-sm">
    <div class="card-header bg-light">
      <h3 class="mb-0">PET DETAIL</h3>
    </div>
    <div class="card-body d-flex align-items-start">
      <!-- 펫 이미지 -->
      <div class="me-4">
        <img src="${pageContext.request.contextPath}/upload/${pet.pfile}" 
             alt="펫 사진" class="rounded shadow-sm" style="width:200px; height:auto;">
      </div>
      <!-- 펫 정보 -->
      <ul class="list-group list-group-flush flex-fill">
        <li class="list-group-item"><strong>이름:</strong> ${pet.petName}</li>
        <li class="list-group-item"><strong>품종:</strong> ${pet.petBreed}</li>
        <li class="list-group-item"><strong>생일:</strong> ${pet.birthDate}</li>
        <li class="list-group-item"><strong>타입:</strong>
          <c:choose>
            <c:when test="${pet.petTypeId == 1}">고양이</c:when>
            <c:when test="${pet.petTypeId == 2}">강아지</c:when>
            <c:otherwise>기타</c:otherwise>
          </c:choose>
        </li>
        <li class="list-group-item"><strong>등록일:</strong> ${pet.createdAt}</li>
      </ul>
    </div>
    <div class="card-footer text-end">
      <a href="${pageContext.request.contextPath}/security/mypage" class="btn btn-sage">목록으로</a>
      <a href="${pageContext.request.contextPath}/pet/update/user?petId=${pet.petId}" class="btn btn-mustard">수정하기</a>
      <a href="${pageContext.request.contextPath}/pet/delete?petId=${pet.petId}" class="btn btn-burgundy">삭제하기</a>
    </div>
  </div>
</div>
=======
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
        <a href="${pageContext.request.contextPath}/security/mypage" class="btn btn-secondary">목록으로</a>
        <a href="${pageContext.request.contextPath}/pet/update/user?petId=${pet.petId}" class="btn btn-primary">수정하기</a>
        <a href="${pageContext.request.contextPath}/pet/delete?petId=${pet.petId}" class="btn btn-danger">삭제하기</a>
    </div>
</div>

>>>>>>> cb4a38b2ec94f84ae8d5b1165d16b0247b5b119c
<%@ include file="../inc/footer.jsp" %>