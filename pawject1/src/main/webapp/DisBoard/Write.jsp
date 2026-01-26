<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@include file="../inc/header.jsp" %>
    
    <div class = "container card my-5 p-4">
    	<h3> DISEASE 기입(INSERT)</h3>
    		<form action = "<%=request.getContextPath()%>/Pawjectwrite.swc"  method = "post">
    			<input type = "hidden" name = "disease" value = "">
						<div class="mb-3 mt-3">
							<label for="disname" class="form-label">질환명:</label> <input
								type="text" class="form-control" id="disname" placeholder="질환명 입력해주세요"
								name="disname"  required>
						</div>
				
						<div class="mb-3">
							<label for="disex" class="form-label">질환 설명:</label>
							<textarea class="form-control" id="disex" placeholder="질환 설명 내용을 입력해주세요"
								name="disex"  required></textarea>
						</div>
						
						
						
						<div class="mb-3">
							<label for="kindpet" class="form-label">대표 품종:</label>
							<textarea class="form-control" id="kindpet" placeholder="대표 품종을 입력해주세요"
								name="kindpet" required></textarea>
						</div>
						
						<div class="mb-3">
							<label for="infval" class="form-label">수치화 정보:</label>
							<textarea class="form-control" id="infval" placeholder="수치화 정보 내용을 입력해주세요 (발생률: 특정 대형견 품종에서 **15% ∼ 50%**까지 보고됨.)"
								name="infval" required></textarea>
						</div>
						
						<div class="mb-3">
							<label for="mannote" class="form-label">관리 및 주의점:</label>
							<textarea class="form-control" id="mannote" placeholder="관리 및 주의점 내용을 입력해주세요"
								name="mannote" required></textarea>
						</div>

						<div class="mb-3">
						<button type="submit" class="btn btn-primary">글쓰기(Submit)</button>
						</div>
		
						<div class="mb-3  text-end">
							
							<a href="<%=request.getContextPath()%>/Pawjectlist.swc"
								class="btn btn-primary">목록보기</a>
						</div>


	</form>
    
    
    
    </div>