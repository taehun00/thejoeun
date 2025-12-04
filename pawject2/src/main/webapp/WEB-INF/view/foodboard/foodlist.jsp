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
					<th scope="col" id="pettypeFilterToggle" class="filterToggle">
					    <div class="filter-head">펫타입 ▼</div>
					    <div class="filter-panel" style="display:none;"></div>
					</th>
					
					<th scope="col" id="brandFilterToggle" class="filterToggle">
					    <div class="filter-head">브랜드 ▼</div>
					    <div class="filter-panel" style="display:none;"></div>
					</th>       
                    <th scope="col">사료명</th>
                    <th scope="col">등록일</th>
                    <th scope="col">수정일</th>
                    <th scope="col">빠른삭제</th>
                </tr>
                
                <div id="filterBox" style="display:none;" class="mt-3">
				    <div id="filterContent"></div>
				</div>
                
                
            </thead>

            <tbody>
            </tbody>
            

            <div class="row">
			  <div class="col-3">
			  	<select id="searchType">
				    <option value="all">전체</option>
				    <option value="foodname">사료명</option>
				    <option value="description">내용</option>
				    <option value="pettypeid">강아지/고양이</option>
				    <option value="brandname">브랜드명</option>
				</select>
			  </div>
		      <div class="col-6"><input type="text" id="searchKeyword" placeholder="검색어를 입력해 주세요"></div>
			  <div class="col-2"><button class="btn btn-mint" onclick="searchFood()">검색</button></div>
		
		    </div>
            
            
            
		<tfoot>
		<tr>
		<td colspan="9">
		    <ul class="pagination justify-content-center">
		
		        <!-- 이전 -->
		        <c:if test="${foodpaging.start > 10}">
		            <li class="page-item">
		                <a href="#" class="page-link"
		                   onclick="foodList(${foodpaging.start - 1})">이전</a>
		            </li>
		        </c:if>
		
		        <!-- 페이지 숫자 -->
		        <c:forEach var="i" begin="${foodpaging.start}" end="${foodpaging.end}">
		            <li class="page-item <c:if test='${i == foodpaging.current}'>active</c:if>'">
		                <a href="#" class="page-link"
		                   onclick="foodList(${i})">${i}</a>
		            </li>
		        </c:forEach>
		
		        <!-- 다음 -->
		        <c:if test="${foodpaging.pagetotal > foodpaging.end}">
		            <li class="page-item">
		                <a href="#" class="page-link"
		                   onclick="foodList(${foodpaging.end + 1})">다음</a>
		            </li>
		        </c:if>
		
		    </ul>
		</td>
		</tr>
		</tfoot>            

        </table>
         

        <div class="text-end">
            <a href="${pageContext.request.contextPath}/foodwrite.fn"
               class="btn btn-slateBlue">사료 등록</a>
        </div>
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    </div>
</div>

<script>
$(function() {
    let currentPage = $("#currentPage").val() || 1;
    foodList($("#currentPage").val() || 1);
    foodquikdelete();
});

//검색
function doFoodSearch(type, keyword, pstartno = 1){
    $.ajax({
        url: "/foodsearch",
        type: "GET",
        data: { keyword:keyword, searchType:type, pstartno:pstartno },
        success: function(json){
            foodListResult(json, pstartno);
        }
    });
}

//검색창
function searchfood(){
    let keyword = $("#searchKeyword").val().trim();
    let type = $("#searchType").val().trim();

    doFoodSearch(type, keyword);
}


//리스트에서 클릭으로 검색
/* function searchByType(type, keyword){
    doFoodSearch(type, keyword);
} */

//펫타입 필터
$("#pettypeFilterToggle").on("click", function() {
    let panel = $(this).find(".filter-panel");
    panel.html(`
        <select id="pettypeSelect" class="form-select form-select-sm">
            <option value="">전체</option>
            <option value="1">고양이</option>
            <option value="2">강아지</option>
        </select>
        <button class="btn btn-sm btn-mint mt-2" onclick="applyFilter('pettype')">검색</button>
    `);

    panel.slideToggle(120);
});


/*         <select name="brandid" id="brandid" class="form-select">
<option value="">브랜드 선택</option>
<c:forEach var="b" items="${brandlist}">
  <option value="${b.brandid}">${b.brandname}</option>
</c:forEach>
</select> */

function applyFilter(type){
    let keyword = "";

    if(type === "pettype"){
        keyword = $("#pettypeSelect").val();
    } else if(type === "brandname"){
        keyword = $("#brandSelect").val();
    }

    doFoodSearch(type, keyword);

    // 모든 드롭다운 닫기
    $(".filter-panel").slideUp(100);
}



$(".filter-panel").on("click", function(e){
    e.stopPropagation();
});


function foodList(pstartno) {
    $.ajax({
        url: "foodselectForList",
        type: "GET",
        data: { pstartno: pstartno },
        success: function(json) {
            foodListResult(json, pstartno);
        },
        error: function() {
            alert("error");
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
            .append($("<td>").html(number))
			.append($("<td>").html(pettypename(food.pettypeid)))
			.append($("<td>").html(food.brandname))
			.append($("<td>").html("<a href='fooddetail.fn?foodid=" 
                   + food.foodid 
                   + "' style='text-decoration:none; color:black; font-weight:bold;'>"
                   + food.foodname + "</a>"))
            .append($("<td>").html(food.createdat))
            .append($("<td>").html(food.updatedat))
            .append($("<td>").html("<button class='btn btn-mint foodquikdelete' data-foodid='"
                   + food.foodid + "'>빠른삭제</button>"))
                 
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
                },  //토큰 여기!!
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