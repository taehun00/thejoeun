<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="../inc/header.jsp"%>

<div class="container card my-5 p-4">
	<h3 class="card-header">FOOD 정보삭제</h3>
	<form action="<%=request.getContextPath()%>/delete.jys" method="post">
		<input type="hidden" name="foodid" value="<%= request.getParameter("id") %>"> <%-- sb --%>
		<div class="my-3 text-end">
		<button type="submit" class="btn btn-primary">삭제하기</button>
		<a href="javascript:history.go(-1)" class="btn btn-danger">BACK</a>
		</div>
	</form>
</div>
<%@include file="../inc/footer.jsp"%>