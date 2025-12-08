<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../inc/header.jsp" %>

<div class="container my-5">
    <h3 class="mb-4">펫 이름 수정 (관리자)</h3>
	<meta name="_csrf" content="${_csrf.token}"/>
	<meta name="_csrf_header" content="${_csrf.headerName}"/>
	

    <form id="updatePetForm">
        <input type="hidden" name="petId" value="${pet.petId}" />

        <div class="mb-3">
            <label for="petName" class="form-label">펫 이름</label>
            <input type="text" class="form-control" id="petName" name="petName" value="${pet.petName}">
        </div>

        <button type="button" class="btn btn-primary" id="updateBtn">수정하기</button>
    </form>
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