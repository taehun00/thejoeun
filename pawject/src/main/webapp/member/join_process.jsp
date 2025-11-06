<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	
	
<%		//기본
	Connection conn = null;  PreparedStatement pstmt = null;   ResultSet  rset = null;
	String driver   = "oracle.jdbc.driver.OracleDriver";
	String url      = "jdbc:oracle:thin:@localhost:1521:xe";
	String user     = "scott"; 
	String pass     = "tiger";
	

	//SQL	
	String sql = "insert into testuser(userid, email, nickname, password, createdat) values (testuser_seq.nextval,?,?,?,sysdate)";
	int result = -1;
	
	//데이터 넘겨받기
	String email = request.getParameter("email");
	String password=request.getParameter("password");
	String nickname=request.getParameter("nickname");
	
	try{
	
			//드커프리
			Class.forName(driver);
			conn=DriverManager.getConnection(url,user,pass);
			
			//pstmt
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, nickname);
			pstmt.setString(3, password);
			
			//rset
			result=pstmt.executeUpdate();
			if(result>0){   out.println("<script>alert('회원가입 성공!'); location.href='login.jsp'</script>");  }
		
	}catch(Exception e){ e.printStackTrace();
		}finally{
			
			try{if(conn!=null){conn.close();}}catch(Exception e){e.printStackTrace();};
			try{if(pstmt!=null){pstmt.close();}}catch(Exception e){e.printStackTrace();};
			try{if(rset!=null){rset.close();}}catch(Exception e){e.printStackTrace();};
			
		}
		
		
		
	
	
	
	
%>