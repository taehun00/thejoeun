package com.pawject.model.dis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class PawPostDao {
	
	
//1. create ( insert) 	
	public int insert(PawPostDto dto) {  //Dto
		int result=-1;
		String sql = "  insert into disease (disno,disname,disex,kindpet,infval,mannote)"
				   + "  values(disno_seq.nextval, ?, ?, ?, ?, ?)  ";
		
		Connection conn = null; PreparedStatement pstmt = null; ResultSet  rset = null;
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "scott";
		String pass = "tiger";
		
		try {
				//1. 드라이버 연동
				Class.forName(driver);
				//2. 커넥션
				conn = DriverManager.getConnection(url,user,pass);
				//3. PSTMT
				pstmt = conn.prepareStatement(sql);
				
				
				pstmt.setString(1, dto.getDisname());
				pstmt.setString(2, dto.getDisex());
				pstmt.setString(3, dto.getKindpet());
				pstmt.setString(4, dto.getInfval() );
				pstmt.setString(5, dto.getMannote());
				//4. result (select:executeQuery / insert, update, delete: executeUpdate)
				if(pstmt.executeUpdate() > 0) {result = 1;}
			
			
			
		}catch (Exception e) {e.printStackTrace();
		
		}finally {
			if(rset != null) {try {rset.close();}catch(SQLException e) {e.printStackTrace();}}
			if(pstmt != null) {try {pstmt.close();}catch(SQLException e) {e.printStackTrace();}}
			if(conn != null) {try {conn.close();}catch(SQLException e) {e.printStackTrace();}}
		}	
		return result;
		
	}// end insert
	
	
	
//2. read -> [전체보기] 전체글가져오기
	  	public ArrayList<PawPostDto> selectAll(){
	  		ArrayList<PawPostDto> result = new ArrayList<>();
	  		String sql = " select * from disease order by disno desc";
	  		
	  		Connection conn = null; PreparedStatement pstmt = null;  ResultSet rset = null;
			String driver="oracle.jdbc.driver.OracleDriver";
			String    url="jdbc:oracle:thin:@localhost:1521:xe";
			String   user="scott" , pass="tiger";
			try {
				//1. 드라이버연동
				Class.forName(driver);
				//2. 커넥션
				conn = DriverManager.getConnection(url, user, pass);
				//3. PSTMT
				pstmt = conn.prepareStatement(sql);
			
				//4. result (select : executeQuery  / insert, update, delete: executeUpdate)
				rset = pstmt.executeQuery();
				while(rset.next()) {
					result.add( new PawPostDto (
							rset.getInt("disno"), rset.getString("disname")
							,rset.getString("disex"), rset.getString("kindpet")
							,rset.getString("infval"), rset.getString("mannote")
							));
				}
			}catch (Exception e) {e.printStackTrace();
			}finally {
				if( rset != null) {try {rset.close();}catch (SQLException e) {e.printStackTrace();}}
				if( pstmt != null) {try {pstmt.close();}catch (SQLException e) {e.printStackTrace();}}
				if( conn != null) {try {conn.close();}catch (SQLException e) {e.printStackTrace();}}
			}
			return result;
	  	} // end selectAll
	  	
	  	
	  	public PawPostDto select(int id){
	  		PawPostDto result = new PawPostDto();
	  		String sql = " select * from disease where disno=? ";
	  		
	  		Connection conn = null; PreparedStatement pstmt = null;  ResultSet rset = null;
			String driver="oracle.jdbc.driver.OracleDriver";
			String    url="jdbc:oracle:thin:@localhost:1521:xe";
			String   user="scott" , pass="tiger";
			try {
				//1. 드라이버연동
				Class.forName(driver);
				//2. 커넥션
				conn = DriverManager.getConnection(url, user, pass);
				//3. PSTMT
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, id);
				//4. result (select : executeQuery  / insert, update, delete: executeUpdate)
				rset = pstmt.executeQuery();
				while(rset.next()) {
					result =  new PawPostDto (
							rset.getInt("disno"), rset.getString("disname")
							,rset.getString("disex"), rset.getString("kindpet")
							,rset.getString("infval"), rset.getString("mannote")
							);
				}
			}catch (Exception e) {e.printStackTrace();
			}finally {
				if( rset != null) {try {rset.close();}catch (SQLException e) {e.printStackTrace();}}
				if( pstmt != null) {try {pstmt.close();}catch (SQLException e) {e.printStackTrace();}}
				if( conn != null) {try {conn.close();}catch (SQLException e) {e.printStackTrace();}}
			}
			return result;
	  	} // end selectAll2
	
	  	
