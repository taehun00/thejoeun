<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="../inc/header.jsp" %> 

<div class="container card my-5 p-4">
  <h3 class="card-header">질환명 글상세보기</h3>

  <div>
    <input type="hidden" name="app_user_id" value=""/>

    <div class="mb-3 mt-3">
      <label for="title" class="form-label">질환명:</label>
      <input type="text" class="form-control" id="title"
             placeholder="내용을 입력해주세요" name="title" readonly value="${dto.disname}">
    </div>

    <div class="mb-3">
      <label for="content" class="form-label">질환 설명:</label>
      <textarea class="form-control" id="content" placeholder="내용을 입력해주세요"
                readonly name="content">${dto.disex}</textarea>
    </div>
    
    <div class="mb-3">
		    <label for="content" class="form-label">KINDPET(대표 품종):</label>
		    <textarea class="form-control" id="content" placeholder="대표 품종 내용을 입력해주세요" name="kindpet">${dto.kindpet}</textarea>
		  </div> 
		  
		  <div class="mb-3">
		    <label for="content" class="form-label">INFVAL(수치화 정보):</label>
		    <textarea class="form-control" id="content" placeholder="수치화 정보 내용을 입력해주세요" name="infval">${dto.infval}</textarea>
		  </div> 
		  
		  
		  <div class="mb-3">
		    <label for="content" class="form-label">MANNOTE(관리 및 주의점):</label>
		    <textarea class="form-control" id="content" placeholder="관리 및 주의점 내용을 입력해주세요" name="mannote">${dto.mannote}</textarea>
		  </div>




		<div class="mb-3">
			<a
				href="<%=request.getContextPath() %>/PawjecteditView.swc?id=${dto.disno}"
				class="btn btn-success form-control">글수정</a>
		</div>
		<div class="mb-3">
			<a
				href="<%=request.getContextPath() %>/Pawjectdelete.swc?id=${dto.disno}"
				class="btn btn-secondary form-control">글삭제</a>
		</div>


		<div class="mb-3">
			<a href="<%=request.getContextPath()%>/Pawjectlist.swc"
				class="btn btn-primary form-control">목록보기</a>
		</div>

	</div>
</div>

<%@include file="../inc/footer.jsp" %>