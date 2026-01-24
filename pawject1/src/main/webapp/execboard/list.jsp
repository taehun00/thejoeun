<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file="../inc/header.jsp"%>

<div class="container card  my-5">
	<h3 class="card-header">운동정보보드</h3>
<!-- 		     한개			 향상된 for 게시판리스트
<c:forEach  var="변수명" items="서버에서 넘겨받은 값들ㄴ" varStatus="status">
	</c:forEach> 
	-->
	<table class="table table-striped table-bordered table-hover">
		<caption>운동정보</caption>
		<thead>
			<tr>
				<th scope="col">번호</th> 
				<th scope="col">운동유형</th>
				<th scope="col">운동코드</th>
				<th scope="col">설명</th>
				<th scope="col">30분당 평균칼로리소모량</th>
				<th scope="col">권장운동시간</th>
				<th scope="col">추천동물</th>
				<th scope="col">운동강도</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach  var="dto" items="${list}" varStatus="status">
				<tr>
					<td>${list.size() - status.index}</td>
					<td>
					    <a href="<%=request.getContextPath()%>/exec.hsh?id=${dto.execid}">
							${dto.exectype}
						</a>		
<%-- 						<a href="${pageContext.request.contextPath}/detail.do?id=${dto.id}">
							${dto.title}
						</a>--%>
 					</td>
					<td>${dto.execid}</td>
					<td>${dto.description}</td>
					<td>${dto.avgkcal30min}</td>
					<td>${dto.exectargetmin}</td>
					<td>${dto.suitablefor}</td>
					<td>${dto.intensitylevel}</td>
					
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
 <%  if(email != null){ %> <%-- 오류나시면 email로 바꿔서 사용하시면 됩니다. --%>
		<p class="text-end"><a href="<%=request.getContextPath()%>/regForm.hsh" class="btn btn-warning">글쓰기</a>
		</p>
	<%} 
 else{ %>
		<p class="alert alert-info"> 로그인을하면 글쓰기가능합니다.</p>
	<%}  %>  
</div>
<form  <%-- action="<%=request.getContextPath()%>/#?id=<%=request.getParameter("#")%>"  method="post" --%> >	
	<div class= 	"my-3  text-end" >
	    <button type="submit" class="btn btn-primary"> 다음페이지<a href="<%=request.getContextPath()%>/#"></a></button> 	
		<%--다음페이지버튼은 운동정보페이지 다음에오는 페이지로 넘어갈때 사용합니다.--%>
	</div>
</form>


<%@include file="../inc/footer.jsp"%>

<!-- ExecInfoBoard - list -->
