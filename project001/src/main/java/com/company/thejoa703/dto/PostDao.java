package com.company.thejoa703.dto;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class PostDao {
//	1. 글쓰기 

			
	public int insert(PostDto dto){
		//int post = Integer.parseInt(request.getParameter("post"));
		int result = -1;
		String sql =" insert into post (id, app_user_id, title, content, pass) "
				+ " values ( post_seq.nextval, ?, ?, ?, ?) ";
				//+ " order by id desc";
		// 드 커 프 리
		Connection conn = null;  PreparedStatement pstmt = null;   ResultSet  rset = null;
		String driver   = "oracle.jdbc.driver.OracleDriver";
		String url      = "jdbc:oracle:thin:@localhost:1521:xe";
		String user     = "scott"; 
		String pass = "tiger";
		
		
		try {
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url, user, pass);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getAppUserId());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			pstmt.setString(4, dto.getPass());
			
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
	
//	2. 전체글 가져오기, appuser 테이블에서 email도 같이 가져오기
	public ArrayList<PostDto> selectAll(){
		ArrayList<PostDto> result = new ArrayList<>();
		String sql = " SELECT      p.*     ,  u.email  email      "
                + " FROM      post p  join appuser u   on  p.app_user_id= u.app_user_id ";
		// 드 커 프 리
		Connection conn = null;  PreparedStatement pstmt = null;   ResultSet  rset = null;
		String driver   = "oracle.jdbc.driver.OracleDriver";
		String url      = "jdbc:oracle:thin:@localhost:1521:xe";
		String user     = "scott"; 
		String pass = "tiger";
		
		
		try {
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url, user, pass);
			
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				// id, appUserId, title, content, pass, createdAt, hit, email
				result.add(new PostDto(
							rset.getInt("id"), rset.getInt("app_user_id"), rset.getString("title"),
							rset.getString("content"), rset.getString("pass"), rset.getTimestamp("created_at").toLocalDateTime(),
							rset.getInt("hit"), rset.getString("email")
						));
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
	
//	3. 글번호 해당하는 글가져오기
 	public PostDto select(int id){
 		PostDto result = new PostDto();
 		String sql = "select * from post where id=?";
	
 		// 드 커 프 리
 		Connection conn = null;  PreparedStatement pstmt = null;   ResultSet  rset = null;
		String driver   = "oracle.jdbc.driver.OracleDriver";
		String url      = "jdbc:oracle:thin:@localhost:1521:xe";
		String user     = "scott"; 
		String pass = "tiger";
		
		
		try {
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url, user, pass);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				result = new PostDto(
						rset.getInt("id"), rset.getInt("app_user_id"), rset.getString("title"),
						rset.getString("content"), rset.getString("pass"), rset.getTimestamp("created_at").toLocalDateTime(),
						rset.getInt("hit")
						//, rset.getString("email")
				);
				
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
 	
 	public int update_hit( int id ){
 		String sql = "update post set hit=hit+1 where id=?";
		int result = -1;
		// 드 커 프 리
		Connection conn = null;  PreparedStatement pstmt = null;   ResultSet  rset = null;
		String driver   = "oracle.jdbc.driver.OracleDriver";
		String url      = "jdbc:oracle:thin:@localhost:1521:xe";
		String user     = "scott"; 
		String pass = "tiger";
		
		
		try {
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url, user, pass);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			if (pstmt.executeUpdate() > 0) { result = 1; }
			
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
 	
// 	4. 글 수정하기
	public int update( PostDto dto ){
		String sql = "update post set title=?, content=? where id=? and pass=?";
		int result = -1;
		// 드 커 프 리
		Connection conn = null;  PreparedStatement pstmt = null;   ResultSet  rset = null;
		String driver   = "oracle.jdbc.driver.OracleDriver";
		String url      = "jdbc:oracle:thin:@localhost:1521:xe";
		String user     = "scott"; 
		String pass = "tiger";
		
		
		try {
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url, user, pass);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setInt(3, dto.getId());
			pstmt.setString(4, dto.getPass());
			
			if(pstmt.executeUpdate() > 0) { result = 1; }
			
			
			
			
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
	
//	5. 글 번호 해당하는 삭제
	public int delete( PostDto dto ){
		String sql = "delete from post where id=? and pass=?";
		int result = -1;
		// 드 커 프 리
		Connection conn = null;  PreparedStatement pstmt = null;   ResultSet  rset = null;
		String driver   = "oracle.jdbc.driver.OracleDriver";
		String url      = "jdbc:oracle:thin:@localhost:1521:xe";
		String user     = "scott"; 
		String pass = "tiger";
		
		
		try {
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url, user, pass);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getId());
			pstmt.setString(2, dto.getPass());
			
			if (pstmt.executeUpdate() > 0) {result = 1;}
			
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
/*
4. dao
[com.company.thejoa703.dto] - PostDao

| 컬럼명        | 데이터 타입       | 제약 조건       | 설명 |
	|---------------|-------------------|------------------|------|
	| `id`          | `NUMBER`          | `PRIMARY KEY`    | 게시글 고유 ID |
	| `app_user_id` | `NUMBER`          | `NOT NULL`       | 작성자 ID (`appuser` 테이블 참조) |
	| `title`       | `VARCHAR2(200)`   | `NOT NULL`       | 게시글 제목 |
	| `content`     | `CLOB`            | `NOT NULL`       | 게시글 내용 (대용량 텍스트, 최대 4GB) |
	| `pass`        | `VARCHAR2(100)`   | —                | 비회원 삭제용 비밀번호 |
	| `created_at`  | `DATE`            | `DEFAULT SYSDATE`| 작성일 |
	| `hit`         | `NUMBER`          | `DEFAULT 0`      | 조회수 |

1. 글쓰기 
	sql : insert into post (id, app_user_id, title, content, pass) 
			values ( post_seq.nextval, ?, ?, ?, ?);
			
	public int insert(PostDto dto){
		int result = -1;
		// 드 커 프 리
		return result;
	}

2. 전체글 가져오기, appuser 테이블에서 email도 같이 가져오기
	sql : select	p.*, u.email
		  from		post p join appuser u on p.app_user_id = u.app_user_id;
		  
	public ArrayList<PostDto dto> selectAll(){
		ArrayList<PostDto dto> result = new ArrayList<>();
		// 드 커 프 리
		return result;
	}

3. 글번호 해당하는 글가져오기
	sql : select * from post where id=?
 		  update post set hit=hit+1 where id=?;
 		  
 	public PostDto select(int id){
 		PostDto result = new PostDto();
 		// 드 커 프 리
 		return result;
 	}
 	
 	public int update_hit( int id ){
		int result = -1;
		// 드 커 프 리
		return result;
	}


4. 글 수정하기
	sql : update post set title=?, content=? where id=? and pass=?;
	public int update( PostDto dto ){
		int result = -1;
		// 드 커 프 리
		return result;
	}
	
	

5. 글 번호 해당하는 삭제
	sql : delete from post where id=? and pass=?;
	
	public int delete( PostDto dto ){
		int result = -1;
		// 드 커 프 리
		return result;
	}

*/

