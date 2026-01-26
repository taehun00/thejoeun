<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file = "../inc/header.jsp" %>

				<div class="container card  my-5">
					<h3 class="card-header">DISEASE BOARD</h3>
					<table class="table table-striped table-bordered table-hover">
						<caption>반려동물 질병리스트</caption>
						<thead>
							<tr>
								<th scope="col">DISNO</th>
								<th scope="col">DISNAME</th>
								<th scope="col">DISEX</th>
								<th scope="col">KINDPET</th>
								<th scope="col">INFVAL</th>
								<th scope="col">MANNOTE</th>
				
							</tr>
						</thead>
					
					
					<tbody>
	<c:forEach var = "dto" items = "${PawTestList1}" varStatus = "status">
							<tr>
							
								<td>${PawTestList1.size() - status.index}</td>
								<td><a
						href="<%=request.getContextPath()%>/Pawjectdetail.swc?id=${dto.disno}">${dto.disname}</a>

					</td>
								
								<td>${dto.disex}</td>
								<td>${dto.kindpet}</td>
								<td>${dto.infval}</td>
								<td>${dto.mannote}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
		<%-- 
		<% if(email !=null) { %>	
		    <p class = "text-end">
		    	<a href = "<%=request.getContextPath()%>/writeview.do" class = "btn btn-primary">글쓰기(insert)</a>
		    </p>
				<div class = "my-3  text-end">
					<button type = "submit" class = "btn btn-primary">글삭제(수정)</button>
						<a href = "javascript:history.go(-1)" class = "btn btn-danger">BACK</a>
				</div>
				
		<% }else { %>
				<p class = "alert alert-primary"> 로그인하면 글쓰기 가능합니다. </p>
				<% } %>	
				
				--%>					
	</div>
	 
	 
	 <div class="mb-3">
      <a href="<%=request.getContextPath() %>/Pawjectlist.swc" class="btn btn-primary form-control">목록보기</a>
    </div> 
    <div class="mb-3">
			<a
				href="<%=request.getContextPath() %>/PawjectwriteView.swc"
				class="btn btn-success form-control">글쓰기(입력)</a>
		</div>
	

<%@ include file = "../inc/footer.jsp" %>   
