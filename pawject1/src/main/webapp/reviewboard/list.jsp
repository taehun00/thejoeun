<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@include file="../inc/header.jsp"%>

<!-- header -->

<div
	style="width: 80%; margin: 60px auto; background: #fffefb; border-radius: 10px; padding: 30px; box-shadow: 0 3px 10px rgba(0, 0, 0, 0.05);">
		<h2 style="font-weight: 700; color: #4f422a; text-align: center; margin-bottom: 30px;">🐶사료 후기🐱</h2>

	<table
		style="width: 100%; border-collapse: collapse; border: 1px solid #e6c972; font-size: 15px;">
		<caption class="visually-hidden">사료 후기</caption>
		<thead>
			<tr style="background: #fff8d6; text-align: center;">
				<th style="display: none;"></th>
				<th scope="col"
					style="border: 1px solid #e6c972; padding: 10px; width: 40px;">NO.</th>
				<th scope="col"
					style="border: 1px solid #e6c972; padding: 10px; width: 100px;">브랜드</th>
				<th scope="col"
					style="border: 1px solid #e6c972; padding: 10px; width: 180px;">제품명</th>
				<th scope="col"
					style="border: 1px solid #e6c972; padding: 10px; width: 50px;">평점</th>
				<th scope="col"
					style="border: 1px solid #e6c972; padding: 10px; width: 350px;">제목</th>
				<th scope="col"
					style="border: 1px solid #e6c972; padding: 10px; width: 70px">작성자</th>
				<th scope="col"
					style="border: 1px solid #e6c972; padding: 10px; width: 110px;">작성일</th>
			</tr>
		</thead>



		<tbody>
			<c:set var="total" value="${fn:length(list)}" />
			<c:forEach var="r" items="${list}" varStatus="status">
				<!-- 리스트 행 -->
				<tr onclick="toggleContent(${r.reviewid})"
					style="cursor: pointer; text-align: center; background: white;">
					<td style="display: none;">${r.reviewid}</td>
					<td style="border: 1px solid #f1e3a2; padding: 8px;">${total - status.index}</td>
					<!-- count는 0부터라 index 써야됨 리뷰아이디 대신 글번호 출력-->
					<td style="border: 1px solid #f1e3a2; padding: 8px;">${r.brandname}</td>
					<td style="border: 1px solid #f1e3a2; padding: 8px;">${r.foodname}</td>
					<td style="border: 1px solid #f1e3a2; padding: 8px;"><c:choose>
							<c:when test="${r.rating == 5}">★★★★★</c:when>
							<c:when test="${r.rating == 4}">★★★★☆</c:when>
							<c:when test="${r.rating == 3}">★★★☆☆</c:when>
							<c:when test="${r.rating == 2}">★★☆☆☆</c:when>
							<c:otherwise>★☆☆☆☆</c:otherwise>
						</c:choose></td>
					<td style="border: 1px solid #f1e3a2; padding: 8px;">${r.title}</td>
					<td style="border: 1px solid #f1e3a2; padding: 8px;">${r.nickname}</td>
					<td style="border: 1px solid #f1e3a2; padding: 8px;">${r.createdat}</td>
				</tr>



				<!-- 상세 행 -->
				<tr id="content-${r.reviewid}"
					style="display: none; background: #fffef4;">
					<td colspan="8" style="border: 1px solid #f1e3a2; padding: 20px;">

						<table style="width: 100%; border-collapse: collapse;">
							<tr>
								<!-- 이미지 칸 -->
								<td
									style="width: 15%; vertical-align: top; text-align: center; border-right: 1px solid #f1e3a2; padding: 10px;">
									<c:choose>
										<c:when test="${r.brandid eq '1'}">
											<img src="<c:url value='/foodimg/brandid1.png'/>"
												alt="네밥이아니야" style="width: 150px;">
										</c:when>
										<c:when test="${r.brandid == 2}">
											<img src="<c:url value='/foodimg/brandid2.png'/>" alt="명냥스티드"
												style="width: 150px;">
										</c:when>
										<c:when test="${r.brandid == 3}">
											<img src="<c:url value='/foodimg/brandid3.png'/>" alt="모모와밥상"
												style="width: 150px;">
										</c:when>
										<c:when test="${r.brandid == 4}">
											<img src="<c:url value='/foodimg/brandid4.png'/>" alt="밥쌈없다"
												style="width: 150px;">
										</c:when>
										<c:when test="${r.brandid == 5}">
											<img src="<c:url value='/foodimg/brandid5.png'/>" alt="식탁의정체"
												style="width: 150px;">
										</c:when>
										<c:when test="${r.brandid == 6}">
											<img src="<c:url value='/foodimg/brandid6.png'/>"
												alt="츄츄는고양이였다" style="width: 150px;">
										</c:when>
										<c:when test="${r.brandid == 7}">
											<img src="<c:url value='/foodimg/brandid7.png'/>" alt="푸드랑탐탐"
												style="width: 150px;">
										</c:when>

									</c:choose>
								</td>

								<!-- 리뷰 내용 칸 -->
								<td style="width: 85%; padding: 10px 20px; vertical-align: top;">
									<p style="margin-bottom: 10px;">
										<b style="color: #4f422a;">리뷰 내용:</b><br> <span
											style="color: #5b4b2e;">${r.reviewcomment}</span>
									</p> 
									
									<% if(email!=null){ %>
									<!-- 수정 삭제 버튼 -->
									<div class="d-flex justify-content-end gap-3 mt-3">
										<button type="button" class="btn btn-beige"
											onclick="location.href='${pageContext.request.contextPath}/editView.rv?reviewid=${r.reviewid}'">수정</button>
										<button type="button" class="btn btn-beige"
											onclick="location.href='${pageContext.request.contextPath}/deleteView.rv?reviewid=${r.reviewid}'">삭제</button>
									</div>
										<%  } %>

								</td>
							</tr>
						</table>

					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<% if(email!=null){%>

	<div style="text-align: right; margin-top: 20px;">
		<button class="btn-beige" onclick="location.href='writeView.rv'">리뷰
			작성</button>
	</div>
	
	<%  } %>
</div>

<script>
function toggleContent(id) {
  const row = document.getElementById("content-" + id);
  row.style.display = (row.style.display === "table-row") ? "none" : "table-row";
}
</script>

<%@include file="../inc/footer.jsp"%>