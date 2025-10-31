<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/header.jsp" %>
<%  Connection conn = null;  PreparedStatement pstmt = null;   ResultSet  rset = null;
	String driver   = "oracle.jdbc.driver.OracleDriver";
	String url      = "jdbc:oracle:thin:@localhost:1521:xe";
	String user     = "scott"; 
	String pass     = "tiger";
	String EMAIL=""; int MBTI_TYPE_ID=0; String CREATED_AT="";
	String mbti ="";
	//0. 데이터 넘겨받기 - 아이디/비밀번호
	int APP_USER_ID = Integer.parseInt( request.getParameter("APP_USER_ID"));
	//드커프리
	try{
		//1. 드라이버연동
		Class.forName(driver);
		//2. 커넥션
		conn = DriverManager.getConnection(url, user, pass);
		//3. PreparedStatement  select * from appuser  where email=?  and password=?
		String sql = "select * from appuser  where APP_USER_ID = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, APP_USER_ID);
		//4. ResultSet   -  select   : executeQuery() / insert, update, delete - executeUpdate()
		// String EMAIL=""; int MBTI_TYPE_ID=0; String CREATED_AT="";
		rset = pstmt.executeQuery(); // 표
		while(rset.next()){
			EMAIL = rset.getString("EMAIL"); // 칸
			MBTI_TYPE_ID = rset.getInt("MBTI_TYPE_ID"); // 칸
			CREATED_AT = rset.getString("CREATED_AT"); // 칸
		}
		
		//MBTI_TYPE_ID 1이라면 ISTJ, 2라면 ISFJ, 3이라면 INFJ
		switch(MBTI_TYPE_ID){
		    case 1: mbti = "ISTJ"; break;
		    case 2: mbti = "ISFJ"; break;
		    case 3: mbti = "INFJ"; break;
		    case 4: mbti = "INTJ"; break;
		    case 5: mbti = "ISTP"; break;
		    case 6: mbti = "ISFP"; break;
		    case 7: mbti = "INFP"; break;
		    case 8: mbti = "INTP"; break;
		    case 9: mbti = "ESTP"; break;
		    case 10: mbti = "ESFP"; break;
		    case 11: mbti = "ENFP"; break;
		    case 12: mbti = "ENTP"; break;
		    case 13: mbti = "ESTJ"; break;
		    case 14: mbti = "ESFJ"; break;
		    case 15: mbti = "ENFJ"; break;
		    case 16: mbti = "ENTJ"; break;
		    default: mbti = "Unknown";
	}
		
		
	}catch(Exception e){e.printStackTrace();
	}finally{
		if(rset != null)  rset.close();
		if(pstmt != null) pstmt.close();
		if(conn != null)  conn.close();
	}
%>

<table class="table">
    <tbody class="table-dark">
      <tr>
        <th scope="row">Email</th> <td><%=EMAIL%></td>
      </tr>
      <tr>
        <th scope="row">MBTI_TYPE</th><td><%=mbti%></td>
      </tr>
      <tr>
        <th scope="row">회원가입날짜</th><td><%=CREATED_AT %></td>
      </tr>
    </tbody>
  </table>
<%@ include file="../inc/footer.jsp" %>