<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/header.jsp" %>

<div class="container card my-5 p-4">
    <h3 class="card-header text-danger">❗ 반려동물 삭제</h3>

    <div class="mt-4">
        <p>정말 <strong>${dto.petname}</strong> (${dto.petbreed}) 정보를 삭제하시겠습니까?</p>

        <form action="/petdelete.pet" method="post">
            <input type="hidden" name="petid" value="${dto.petid}">
            <input type="hidden" name="userid" value="${dto.userid}">

            <div class="text-end">
                <button type="submit" class="btn btn-danger">삭제하기</button>
                <a href="/petinfo.pet?userid=${dto.userid}" class="btn btn-secondary">취소</a>
            </div>
        </form>
    </div>
</div>

<%@ include file="../inc/footer.jsp" %>
