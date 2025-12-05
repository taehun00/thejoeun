<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="../inc/header.jsp"%>
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/review.css">

	<script>
	$(function(){
		let result = '${success}';
		console.log(result);
		console.log(result.length);
		
		if(result=="리뷰 등록 성공"){alert(result); }
		else if(result.length != 0 ){alert(result); }
		}		
	);
	
	</script>


<div class="review-container">

    <h2 class="review-title">🐶사료 후기🐱</h2>

   <table class="review-table table table-bordered table-hover align-middle text-center">
        <input type="hidden" id="currentPage" value="${reviewpaging.pstartno}">
        <caption class="visually-hidden">사료 후기</caption>
        
       <div class="row my-3">
		    <!-- 가운데 정렬 영역 -->
		    <div class="col d-flex justify-content-center gap-2">
		        <select id="searchType" class="form-select" style="width:150px;">
		            <option value="all">전체</option>
		            <option value="pettypeid">강아지/고양이</option>
		            <option value="brandname">브랜드명</option>
		            <option value="foodname">사료명</option>
		        </select>
		
		        <input type="text" id="searchKeyword" placeholder="검색어 입력" class="form-control" style="width:300px;">
		        <button class="btn btn-mint" onclick="searchReview()">검색</button>
		        <button class="btn btn-slateBlue"
		    	   id="searchlistBtn"
				   style="display:none;"
			        onclick="location.href='${pageContext.request.contextPath}/reviewlist.fn'">
				    목록보기
				</button>

		        
		    </div>
		

		</div>
		
        <thead class="table-light">
            <tr>
                <th style="display:none;"></th>
                <th scope="col">NO.</th>
                <th scope="col">브랜드</th>
                <th scope="col">제품명</th>
                <th scope="col">평점</th>
                <th scope="col">제목</th>
                <th scope="col">작성자</th>
                <th scope="col">등록일</th>
                <th scope="col">수정일</th>
            </tr>
        </thead>

        <tbody>
        </tbody>
		<tfoot>
		<tr>
		<td colspan="9">
		    <ul class="pagination justify-content-center">
		
		        <!-- 이전 -->
		        <c:if test="${reviewpaging.start > 10}">
		            <li class="page-item">
		                <a href="#" class="page-link"
		                   onclick="reviewPaging(${reviewpaging.start - 1})">이전</a>
		            </li>
		        </c:if>
		
		        <!-- 페이지 숫자 -->
		        <c:forEach var="i" begin="${reviewpaging.start}" end="${reviewpaging.end}">
		            <li class="page-item <c:if test='${i == reviewpaging.current}'>active</c:if>'">
		                <a href="#" class="page-link"
		                   onclick="reviewPaging(${i})">${i}</a>
		            </li>
		        </c:forEach>
		
		        <!-- 다음 -->
		        <c:if test="${reviewpaging.pagetotal > reviewpaging.end}">
		            <li class="page-item">
		                <a href="#" class="page-link"
		                   onclick="reviewPaging(${reviewpaging.end + 1})">다음</a>
		            </li>
		        </c:if>
		
		    </ul>
		</td>
		</tr>
		</tfoot>
       </table>
      
		
		
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <div class="write-btn-area">
        <button class="btn btn-slateBlue" 
        		 id="writeBtn" onclick="location.href='${pageContext.request.contextPath}/reviewwrite.fn'">리뷰 작성</button>
        		 
    </div>
</div>

<script>

//전역 상태
let currentMode = "list";       
let currentSearchType = "";
let currentKeyword = "";

//첫 로딩
$(function() {
 const currentPage = $("#currentPage").val() || 1;
 reviewPaging(currentPage);
});



function doReviewSearch(searchType, keyword) {
    currentMode = "search";
    currentSearchType = searchType;
    currentKeyword = keyword;

    $.ajax({
        url: "${pageContext.request.contextPath}/reviewsearch",
        type: "GET",
        data: { keyword: keyword, searchType: searchType },
        success: function(json) {
            reviewPagingResult(json, 1);
            $("#searchlistBtn").show();
            $("tfoot").hide();
            $("#writeBtn").hide();
        }
    }); 
}

function searchReview(){
    const keyword = $("#searchKeyword").val().trim();
    const searchType = $("#searchType").val();

    if(keyword.length === 0){
        alert("검색어를 입력해주세요.");
        return;
    }

    doReviewSearch(searchType, keyword);
}

function reviewPaging(pstartno){
    currentMode = "list";
	$.ajax({
		url:"${pageContext.request.contextPath}/reviewPaging",
        type: "GET",
        data: { pstartno: pstartno },
        success: function(json) {
        	reviewPagingResult(json, pstartno);  // 페이징 기능때매 필요
        	 $("tfoot").show();  //페이징 다시 보이기
        },
		error:function (xhr) {
		    console.error("ERROR:", xhr.responseText);
		    alert("에러 발생: " + xhr.status);}
	})
}

