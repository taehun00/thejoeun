<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="../inc/header.jsp" %>

<<<<<<< HEAD
<div class="container mt-5">
    <div class="card shadow-sm">
        <div class="card-header bg-light">
            <h3 class="mb-0">펫 삭제 확인</h3>
        </div>
        <div class="card-body">
            <p class="fs-5 text-danger">
                정말로 <strong>"${pet.petName}"</strong> 펫 정보를 삭제하시겠습니까?
            </p>
            
            <!-- 기존 form 대신 버튼만 두고 JS에서 AJAX 호출 -->
            <input type="hidden" id="petId" value="${pet.petId}">
            <meta name="_csrf" content="${_csrf.token}"/>
            <meta name="_csrf_header" content="${_csrf.headerName}"/>
            
            <div class="d-flex justify-content-end gap-2">
                <button type="button" id="deleteBtn" class="btn btn-burgundy">삭제</button>
                <a href="${pageContext.request.contextPath}/pet/detail?petId=${pet.petId}" class="btn btn-sage">취소</a>
            </div>
        </div>
    </div>
</div>

<%@include file="../inc/footer.jsp" %>

<script>
$(function(){
    $("#deleteBtn").on("click", function(){
        let petId = $("#petId").val();
        let token = $("meta[name='_csrf']").attr("content");
        let header = $("meta[name='_csrf_header']").attr("content");

        if(confirm("정말로 삭제하시겠습니까?")){
            $.ajax({
                url: "${pageContext.request.contextPath}/pet/delete",
                type: "POST",
                data: { petId: petId },
                dataType: "json",
                beforeSend: function(xhr){
                    xhr.setRequestHeader(header, token);
                },
                success: function(json){
                    if(json.result == 1){
                        alert(json.message);
                        // ✅ 삭제 성공 후 원하는 페이지로 이동
                        window.location.href = "${pageContext.request.contextPath}/security/mypage";
                    } else {
                        alert(json.message);
                        // 실패 시 상세 페이지로 다시 이동
                        window.location.href = "${pageContext.request.contextPath}/pet/detail?petId=" + petId;
                    }
                },
                error: function(xhr, status, msg){
                    console.error("❌ deletePet 에러:", status, msg, xhr.responseText);
                    alert("삭제 중 오류 발생");
                }
            });
        }
    });
});
</script>
=======
	<div class="container mt-5">
	    <h3>펫 삭제 확인</h3>
	    <p>정말로 "${pet.petName}" 펫 정보를 삭제하시겠습니까?</p>
	
	    <form action="${pageContext.request.contextPath}/pet/delete" method="post">
	        <input type="hidden" name="petId" value="${pet.petId}">
	        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	        <button type="submit" class="btn btn-danger">삭제</button>
	        <a href="${pageContext.request.contextPath}/pet/detail?petId=${pet.petId}" class="btn btn-secondary">취소</a>
	    </form>
	</div>

<%@include file="../inc/footer.jsp" %>
>>>>>>> cb4a38b2ec94f84ae8d5b1165d16b0247b5b119c
