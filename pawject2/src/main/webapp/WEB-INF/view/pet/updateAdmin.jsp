<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../inc/header.jsp" %>

<div class="container my-5">
  <div class="card shadow-sm">
    <div class="card-header bg-light">
      <h3 class="mb-0">펫 이름 수정 (관리자)</h3>
    </div>
    <div class="card-body d-flex align-items-start">
      <!-- 펫 이미지 -->
      <div class="me-4">
        <img src="${pageContext.request.contextPath}/upload/${pet.pfile}" 
             alt="펫 이미지" class="rounded shadow-sm" style="width:120px; height:120px;">
      </div>
      <!-- 펫 기본 정보 -->
      <ul class="list-group list-group-flush flex-fill">
        <li class="list-group-item"><strong>PetID:</strong> ${pet.petId}</li>
        <li class="list-group-item"><strong>Email:</strong> ${pet.email}</li>
        <li class="list-group-item"><strong>Breed:</strong> ${pet.petBreed}</li>
        <li class="list-group-item"><strong>BirthDate:</strong> ${pet.birthDate}</li>
        <li class="list-group-item"><strong>Type:</strong>
          <c:choose>
            <c:when test="${pet.petTypeId == 1}">고양이</c:when>
            <c:when test="${pet.petTypeId == 2}">강아지</c:when>
            <c:otherwise>기타</c:otherwise>
          </c:choose>
        </li>
        <li class="list-group-item"><strong>등록일:</strong> ${pet.createdAt}</li>
      </ul>
    </div>

    <!-- 펫 이름 수정 폼 -->
    <div class="card-body border-top mt-3">
      <form id="updatePetForm" class="row g-3">
        <input type="hidden" name="petId" value="${pet.petId}" />
        <meta name="_csrf" content="${_csrf.token}"/>
        <meta name="_csrf_header" content="${_csrf.headerName}"/>

        <div class="col-md-6">
          <label for="petName" class="form-label">펫 이름</label>
          <input type="text" class="form-control" id="petName" name="petName" value="${pet.petName}">
        </div>
        <div class="col-md-6 d-flex align-items-end justify-content-end">
          <button type="button" class="btn btn-mustard" id="updateBtn">수정하기</button>
        </div>
      </form>
    </div>

    <!-- 목록으로 버튼은 footer에 배치 -->
    <div class="card-footer text-end">
      <a href="${pageContext.request.contextPath}/pet/listPetPage" class="btn btn-sage">목록으로</a>
    </div>
  </div>
</div>

<script>
$(function(){
    $("#updateBtn").on("click", function(){
        let formData = $("#updatePetForm").serialize(); // petId + petName만 전달
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");

        $.ajax({
            url: "${pageContext.request.contextPath}/pet/update/admin",
            type: "POST",
            data: formData,
            dataType: "json",
            beforeSend: function(xhr) {
                xhr.setRequestHeader(header, token); // CSRF 토큰 헤더 추가
            },
            success: function(json){
                console.log("✅ update 응답:", json);
                if(json.result == 1){
                    alert("펫 이름 수정 성공");
                    location.href = "${pageContext.request.contextPath}/pet/listPetPage";
                } else {
                    alert("펫 이름 수정 실패");
                }
            },
            error: function(xhr, status, msg){
                console.error("❌ update 에러:", status, msg, xhr.responseText);
                alert("수정 중 오류 발생");
            }
        });
    });
});
</script>

<%@include file="../inc/footer.jsp" %>