<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../inc/header.jsp" %>
<!-- header -->



		<div class="container mt-5">
		  <h3>리뷰 작성</h3>
		
		  	  <form action="${pageContext.request.contextPath}/write.rv"  method="post"> 
			 <input type="hidden"   name="email"  value="<%= session.getAttribute("email") %>">
		    <div class="row">
		      
				<!-- 종 선택 -->
				<div class="col-2">
				  <label class="form-check-label" for="pettypeid">종</label>
				  <select name="pettypeid" id="pettypeid" class="form-select">
				    <option value="">종 선택</option>
				    <option value="1">고양이</option>
				    <option value="2">강아지</option>
				  </select>
				</div>
				
				<!-- 브랜드 선택 -->
				<div class="col-2">
				  <label class="form-check-label" for="brandid">브랜드</label>
				  <select name="brandid" id="brandid" class="form-select">
				    <option value="">브랜드 선택</option>
				    <c:forEach var="b" items="${brandlist}">
				      <option value="${b.brandid}" data-pet="${b.pettypeid}">
				        ${b.brandname}
				      </option>
				    </c:forEach>
				  </select>
				</div>
				
				<!-- 제품 선택 -->
				<div class="col-3">
				  <label class="form-check-label" for="foodid">제품명</label>
				  <select name="foodid" id="foodid" class="form-select">
				    <option value="">제품 선택</option>
				    <c:forEach var="f" items="${foodlist}">
				      <option 
				        value="${f.foodid}" 
				        data-brand="${f.brandid}" 
				        data-pet="${f.pettypeid}">
				        ${f.foodname}
				      </option>
				    </c:forEach>
				  </select>
				</div>
		
		      <!-- 평점 선택 -->
		      <div class="col-2">
		        <label class="form-check-label" for="rating">평점</label>
		        <select name="rating" id="rating" class="form-select">
		          <option value="5">★★★★★</option>
		          <option value="4">★★★★☆</option>
		          <option value="3">★★★☆☆</option>
		          <option value="2">★★☆☆☆</option>
		          <option value="1">★☆☆☆☆</option>
		        </select>
		      </div>
		
		      <!-- 버튼 -->
		      <div class="col-3 d-flex justify-content-end align-items-end mt-4">	
		        <button type="submit" class="btn btn-beige me-2">등록</button>
		        
				<button type="button" class="btn btn-beige"
        			onclick="location.href='${pageContext.request.contextPath}/list.rv'">목록보기</button>
		      </div>	
		    </div>
		 <div class="row">
		    <!-- 제목 입력 -->
		    <div class="mb-3 mt-4 col-8">
		      <label for="title" class="form-label">TITLE:</label>
		      <input type="text" class="form-control" id="title" name="title" placeholder="제목을 입력해 주세요">
		    </div>
			 <!-- 비번 입력 -->
			<div class="mb-3 mt-4 col-4">
		      <label for="password" class="form-label">Password:</label>
		      <input type="password" class="form-control" id="password" name="password" placeholder="글 비밀번호를 입력해 주세요">
		    </div>
		</div>
		
		
		    <!-- 댓글 입력 -->
		    <div class="mb-3">
		      <label for="reviewcomment" class="form-label">Comments:</label>
		      <textarea class="form-control" rows="5" id="reviewcomment" name="reviewcomment"
		        placeholder="리뷰를 작성해 주세요 (250자 이내)"></textarea>
		    </div>
		  </form>
		</div>


		<script>
		document.addEventListener("DOMContentLoaded", function() {
		  const petSelect = document.getElementById("pettypeid");
		  const brandSelect = document.getElementById("brandid");
		  const foodSelect = document.getElementById("foodid");
		
		  // 종 변경 시
		  petSelect.addEventListener("change", function() {
		    const petId = this.value;
		    filterBrandsByPet(petId);
		    filterFoodsByPetAndBrand(petId, null);
		  });
		
		  // 브랜드 변경 시
		  brandSelect.addEventListener("change", function() {
		    const petId = petSelect.value;
		    const brandId = this.value;
		    filterFoodsByPetAndBrand(petId, brandId);
		  });
		
		  // 브랜드 필터링
		  function filterBrandsByPet(petId) {
		    const options = brandSelect.options;
		    for (let i = 0; i < options.length; i++) {
		      const opt = options[i];
		      if (!opt.dataset.pet) {
		        opt.style.display = "block"; // 안내문 유지
		        continue;
		      }
		      opt.style.display = (opt.dataset.pet === petId) ? "block" : "none";
		    }
		    brandSelect.value = "";
		  }
		
		  // 종 + 브랜드 둘 다 필터링
		  function filterFoodsByPetAndBrand(petId, brandId) {
		    const options = foodSelect.options;
		    for (let i = 0; i < options.length; i++) {
		      const opt = options[i];
		      if (!opt.dataset.brand) {
		        opt.style.display = "block"; // 안내문 유지
		        continue;
		      }
		
		      const matchesPet = (opt.dataset.pet === petId);
		      const matchesBrand = (!brandId || opt.dataset.brand === brandId);
		
		      // 둘 다 일치할 때만 표시
		      opt.style.display = (matchesPet && matchesBrand) ? "block" : "none";
		    }
		    foodSelect.value = "";
		  }
		
		  // 초기화
		  filterBrandsByPet("");
		  filterFoodsByPetAndBrand("", null);
		});
		</script>
<!-- footer -->
<%@include file="../inc/footer.jsp" %>
