<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	
	
<%		//기본
	Connection conn = null;  PreparedStatement pstmt = null;   ResultSet  rset = null;
	String driver   = "oracle.jdbc.driver.OracleDriver";
	String url      = "jdbc:oracle:thin:@localhost:1521:xe";
	String user     = "scott"; 
	String pass     = "tiger";
	
	//sql
	//String sql = "select * from appuser where email=? and password=?"; 틀림!!!
	
	String sql = "select count(*) cnt, userid from testuser where email=? and password=? group by userid";

	int userid=0;
	try{
			//정보 받아오기
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			//드커프리
			Class.forName(driver);
			conn=DriverManager.getConnection(url,user,pass);
			
			//pstmt
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			
			//rset	
			rset=pstmt.executeQuery();
			int result= -1;
			while(rset.next()){
				result=rset.getInt("cnt");   //이 부분 주의!!!**
				 userid= rset.getInt("userid");
				
				session.setAttribute("email", email);
				session.setAttribute("userid", userid);
				
				
			}if(result==1){out.println("<script>alert('로그인 성공!'); location.href='mypage.jsp?userid="+userid +"';</script>");
			}else{out.println("<script>alert('관리자에게 문의하세요'); history.go(-1);</script>");}
			
	}catch(Exception e){ e.printStackTrace();
		
	}finally{
		try{
				if(conn!=null){conn.close();}
				if(pstmt!=null){pstmt.close();}
				if(rset!=null){rset.close();}
				
		}catch(Exception e){ e.printStackTrace();}
		
	}
	
	%>