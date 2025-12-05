<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="../inc/header.jsp"%>
<script>
	$(function () {
	    let result = '${success}';
	    if (result && result.length > 0) {
	        alert(result);
	    }
	});
</script>


<div class="container my-5">

    <div class="card p-4 shadow-sm foodTable">

        <h3 class="card-header mb-3">사료 정보</h3>

        <table class="table table-hover text-center align-middle">
        <input type="hidden" id="currentPage" value="${foodpaging.pstartno}">
			<caption class="visually-hidden">사료 관리자 페이지</caption>
            <thead class="table-light">
                <tr>
                    <th scope="col">NO</th>
                    <th scope="col">펫타입</th>
                    <th scope="col">브랜드</th> 
                    <th scope="col">사료명</th>
                    <th scope="col">등록일</th>
                    <th scope="col">수정일</th>
                    <th scope="col">빠른삭제</th>
                </tr>

                
                
            </thead>

            <tbody>
            </tbody>
            <tfoot>
            <tr><td colspan="5"><ul class="pagination  justify-content-center">
            	<c:if   test="${foodpaging.start >10}">
	      			<li  class="page-item">
	      				<a  class="page-link"  href="?pstartno=${foodpaging.start-1}">이전</a>
	      			</li>
	      		</c:if>
	      		<c:forEach var="i"  begin="${foodpaging.start}" end="${foodpaging.end}">
	      			<li class="page-item  <c:if test="${i==foodpaging.current}"> active </c:if>">
		      			<a href="#" onclick="foodList(${i})" class="page-link">${i}</a>
	      			</li>
	      		 
	      		</c:forEach>
	      		<c:if   test="${foodpaging.pagetotal>foodpaging.end}">
	      			<li  class="page-item">
	      				<a  class="page-link"  href="?pstartno=${foodpaging.end+1}">다음</a>
	      			</li>
	      		</c:if>             
            </ul></td></tr>
            </tfoot>
        </table>

		<div class="row my-3">
		    <div class="search-bar d-flex gap-2">
		        <select id="searchType" class="form-select" style="width:150px;">
		            <option value="all">전체</option>
		            <option value="pettypeid">강아지/고양이</option>
		            <option value="brandname">브랜드명</option>
		            <option value="foodname">사료명</option>
		        </select>
		
		        <input type="text" id="searchKeyword" placeholder="검색어 입력" class="form-control" style="width:300px;">
		        <button class="btn btn-mint" onclick="searchFood()">검색</button>
		    </div>
		</div>
		

		<div id="pagingArea" class="mt-3"></div>

        <div class="text-end">
            <a href="${pageContext.request.contextPath}/foodwrite.fn"
               class="btn btn-slateBlue">사료 등록</a>
        </div>
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    </div>
</div>
<script>
// 전역 상태
let currentMode = "list";       // 리스트/서치 구분
let currentSearchType = "";
let currentKeyword = "";

// 페이지 첫 로딩
$(function() {
    const currentPage = $("#currentPage").val() || 1;
    foodList(currentPage);
    foodquikdelete();
});

function doFoodSearch(searchType, keyword){
    currentMode = "search";
    currentSearchType = searchType;
    currentKeyword = keyword;

    $.ajax({
        url: "${pageContext.request.contextPath}/foodsearch",
        type: "GET",
        data: { keyword: keyword, searchType: searchType },
        success: function(json){
            foodListResult(json, 1);
            $("#pagingArea").empty();   // 검색 시 페이징 제거
            $("tfoot").hide();         // JSP 페이징도 숨김
        }
    });
}

// 검색창 + 버튼
function searchFood(){
    const keyword = $("#searchKeyword").val().trim();
    const searchType = $("#searchType").val();

    if(keyword.length === 0){
        alert("검색어를 입력해주세요.");
        return;
    }

    doFoodSearch(searchType, keyword);
}

function foodList(pstartno = 1) {
    currentMode = "list";
    $("tfoot").show();  // JSP 페이징 다시 보이게

    $.ajax({
        url: "${pageContext.request.contextPath}/foodselectForList",
        type: "GET",
        data: { pstartno: pstartno },
        success: function(json) {
            foodListResult(json, pstartno);
        },
        error: function() {
            alert("목록 불러오기 실패");
        }
    });
}

function foodListResult(json, pstartno) {

    let list = json.list;
    let total = json.total;

    $(".foodTable tbody").empty();

    $.each(list, function(idx, food) {
        let number = total - ((pstartno - 1) * 10 + idx);

        $("<tr>")
            .append($("<td>").text(number))
            .append($("<td>").text(pettypename(food.pettypeid)))
            .append($("<td>").text(food.brandname))
            .append($("<td>").html(
                "<a href='fooddetail.fn?foodid=" + food.foodid +
                "' style='text-decoration:none; color:black; font-weight:bold;'>" +
                food.foodname + "</a>"
            ))
            .append($("<td>").text(food.createdat))
            .append($("<td>").text(food.updatedat))
            .append($("<td>").html(
                "<button class='btn btn-mint foodquikdelete' data-foodid='" +
                food.foodid + "'>빠른삭제</button>"
            ))
            .appendTo(".foodTable tbody");
    });
}


function foodquikdelete() {
    $("body").on("click", ".foodquikdelete", function() {

        let foodid = $(this).data("foodid");
        let currentPage = $("#currentPage").val() || 1;

        if (confirm("삭제하시겠습니까?")) {
            $.ajax({
                url: "foodquikdelete",
                type: "POST",
                beforeSend: function(xhr) {
                    xhr.setRequestHeader(csrfHeader, csrfToken);
                },
                data: {
                    foodid: foodid,
                    pstartno: currentPage  
                },
                success: function() {
                    foodList(currentPage);
                },
                error: function() {
                    alert("error");
                }
            });
        }
    });
}


function pettypename(pettypeid){
    if(pettypeid == 1) return "고양이";
    else if(pettypeid == 2) return "강아지";    
}
</script>




<%@include file="../inc/footer.jsp"%>