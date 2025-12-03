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

function foodList(pstartno) {
    $.ajax({
        url: "foodselectForList",
        type: "GET",
        data: { pstartno: pstartno },  //조심!
        success: foodListResult,
        error: function() {
            alert("error");
        }
    });
}

function foodListResult(json) {
    console.log(json);
    $(".foodTable tbody").empty();
    let total = json.length;

    $.each(json, function(idx, food) {
        $("<tr>")
            .append($("<td>").html(total - idx))
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