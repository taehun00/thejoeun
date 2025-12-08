<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="../inc/header.jsp"%>
<script>
	$(function() {
		let result = '${success}';
		console.log(result);
		if (result == "글쓰기 실패") {
			alert(result);
			history.go(-1);
		} else if (result == '운동아이디를 확인해주세요.') {
			alert(result);
			history.go(-1);
		} else if (result.length != 0) {
			alert(result);
		} //아까 처음 값이없을때 공백 
	});
</script>
 <div class="container card  my-5 p-4">
	<h3 class="card-header">운동정보게시판</h3>
	<table class="table table-striped table-bordered table-hover">
		<caption>운동정보</caption>
		<thead>
			<tr>
		    	<th scope="col">운동아이디</th> 
				<th scope="col">운동유형</th>
		   <%-- <th scope="col">설명</th>  --%>
		   <%-- <th scope="col">평균칼로리소모량(30분기준)</th> --%>
				<th scope="col">권장운동시간</th>
		   <%-- <th scope="col">운동추천동물</th>  --%>
				<th scope="col">운동강도</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="dto" items="${list}" varStatus="status">
				<tr>
					<%-- <td>${dto}</td> --%>
					<td>${paging.listtotal -((paging.current-1) *10) -status.index}</td>
					<%-- <td>${list.size()-status.index}</td>  --%>
					<!-- 3-0=3  3-1=2  3=2=1  -->
					<td><a
						href="${pageContext.request.contextPath}/detail.execinfo?execid=${dto.execid}">
						${dto.exectype}</a></td>
			   <%-- <td>${dto.description}</td>  --%>
			   <%-- <td>${dto.avgkcal30min}</td> --%>
					<td>${dto.exectargetmin}</td>
			   <%-- <td>${dto.suitablefor}</td>  --%>
					<td>${dto.intensitylevel}</td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="5"> <!-- Paging -->
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
		<p class="text-end">
			<a href="${pageContext.request.contextPath}/list.execboard"
				class=" text-white btn btn-mustard">운동챌린지게시판</a>
		</p>
	</sec:authorize>
	

	<sec:authorize access="isAuthenticated()">
		<p class="text-end">
			<a href="${pageContext.request.contextPath}/write.execinfo"
				class=" text-white btn btn-navy">글쓰기</a>
		</p>
	</sec:authorize>
	
	<p class="text-end alert alert-info">운동정보 참고용 게시판입니다.</p>

	<div class="mb-3 mt-3 alert alert-info">
		<label for="search" class="form-label"> SEARCH</label> 
		<input
			type="search" class="form-control" id="search"
			placeholder="검색어를 입력해주세요." name="search">
		<!--                    -->
		<!--                    -->
		<div id="resultArea">
			<table class="table table-striped table-bordered table-hover  my-3">
				<caption>운동정보</caption>
				<thead>
					<tr>
						<th scope="col">번호</th>
						<th scope="col">운동유형</th>
						<th scope="col">권장운동시간</th>
						<th scope="col">운동강도</th>
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

	<script>
		$(function() {
			$("#search")
					.on(
							"keyup",
							function() { // keyup (키보드뗐을때)
								console.log($(this).val().trim());
								let keyword = $(this).val().trim();
								////////////////////////////////////////////////////////
								if (keyword === "") { //빈칸일때
									$("#resultArea  tbody")
											.empty()
											.append(
													"<tr><td colspan='5'>검색어를 입력하세요.</td></tr>");
								} else { // 빈칸이 아니면? 서버요청
									$
											.ajax({
												url : "${pageContext.request.contextPath}/selectSearch2",
												type : "GET", //GET, POST,PUT
												data : {
													search : keyword
												},
												success : function(res) {
													console.log(res);
													$("#resultArea  tbody")
															.empty(); //초기화
													$
															.each(
																	res,
																	function(
																			index,
																			dto) {
																		let row = "<tr>"
																				+ "<td>"
																				+ (res.length - index)
																				+ "</td>"
																				+ "<td><a href='${pageContext.request.contextPath}/detail.execinfo?execid="
																				+ dto.execid
																				+ "'>"
																				+ dto.exectype
																				+ "</a></td>"
																				+ "<td>"
																				+ dto.exectargetmin
																				+ "</td>"
																				+ "<td>"
																				+ dto.intensitylevel
																				+ "</td>"
																				+ "</tr>";
																		$(
																				"#resultArea  tbody")
																				.append(
																						row);
																	});
												}
											});
								}
								////////////////////////////////////////////////////////
							});
		});
	</script>
	
</div> 
<%@include file="../inc/footer.jsp"%>

<!-- [ mbtiBoard - list.jsp ]  -->
