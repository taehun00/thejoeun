<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file="../inc/header.jsp" %>
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/butten.css">



<div class="container mt-3">
  <h2>버튼 샘플표</h2>
     
	<table class="table" style="width:500px;">
	  <thead>
	    <tr>
	      <th scope="col">태그명</th>
	      <th scope="col">샘플</th>
	      <th scope="col">태그명</th>
	      <th scope="col">샘플</th>
	    </tr>
	  </thead>
	  <tbody>
	    <tr>
	      <td>btn-green</td>
	      <td><button class="btn btn-green" >그린</button> </td>
	      <td>btn-burgundy</td>
	      <td><button class="btn btn-burgundy">버건디</button> </td>
	    </tr>
	    
	    <tr>
	      <td>btn-mustard</td>
	      <td><button class="btn btn-mustard">머스터드</button> </td>
	      <td>btn-mint</td>
	      <td> <button class="btn btn-mint">민트</button> </td>
	    </tr>
	    
	    <tr>
	      <td>btn-green</td>
	      <td> <button class="btn btn-rose">로즈핑크</button></td>
	      <td>btn-burgundy</td>
	      <td> <button class="btn btn-lavender">라벤더</button> </td>
	    </tr>	 
	       
	    <tr>
	      <td>btn-olive</td>
	      <td> <button class="btn btn-olive">올리브</button></td>
	      <td>btn-navy</td>
	      <td><button class="btn btn-navy">네이비</button> </td>
	    </tr>
	    
	    <tr>
	      <td>btn-terra</td>
	      <td><button class="btn btn-terra">테라코타</button> </td>
	      <td>btn-roseRed</td>
	      <td> <button class="btn btn-roseRed">로즈레드</button> </td>
	    </tr>	    
	    
	    <tr>
	      <td>btn-slateBlue</td>
	      <td>  <button class="btn btn-slateBlue">슬레이트블루</button></td>
	      <td>btn-sage</td>
	      <td>  <button class="btn btn-sage">세이지그린</button></td>
	    </tr>
	    	
	  </tbody>
	</table>

</div>

<%@include file="../inc/footer.jsp" %>	