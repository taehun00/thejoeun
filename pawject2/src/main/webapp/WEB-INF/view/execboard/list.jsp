<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="../inc/header.jsp"%>
<script>
	$(function(){
		let result = '${success}';
		console.log(result);
		if(resut == '글쓰기실패'){
			alert(result); history.go(-1);
		}else if( result == '사용자 아이디를 확인해주세요.' ){
			alert(result); history.go(-1);
		}else if(result.length !=0){
			alert(result);
		}
	});
</script>
<div class="container card  my-5 p-4">
	<h3 class="card-header">운동챌린지게시판</h3>
	<div>${list}</div>
	<div>${paging}</div>
	<table class="table table-striped table-bordered table-hover">
		<caption>운동챌린지게시판</caption>
		<thead>
			<tr>
				<th scope="col">번호</th>
				<th scope="col">운동아이디</th>
				<th scope="col">사용자아이디</th>
				<th scope="col">제목</th>
				<th scope="col">내용</th>
				<th scope="col">이미지</th>
				<th scope="col">조회수</th>
				<th scope="col">작성일</th>
				<th scope="col">수정일</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="dto" items="${list}" varStatus="status">
				<tr>
					<%-- <td>${dto}</td> --%>
					<td>${paging.listtotal -((paging.current-1) *10) -status.index}</td>
					<%-- 	<td>${list.size()-status.index}</td> --%>
					<!-- 3-0=3  3-1=2  3=2=1  -->
					<td><a
						href="${pageContext.request.contextPath}/detail.execboard?id=${dto.postid}">
							${dto.etitle} </a></td>
					<td>${dto.userId}</td>
					<td>${dto.createdAt}</td>
					<td>${dto.ehit}</td>
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
<%-- 	<sec:authorize access="isAuthenticated()">	
		<p class="text-end">
			<a href="${pageContext.request.contextPath}/write.execboard"
				class="btn btn-primary">글쓰기</a>
		</p>
	</sec:authorize>--%>
 	<p class="text-end alert alert-primary">로그인을 하면 글쓰기가능합니다.</p>

	<div class="mb-3 mt-3 alert alert-primary">
		<label for="search" class="form-label"> SEARCH</label> <input
			type="search" class="form-control" id="search"
			placeholder="검색어를 입력해주세요" name="search">
		<!--                    -->
		<!--                    -->
		<div id="resultArea">
			<table class="table table-striped table-bordered table-hover  my-3">
				<caption>mbti</caption>
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

	<script>
		$(function(){
			$("search").on()
		});
	</script>

</div>

<%@include file="../inc/footer.jsp"%>

<!-- [ mbtiBoard - list.jsp ]  -->