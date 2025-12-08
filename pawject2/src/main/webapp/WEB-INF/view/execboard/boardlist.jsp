<<<<<<< HEAD
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

=======

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
>>>>>>> cb4a38b2ec94f84ae8d5b1165d16b0247b5b119c
<%@include file="../inc/header.jsp"%>
<script>
	$(function(){
		let result = '${success}';
		console.log(result);
<<<<<<< HEAD
		if(resut == '글쓰기실패'){
			alert(result); history.go(-1);
		}else if( result == '사용자 아이디를 확인해주세요.' ){
=======
		if(result == '글쓰기에 실패했습니다.'){
>>>>>>> cb4a38b2ec94f84ae8d5b1165d16b0247b5b119c
			alert(result); history.go(-1);
		}else if(result.length !=0){
			alert(result);
		}
	});
</script>
<div class="container card  my-5 p-4">
	<h3 class="card-header">운동챌린지게시판</h3>
<<<<<<< HEAD
	<div>${list}</div>
	<div>${paging}</div>
=======
>>>>>>> cb4a38b2ec94f84ae8d5b1165d16b0247b5b119c
	<table class="table table-striped table-bordered table-hover">
		<caption>운동챌린지게시판</caption>
		<thead>
			<tr>
				<th scope="col">번호</th>
				<th scope="col">조회수</th>
		   <%-- <th scope="col">운동아이디</th> --%>
		   <%-- <th scope="col">사용자아이디</th> --%>
<<<<<<< HEAD
				<th scope="col">제목</th> 
=======
				<th scope="col">제목</th> <%-- 글상세글 링크걸기 --%>
>>>>>>> cb4a38b2ec94f84ae8d5b1165d16b0247b5b119c
		   		<th scope="col">이미지</th>	
		   <%--	<th scope="col">내용</th>  --%>
				<th scope="col">작성일</th>
		   <%-- <th scope="col">수정일</th>  --%>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="dto" items="${list}" varStatus="status">
				<tr>
					<%-- <td>${dto}</td> --%>
					<td>${paging.listtotal -((paging.current-1) *10) -status.index}</td>
<<<<<<< HEAD
					<%-- 	<td>${list.size()-status.index}</td> --%>
					<!-- 3-0=3  3-1=2  3=2=1  -->
					<td>${dto.ehit}</td>
					<td><a
						href="${pageContext.request.contextPath}/detail.execboard?postid=${dto.postid}">
							${dto.etitle} </a></td>
			   <%--		${dto.execId}  --%>
			   <%-- <td>${dto.userId}</td>  --%>
			
			   <%-- <td>${dto.econtent}</td> --%>
					<td>${dto.eimg}</td>
					<td>${dto.createdat}</td>
			   <%-- <td>${dto.updatedat}</td>  --%>
				<tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="5"><ul class="pagination  justify-content-center">
						<!-- 이전 -->
						<c:if test="${ paging.start >10 }">
							<li class="page-item"><a class="page-link"
								href="?pstartno=${paging.start-1}">이전</a></li>
						</c:if>

						<!-- 1,2,3,4,5,6,7,8,9,10 -->
						<c:forEach var="i" begin="${paging.start}" end="${paging.end}">
							<li
								class="page-item  <c:if test="${i==paging.current}">  active  </c:if>">
								<a class="page-link" href="?pstartno=${i}">${i}</a>
							</li>
						</c:forEach>

						<!-- 다음 -->
						<c:if test="${ paging.pagetotal > paging.end }">
							<li class="page-item"><a class="page-link"
								href="?pstartno=${paging.end+1}">다음</a></li>
						</c:if>

					</ul></td>
			</tr>
		</tfoot>
	</table>
 	<sec:authorize access="isAuthenticated()">	</sec:authorize>
=======
					<%-- <td>${list.size()-status.index}</td>   --%>
					<!-- 3-0=3  3-1=2  3=2=1  -->
					<td>${dto.ehit}</td>
					<td>
						<a href="${pageContext.request.contextPath}/detail.execboard?postid=${dto.postid}">${dto.etitle}</a>
					</td>
					<td>
						<img src="${pageContext.request.contextPath}/upload/${dto.eimg}"  alt="eimg"
				       		 style="width:100px;" />
					</td>
					<td>${dto.createdat}</td>
				</tr>
			</c:forEach>
		</tbody>
		
		<tfoot>
			<tr>
				<td colspan="5">
					<ul class="pagination  justify-content-center">
							<!-- 이전 -->
							<c:if test="${ paging.start >10 }">
								<li class="page-item"><a class="page-link"
									href="?pstartno=${paging.start-1}">이전</a></li>
							</c:if>
	
							<!-- 1,2,3,4,5,6,7,8,9,10 -->
							<c:forEach var="i" begin="${paging.start}" end="${paging.end}">
								<li
									class="page-item  <c:if test="${i==paging.current}">  active  </c:if>">
									<a class="page-link" href="?pstartno=${i}">${i}</a>
								</li>
							</c:forEach>
	
							<!-- 다음 -->
							<c:if test="${ paging.pagetotal > paging.end }">
								<li class="page-item"><a class="page-link"
									href="?pstartno=${paging.end+1}">다음</a></li>
							</c:if>
					</ul>
				</td>
			</tr>
		</tfoot>
	</table>
	
	<sec:authorize access="isAuthenticated()">
>>>>>>> cb4a38b2ec94f84ae8d5b1165d16b0247b5b119c
		<p class="text-end">
			<a href="${pageContext.request.contextPath}/list.execinfo"
				class="btn btn-green">운동정보</a>
		</p>
<<<<<<< HEAD
 	
 	<sec:authorize access="isAuthenticated()">	</sec:authorize>
=======
	</sec:authorize>
	
	<sec:authorize access="isAuthenticated()">
>>>>>>> cb4a38b2ec94f84ae8d5b1165d16b0247b5b119c
		<p class="text-end">
			<a href="${pageContext.request.contextPath}/write.execboard"
				class="btn btn-mint">글쓰기</a>
		</p>
<<<<<<< HEAD
	
	
=======
	</sec:authorize>
>>>>>>> cb4a38b2ec94f84ae8d5b1165d16b0247b5b119c
	
 	<p class="text-end alert alert-info">로그인을 하면 글쓰기가능합니다.</p>

	<div class="mb-3 mt-3 alert alert-info">
		<label for="search" class="form-label">검색</label> 
		<input type="search" class="form-control" id="search"
			  placeholder="검색어를 입력해주세요" name="search"/>
		<!--                    -->
		<!--                    -->
		<div id="resultArea">
			<table class="table table-striped table-bordered table-hover  my-3">
				<caption>게시판</caption>
				<thead>
					<tr>
						<th scope="col">번호</th>
						<th scope="col">제목</th>
						<th scope="col">내용</th>
						<th scope="col">이미지</th>
						<th scope="col">작성일</th>
					</tr>
				</thead>
				<tbody>
					<!-- AJAX 결과가 여기에 들어감 -->
				</tbody>
			</table>
		</div>
		<!--                    -->
		<!--                    -->
	</div>

<<<<<<< HEAD
	<script>
		$(function(){
			$("search").on("keyup", function(){ //keyup (키보드떴을때)
				console.log($(this).val().trim());
				let keyword = $(this).val().trim();
				///////////////////////////////////////
				if(keyword === ""){ //빈칸일때
					$("#resultArea tbody")
							.empty()
							.append( "<tr><td colspan='5'>검색어를 입력하세요.</td></tr>" );
				} else{ //빈칸이 아니라면? 서버요청
					$.ajax({
						url:"${pageContext.request.contextPath}/selectSearch1",
						type:"GET", //GET, POST, PUT
						data:{ search : keyword },
						success : function( res ){
							console.log(res);
							$("#resultArea tbody")
									.empty(); //초기화
							$.each(res, function(index, dto){
								let row = "<tr>"
										+ "<td>"
										+ (res.length - index)
										+ "</td>"
										+ "<td><a href='${pageContext.request.contextPath}/detail.execboard?postid="
										+ dto.id
										+ "'>"
										+ dto.etitle
										+ "</a></td>"
										+ "<td>"
										+ dto.postId
										+ "</td>"
										+ "<td>"
										+ dto.creatdAt
										+ "</td>"
										+ "<td>"
										+ dto.ehit
										+ "</td>"
										+ "</tr>"
								$( "#resultArea tbody" )
									.append(row);
							});		
						}
					})
				}
				///////////////////////////////////////
			});
		});
	</script>

=======
<script>
$(function(){
    $("#search").on("keyup", function(){
    	console.log($(this).val().trim());
        let keyword = $(this).val().trim();
        if(keyword === ""){
            $("#resultArea tbody")
                .empty()
                .append("<tr><td colspan='5'>검색어를 입력하세요.</td></tr>");
        } else {
            $.ajax({
                url: "${pageContext.request.contextPath}/selectSearch1",
                type: "GET",
                data: { search: keyword },
                success: function(res){
                    $("#resultArea tbody").empty();
                    $.each(res, function(index, dto){
                        let row = "<tr>"
                                + "<td>" + (res.length - index) + "</td>"
                                + "<td><a href='${pageContext.request.contextPath}/detail.execboard?postid="
                                + dto.postId + "'>" + dto.etitle + "</a></td>"
                                + "<td>" + dto.econtent + "</td>"
                                + "<td><img src='/pawject2/upload/" + dto.eimg + "' alt='eimg' style='width:100px;'/></td>"
                                + "<td>" + dto.createdat + "</td>"
                                + "</tr>";
                        $("#resultArea tbody").append(row);
                    });
                }
            });
        }
    });
});
</script>
>>>>>>> cb4a38b2ec94f84ae8d5b1165d16b0247b5b119c
</div>

<%@include file="../inc/footer.jsp"%>

<<<<<<< HEAD
<!-- [ ExecBoard - boardedit.jsp ]  -->
=======
<!-- [ ExecBoard - boardedit.jsp ]  -->
>>>>>>> cb4a38b2ec94f84ae8d5b1165d16b0247b5b119c
