<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@include file="../inc/header.jsp"%>
	<script>
	$(function(){
		let result = '${success}';
		console.log(result);
		console.log(result.length);
		
		if(result=="리뷰 등록 실패"){alert(result); }
		else if(result.length != 0 ){alert(result); }
		}		
	);
	
	</script>

		<div class="container mt-5">
		  <h3>리뷰 작성</h3>
		
		  	  <form action="${pageContext.request.contextPath}/reviewwrite.fn"  
		  	  method="post"   enctype="multipart/form-data"> 
		  	  <input type="hidden" name="nickname" value="${nickname}">

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
				      <option value="${b.brandid}">
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
		        <button type="submit" class="btn btn-slateBlue me-2">등록</button>
		        
				<button type="button" class="btn btn-mint"
        			onclick="location.href='${pageContext.request.contextPath}/reviewlist.fn'">목록보기</button>
		      </div>	
		    </div>
		 <div class="row">	
	
		    <div class="mb-3 mt-4 col-6">
		      <label for="title" class="form-label">TITLE:</label>
		      <input type="text" class="form-control" id="title" name="title" placeholder="제목을 입력해 주세요">
		    </div>
		    <div class="mb-3 mt-4 col-3 justify-content-end">
		      <label for="file" class="form-label">후기사진:</label>
		      <input type="file" class="form-control" id="file" name="file">
		    </div>
		

		    <div class="mb-3">
		      <label for="reviewcontent" class="form-label">Comments:</label>
		      <textarea class="form-control" rows="5" id="reviewcomment" name="reviewcomment"
		        placeholder="리뷰를 작성해 주세요 (250자 이내)"></textarea>
		    </div>
		    
		  </form>
		</div>


	<script>
	window.onload = function() {
	
	  var pet = document.getElementById("pettypeid");
	  var brand = document.getElementById("brandid");
	  var food = document.getElementById("foodid");
	
	  function filter() {
	    var p = pet.value;
	    var b = brand.value;
	
	    for (var i = 0; i < food.options.length; i++) {
	      var opt = food.options[i];
	
	      // 안내문 (data attribute 없음)
	      if (!opt.getAttribute("data-pet")) {
	        opt.style.display = "block";
	        continue;
	      }
	
	      var matchPet = opt.getAttribute("data-pet") === p;
	      var matchBrand = opt.getAttribute("data-brand") === b;
	
	      if (matchPet && matchBrand) {
	        opt.style.display = "block";
	      } else {
	        opt.style.display = "none";
	      }
	    }
	
	    // 선택 초기화
	    food.value = "";
	  }
	
	  pet.onchange = filter;
	  brand.onchange = filter;
	
	};
	</script>
<%@include file="../inc/footer.jsp"%>

