<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="../inc/header.jsp"%>
<div class="container mt-5">
  <h3>리뷰 작성</h3>

  <form method="post" enctype="multipart/form-data" id="reviewUploadForm">
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
            <option value="${b.brandid}">${b.brandname}</option>
          </c:forEach>
        </select>
      </div>

      <!-- 제품 선택 -->
      <div class="col-3">
        <label class="form-check-label" for="foodid">제품명</label>
        <select name="foodid" id="foodid" class="form-select">
          <option value="">제품 선택</option>
          <c:forEach var="f" items="${foodlist}">
            <option value="${f.foodid}"
                    data-brand="${f.brandid}"
                    data-pet="${f.pettypeid}">
              ${f.foodname}
            </option>
          </c:forEach>
        </select>
      </div>

      <!-- 평점 -->
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
        <button type="button" class="btn btn-slateBlue me-2" onclick="submitReview()">등록</button>
        <button type="button" class="btn btn-mint"
                onclick="location.href='${pageContext.request.contextPath}/reviewlist.fn'">
          목록보기
        </button>
      </div>
    </div>

    <!-- 제목 -->
    <div class="row">
      <div class="mb-3 mt-4 col-6">
        <label for="title" class="form-label">TITLE:</label>
        <input type="text" class="form-control" id="title" name="title" placeholder="제목을 입력해 주세요">
      </div>
    </div>

    <!-- 내용 -->
    <div class="mb-3">
      <label for="reviewcomment" class="form-label">Comments:</label>
      <textarea class="form-control" rows="5" id="reviewcomment" name="reviewcomment"
                placeholder="리뷰를 작성해 주세요 (250자 이내)"></textarea>
    </div>

	<!-- 파일 업로드 + 프리뷰 -->
		<div class="row">
		   <div class="mb-3 mt-4 col-3 justify-content-end">
		      <label for="files" class="form-label">후기사진:</label>
		      <input type="file" class="form-control" id="files" name="files" multiple accept="image/*">
		   </div>
		</div>

		<div id="previewBox" class="preview-img-wrap"></div>
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
  </form>
</div>


<!-- 사료 선택 필터 -->
<script>
window.onload = function () {
  const pet = document.getElementById("pettypeid");
  const brand = document.getElementById("brandid");
  const food = document.getElementById("foodid");

  function filter() {
    const p = pet.value;
    const b = brand.value;

    for (let i = 0; i < food.options.length; i++) {
      const opt = food.options[i];

      if (!opt.getAttribute("data-pet")) {
        opt.style.display = "block";
        continue;
      }

      const matchPet = opt.getAttribute("data-pet") === p;
      const matchBrand = opt.getAttribute("data-brand") === b;

      opt.style.display = (matchPet && matchBrand) ? "block" : "none";
    }

    food.value = "";
  }

  pet.onchange = filter;
  brand.onchange = filter;
};
</script>


<!-- 이미지 미리보기 -->
<script>
let uploadFiles = [];   // 전체 이미지 목록 - 배열 꼭 넣어줘야됨
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

  //e.target.value = ""; // 초기화 <-이거때매 이미지가 통으로 자꾸 날아감! 제거.
});
</script>

<!-- 프리뷰 삭제 기능 -->
<script>
document.getElementById("previewBox").addEventListener("click", function(e){
    if (!e.target.classList.contains("preview-del")) return;

    const index = Number(e.target.dataset.index);
    
    // DOM 삭제
    const box = e.target.closest(".preview-img-box");
    box.remove();

    // 배열에서도 제거 -> 업로드 제외)
    uploadFiles[index] = null;
});
</script>




<!-- 업로드 -->
<script>
function submitReview() {

	$.ajax({
		  url: "${pageContext.request.contextPath}/reviewwrite.fn",
		  type: "POST",
		  data: $("#reviewUploadForm").serialize(),
		  success: function (reviewid) {
		    console.log("SUCCESS:", reviewid);
		    uploadImages(reviewid);
		  },
		  error: function (xhr) {
		    console.error("ERROR:", xhr.responseText);
		    alert("에러 발생: " + xhr.status);
		  }
		});
}

function uploadImages(reviewid) {

  const realFiles = uploadFiles.filter(f => f != null);

  // 이미지 없는 경우
  if (realFiles.length === 0) {
    alert("리뷰 등록 성공");
    location.href="${pageContext.request.contextPath}/reviewlist.fn";
    return;
  }

  let uploaded = 0;

  for (let i = 0; i < realFiles.length; i++) {

    let fd = new FormData();
    fd.append("file", realFiles[i]);
    fd.append("reviewid", reviewid);
    fd.append("${_csrf.parameterName}", "${_csrf.token}");

    $.ajax({
    	url: "${pageContext.request.contextPath}/reviewimg/upload",
      type: "POST",
      data: fd,
      contentType: false,
      processData: false,
      success: function (res) {

        uploaded++;

        if (uploaded === realFiles.length) {
          alert("리뷰 등록 성공");
          location.href="${pageContext.request.contextPath}/reviewlist.fn";
        }
      }
    });
  }
}
</script>




<%@ include file="../inc/footer.jsp"%>
