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

<div class="container card my-5 p-4 petTable">
    <h3 class="card-header">관리자 PET BOARD</h3>
    <table class="table table-striped table-bordered table-hover">
        <meta name="_csrf" content="${_csrf.token}"/>
        <meta name="_csrf_header" content="${_csrf.headerName}"/>
        <caption>PETS</caption>
        <thead>
            <tr>
                <th scope="col">NO</th>
                <th scope="col">이미지</th>
                <th scope="col">PetID</th>
                <th scope="col">Email</th>
                <th scope="col">PetName</th>
                <th scope="col">Breed</th>
                <th scope="col">BirthDate</th>
                <th scope="col">Type</th>
                <th scope="col">등록일</th>
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
        <select id="searchType" class="form-select">
            <option value="email">이메일</option>
            <option value="petName">펫 이름</option>
        </select>
        <!-- 검색 입력창: 넓게 -->
        <div class="col-6 col-md-8">
            <input type="text" id="searchKeyword" class="form-control" placeholder="검색어 입력">
        </div>

        <!-- 검색 버튼 -->
        <div class="col-3 col-md-2">
            <button type="button" class="btn btn-primary w-100" id="searchBtn">검색</button>
        </div>
    </div>
</div>

<script>
$(function(){
    petList();   // 전체 리스트
    petDelete(); // 펫 삭제
});

// 전체 펫 리스트
function petList(pstartno){
    $.ajax({
        url: "/pawject2/pet/list",
        type: "GET",
        data: { pstartno: pstartno || 1 },
        success: function(json){
            console.log("✅ pet list 응답:", json);
            petListResult(json.list, json.paging);
            renderPaging(json.paging);
        },
        error: function(xhr, status, msg){
            console.error("❌ pet list 에러:", status, msg, xhr.responseText);
            alert(status + "/" + msg);
        }
    });
}

function petListResult(list, paging){
    $(".petTable tbody").empty();
    var contextPath = "${pageContext.request.contextPath}";

    $.each(list, function(idx, pet){
        let rowNum = paging.listtotal - ((paging.current - 1) * paging.onepagelist) - idx;

        $("<tr>")
            .append($("<td>").html(rowNum))
            .append($("<td>").html('<img src="'+contextPath+'/upload/'+pet.pfile+'" alt="" style="width:80px" />'))
            .append($("<td>").html(pet.petId))
            .append($("<td>").html(pet.email))
            .append($("<td>").html(pet.petName))
            .append($("<td>").html(pet.petBreed))
            .append($("<td>").html(pet.birthDate))
            .append($("<td>").html(pet.petTypeId))
            .append($("<td>").html(pet.createdAt))
            .append($("<td>").html("<a href='/pawject2/pet/update/admin?petId="+pet.petId+"' class='btn btn-primary'>수정</a>"))
            .append($("<td>").html("<input type='button' class='btn btn-danger deletePet' value='삭제' />"))
            .append($("<input type='hidden' class='hidden_id'/>").val(pet.petId))
            .append($("<input type='hidden' class='hidden_name'/>").val(pet.petName))
            .appendTo(".petTable tbody");
    });
}

// 페이징
function renderPaging(paging){
    let html = "";

    if(paging.current > 1){
        html += '<li class="page-item"><a class="page-link" href="javascript:petList('+(paging.current-1)+')">이전</a></li>';
    }

    for(let i=Number(paging.start); i<=Number(paging.end); i++){
        html += '<li class="page-item '+(i==paging.current?'active':'')+'">' +
                  '<a class="page-link" href="javascript:petList('+i+')">'+i+'</a>' +
                '</li>';
    }

    if(paging.current < paging.pagetotal){
        html += '<li class="page-item"><a class="page-link" href="javascript:petList('+(paging.current+1)+')">다음</a></li>';
    }

    $(".pagination").html(html);
}

// 펫 삭제
function petDelete(){
    $("body").on("click", ".deletePet", function(){
        let petId = $(this).closest("tr").find(".hidden_id").val();
        let petName = $(this).closest("tr").find(".hidden_name").val();
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");

        if(confirm(petName + " 펫을 삭제하시겠습니까?")){
            $.ajax({
                url: "/pawject2/pet/delete",
                type: "POST",
                data: { petId: petId },
                dataType: "json",
                beforeSend: function(xhr) {
                    xhr.setRequestHeader(header, token);
                },
                success: function(json){
                    console.log("✅ deletePet 응답:", json);
                    if(json.result == 1){
                        alert("삭제 완료");
                        petList();
                    } else {
                        alert("삭제 실패");
                    }
                },
                error: function(xhr, status, msg){
                    console.error("❌ deletePet 에러:", status, msg, xhr.responseText);
                    alert(status + "/" + msg);
                }
            });
        }
    });
}

//검색
$("#searchBtn").on("click", function(){
    let keyword = $("#searchKeyword").val();
    let type = $("#searchType").val(); // email or petName

    $.ajax({
        url: "/pawject2/pet/search",
        type: "GET",
        data: { keyword: keyword, type: type },
        success: function(json){
            console.log("✅ search 응답:", json);
            let fakePaging = { listtotal: json.length, onepagelist: json.length, current: 1 };
            petListResult(json, fakePaging);
        },
        error: function(xhr, status, msg){
            console.error("❌ search 에러:", status, msg, xhr.responseText);
            alert("검색 실패");
        }
    });
});

</script>

<%@include file="../inc/footer.jsp" %>