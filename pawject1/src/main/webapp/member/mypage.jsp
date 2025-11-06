<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../inc/header.jsp" %>
<!-- header -->


	<div class="container mt-5">
		<h3>MYPAGE</h3>
		
		<%
		
	  	Connection conn = null;  PreparedStatement pstmt = null;   ResultSet  rset = null;
		String driver   = "oracle.jdbc.driver.OracleDriver";
		String url      = "jdbc:oracle:thin:@localhost:1521:xe";
		String user     = "scott"; String pass     = "tiger";
		String nickname=""; 
		String createdat=""; 

		//정보 받아오기
		
		int  userid = Integer.parseInt( request.getParameter("userid") );
		
		try{
		
			//드커프리
			Class.forName(driver);
			conn=DriverManager.getConnection(url,user,pass);
			
			//pstmt
			String sql = "select * from testuser";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, userid);
			
			//rset
			rset=pstmt.executeQuery();
			while(rset.next()){
				email=rset.getString("email");
				nickname=rset.getString("nickname");
				createdat=rset.getString("createdat");
				
			}
			
			//출력
		}catch(Exception e){e.printStackTrace();
			}finally{
				if(conn!=null){conn.close();}
				if(pstmt!=null){pstmt.close();}
				if(rset!=null){rset.close();}
			}
		
		%>
		
		
		
		
		  <table class="table table-hover">
		    <tbody>
		      <tr><th scope="row">EMAIL</th> <td><%=email%></td></tr>
		        <tr><th scope="row">NICKNAME</th><td><%=nickname%></td></tr>
		        <tr><th scope="row">CREATED_AT</th><td><%=createdat%></td></tr>
		 
		    </tbody>
		  </table>
		
		
	</div>





<!-- footer -->
<%@include file="../inc/footer.jsp" %>
