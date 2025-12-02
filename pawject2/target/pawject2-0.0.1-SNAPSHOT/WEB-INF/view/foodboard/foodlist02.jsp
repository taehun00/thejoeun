<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="../inc/header.jsp"%>

<c:if test="${not empty success}">
    <script>
        alert("${success}");
    </script>
</c:if>

<script>
    $(function () {
        let result = '${success}';
        if (result && result.length > 0) {
            alert(result);
        }
    });
</script>

<div class="container my-5">

    <div class="card p-4 shadow-sm">

        <h3 class="card-header mb-3">사료 정보</h3>

        <table class="table table-hover text-center align-middle">
			<caption class="visually-hidden">사료 관리자 페이지</caption>
            <thead class="table-light">
                <tr>
                    <th scope="col">NO</th>
                    <th scope="col">펫타입</th>
                    <th scope="col">브랜드</th>
                    <th scope="col">사료명</th>
                    <th scope="col">등록일</th>
                    <th scope="col">수정일</th>
                </tr>
            </thead>

            <tbody>
                <c:forEach var="fdto" items="${foodlist}" varStatus="status">
                    <tr>
                        <td>${foodlist.size() - status.index}</td>
                        <td>
                            <c:choose>
                                <c:when test="${fdto.pettypeid == 1}">고양이</c:when>
                                <c:when test="${fdto.pettypeid == 2}">강아지</c:when>
                                <c:otherwise>기타</c:otherwise>
                            </c:choose>
                        </td>
                        <td>${fdto.brandname}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/fooddetail.fn?foodid=${fdto.foodid}">
                                ${fdto.foodname}
                            </a>
                        </td>
                        <td>${fdto.createdat}</td>
                        <td>${fdto.updatedat}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div class="text-end">
            <a href="${pageContext.request.contextPath}/foodwrite.fn"
               class="btn btn-slateBlue">사료 등록</a>
        </div>

    </div>
</div>

<%@include file="../inc/footer.jsp"%>