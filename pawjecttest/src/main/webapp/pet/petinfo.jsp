<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/header.jsp" %>

<div class="container card my-5 p-4">
    <h3 class="card-header">👤 사용자 정보</h3>

    <table class="table table-bordered table-hover mt-3">
        <caption>회원 정보</caption>
        <thead>
            <tr>
                <th scope="col">PETID</th>
                <th scope="col">USERID</th>
                <th scope="col">PETNAME</th>
                <th scope="col">PETBREED</th>
                <th scope="col">BIRTHDATE</th>
                <th scope="col">PETTYPEID</th>
                <th scope="col">CREATEDAT</th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach var="pet" items="${dtoList}">
	            <tr>
	                <td>${pet.petid}</td>
	                <td>${pet.userid}</td>
	                <td>${pet.petname}</td>
	                <td>${pet.petbreed}</td>
	                <td>${pet.birthdate}</td>
	                <td>${pet.pettypeid}</td>
	                <td>${pet.createdat}</td>
	            </tr>
            </c:forEach>
        </tbody>
    </table>

    <div class="mt-4 text-end">
    
    </div>
</div>

<%@ include file="../inc/footer.jsp" %>