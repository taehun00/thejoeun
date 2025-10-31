<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file="../inc/header.jsp" %>

   <div class="container card  my-5 p-4">
      <h3 class="card-header"> MBTI 글수정</h3>
      <form action="${pageContext.request.contextPath}/edit.do?id=${dto.id}" method="post">
      	<div class="mb-3 mt-3">
      		<label for="title" class="form-label"> TITLE </label>
      		<input type="text" class="form-control" id="title" placeholder="내용을 입력해주세요" name="title" value="${dto.title}">
      	</div>
      	<div class="mb-3">
      		<label for="pass" class="form-label"> PASS </label>
      		<input type="password" class="form-control" id="pass" placeholder="비밀번호를 입력해주세요" name="pass">
      	</div>
      	<div class="mb-3">
      		<label for="content" class="form-label"> CONTENT </label>
      		<textarea class="form-control" id="content" placeholder="내용을 입력해주세요" name="content">${dto.content}</textarea>
      	</div>
      	<div class="mb-3 text-end">
        	<button type="submit" class="btn btn-primary">글수정</button>
        	<a href="javascript:history.go(-1)" class="btn btn-danger">BACK</a>
      	</div>
      </form>
   </div>
   
<%@include file="../inc/footer.jsp" %>