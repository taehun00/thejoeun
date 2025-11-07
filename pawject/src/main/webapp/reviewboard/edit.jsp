<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../inc/header.jsp" %>
<!-- header -->


<div class="container mt-5">
	<h3>리뷰 수정</h3>
	<form action="${pageContext.request.contextPath}/edit.rv" method="post">
		<input type="hidden" name="reviewid" value="${dto.reviewid}">
		<input type="hidden" name="userid" value="${dto.userid}">
		<div class="row">

			<!-- 종 선택 -->
			<div class="col-2">
				<label class="form-check-label" for="pettypeid">종</label> <select
					name="pettypeid" id="pettypeid" class="form-select">
					<option value="">종 선택</option>
					<option value="1">고양이</option>
					<option value="2">강아지</option>
				</select>
			</div>

			<!-- 브랜드 선택 -->
			<div class="col-2">
				<label class="form-check-label" for="brandid">브랜드</label> <select
					name="brandid" id="brandid" class="form-select">
					<option value="">브랜드 선택</option>
					<c:forEach var="b" items="${brandlist}">
						<option value="${b.brandid}" data-pet="${b.pettypeid}"
							<c:if test="${dto.brandid == b.brandid}">selected</c:if>>
							${b.brandname}</option>
					</c:forEach>
				</select>
			</div>

			<!-- 제품 선택 -->
			<div class="col-3">
				<label class="form-check-label" for="foodid">제품명</label> <select
					name="foodid" id="foodid" class="form-select">
					<option value="">제품 선택</option>
					<c:forEach var="f" items="${foodlist}">
						<option value="${f.foodid}" data-brand="${f.brandid}"
							data-pet="${f.pettypeid}"
							<c:if test="${dto.foodid == f.foodid}">selected</c:if>>
							${f.foodname}</option>
					</c:forEach>
				</select>
			</div>


			<!-- 평점 선택 -->
			<div class="col-2">
				<label class="form-check-label" for="rating">평점</label> <select
					name="rating" id="rating" class="form-select">
					<option value="5" <c:if test="${dto.rating == 5}">selected</c:if>>★★★★★</option>
					<option value="4" <c:if test="${dto.rating == 4}">selected</c:if>>★★★★☆</option>
					<option value="3" <c:if test="${dto.rating == 3}">selected</c:if>>★★★☆☆</option>
					<option value="2" <c:if test="${dto.rating == 2}">selected</c:if>>★★☆☆☆</option>
					<option value="1" <c:if test="${dto.rating == 1}">selected</c:if>>★☆☆☆☆</option>
				</select>
			</div>

			<!-- 버튼 -->
			<div class="col-3 d-flex justify-content-end align-items-end mt-4">
				<button type="submit" class="btn btn-beige me-2">수정</button>
				<button type="button" class="btn btn-beige"
					onclick="location.href='<%=request.getContextPath()%>/list.rv'">목록보기</button>
			</div>
		</div>
		<div class="row">
			<!-- 제목 입력 -->
			<div class="mb-3 mt-4 col-8">
				<label for="title" class="form-label">TITLE:</label> <input
					type="text" class="form-control" id="title" name="title"
					value="${dto.title}">
			</div>
			<!-- 비번 입력 -->
			<div class="mb-3 mt-4 col-4">
				<label for="password" class="form-label">Password:</label> <input
					type="password" class="form-control" id="password" name="password"
					placeholder="비밀번호를 입력해주세요">
			</div>
		</div>


		<!-- 내용 -->
		<div class="mb-3">
			<label for="reviewcomment" class="form-label">Comments:</label>
			<textarea class="form-control" rows="5" id="reviewcomment"
				name="reviewcomment">${dto.reviewcomment}</textarea>
		</div>
	</form>
</div>

<script>
	document
			.addEventListener(
					"DOMContentLoaded",
					function() {
						const petSelect = document.getElementById("pettypeid");
						const brandSelect = document.getElementById("brandid");
						const foodSelect = document.getElementById("foodid");

						// 종 변경 시
						petSelect.addEventListener("change", function() {
							const petId = this.value;

							// 선택값 초기화
							brandSelect.selectedIndex = 0;
							foodSelect.selectedIndex = 0;

							filterBrandsByPet(petId);
							filterFoodsByPetAndBrand(petId, null);
						});

						// 브랜드 변경 시
						brandSelect.addEventListener("change", function() {
							foodSelect.selectedIndex = 0;
							syncPetTypeWithBrand();
						});

						// 브랜드 필터링
						function filterBrandsByPet(petId) {
							const options = brandSelect.options;
							for (let i = 0; i < options.length; i++) {
								const opt = options[i];
								if (!opt.dataset.pet) {
									opt.style.display = "block";
									continue;
								}
								opt.style.display = (opt.dataset.pet === petId) ? "block"
										: "none";
							}
						}

						// 종 + 브랜드 둘 다 필터링
						function filterFoodsByPetAndBrand(petId, brandId) {
							const options = foodSelect.options;
							for (let i = 0; i < options.length; i++) {
								const opt = options[i];
								if (!opt.dataset.brand) {
									opt.style.display = "block";
									continue;
								}
								const matchesPet = (opt.dataset.pet === petId);
								const matchesBrand = (!brandId || opt.dataset.brand === brandId);
								opt.style.display = (matchesPet && matchesBrand) ? "block"
										: "none";
							}
						}

						// 브랜드 선택값으로 pettypeid 자동 설정
						function syncPetTypeWithBrand() {
							const selectedBrand = brandSelect.options[brandSelect.selectedIndex];
							const petId = selectedBrand.dataset.pet;
							if (petId) {
								petSelect.value = petId;
								filterBrandsByPet(petId);
								filterFoodsByPetAndBrand(petId,
										brandSelect.value);
							}
						}

						// 페이지 로드 시 초기 필터링
						const currentPet = petSelect.value;
						const currentBrand = brandSelect.value;
						filterBrandsByPet(currentPet);
						filterFoodsByPetAndBrand(currentPet, currentBrand);
						syncPetTypeWithBrand(); // <- 초기 로딩 시 pettypeid 자동 설정

					});
</script>



<!-- footer -->
<%@include file="../inc/footer.jsp" %>
