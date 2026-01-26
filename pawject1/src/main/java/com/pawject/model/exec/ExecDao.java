package com.pawject.model.exec;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ExecDao {
	//1. [글쓰기] sql
	public int insert ( ExecDto dto ) {  // sql에서 ?가 1개라면   (변수 id) 
									 //        ?가 여러개라면 (생성자 있는 클래스명)  
		int result = -1;
		String sql =" insert into exerciseinfo (execid, exectype, description, avgkcal30min,"
				   + " exectargetmin, suitablefor, intensitylevel )"
				   + " values  (exerciseinfo_seq.nextval,? ,?, ?, ?, ?, ?)";
		//드커프리
		Connection conn = null;  PreparedStatement pstmt = null; ResultSet rset = null;
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "scott", pass = "tiger";
		try {
			//1. 드리이버연동
			Class.forName(driver);
			//2. 커넥션
			conn = DriverManager.getConnection(url, user, pass);
			//3. PSTMT
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getExectype()         );
			pstmt.setString(2, dto.getDescription()      );
			pstmt.setFloat( 3, dto.getAvgkcal30min()     );
			pstmt.setInt(   4, dto.getExectargetmin()    );
			pstmt.setString(5, dto.getSuitablefor()      );
			pstmt.setString(6, dto.getIntensitylevel()   );
	        //4. RESULT (  select : executeQuery  / insert,update, delete: executeUpdate)
			if(pstmt.executeUpdate() > 0) { result =1; }
		} catch (Exception e) {e.printStackTrace();
		}finally {
			if( rset  != null ) { try { rset.close(); } catch (Exception e) { e.printStackTrace(); } }
			if( pstmt != null ) { try { pstmt.close(); } catch (Exception e) { e.printStackTrace(); } }
			if( conn  != null ) { try { conn.close(); } catch (Exception e) { e.printStackTrace(); } }
		}
		return result; 
	};
	
	//////////////////////////
	//2. [전체글가져오기] sql
	public ArrayList<ExecDto> selectAll() {
		ArrayList<ExecDto> result = new ArrayList<ExecDto>();
//		int result = -1;
		String sql = "select * from exerciseinfo ";
		//드커프리
		Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "scott", pass = "tiger";
		/////////////////////////
		try {
			//1. 드라이브연동
			Class.forName(driver);
			//2. 커넥션 
			conn = DriverManager.getConnection(url, user, pass);
			//3. PreparedStatement
			pstmt = conn.prepareStatement(sql); 
			
			//4. 결과
			rset = pstmt.executeQuery();
			while(rset.next()) {
				result.add(  new ExecDto(
						rset.getInt("execid"), rset.getString("exectype"), rset.getString("description"),
						rset.getFloat("avgkcal30min"), rset.getInt("exectargetmin"), rset.getString("suitablefor"),
						rset.getString("intensitylevel")
				));
			}
			
		}catch(Exception e) { e.printStackTrace();
		}finally {
			if( rset  != null ) { try { rset.close(); } catch (Exception e) { e.printStackTrace(); } }
			if( pstmt != null ) { try { pstmt.close(); } catch (Exception e) { e.printStackTrace(); } }
			if( conn  != null ) { try { conn.close(); } catch (Exception e) { e.printStackTrace(); } }
		}
		/////////////////////////
		return result;
	};
	
	
	//3. [상세보기] 해당하는 번호의 글가져오기 sql :  
	public ExecDto select(int execid) {
		ExecDto result = new ExecDto();
//		int result = -1;
		String sql = "select * from exerciseinfo  where  execid=?";
		//드커프리
		Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "scott", pass = "tiger";
		/////////////////////////
		try {
			//1. 드라이브연동
			Class.forName(driver);
			//2. 커넥션 
			conn = DriverManager.getConnection(url, user, pass);
			//3. PreparedStatement
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, execid);
			//4. 결과
			rset = pstmt.executeQuery();
			while(rset.next()) {
				result = new ExecDto(
						rset.getInt("execid"), rset.getString("exectype"), rset.getString("description"),
						rset.getFloat("avgkcal30min"), rset.getInt("exectargetmin"), rset.getString("suitablefor"),
						rset.getString("intensitylevel")
				);
			}
			
		}catch(Exception e) { e.printStackTrace();
		}finally {
			if( rset  != null ) { try { rset.close(); } catch (Exception e) { e.printStackTrace(); } }
			if( pstmt != null ) { try { pstmt.close(); } catch (Exception e) { e.printStackTrace(); } }
			if( conn  != null ) { try { conn.close(); } catch (Exception e) { e.printStackTrace(); } }
		}
		/////////////////////////
		return result;
	}; 
	
	//4. [글수정하기]  sql :  
	public int update(ExecDto dto) {  
		int result = -1;
		String sql = " update exerciseinfo set exectype=?, description=?, avgkcal30min=?,"
				   + " exectargetmin=?, suitablefor=?, intensitylevel=?"
				   + " where  execid=?";
		//드커프리
		Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "scott", pass = "tiger";
		/////////////////////////
		try {
			//1. 드라이브연동 
			Class.forName(driver);
			//2. 커넥션 
			conn = DriverManager.getConnection(url, user, pass);
			//3. PreparedStatement
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getExectype()         );
			pstmt.setString(2, dto.getDescription()      );
			pstmt.setFloat( 3, dto.getAvgkcal30min()     );
			pstmt.setInt(   4, dto.getExectargetmin()    );
			pstmt.setString(5, dto.getSuitablefor()      );
			pstmt.setString(6, dto.getIntensitylevel()   );
			pstmt.setInt(7, dto.getExecid()         );

			//4. 결과
			int presult = pstmt.executeUpdate();
			if(presult > 0) {result = 1;}
		}catch(Exception e) { e.printStackTrace();
		}finally {
			if( rset  != null ) { try { rset.close(); } catch (Exception e) { e.printStackTrace(); } }
			if( pstmt != null ) { try { pstmt.close(); } catch (Exception e) { e.printStackTrace(); } }
			if( conn  != null ) { try { conn.close(); } catch (Exception e) { e.printStackTrace(); } }
		}
		/////////////////////////
		return result;
	};
	
	//5. [글삭제하기] 해당하는 번호의 글삭제하기. sql:
	public int delete(ExecDto dto) {
		int result = -1; 
		String sql = " delete from exerciseinfo where execid=?";
		//드커프리
		Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "scott", pass = "tiger";
		
		/////////////////////////
		try {
			//1. 드라이브연동 
			Class.forName(driver);
			//2. 커넥션 
			conn = DriverManager.getConnection(url, user, pass);
			//3. PreparedStatement
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getExecid());
			//4. 결과
			int presult = pstmt.executeUpdate();
			if(presult > 0 ) {result = 1;}
			
		}catch(Exception e) { e.printStackTrace();
		}finally {
			if( rset  != null ) { try { rset.close(); } catch (Exception e) { e.printStackTrace(); } }
			if( pstmt != null ) { try { pstmt.close(); } catch (Exception e) { e.printStackTrace(); } }
			if( conn  != null ) { try { conn.close(); } catch (Exception e) { e.printStackTrace(); } }
		}
		/////////////////////////
		
		return result;
	};
	
}//end class