//3. update	  	
	  	public int update(PawPostDto dto ) {
	  		int result = -1;
	  		String sql = " update disease set disname=?, disex=?, kindpet=?, infval=?, mannote=? where disno=? ";
	  		Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
	  		String driver = "oracle.jdbc.driver.OracleDriver";
	  		String url = "jdbc:oracle:thin:@localhost:1521:xe";
	  		String user = "scott", pass = "tiger";
	  		// 드커프리
	  		try {
	  			//1. 드라이버 연동
	  			Class.forName(driver);
	  			//2. 커넥션
	  			conn = DriverManager.getConnection(url, user, pass);
	  			//3. PSTMT
	  			pstmt = conn.prepareStatement(sql);
	  			pstmt.setString(1, dto.getDisname());
	  			pstmt.setString(2, dto.getDisex());
	  			pstmt.setString(3, dto.getKindpet());
	  			pstmt.setString(4, dto.getInfval());
	  			pstmt.setString(5, dto.getMannote());
	  			pstmt.setInt(6, dto.getDisno());
	  			
	  			//4. result
	  			int presult = pstmt.executeUpdate();
	  			if(presult > 0 ) {result = 1;}
	  			
	  			
	  		}catch(Exception e) {e.printStackTrace();
	  			
	  		}finally {
	  			if( rset  != null ) { try { rset.close(); } catch (SQLException e) { e.printStackTrace(); } }
				if( pstmt != null ) { try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); } }
				if( conn  != null ) { try { conn.close(); } catch (SQLException e) { e.printStackTrace(); } }
	  			
	  		}
	  		return result;
	  		
	  	}// end update
	
	  	
// 4. delete	  	
	  	public int delete(PawPostDto dto) {
	  		int result = -1;
	  		String sql = " delete from disease where disno = ? ";
	  		
	  		Connection conn = null; PreparedStatement pstmt = null;  ResultSet rset = null;
			String driver="oracle.jdbc.driver.OracleDriver";
			String    url="jdbc:oracle:thin:@localhost:1521:xe";
			String   user="scott" , pass="tiger";
			
			try {
				//1. 드라이버 연동
				Class.forName(driver);
				//2. 커넥션
				conn = DriverManager.getConnection(url, user, pass);
				//3. PSTMT
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, dto.getDisno());
				//4. result
				int presult = pstmt.executeUpdate();
				if(presult > 0) {result = 1;}
				
			}catch (Exception e) {e.printStackTrace();
				
			}finally {
				if( rset != null)  {try { rset.close();} catch (SQLException e) {e.printStackTrace();}}
				if( pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
				if( conn != null)  {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
				
			}
	  		return result;
	  	}// end delete
 

}// end class




/*
 * 
 SQL> desc disease
 Name                                      Null?    Type
 ----------------------------------------- -------- ----------------------------
 DISNO                                     NOT NULL NUMBER
 DISNAME                                            VARCHAR2(50)
 DISEX                                              VARCHAR2(150)
 KINDPET                                            VARCHAR2(200)
 INFVAL                                             VARCHAR2(100)
 MANNOTE                                            VARCHAR2(200)
 
 
 * public class PawPostDto {
	private int disno;
	private String disname;
	private String disex;
	private String kindpet;
	private String infval;
	private String mannote;
	
 create: insert into disease(disno,disname,disex,kindpet,infval,mannote) values(disno_seq.nextval, ?, ?, ?, ?, ?);
 read : select * from disease;
  		select * from disease where disno=?;
 update: update disease set disname=?,disex=?,kindpet=?, infval = ?, mannote = ?  where disno = ?;
 delete: delete from disease where disno = ?;  	
 
 Dao 작성이 어렵습니다. 
 
 */

