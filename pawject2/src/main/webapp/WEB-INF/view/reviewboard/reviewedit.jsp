<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@include file="../inc/header.jsp"%>
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/review.css">

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
		
		if(result=="사료 수정 실패"){alert(result); }
		}		
	);
	
	</script>

		<div class="container mt-5">
		  <h3>리뷰 수정</h3>
		
		  	  <form action="${pageContext.request.contextPath}/reviewedit.fn"  
		  	  method="post"   enctype="multipart/form-data"> 
		  	  <input type="hidden" name="reviewid" value="${rdto.reviewid}">

		    <div class="row">
		      
				<!-- 종 선택 -->
				<div class="col-2">
				  <label class="form-check-label" for="pettypeid">종</label>
				  <select name="pettypeid" id="pettypeid" class="form-select">
					<option value="1" <c:if test="${rdto.pettypeid == 1}">selected</c:if>>고양이</option>   				    	
				    <option value="2" <c:if test="${rdto.pettypeid == 2}">selected</c:if>>강아지</option>
				  </select>
				</div>
				
				<!-- 브랜드 선택 -->
				<div class="col-2">
				  <label class="form-check-label" for="brandid">브랜드</label>
				  <select name="brandid" id="brandid" class="form-select">
				    <option value="">브랜드 선택</option>
				    <c:forEach var="b" items="${brandlist}">
				      <option value="${b.brandid}"
				      	<c:if test="${b.brandid eq rdto.brandid}">selected</c:if>>
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
				        data-pet="${f.pettypeid}" 
				        	<c:if test="${f.foodid eq rdto.foodid}">selected</c:if>>
				        ${f.foodname}
				      </option>
				    </c:forEach>
				  </select>
				</div>
		
		      <!-- 평점 선택 -->
		      <div class="col-2">
		        <label class="form-check-label" for="rating">평점</label>
		        <select name="rating" id="rating" class="form-select">
		          <option value="5" <c:if test="${rdto.rating==5}">selected</c:if> >★★★★★</option>
		          <option value="4" <c:if test="${rdto.rating==4}">selected</c:if> >★★★★☆</option>
		          <option value="3" <c:if test="${rdto.rating==3}">selected</c:if> >★★★☆☆</option>
		          <option value="2" <c:if test="${rdto.rating==2}">selected</c:if> >★★☆☆☆</option>
		          <option value="1" <c:if test="${rdto.rating==1}">selected</c:if> >★☆☆☆☆</option>
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
		      <input type="text" class="form-control" id="title" name="title" value="${rdto.title}">
		    </div>
		</div>
		

		    <div class="mb-3">
		      <label for="reviewcontent" class="form-label">Comments:</label>
		      <textarea class="form-control" rows="5" id="reviewcomment" name="reviewcomment"
		        placeholder="리뷰를 작성해 주세요 (250자 이내)">${rdto.reviewcomment}</textarea>
		    </div>
		    
			<div class="row">
			    
			    <!-- 새로 첨부할 사진 -->
			    <div class="mb-3 mt-4 col-3">
			        <label for="files" class="form-label">후기 사진:</label>
			        <input type="file" class="form-control" id="files" name="files" multiple>
			    </div>  
			    
			    <div class="mb-3 mt-4 col-9">
			        <label class="form-label"></label>
			        
			        <div class="review-img-wrap">
			            <c:forEach var="img" items="${imglist}">
			                <div class="review-img-box">
			                    <img src="${pageContext.request.contextPath}/upload/${img.reviewimgname}" 
			                         alt="리뷰이미지" 
			                         class="review-img">
			                </div>
			            </c:forEach>
			        </div>
			    </div>
			</div>
		  </form>
		</div>


<script>
window.onload = function() {

  var pet = document.getElementById("pettypeid");
  var brand = document.getElementById("brandid");
  var food = document.getElementById("foodid");

  // 기존 저장된 값 불러오기
  var savedFoodId = "${rdto.foodid}";

  function filter() {
    var p = pet.value;
    var b = brand.value;

    for (var i = 0; i < food.options.length; i++) {
      var opt = food.options[i];

      if (!opt.getAttribute("data-pet")) {
        opt.style.display = "block";
        continue;
      }

      var matchPet = (opt.getAttribute("data-pet") === p);
      var matchBrand = (opt.getAttribute("data-brand") === b);

      if (matchPet && matchBrand) {
        opt.style.display = "block";
      } else {
        opt.style.display = "none";
      }
    }
  }

  // 필터 먼저 적용
  filter();

  if (savedFoodId) {
    food.value = savedFoodId;
  }

  pet.onchange = function() {
    filter();
    food.value = ""; 
  };

  brand.onchange = function() {
    filter();
    food.value = ""; 
  };

};
</script>
<%@include file="../inc/footer.jsp"%>

	