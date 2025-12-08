<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../inc/header.jsp"%>
<!-- header -->

<div class="container mt-5">
    <h3>펫 정보 수정</h3>
    <form action="${pageContext.request.contextPath}/pet/update/user"
          method="post" enctype="multipart/form-data">

        <!-- hidden: petId -->
        <input type="hidden" name="petId" value="${pet.petId}">
        <!-- hidden: CSRF -->
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

        <div class="mb-3 mt-3">
            <label for="petName" class="form-label">펫 이름:</label>
            <input type="text" class="form-control" id="petName"
                   placeholder="펫 이름을 입력해주세요" required
                   name="petName" value="${pet.petName}">
        </div>

        <div class="mb-3">
            <label for="petBreed" class="form-label">펫 품종:</label>
            <input type="text" class="form-control" id="petBreed"
                   placeholder="품종을 입력해주세요"
                   name="petBreed" value="${pet.petBreed}">
        </div>

        <div class="mb-3">
            <label for="birthDate" class="form-label">생일:</label>
            <input type="date" class="form-control" id="birthDate"
                   name="birthDate" value="${pet.birthDate}">
        </div>

		<div class="mb-3">
            <label for="petTypeId" class="form-label">펫 타입 ID:</label>
            <select class="form-select" id="petTypeId" name="petTypeId" value="${pet.petTypeId}" required>  
                <option value="1">Cat</option>
                <option value="2">Dog</option>
            </select>
        </div>
        <div class="mb-3">
            <label for="file" class="form-label">펫 이미지 수정:</label>
            <input type="file" class="form-control" id="file"
                   name="file">
        </div>
		<input type="hidden" name="pfile" value="${pet.pfile}">
		<div class="mb-3">
		    <label for="currentFile" class="form-label">현재 등록된 이미지:</label>
		    <input type="text" class="form-control" id="currentFile"
		           value="${pet.pfile}" readonly>
		</div>
				

        <button type="submit" class="btn btn-mustard">펫 정보 수정</button>
		<a href="${pageContext.request.contextPath}/pet/detail?petId=${pet.petId}" class="btn btn-sage">뒤로가기</a>    </form>
</div>

<!-- footer -->
<%@ include file="../inc/footer.jsp"%>