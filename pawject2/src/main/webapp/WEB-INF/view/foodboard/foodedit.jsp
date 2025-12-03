<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@include file="../inc/header.jsp"%>

<script>
	$(function(){
		if(result=="사료 수정 실패"){alert(result); }
		}		
	);
	
	</script>

<div class="container card my-5 p-4">

	<h3 class="card-header">사료 정보 수정</h3>

	<form action="${pageContext.request.contextPath}/foodedit.fn"
		method="post">

		<div class="row mt-4">

			<input type="hidden" name="foodid" value="${fdto.foodid}">

			<div class="col-4 mb-3">
				<label class="form-label">브랜드 :</label> <select name="brandid"
					class="form-control">
					<c:forEach var="b" items="${brandlist}">
						<option value="${b.brandid}"
							<c:if test="${b.brandid == fdto.brandid}">selected</c:if>>
							${b.brandname}</option>
					</c:forEach>
				</select>
			</div>

			<div class="col-4 mb-3">
				<label class="form-label">펫타입 :</label> <select name="pettypeid"
					class="form-control">
					<option value="1"
						<c:if test="${fdto.pettypeid == 1}">selected</c:if>>고양이</option>
					<option value="2"
						<c:if test="${fdto.pettypeid == 2}">selected</c:if>>강아지</option>
				</select>
			</div>

			<div class="col-4 mb-3">
				<label class="form-label">사료 타입 :</label> <select name="foodtype"
					class="form-control">
					<option value="건식"
						<c:if test="${fdto.foodtype == '건식'}">selected</c:if>>건식</option>
					<option value="습식"
						<c:if test="${fdto.foodtype == '습식'}">selected</c:if>>습식</option>
				</select>
			</div>

		</div>

		<!-- 이름 + 설명 -->
		<div class="row">
			<div class="col-12 mb-3">
				<label class="form-label">사료 이름 :</label> <input type="text"
					class="form-control" name="foodname" value="${fdto.foodname}">
			</div>

			<div class="col-12 mb-3">
				<label class="form-label">사료 설명 :</label>
				<textarea class="form-control" name="description" rows="3">${fdto.description}</textarea>
			</div>
		</div>

		<!-- 주재료 / 부재료 -->
		<div class="row">
			<div class="col-4 mb-3">
				<label class="form-label">주재료 :</label> <input type="text"
					class="form-control" name="mainingredient"
					value="${fdto.mainingredient}">
			</div>

			<div class="col-4 mb-3">
				<label class="form-label">부재료 :</label> <input type="text"
					class="form-control" name="subingredient"
					value="${fdto.subingredient}">
			</div>
		</div>

		<!-- 카테고리 / 연령 -->
		<div class="row">

			<div class="col-4 mb-3">
				<label class="form-label">분류 :</label> <select name="category"
					class="form-control">
					<option value="일반"
						<c:if test="${fdto.category == '일반'}">selected</c:if>>일반</option>
					<option value="처방식"
						<c:if test="${fdto.category == '처방식'}">selected</c:if>>처방식</option>
					<option value="기능식"
						<c:if test="${fdto.category == '기능식'}">selected</c:if>>기능식</option>
				</select>
			</div>

			<div class="col-4 mb-3">
				<label class="form-label">연령 :</label> <select name="petagegroup"
					class="form-control">
					<option value="어덜트"
						<c:if test="${fdto.petagegroup == '어덜트'}">selected</c:if>>어덜트</option>
					<option value="시니어"
						<c:if test="${fdto.petagegroup == '시니어'}">selected</c:if>>시니어</option>
					<option value="키튼"
						<c:if test="${fdto.petagegroup == '키튼'}">selected</c:if>>키튼</option>
					<option value="퍼피"
						<c:if test="${fdto.petagegroup == '퍼피'}">selected</c:if>>퍼피</option>
				</select>
			</div>

			<div class="col-4 mb-3">
				<label class="form-label">그레인프리 :</label> <select name="isgrainfree"
					class="form-control">
					<option value="Y"
						<c:if test="${fdto.isgrainfree=='Y'}">selected</c:if>>Y</option>
					<option value="N"
						<c:if test="${fdto.isgrainfree=='N'}">selected</c:if>>N</option>
				</select>
			</div>

		</div>

		<!-- 칼로리 -->
		<div class="row">
			<div class="col-4 mb-3">
				<label class="form-label">칼로리 :</label> <input type="text"
					class="form-control" name="calorie" value="${fdto.calorie}">
			</div>
		</div>

		<!-- 영양소 입력 박스 -->
		<div id="nutri-box">

			<c:forEach var="nutri" items="${nutriList}" varStatus="st">
				<div class="row nutri-row mb-2">

					<div class="col-4">
						<label class="form-label">영양소 ${st.index+1} :</label> <select
							name="nutrientid" class="form-control"
							onchange="updateUnit(this)">
							<c:forEach var="n" items="${nutrientlist}">
								<option value="${n.nutrientid}" data-unit="${n.unit}"
									<c:if test="${n.nutrientid == nutri.nutrientid}">selected</c:if>>
									${n.nutrientname}</option>
							</c:forEach>
						</select>
					</div>

					<div class="col-4">
						<label class="form-label">함량 :</label>
						<div class="input-group">
							<input type="text" name="amount" class="form-control"
								value="${nutri.amount}"> <span
								class="input-group-text unitLabel"> ${nutri.unit} </span>
						</div>
					</div>

				</div>
			</c:forEach>

			<!-- 부족한 칸 채우기 -->
			<c:forEach var="i" begin="1" end="${4 - fn:length(nutriList)}">
				<div class="row nutri-row mb-2">

					<div class="col-4">
						<label class="form-label">영양소 :</label> <select name="nutrientid"
							class="form-control" onchange="updateUnit(this)">
							<option value="">-- 선택 --</option>
							<c:forEach var="n" items="${nutrientlist}">
								<option value="${n.nutrientid}" data-unit="${n.unit}">
									${n.nutrientname}</option>
							</c:forEach>
						</select>
					</div>

					<div class="col-4">
						<label class="form-label">함량 :</label>
						<div class="input-group">
							<input type="text" name="amount" class="form-control"> <span
								class="input-group-text unitLabel"> % </span>
						</div>
					</div>

				</div>
			</c:forEach>

		</div>

		<!-- 이미지 URL -->
		<div class="mb-3 mt-3">
			<label class="form-label">사료 이미지 URL :</label> <input type="text"
				class="form-control" name="foodimg" value="${fdto.foodimg}">
		</div>

		<div class="mb-3 text-end">
			<button type="submit" class="btn btn-mint">사료 정보 수정</button>

			<a href="javascript:history.back()" class="btn btn-slateBlue">
				뒤로가기 </a> <a href="${pageContext.request.contextPath}/foodlist.fn"
				class="btn btn-navy"> 목록보기</a>
		</div>
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
	</form>
</div>

<script>
	// 단위 자동표시
		function updateUnit(selectElem) {
		    const unit = selectElem.selectedOptions[0].dataset.unit;
		    selectElem.closest('.nutri-row').querySelector('.unitLabel').textContent = unit;
		}
	</script>

<%@include file="../inc/footer.jsp"%>