function ratingToStar(rating){
	if(rating==5) return "★★★★★";
	if(rating==4) return "★★★★☆";
	if(rating==3) return "★★★☆☆";
	if(rating==2) return "★★☆☆☆";
	if(rating==1) return "★☆☆☆☆";
	
}

function reviewPagingResult(json, pstartno) {
    console.log(json);
 
    let contextPath = "${pageContext.request.contextPath}";  //자꾸 문자열로 오류 나서 따로 빼주기
    let list = json.list;
    let total = json.total;
    
    let tbody = $(".review-table tbody");
    tbody.empty();

    $.each(list, function(idx, review)  {
    	let number = total - ((pstartno - 1) * 10 + idx);
    	
    	//요약-바로 보이는 행
		let summary = $("<tr>")
			.addClass("review-row")
			.attr("onclick", "toggleContent(" + review.reviewid + ")")
			.append($("<td>").css("display","none").text(review.reviewid))
			.append($("<td>").text(number))
			.append($("<td>").text(review.brandname))
			.append($("<td>").text(review.foodname))
			.append($("<td>").html(ratingToStar(review.rating)))
			.append($("<td>").text(review.title))
			.append($("<td>").text(review.nickname))
			.append($("<td>").text(review.createdat))
			.append($("<td>").text(review.updatedat));

		tbody.append(summary);

    	
    	//여기서부터 스불재 시작
		let detail = $("<tr>")
		    .attr("id", "content-" + review.reviewid)
		    .addClass("review-detail")
		    .css("display", "none");
		
		let td = $("<td>").attr("colspan", 9);
		
		// detail-inner-table
		let innerTable = $("<table>").addClass("detail-inner-table");
		
		// inner table row
		let innerTr = $("<tr>");
		
		//이미지
		let foodImgTd = $("<td>")
		    .addClass("detail-img");
		
		let foodImg = $("<img>")
		    .addClass("review-thumb")
		    .attr("src", contextPath + "/static/foodimg/" + review.foodimg)
		    .attr("onclick", "openImg('" + contextPath + "/static/foodimg/" + review.foodimg + "')");
		
		foodImgTd.append(foodImg);
		
		let contentTd = $("<td>")
		    .addClass("detail-content");
		
		let imgWrap = $("<div>").addClass("review-img-wrap");
		
		review.reviewimglist.forEach(function(img) {
		    let imgBox = $("<div>").addClass("review-img-box");
		
		    let reviewImg = $("<img>")
		        .addClass("review-img")
		        .attr("src", contextPath + "/upload/" + img.reviewimgname)
		        .attr("onclick", "openImg('" + contextPath + "/upload/" + img.reviewimgname + "')");
		
		    imgBox.append(reviewImg);
		    imgWrap.append(imgBox);
		});
		
		let comment = $("<p>")
		    .addClass("detail-text")
		    .text(review.reviewcomment);
		
		let btns = $("<div>").addClass("detail-btns")
		    .append(
		        $("<button>")
		            .addClass("btn btn-green")
		            .text("수정")
		            .attr("onclick", "location.href='reviewedit.fn?reviewid=" + review.reviewid + "'")
		    )
		    .append(
		        $("<button>")
		            .addClass("btn btn-olive")
		            .text("삭제")
		            .attr("onclick", "location.href='reviewdelete.fn?reviewid=" + review.reviewid + "'")
		    );
		
		// contentTd 구성
		contentTd.append(imgWrap);
		contentTd.append(comment);
		contentTd.append(btns);
		
		// inner row 조립
		innerTr.append(foodImgTd);
		innerTr.append(contentTd);
		
		// innerTable 조립
		innerTable.append(innerTr);
		
		// td에 innerTable 삽입
		td.append(innerTable);
		
		// detail row 완성
		detail.append(td);
		
		// tbody에 detail 추가
		tbody.append(detail);

    });   //$.each 닫음

}   // function reviewPagingResult 닫음


function toggleContent(id) {
    let row = document.getElementById("content-" + id);
    if (!row) return;  // 안전장치
    row.style.display = (row.style.display === "none") ? "table-row" : "none";
}

function openImageModal(src){
    document.getElementById("modalImg").src = src;
    document.getElementById("imgModal").style.display = "flex";
}


function openImg(url) {
    window.open(
        url, 
        "_blank",
        "width=800,height=600,toolbar=no,menubar=no,resizable=yes"
    );
}
</script>

<%@ include file="../inc/footer.jsp"%>
