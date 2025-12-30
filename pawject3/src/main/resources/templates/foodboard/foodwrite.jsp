<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="../inc/header.jsp"%>

<c:if test="${not empty success}">
	<script>
	        alert("${success}");
	    </script>
</c:if>

<script>
	$(function(){
		let result = '${success}';
		console.log(result);
		console.log(result.length);
		
		if(result=="사료 등록 실패"){alert(result); }
		}		
	);
	
	</script>

<div class="container card my-5 p-4">

	<h3 class="card-header">신규 사료 등록</h3>

	<form action="${pageContext.request.contextPath}/foodwrite.fn"
		method="post"
		enctype="multipart/form-data">

		<div class="row mt-4">

			<div class="col-4 mb-3">
				<label class="form-label">브랜드 :</label> <select name="brandid"
					class="form-control">
					<option value="">-- 선택 --</option>
					<c:forEach var="b" items="${brandlist}">
						<option value="${b.brandid}">${b.brandname}</option>
					</c:forEach>
				</select>
			</div>

			<div class="col-4 mb-3">
				<label class="form-label">펫타입 :</label> <select name="pettypeid"
					class="form-control">
					<option value="">-- 선택 --</option>
					<option value="1">고양이</option>
					<option value="2">강아지</option>
				</select>
			</div>

			<div class="col-4 mb-3">
				<label class="form-label">사료 타입 :</label> <select name="foodtype"
					class="form-control">
					<option value="">-- 선택 --</option>
					<option value="건식">건식</option>
					<option value="습식">습식</option>
				</select>
			</div>

		</div>

		<!-- 텍스트 입력들 -->
		<div class="row">
			<div class="col-12 mb-3">
				<label class="form-label">사료 이름 :</label> <input type="text"
					class="form-control" name="foodname">
			</div>

			<div class="col-12 mb-3">
				<label class="form-label">사료 설명 :</label>
				<textarea class="form-control" name="description" rows="3"></textarea>
			</div>
		</div>

		<!-- 주재료 / 부재료 -->
		<div class="row">
			<div class="col-4 mb-3">
				<label class="form-label">주재료 :</label> <input type="text"
					class="form-control" name="mainingredient">
			</div>

			<div class="col-4 mb-3">
				<label class="form-label">부재료 :</label> <input type="text"
					class="form-control" name="subingredient">
			</div>
		</div>

		<!-- 카테고리 / 연령 -->
		<div class="row">

			<div class="col-4 mb-3">
				<label class="form-label">분류 :</label> <select name="category"
					class="form-control">
					<option value="">-- 선택 --</option>
					<option value="일반">일반</option>
					<option value="처방식">처방식</option>
					<option value="기능식">기능식</option>
				</select>
			</div>

			<div class="col-4 mb-3">
				<label class="form-label">연령 :</label> <select name="petagegroup"
					class="form-control">
					<option value="">-- 선택 --</option>
					<option value="어덜트">어덜트</option>
					<option value="시니어">시니어</option>
					<option value="키튼">키튼</option>
					<option value="퍼피">퍼피</option>
				</select>
			</div>

			<div class="col-4 mb-3">
				<label class="form-label">그레인프리 :</label> <select name="isgrainfree"
					class="form-control">
					<option value="">-- 선택 --</option>
					<option value="Y">Y</option>
					<option value="N">N</option>
				</select>
			</div>

		</div>

		<!-- 칼로리 -->
		<div class="row">
			<div class="col-4 mb-3">
				<label class="form-label">칼로리 :</label> <input type="text"
					class="form-control" name="calorie">
			</div>
		</div>

		<div id="nutri-box">

			<c:forEach var="i" begin="1" end="4">
				<div class="row nutri-row align-items-center mb-2">

					<div class="col-4">
						<label class="form-label">영양소 ${i} :</label> <select
							name="nutrientid" class="form-control"
							onchange="updateUnit(this)">
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
							<input type="text" name="amount" class="form-control">
							<span class="input-group-text unitLabel">
								% </span>
						</div>
					</div>

				</div>
			</c:forEach>

		</div>



		<!-- 이미지 URL -->
		<div class="row">
		   <div class="mb-3 mt-4 col-3 justify-content-end">
		      <label for="file" class="form-label">제품 이미지:</label>
		      <input type="file" class="form-control" id="file" name="file">
		   </div>
		</div>

		<div class="mb-3 text-end">
			<button type="submit" class="btn btn-mint">사료 등록</button>
			<a href="${pageContext.request.contextPath}/foodlist.fn"
				class="btn btn-slateBlue"> 목록보기 </a>
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