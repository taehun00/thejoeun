<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="../inc/header.jsp"%>

<div class="container card  my-5 p-4 ">
	<h3 class="card-header"> 운동정보 글삭제</h3>
<%-- <form action="${pageContext.request.contextPath}/delete.do?id=${param.id}"     method="post"> → spring에서 해볼예정  --%> 
	<form action="<%=request.getContextPath()%>/delete.hsh?id=<%=request.getParameter("execid")%>"  method="post"> 
	<input type="hidden" name="execid" value="">
		<div class="my-3">
			<label for="pass" class="form-label">비밀번호</label> 
			<input type="text" class="form-control" id="pass"
				   placeholder="비밀번호를 입력해주세요" name="pass">
		</div>
		<div class="my-3  text-end">
			<button type="submit" class="btn btn-primary">글쓰기</button>
			<a href="javascript:history.go(-1)" class="btn btn-danger">뒤로가기</a>
		</div>
	</form>
</div>

<%@include file="../inc/footer.jsp"%>

<!-- [ execinfoboard - list.jsp ]  -->