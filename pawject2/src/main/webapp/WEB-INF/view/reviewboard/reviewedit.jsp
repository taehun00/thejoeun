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
		
			<form id="reviewUploadForm"
			      action="${pageContext.request.contextPath}/reviewedit.fn"  
			      method="post"
			      enctype="multipart/form-data">
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
				<button type="button" class="btn btn-slateBlue me-2" onclick="submitReview()">등록</button>
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
			        <input type="file" class="form-control" id="files" name="files" multiple accept="image/*">
			    </div>  
			    
			    <div class="mb-3 mt-4 col-9">
			        <label class="form-label"></label>
			        
			       <div id="previewBox" class="preview-img-wrap">
					  <c:forEach var="img" items="${imglist}">
					    <div class="preview-img-box" data-imgid="${img.reviewimgid}">
					      <img src="${pageContext.request.contextPath}/upload/${img.reviewimgname}" class="preview-img">
							<button type="button" class="preview-del old-del" data-imgid="${img.reviewimgid}">
							    X
							</button>
					    </div>
					  </c:forEach>
					
					</div>
			        
			    </div>
			</div>
			
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		  </form>
		</div>




<script>

/* 토큰 여기서 미리 넣어두기 - 중간에 넣었더니 자꾸 403 뜸 */
const csrfHeader = "${_csrf.headerName}";
const csrfToken = "${_csrf.token}";

$(document).ajaxSend(function(e, xhr, options) {
    xhr.setRequestHeader(csrfHeader, csrfToken);
});

/* 사료 선택 */
window.onload = function() {

  var pet = document.getElementById("pettypeid");
  var brand = document.getElementById("brandid");
  var food = document.getElementById("foodid");

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

      opt.style.display = (matchPet && matchBrand) ? "block" : "none";
    }
  }

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


/* 이미지 프리뷰 */
let uploadFiles = [];
let previewIndex = 0;

document.getElementById("files").addEventListener("change", function (e) {

  const files = [...e.target.files];
  const previewBox = document.getElementById("previewBox");

  for (let i = 0; i < files.length; i++) {

    let file = files[i];
    uploadFiles.push(file);

    let reader = new FileReader();
    reader.onload = function (ev) {

      const div = document.createElement("div");
      div.className = "preview-img-box";

      div.innerHTML = 
        '<img src="' + ev.target.result + '" class="preview-img"/>' +
        '<button type="button" class="preview-del" data-index="' + previewIndex + '">X</button>';

      previewBox.appendChild(div);
      previewIndex++;
    };

    reader.readAsDataURL(file);
  }

});


/* 프리뷰 삭제 */
document.getElementById("previewBox").addEventListener("click", function(e){
    
    if (!e.target.classList.contains("preview-del")) return;

    const box = e.target.closest(".preview-img-box");

    // 기존 이미지 삭제
    if(e.target.classList.contains("old-del")){
        
        const imgid = e.target.dataset.imgid;

        $.ajax({
            url: "${pageContext.request.contextPath}/reviewimg/delete",
            type: "POST",
            data: { reviewimgid: imgid },
            success: function(){ box.remove(); }
        });

        return;
    }

    // 새 이미지 삭제
    const index = Number(e.target.dataset.index);
    uploadFiles[index] = null;
    box.remove();
});


/* 수정 업로드 기능 */
function submitReview() {

  $.ajax({
    url: "${pageContext.request.contextPath}/reviewedit.fn",
    type: "POST",
    data: $("#reviewUploadForm").serialize(),
    success: function (reviewid) {
      uploadImages(reviewid);
    }
  });
}


/* 새 이미지 업로드 - 기존과 거의 동일 */
function uploadImages(reviewid) {

  const realFiles = uploadFiles.filter(f => f != null);

  if (realFiles.length === 0) {
    alert("수정 성공");
    location.href = "${pageContext.request.contextPath}/reviewlist.fn";
    return;
  }

  let uploaded = 0;

  realFiles.forEach(file => {

      let fd = new FormData();
      fd.append("file", file);
      fd.append("reviewid", reviewid);

      $.ajax({
        url: "${pageContext.request.contextPath}/reviewimg/upload",
        type: "POST",
        data: fd,
        contentType: false,
        processData: false,
        success: function () {
          uploaded++;
          if (uploaded === realFiles.length) {
            alert("수정 성공");
            location.href="${pageContext.request.contextPath}/reviewlist.fn";
          }
        }
      });

  });

}
</script>




<%@include file="../inc/footer.jsp"%>

	