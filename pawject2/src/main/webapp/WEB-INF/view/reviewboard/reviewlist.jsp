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
        <caption class="visually-hidden">사료 후기</caption>
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
            <c:set var="total" value="${fn:length(reviewlist)}" />
            <c:forEach var="r" items="${reviewlist}" varStatus="st">

                <!-- 리스트 행 -->
                <tr class="review-row" onclick="toggleContent(${r.reviewid})">
                    <td style="display:none;">${r.reviewid}</td>
                    <td>${total - status.index}</td>
                    <td>${r.brandname}</td>
                    <td>${r.foodname}</td>
                    <td>
                        <c:choose>
                            <c:when test="${r.rating == 5}">★★★★★</c:when>
                            <c:when test="${r.rating == 4}">★★★★☆</c:when>
                            <c:when test="${r.rating == 3}">★★★☆☆</c:when>
                            <c:when test="${r.rating == 2}">★★☆☆☆</c:when>
                            <c:otherwise>★☆☆☆☆</c:otherwise>
                        </c:choose>
                    </td>
                    <td>${r.title}</td>
                    <td>${r.nickname}</td>
                    <td>${r.createdat}</td>
                    <td>${r.updatedat}</td>
                </tr>

                <!-- 상세 행 -->
                <tr id="content-${r.reviewid}" class="review-detail">
                    <td colspan="9">

                        <table class="detail-inner-table">
                            <tr>
                                <td class="detail-img">
								     <img class="review-thumb"
				                         src="${pageContext.request.contextPath}/static/foodimg/${r.foodimg}"
				                         alt="사료 이미지"
				                         onclick="openImg('${pageContext.request.contextPath}/static/foodimg/${r.foodimg}')">
                                </td>
                                <!-- 리뷰 내용 -->
                                <td class="detail-content">
									<div class="review-img-wrap">
									    <c:forEach var="img" items="${imglist}">
									    	<c:if test="${img.reviewid eq r.reviewid}">
										    	<div class="review-img-box">
										            <img src="${pageContext.request.contextPath}/upload/${img.reviewimgname}" 
										                  alt="리뷰이미지" 
										                  class="review-img"	
										                  onclick="openImg('${pageContext.request.contextPath}/upload/${img.reviewimgname}')">
										        </div>
									    	</c:if>
									    </c:forEach>
									</div>
									
                                    <p class="detail-text"> ${r.reviewcomment} </p>

                                    <div class="detail-btns">
                                        <button type="button" class="btn btn-green"
                                            onclick="location.href='${pageContext.request.contextPath}/reviewedit.fn?reviewid=${r.reviewid}'">
                                            수정
                                        </button>

                                        <button type="button" class="btn btn-olive"
                                            onclick="location.href='${pageContext.request.contextPath}/reviewdelete.fn?reviewid=${r.reviewid}'">
                                            삭제
                                        </button>
                                    </div>
                                </td>
                            </tr>
                        </table>

                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <div class="write-btn-area">
        <button class="btn btn-slateBlue" onclick="location.href='${pageContext.request.contextPath}/reviewwrite.fn?reviewid=${r.reviewid}'">리뷰 작성</button>
    </div>
</div>

<script>
function toggleContent(id) {
    const row = document.getElementById("content-" + id);
    row.style.display = (row.style.display === "table-row") ? "none" : "table-row";
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
