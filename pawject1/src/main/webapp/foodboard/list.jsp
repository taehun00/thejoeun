<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
  <%@include file="../inc/header.jsp"%>

   <div class="container card  my-5 p-4">
      <h3  class="card-header">FOOD BOARD </h3>
      


	  <TABLE class="table table-striped table-bordered table-hover">
	  <caption>FOOD 정보리스트</caption>
	  <thead>
	  		<tr>
		  		<th scope="col">NO</th>
		  		<th scope="col">FOODNAME</th>
		  		<th scope="col">DESCRIPTION</th>
		  		<th scope="col">PETTYPE</th>
		  		

	  		</tr>
	  </thead>
	  <tbody>
	  	<c:forEach var="dto" items="${list}" varStatus="status"> 
      	<tr>
      		<td>${ list.size() - status.index }</td>

      		<td><a href="<%=request.getContextPath()%>/detail.jys?id=${dto.foodid}">${dto.foodname}</a></td>
      		<td>${dto.description}</td>
      		<td>${dto.pettypeid}</td>
      		
<%--       		<td>${dto.hit}</td> --%>
      	</tr>
      </c:forEach>
	  </tbody>
	  </TABLE>




<p class="text-end">
 <% if(email != null) { %> 
    <a href="${pageContext.request.contextPath}/writeView.jys" class="btn btn-primary">글쓰기</a>
 <% } %> 
</p>

   </div>
   
<%@include file="../inc/footer.jsp" %>
   


<!-- mbtiBoard - list.jsp -->