<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file="../inc/header.jsp" %>
   <script>
   $(function(){
	   let result = '${success}';
	   console.log(result); 
	   if(result == "글쓰기 실패"){   alert( result );   history.go(-1); }
	   else if(result == '비밀번호를 확인해주세요'){  alert( result  );  history.go(-1); }
	   else if(result.length  != 0 ){  alert(result); }  //아까 처음 값이없을때 공백 
   });
   
   </script>
   <div class="container card  my-5 p-4">
      <h3 class="card-header"> 반려동물 질환 리스트 </h3>  
<%--       <div>${list}</div> 
      <div>${paging}</div>--%>
      <table class="table table-striped table-bordered table-hover">
      	<caption>Disease</caption>
      	<thead>
      		<tr>
      			<th scope="col">DISNO</th>
      			<th scope="col">DISNAME</th>
      			<th scope="col">DISEX</th>
      			<th scope="col">KINDPET</th>
      			<th scope="col">INFVAL</th>
      			<th scope="col">MANNOTE</th>
      			<th scope="col">CREATEDAT</th>
      			<th scope="col">BHIT</th>
      			<!-- <th scope="col">APPUSERID</th> -->
      			<!-- <th scope="col">BPASS</th> -->
      			
      		</tr>	
      	</thead>
      	<tbody>  
      	<%-- <tr><td>${list}</td></tr> --%>
      	<%-- <tr><td><a href="${pageContext.request.contextPath}/detail.quest?disno=145">detail</a> --%> 
      	
      	 <c:forEach  var="dto"  items="${list}"  varStatus="status">  	
	  		<tr> 
	  			<td>${paging.listtotal -((paging.current-1) *10) -status.index} </td>
	  			<td> <a href="${pageContext.request.contextPath}/detail.quest?disno=${dto.disno}">
	  				${dto.disname}
	  			</a> </td>
	  			<td>${dto.disex}</td>
	  			<td>${dto.kindpet}</td>
	  			<td>${dto.infval}</td>
	  			<td>${dto.mannote}</td>
	  			<td>${dto.createdat}</td>
	  			<td>${dto.bhit}</td>
	  		 <tr>
	  	  </c:forEach>
	  	  
      	</tbody>
      	<tfoot>
        	  <tr><td colspan="5"><ul class="pagination  justify-content-center"> 
	      		<!-- 이전 -->	
	      		<c:if   test="${ paging.start >10 }">
	      			<li  class="page-item">
	      				<a  class="page-link"  href="?pstartno=${paging.start-1}">이전</a>
	      			</li>
	      		</c:if>
	      		
	      		<!-- 1,2,3,4,5,6,7,8,9,10 -->	
	      		<c:forEach  var="i" begin="${paging.start}"  end="${paging.end}" >
	      			<li  class="page-item  <c:if test="${i==paging.current}">  active  </c:if>">
	      				<a  class="page-link"  href="?pstartno=${i}">${i}</a>
	      			</li>
	      		</c:forEach>	 
	      		
	      		<!-- 다음 -->	
	      		<c:if   test="${ paging.pagetotal > paging.end }">
	      			<li  class="page-item">
	      				<a  class="page-link"  href="?pstartno=${paging.end+1}">다음</a>
	      			</li>
	      		</c:if> 
	      		
      	  </ul></td></tr>	 
      	</tfoot>
      </table> 
      
      <!-- 로그인 사람만 글쓰기 가능 -->
      <sec:authorize access="isAuthenticated()">
		<p class="text-end">
			<a href="${pageContext.request.contextPath}/write.quest" class="btn btn-primary">글쓰기</a>
		</p>	
		
		</sec:authorize> 
		
		
		<sec:authorize access="isAnonymous()">
		    <p class="text-end alert alert-primary">로그인을 하면 글쓰기가능합니다.</p> 
		</sec:authorize>
		
		
	  <div class="mb-3 mt-3 alert alert-primary">
	    <label for="search" class="form-label">	SEARCH</label>
	    <input type="search" class="form-control" id="search"  placeholder="검색어를 입력해주세요" name="search">
	    <!-- 						 -->
	    <!-- 						 -->
	    <div  id="resultArea">
	      <table class="table table-striped table-bordered table-hover  my-3">
		    <caption>Disease</caption>
		    <thead>
		      <tr>
		        <th scope="col">DISNO</th>
      			<th scope="col">DISNAME</th>
      			<th scope="col">DISEX</th>
      			<th scope="col">KINDPET</th>
      			<th scope="col">INFVAL</th>
      			<th scope="col">MANNOTE</th>
      			<th scope="col">CREATEDAT</th>
      			<th scope="col">BHIT</th>
		      </tr> 
		    </thead>
		    <tbody>
		      <!-- AJAX 결과가 여기에 들어감 -->
		    </tbody>
		  </table> 
	    </div>
	    <!-- 						 -->
	    <!-- 						 -->	    	
	  </div>		
	  <script>
	  $(function(){
		  $("#search").on("keyup" , function(){    // keyup (키보드뗐을때)
				console.log( $(this).val().trim()  ); 
				let keyword = $(this).val().trim();
				
			/////////////////////////////////////////////
				if(keyword ===""){ //빈칸일때
					$("#resultArea  tbody")
					.empty()
					.append("<tr><td colspan='5'>검색어를 입력하세요.</td></tr>");
				}else{ // 서버요청
					$.ajax({
						url:"${pageContext.request.contextPath}/selectSearch",
						type:"GET",  //GET, POST,PUT
						data:{search:keyword},
						success:function( res ){ 
							console.log(res); 
							$("#resultArea  tbody").empty();  //초기화
							$.each( res , function( index, dto  ){ 
								let row = "<tr>"
								+"<td>"+(res.length-index)+"</td>"
								+"<td><a href='${pageContext.request.contextPath}/detail.quest?disno="+dto.disno+"'>"
								+dto.disname+"</a></td>"
								+"<td>"+dto.disex+"</td>"
								+"<td>"+dto.kindpet+"</td>"
								+"<td>"+dto.infval+"</td>"
								+"<td>"+dto.mannote+"</td>"
								+"<td>"+dto.createdat+"</td>"
								+"<td>"+dto.bhit+"</td>"
								+"</tr>";
								$("#resultArea  tbody").append(row);
								
								
							});
						}
					});
				} 
				///////////////////////////////////////////// 
		  });
	  });
	  </script>	
		
   </div>
   
<%@include file="../inc/footer.jsp" %>

<!-- [ mbtiBoard - list.jsp ]  -->