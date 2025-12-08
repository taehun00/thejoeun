<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 

<%@  include file="../inc/header.jsp" %>  
   <script>
   $(function(){
	   let result = '${success}';
	   if(result == "회원가입 실패"){   alert( result );   history.go(-1); }
	   else if(result.length  != 0 ){  alert(result); }  //아까 처음 값이없을때 공백 
   }); 
   </script>
<c:if test="${not empty loginError}">
    <script>
        alert("${loginError}");
    </script>
</c:if>

<div class="container mt-5">
	<h3> 로그인</h3>
	<form action="${pageContext.request.contextPath}/security/doLogin"  method="post">
	  <div class="mb-3 mt-3">
	    <label for="username" class="form-label">Email:</label>
	    <input type="email" class="form-control" id="username" 
	    			placeholder="이메일을 적어주세요"  required name="username">
	  </div>
	  <div class="mb-3">
	    <label for="password" class="form-label">Password:</label>
	    <input type="password" class="form-control" id="password" placeholder="비밀번호를 적어주세요"  
	    			required  name="password">
	  </div>
<!-- 	  <div class="form-check mb-3">
	    <label class="form-check-label">
	      <input class="form-check-input" type="checkbox" name="remember"> Remember me
	    </label>
	  </div> -->
<<<<<<< HEAD
	  <button type="submit" class="btn btn-mint">로그인</button>
		<div class="text-end">
		    <a href="${pageContext.request.contextPath}/security/join" class="btn btn-rose">회원가입</a>
		</div>

=======
	  <button type="submit" class="btn btn-primary">로그인</button>
	  <div class="text-end">
	  <a href="${pageContext.request.contextPath}/security/join"  class="btn btn-danger">회원가입</a>
	  </div>
>>>>>>> cb4a38b2ec94f84ae8d5b1165d16b0247b5b119c
	  <input  type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
	</form>
</div>

<%@  include file="../inc/footer.jsp" %>    

 
 
<!-- 	로그인폼		로그인처리
	login.jsp  → login_process.jsp   

				action="login_process.jsp"
				method="post"
				name="email"   name="password"     
				
 
-->