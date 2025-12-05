<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file="../inc/header.jsp" %>
<!-- 성공 메시지 -->
<c:if test="${not empty success}">
    <script>
        alert("${success}");
    </script>
</c:if>

<!-- 실패 메시지 -->
<c:if test="${not empty error}">
    <script>
        alert("${error}");
    </script>
</c:if>



   <div class="container card  my-5 p-4  userTable">
      <h3 class="card-header"> 관리자 USER BOARD</h3>  
      <table class="table table-striped table-bordered table-hover">
      <meta name="_csrf" content="${_csrf.token}"/>
	  <meta name="_csrf_header" content="${_csrf.headerName}"/>

      	<caption>USERS </caption>   	
      	<thead>
      		<tr>
      			<th scope="col">NO</th>
      			<th scope="col">PROFILE</th> 
      			<th scope="col">USERNO</th>
      			<th scope="col">EMAIL</th>
      			<th scope="col">NICKNAME</th>
      			<th scope="col">DATE</th>  
      			<th scope="col">수정</th>
      			<th scope="col">삭제</th> 
      		</tr>	
      	</thead>
      	<tbody>   
      	</tbody>
      </table>      
   </div>
    <div class="paging text-center my-3">
	    <ul class="pagination justify-content-center"></ul>
	</div>
		<div class="container my-3">
    <div class="input-group">
        <select id="searchType" class="form-select" style="max-width:150px;">
            <option value="email">이메일</option>
            <option value="nickname">닉네임</option>
        </select>
        <input type="text" id="searchKeyword" class="form-control" placeholder="검색어 입력">
        <button type="button" class="btn btn-primary" id="searchBtn">검색</button>
    </div>
</div>

<script>
$(function(){        
    userList();   // 전체리스트
    //userSelect(); // 1명분의 정보
    //userUpdate(); // 유저정보수정
    userDelete(); // 유저정보삭제
});


// 전체 유저 리스트
function userList(pstartno){
    $.ajax({
        url: "/pawject2/security/list",   // 절대경로로 지정
        type: "GET",
        data:{ pstartno: pstartno || 1},
        success: function(json){
            console.log("✅ list 응답:", json);
            userListResult(json.list, json.paging);
            renderPaging(json.paging);
        },
        error: function(xhr, status, msg){
            console.error("❌ list 에러:", status, msg, xhr.responseText);
            alert(status + "/" + msg);
        }
    });
}

function userListResult(list, paging){
    $(".userTable tbody").empty();
    var contextPath = "${pageContext.request.contextPath}";

    $.each(list, function(idx, user){
    	let rowNum = paging.listtotal - ((paging.current - 1) * paging.onepagelist) - idx;
    	
        $("<tr>")
            .append($("<td>").html(rowNum))
            .append($("<td>").html('<img src="'+contextPath+'/upload/'+user.ufile+'" alt="" style="width:80px" />'))
            .append($("<td>").html(user.userId))
            .append($("<td>").html(user.email))
            .append($("<td>").html(user.nickname))
            .append($("<td>").html(user.createdAt))
            // 수정 버튼 → 상세 페이지 이동
            .append($("<td>").html("<a href='/pawject2/security/detail?userId="+user.userId+"' class='btn btn-primary'>수정</a>"))
            .append($("<td>").html("<input type='button' class='btn btn-danger deleteUser' value='삭제' />"))
            .append($("<input type='hidden' class='hidden_id'/>").val(user.userId))
            .append($("<input type='hidden' class='hidden_email'/>").val(user.email))
            .appendTo(".userTable tbody");
    });
}


function renderPaging(paging){
    let html = "";

    if(paging.current > 1){
        html += '<li class="page-item"><a class="page-link" href="javascript:userList('+(paging.current-1)+')">이전</a></li>';
    }

    // 숫자 부분
    let start = Number(paging.start);
    let end = Number(paging.end);
    for(let i=start; i<=end; i++){
        html += '<li class="page-item '+(i==paging.current?'active':'')+'">' +
                  '<a class="page-link" href="javascript:userList('+i+')">'+i+'</a>' +
                '</li>';
    }

    if(paging.current < paging.pagetotal){
        html += '<li class="page-item"><a class="page-link" href="javascript:userList('+(paging.current+1)+')">다음</a></li>';
    }

    $(".pagination").html(html);
}



// 유저 삭제
function userDelete(){
    $("body").on("click", ".deleteUser", function(){
        let userId = $(this).closest("tr").find(".hidden_id").val();
        let email = $(this).closest("tr").find(".hidden_email").val();
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");

        if(confirm(email + " 유저를 삭제하시겠습니까?")){
            $.ajax({
            	url: "/pawject2/security/deleteUser",
                type: "POST",
                data: { email: email },
                dataType: "json", 
                beforeSend: function(xhr) {
                    xhr.setRequestHeader(header, token); // ✅ CSRF 토큰 헤더 추가
                },
                success: function(json){
                    console.log("✅ deleteAdmin 응답:", json);
                    if(json.result == 1){
                        alert("삭제 완료");
                        userList();
                    } else {
                        alert("삭제 실패");
                    }
                },
                error: function(xhr, status, msg){
                    console.error("❌ deleteAdmin 에러:", status, msg, xhr.responseText);
                    alert(status + "/" + msg);
                }
            });
        }
    });
}

$("#searchBtn").on("click", function(){
    let keyword = $("#searchKeyword").val();
    let type = $("#searchType").val(); // email or nickname

    $.ajax({
        url: "/pawject2/security/search",
        type: "GET",
        data: { keyword: keyword, type: type },
        success: function(json){
            console.log("✅ search 응답:", json);
            let fakePaging = { listtotal: json.length, onepagelist: json.length, current: 1 };
            userListResult(json, fakePaging);
        },
        error: function(xhr, status, msg){
            console.error("❌ search 에러:", status, msg, xhr.responseText);
            alert("검색 실패");
        }
    });
});


</script>  
   
<%@include file="../inc/footer.jsp" %>