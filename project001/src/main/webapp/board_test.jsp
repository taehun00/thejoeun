<%@page import="com.company.thejoa703.dto.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
   <div class="container card  my-5">
      <h3  class="card-header"> DAO TEST </h3>
      
     <!--  <pre class="alert alert-success">
      1. insert
      	insert into post (id, app_user_id, title, content, pass)
		values ( post_seq.nextval, ?, ?, ?, ?)
      </pre> -->
		
		
<%-- 		<% 
		PostDao dao = new PostDao();
		PostDto dto = new PostDto();
		dto.setAppUserId(6);
		dto.setTitle("첫번째 글쓰기입니다.s");
		dto.setContent("내용");
		dto.setPass("1234");
		out.println( dao.insert(dto) );
		%>  --%>
		
		<%-- <pre class="alert alert-success">
		2. selectAll
		
		select	p.*, u.email  email
		from		post p join appuser u on p.app_user_id = u.app_user_id;
		</pre>
		<%
		PostDao dao = new PostDao();
		out.println(dao.selectAll());
		%> --%>
		
<%-- 		<%PostDao dao = new PostDao();
		out.println(dao.select(3));
		%> --%>
		
<%-- 		<%PostDao dao = new PostDao();
		out.println(dao.update_hit(3));
		%> --%>
		
		<%-- <%PostDao dao = new PostDao();
		PostDto dto = new PostDto();
		dto.setTitle("4");
		dto.setContent("hihi");
		dto.setId(13);
		dto.setPass("1234");
		
		out.println(dao.delete(dto));
		%> --%>
		
		<%PostDao dao = new PostDao();
		PostDto dto = new PostDto();
		dto.setTitle("111111111");
		dto.setContent("2222222222");
		dto.setId(17);
		dto.setPass("123");
		
		out.println(dao.update(dto));
		%>
   </div>
</body>
</html>