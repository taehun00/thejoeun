package com.pawject.model.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.pawject.model.user.UserDto;

public class UserDao {
	
	public int insertUser(String email, String nickname, String password) {
		int result = 0;
		String sql = "INSERT INTO users (userid, email, nickname, password, createdat) " +
                "VALUES (users_seq.NEXTVAL, ?, ?, ?, SYSDATE)";
		
		Connection conn = null;  PreparedStatement pstmt = null;   ResultSet  rset = null;
		String driver   = "oracle.jdbc.driver.OracleDriver";
		String url      = "jdbc:oracle:thin:@localhost:1521:xe";
		String user     = "scott"; 
		String pass = "tiger";
		
		try {
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url, user, pass);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, nickname);
			pstmt.setString(3, password);
			
			if( pstmt.executeUpdate() > 0) { result = 1; }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rset != null) try { rset.close(); } catch (SQLException e) { e.printStackTrace(); }
			if(pstmt != null) try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		return result;
	}


	
	public int loginCheck(UserDto dto) {
		int result = 0;
		String sql = "SELECT COUNT(*) FROM users WHERE email=? AND password=?";

		Connection conn = null;  PreparedStatement pstmt = null;   ResultSet  rset = null;
		String driver   = "oracle.jdbc.driver.OracleDriver";
		String url      = "jdbc:oracle:thin:@localhost:1521:xe";
		String user     = "scott"; 
		String pass = "tiger";
		
		try {
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url, user, pass);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getEmail());
			pstmt.setString(2, dto.getPassword());
			
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
			    int count = rset.getInt(1); // 첫 번째 컬럼의 COUNT 값 가져오기
			    if (count > 0) {
			        result = 1;			    }
			}


			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rset != null) try { rset.close(); } catch (SQLException e) { e.printStackTrace(); }
			if(pstmt != null) try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		return result;
		
		
	}

	public UserDto getUserInfoByEmail(String email) {
		UserDto result = null;
		//UserDto result = new UserDto();
		String sql = "select userid, email, nickname, createdat from users where email=?";
	
		Connection conn = null;  PreparedStatement pstmt = null;   ResultSet  rset = null;
		String driver   = "oracle.jdbc.driver.OracleDriver";
		String url      = "jdbc:oracle:thin:@localhost:1521:xe";
		String user     = "scott"; 
		String pass = "tiger";
		
		try {
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url, user, pass);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				
				  result = new UserDto(); 
				  result.setUserid(rset.getInt("userid"));
				  result.setEmail(rset.getString("email"));
				  result.setNickname(rset.getString("nickname"));
				  result.setCreatedat(rset.getTimestamp("createdat").toLocalDateTime());
				 
				/*
				 * result = new UserDto( rset.getInt("userid"), rset.getString("email"),
				 * rset.getString("nickname"), rset.getString("password"),
				 * rset.getTimestamp("createdat").toLocalDateTime()
				 * 
				 * );
				 */

	        }

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rset != null) try { rset.close(); } catch (SQLException e) { e.printStackTrace(); }
			if(pstmt != null) try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		return result;
	}
	
	public int deleteUser(UserDto dto) {
		int result = 0;
		String sql = "delete from users where email = ? and userpass = ?";

		Connection conn = null;  PreparedStatement pstmt = null;   ResultSet  rset = null;
		String driver   = "oracle.jdbc.driver.OracleDriver";
		String url      = "jdbc:oracle:thin:@localhost:1521:xe";
		String user     = "scott"; 
		String pass = "tiger";
		
		try {
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url, user, pass);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getEmail());
			pstmt.setString(2, dto.getPassword());
			
			
			if (pstmt.executeUpdate() > 0) result = 1;
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rset != null) try { rset.close(); } catch (SQLException e) { e.printStackTrace(); }
			if(pstmt != null) try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		return result;
		
		
		
	}

}
