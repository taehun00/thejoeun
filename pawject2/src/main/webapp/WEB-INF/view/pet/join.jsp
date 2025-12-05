<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../inc/header.jsp" %>

<div class="container card my-5 p-4">
    <h3 class="card-header mb-3">펫 등록하기</h3>

    <!-- 펫 등록 폼 -->
    <form action="${pageContext.request.contextPath}/pet/join" 
          method="post" enctype="multipart/form-data">
	<input  type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
        <!-- PETNAME -->
        <div class="mb-3">
            <label for="petName" class="form-label">Pet Name</label>
            <input type="text" class="form-control" id="petName" name="petName" required>
        </div>

        <!-- PETBREED -->
        <div class="mb-3">
            <label for="petBreed" class="form-label">Pet Breed</label>
            <input type="text" class="form-control" id="petBreed" name="petBreed" required>
        </div>

        <!-- BIRTHDATE -->
        <div class="mb-3">
            <label for="birthDate" class="form-label">Birth Date</label>
            <input type="date" class="form-control" id="birthDate" name="birthDate" required value="2025-12-04">
        </div>

        <!-- PETTYPEID -->
        <div class="mb-3">
            <label for="petTypeId" class="form-label">Pet Type</label>
            <select class="form-select" id="petTypeId" name="petTypeId" required>
                <option value="">-- Select Type --</option>
                <option value="1">Cat</option>
                <option value="2">Dog</option>
            </select>
        </div>

        <!-- PFILE -->
        <div class="mb-3">
            <label for="pfile" class="form-label">Pet Image</label>
            <input type="file" class="form-control" id="pfile" name="file">
        </div>

        <!-- 버튼 -->
        <div class="text-end">
            <button type="submit" class="btn btn-primary">등록하기</button>
            <a href="${pageContext.request.contextPath}/security/mypage" class="btn btn-secondary">취소</a>
        </div>
    </form>
</div>

<%@ include file="../inc/footer.jsp" %>
