<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../inc/header.jsp" %>
<script>
   $(function(){
	   let result = '${success}';
	   console.log(result); 
	   if(result == "비밀번호를 확인해주세요"){   alert( result );   history.go(-1); } 
	   else if(result == "수정 실패"){   alert( result );   history.go(-1); }
	   else if(result.length  != 0 ){  alert(result); }  //아까 처음 값이없을때 공백 
   }); 
   </script>
<!-- 펫 정보 수정 성공 시 alert -->
<c:if test="${not empty success1}">
    <script>
        alert("${success1}");
    </script>
</c:if>

   <c:if test="${not empty deleteError}">
    <script>
        alert("${deleteError}");
    </script>
</c:if>

<c:if test="${not empty error}">
    <script>alert("${error}");</script>
</c:if>


<div class="container my-5">
  <div class="card shadow-sm">
    <div class="card-header bg-info text-white">
      <h3 class="mb-0">MYPAGE</h3>
    </div>
    <div class="card-body d-flex align-items-center">
      <!-- 프로필 이미지 -->
      <div class="me-4">
        <img src="${pageContext.request.contextPath}/upload/${dto.ufile}" 
             alt="프로필" class="rounded-circle border" style="width:120px; height:120px;">
      </div>
      <!-- 유저 정보 -->
      <ul class="list-group list-group-flush flex-fill">
        <li class="list-group-item"><strong>Email:</strong> ${dto.email}</li>
        <li class="list-group-item"><strong>Nickname:</strong> ${dto.nickname}</li>
        <li class="list-group-item"><strong>Mobile:</strong> ${dto.mobile}</li>
        <li class="list-group-item"><strong>가입일:</strong> ${dto.createdAt}</li>
      </ul>
    </div>
    <div class="card-footer text-end">
      <a href="${pageContext.request.contextPath}/security/update" class="btn btn-mustard">UPDATE</a>
      <a href="${pageContext.request.contextPath}/security/delete" class="btn btn-burgundy">DELETE</a>
    </div>
  </div>
</div>
<div class="container my-4">
  <h4 class="mb-3">등록된 펫 정보</h4>
  <div class="row">
    <c:forEach var="pet" items="${pets}">
      <div class="col-md-4 mb-3">
        <div class="card h-100 shadow-sm">
          <img src="${pageContext.request.contextPath}/upload/${pet.pfile}" 
               class="card-img-top" alt="펫 이미지" style="height:200px; object-fit:cover;">
          <div class="card-body">
            <h5 class="card-title">
              <a href="${pageContext.request.contextPath}/pet/detail?petId=${pet.petId}" class="text-decoration-none">
                ${pet.petName}
              </a>
            </h5>
            <p class="card-text">
              <strong>품종:</strong> ${pet.petBreed}<br>
              <strong>생일:</strong> ${pet.birthDate}<br>
              <strong>타입:</strong>
              <c:choose>
                <c:when test="${pet.petTypeId == 1}">고양이</c:when>
                <c:when test="${pet.petTypeId == 2}">강아지</c:when>
                <c:otherwise>기타</c:otherwise>
              </c:choose><br>
              <strong>등록일:</strong> ${pet.createdAt}
            </p>
          </div>
        </div>
      </div>
    </c:forEach>
  </div>
  <div class="text-end mt-3">
    <a href="${pageContext.request.contextPath}/pet/join" class="btn btn-mint">펫 작성하기</a>
  </div>
</div>
<%@ include file="../inc/footer.jsp" %>


