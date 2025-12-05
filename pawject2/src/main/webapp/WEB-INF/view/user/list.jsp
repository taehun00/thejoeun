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
		<div class="container my-3">
		    <input type="text" id="searchKeyword" class="form-control" placeholder="이메일 또는 닉네임 검색">
		    <button type="button" class="btn btn-primary mt-2" id="searchBtn">검색</button>
		</div>
<script>
$(function(){        
    userList();   // 전체리스트
    //userSelect(); // 1명분의 정보
    //userUpdate(); // 유저정보수정
    userDelete(); // 유저정보삭제
});


// 전체 유저 리스트
function userList(){
    $.ajax({
        url: "/pawject2/security/selectAll",   // 절대경로로 지정
        type: "GET",
        success: function(json){
            console.log("✅ selectAll 응답:", json);
            userListResult(json);
        },
        error: function(xhr, status, msg){
            console.error("❌ selectAll 에러:", status, msg, xhr.responseText);
            alert(status + "/" + msg);
        }
    });
}

function userListResult(json){
    $(".userTable tbody").empty();
    let total = json.length;
    var contextPath = "${pageContext.request.contextPath}";

    $.each(json, function(idx, user){
        $("<tr>")
            .append($("<td>").html(total-idx))
            
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
    $.ajax({
        url: "/pawject2/security/search",
        type: "GET",
        data: { keyword: keyword },
        success: function(json){
            console.log("✅ search 응답:", json);
            userListResult(json);
        },
        error: function(xhr, status, msg){
            console.error("❌ search 에러:", status, msg, xhr.responseText);
            alert("검색 실패");
        }
    });
});

</script>  
   
<%@include file="../inc/footer.jsp" %>