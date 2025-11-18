<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/header.jsp" %>

<div class="container card my-5 p-4">
    <h3 class="card-header">🐶 반려동물 등록</h3>

    <form action="/petinsert.pet" method="post" class="mt-4">
        <div class="mb-3">
            <label for="petname" class="form-label">이름</label>
            <input type="text" class="form-control" id="petname" name="petname" required>
        </div>

        <div class="mb-3">
            <label for="petbreed" class="form-label">품종</label>
            <input type="text" class="form-control" id="petbreed" name="petbreed" required>
        </div>

        <div class="mb-3">
            <label for="birthdate" class="form-label">생일</label>
            <input type="date" class="form-control" id="birthdate" name="birthdate">
        </div>

        <div class="mb-3">
            <label for="pettypeid" class="form-label">종류</label>
            <select class="form-select" id="pettypeid" name="pettypeid" required>
                <option value="">선택하세요</option>
                <option value="1">고양이</option>
                <option value="2">개</option>
            </select>
        </div>

        <input type="hidden" name="userid" value="${sessionScope.userid}">

        <div class="text-end">
            <button type="submit" class="btn btn-primary">등록하기</button>
        </div>
    </form>
</div>

<%@ include file="../inc/footer.jsp" %>