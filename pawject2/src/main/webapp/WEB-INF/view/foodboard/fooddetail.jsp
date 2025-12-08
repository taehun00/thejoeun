<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="../inc/header.jsp"%>
<c:if test="${not empty success}">
	<script>
	        alert("${success}");
	    </script>
</c:if>



<div class="container card my-5 p-4">

	<h3 class="card-header mb-4">사료 상세</h3>

	<div class="row mt-3">

		<!-- 이미지 -->
		<div class="col-3 d-flex align-items-center justify-content-center">
			<c:choose>
				<c:when test="${not empty fdto.foodimg}">
				<img src="${pageContext.request.contextPath}/foodimg/${fdto.foodimg}"
						 alt="사료 이미지" style="width:100%">
				</c:when>
				 <c:otherwise>
				     <img src="${pageContext.request.contextPath}/foodimg/brand0${fdto.brandid}.png"
				          alt="브랜드 로고" style="width:100%">
				</c:otherwise>
		</c:choose>				
				
		</div>
		
		
		<!-- 기본 정보 -->
		<div class="col-9">

			<div class="row mb-3">
				<div class="col-4">
					<label class="form-label">브랜드</label> <input type="text"
						class="form-control" readonly value="${fdto.brandname}">
				</div>

				<div class="col-8">
					<label class="form-label">사료명</label> <input type="text"
						class="form-control" readonly value="${fdto.foodname}">
				</div>
			</div>

			<div class="row mb-3">
				<div class="col-4">
					<label class="form-label">종</label> <input type="text"
						class="form-control" readonly
						value="${fdto.pettypeid == 1 ? '고양이' : fdto.pettypeid == 2 ? '강아지' : '기타'}">
				</div>

				<div class="col-4">
					<label class="form-label">사료타입</label> <input type="text"
						class="form-control" readonly value="${fdto.foodtype}">
				</div>

				<div class="col-4">
					<label class="form-label">그레인프리</label> <input type="text"
						class="form-control" readonly value="${fdto.isgrainfree}">
				</div>
			</div>

			<div class="row mb-3">
				<div class="col-4">
					<label class="form-label">분류</label> <input type="text"
						class="form-control" readonly value="${fdto.category}">
				</div>

				<div class="col-4">
					<label class="form-label">연령</label> <input type="text"
						class="form-control" readonly value="${fdto.petagegroup}">
				</div>

				<div class="col-4">
					<label class="form-label">칼로리</label> <input type="text"
						class="form-control" readonly value="${fdto.calorie}">
				</div>
			</div>

			<div class="row mb-3">
				<div class="col-6">
					<label class="form-label">주재료</label> <input type="text"
						class="form-control" readonly value="${fdto.mainingredient}">
				</div>

				<div class="col-6">
					<label class="form-label">부재료</label> <input type="text"
						class="form-control" readonly value="${fdto.subingredient}">
				</div>
			</div>

		</div>
	</div>

	<div class="mt-4">
		<label class="form-label">사료 설명</label>
		<textarea class="form-control" readonly>${fdto.description}</textarea>
	</div>

	<!-- 영양성분 -->
	<h5 class="mt-5 mb-3">영양성분 정보</h5>

	<c:forEach var="n" items="${nutrientList}" varStatus="st">
		<div class="row mb-3">

			<div class="col-4">
				<label class="form-label">영양소 ${st.count}</label> <input type="text"
					class="form-control" readonly value="${n.nutrientname}">
			</div>

			<div class="col-4">
				<label class="form-label">양</label> <input type="text"
					class="form-control" readonly value="${n.amount}">
			</div>

			<div class="col-2">
				<label class="form-label">단위</label> <input type="text"
					class="form-control" readonly value="${n.unit}">
			</div>

		</div>
	</c:forEach>

	<!-- 버튼 -->
	<div class="text-end mt-4">

		<a href="${pageContext.request.contextPath}/foodlist.fn"
			class="btn btn-slateBlue" style="margin-right: 6px;"> 목록보기 </a> <a
			href="${pageContext.request.contextPath}/foodedit.fn?foodid=${fdto.foodid}"
			class="btn btn-mint"> 수정 </a> <a
			href="${pageContext.request.contextPath}/fooddelete.fn?foodid=${fdto.foodid}"
			onclick="return confirm('정말로 삭제하시겠습니까?');" class="btn btn-burgundy">
			삭제 </a>


	</div>
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
</div>

<%@include file="../inc/footer.jsp"%>
