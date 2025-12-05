<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../inc/header.jsp"%>
<!-- 	header		 --> 
<div class="container mt-5">
	<h3>유저정보수정</h3>
	<form action="${pageContext.request.contextPath}/security/update" 	
		method="post"  encType="multipart/form-data" > 
	    <input type="hidden"   name="appUserId"  value="${dto.appUserId}"> 
	         <input  type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
	         
		<div class="mb-3 mt-3">
			<label  for="email" class="form-label">Email:</label> 
			<input  type="email" class="form-control" id="email"
					placeholder="이메일을 입력해주세요" required  name="email"  value="${dto.email}"  readonly>
		</div>
		
		<div class="mb-3 mt-3">
			<label  for="nickname" class="form-label">Nickname:</label> 
			<input  type="text" class="form-control" id="nickname"
					placeholder="닉네임을 입력해주세요" required  name="nickname"  value="${dto.nickname}" >
		</div>
		
		<div class="mb-3 mt-3">
			<label  for="mobile" class="form-label">Mobile:</label> 
			<input  type="text" class="form-control" id="mobile"
					placeholder="모바일을 입력해주세요" required  name="mobile"  value="${dto.mobile}" >
		</div>
		
		<div class="mb-3">
			<label for="password" class="form-label">Password:</label> 
			<input type="password" class="form-control" id="password"
					placeholder="비밀번호를 입력해주세요" name="password">
		</div>
		  <div class="mb-3">
		    <label for="file" class="form-label">프로필이미지 수정</label>
		    <input type="file" class="form-control" id="file" placeholder="파일을 입력해주세요" name="file">
		</div>
		  <div class="mb-3">
		  	<input type="text" class="form-control" id="ufile"   readonly  name="ufile"  value="${dto.ufile}">
		  </div>			
		<div class="mb-3">
			<label class="form-check-label"  for="mbtiTypeId">MBTI TYPE : </label>  
			<select   name="mbtiTypeId"  id="mbtiTypeId"  class="form-control">
				<option value="1">ISTJ</option>
				<option value="2">ISFJ</option>
				<option value="3">INFJ</option>
			</select>
		</div>
		<button type="submit" class="btn btn-primary">정보수정-비밀번호/MBTI TYPE</button>
		     <input  type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
	</form>
</div>
<!-- ctrl + shift + f -->
<!-- 	footer		 --> 
<%@ include file="../inc/footer.jsp"%>





	
<!-- 				
	action="join_process.jsp"
	method="post"
	name="email"   name="password"  name="mbti_type_id" 
-->
