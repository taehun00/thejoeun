package com.pawject.model.review;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

public class ReDao {

	
	
//	 1. 글쓰기 sql

	public int insert(ReDto dto) {
	    int result = -1;

	    // 기본
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rset = null;
	    String driver = "oracle.jdbc.driver.OracleDriver";
	    String url = "jdbc:oracle:thin:@localhost:1521:xe";
	    String user = "scott";
	    String pass = "tiger";

	    try {
	        Class.forName(driver);
	        conn = DriverManager.getConnection(url, user, pass);

	        // 기존 userid email로 교체 작업 중.
	        //email은 review 테이블 없어서 가져와야됨
	        String sql1 = "select userid from users where email = ?";
	        pstmt = conn.prepareStatement(sql1);
	        pstmt.setString(1, dto.getEmail());

	        rset = pstmt.executeQuery();

	        int userid = 0;
	        if (rset.next()) {
	            userid = rset.getInt("userid");
	        } else {
	            return -1;
	        }

	        rset.close();
	        pstmt.close();

	        String sql2 = "insert into review values (review_seq.nextval, ?, ?, ?, ?, '#', ?, ?, ?, SYSDATE)";
	        pstmt = conn.prepareStatement(sql2);
	        
	        pstmt.setInt(1, userid);
	        pstmt.setString(2, dto.getPassword());
	        pstmt.setInt(3, dto.getBrandid());
	        pstmt.setInt(4, dto.getFoodid());
	        pstmt.setInt(5, dto.getRating());
	        pstmt.setString(6, dto.getTitle());
	        pstmt.setString(7, dto.getReviewcomment());

	        if (pstmt.executeUpdate() > 0) {
	            result = 1;
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rset != null) rset.close();
	            if (pstmt != null) pstmt.close();
	            if (conn != null) conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    return result;  //위치 조심! 
	}
	
	   
//	 2. 전체 글 가져오기 리뷰테이블+사료테이블+유저테이블 종합

	   public ArrayList<ReDto> selectAll(){
	   		ArrayList<ReDto> result = new ArrayList();
	   		
	   	//기본
	   		Connection conn = null;  PreparedStatement pstmt = null;   ResultSet  rset = null;
	   		String driver   = "oracle.jdbc.driver.OracleDriver";
	   		String url      = "jdbc:oracle:thin:@localhost:1521:xe";
	   		String user     = "scott"; 
	   		String pass     = "tiger";
	   		

	   	String sql = "select r.reviewid as reviewid, b.brandid as brandid, b.brandname as brandname, f.foodname as foodname, r.foodimg as foodimg, r.rating as rating, r.title as title, r.reviewcomment as reviewcomment, u.nickname as nickname, r.createdat as createdat from review r join users u on(r.userid= u.userid) join food f on(r.foodid=f.foodid) join foodbrand b on(f.brandid=b.brandid) order by reviewid desc";
	   	try {
				Class.forName(driver);
				conn=DriverManager.getConnection(url,user,pass);
					
				//pstmt 입력자료x
				pstmt=conn.prepareStatement(sql);
				
				//rset
				rset=pstmt.executeQuery();

				while (rset.next()) {
					Timestamp ts = rset.getTimestamp("createdat");
					String created = (ts != null) ? ts.toLocalDateTime().toString() : "미기록";

					result.add(new ReDto(
					    rset.getInt("reviewid"),
					    rset.getInt("brandid"),
					    rset.getString("foodimg"),
					    rset.getInt("rating"),
					    rset.getString("title"),
					    rset.getString("reviewcomment"),
					    created ,
					    rset.getString("brandname"),
					    rset.getString("foodname"),
					    rset.getString("nickname")
					      // ✅ String으로 변환됨
					));
				}

				
				
				
				
	   		} catch (Exception e) {	e.printStackTrace();
			} finally {
				try {
					if(conn!=null){conn.close();}
					if(pstmt!=null){pstmt.close();}
					if(rset!=null){rset.close();}
				}catch (Exception e) {	e.printStackTrace();}
			}
	   	
	   		return result;
	   }
	
//	   3. 글 수정하기 sql
//	  update review set brandid=?, foodid=?, rating=?, title=?, reviewcomment=? where reviewid=? and userid=? and password='?;
	   
	   	//기본
  		Connection conn = null;  PreparedStatement pstmt = null;   ResultSet  rset = null;
  		String driver   = "oracle.jdbc.driver.OracleDriver";
  		String url      = "jdbc:oracle:thin:@localhost:1521:xe";
  		String user     = "scott"; 
  		String pass     = "tiger";
  		
  		String sql="update review set brandid=?, foodid=?, rating=?, title=?, reviewcomment=? where reviewid=? and userid=? and password=?";
  		
  		public int update(ReDto dto){
  	   		int result = -1;
  	   		
 	   		try {
				Class.forName(driver);
				conn=DriverManager.getConnection(url,user,pass);
	   		
				//pstmt
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, dto.getBrandid());
				pstmt.setInt(2, dto.getFoodid());
				pstmt.setInt(3, dto.getRating());
				pstmt.setString(4, dto.getTitle());
				pstmt.setString(5, dto.getReviewcomment());
				pstmt.setInt(6, dto.getReviewid());
				pstmt.setInt(7, dto.getUserid());
				pstmt.setString(8, dto.getPassword());
				
				//rset
				int presert=pstmt.executeUpdate();
				if(presert>0) {result = 1;}
				
	   		
	   		} catch (Exception e) {	e.printStackTrace();
			} finally {
				try {
					if(conn!=null){conn.close();}
					if(pstmt!=null){pstmt.close();}
					if(rset!=null){rset.close();}
				}catch (Exception e) {	e.printStackTrace();}
			}
  	   		
  	   		return result;
  	   }


//  	   4. 글 삭제하기 sql
//  		delete from review  where userid=? and password=?

 	   public int delete(ReDto dto){
 	   		int result = -1;
 	   		
 		   	//기본
 	  		Connection conn = null;  PreparedStatement pstmt = null;   ResultSet  rset = null;
 	  		String driver   = "oracle.jdbc.driver.OracleDriver";
 	  		String url      = "jdbc:oracle:thin:@localhost:1521:xe";
 	  		String user     = "scott"; 
 	  		String pass     = "tiger";
 	  		
 	  		String sql="delete from review  where reviewid=? and userid=? and password=?";
 	  		
 	   		
 	   		//수행내용
 	   		try {
				Class.forName(driver);
				conn=DriverManager.getConnection(url,user,pass);

				//pstmt
				
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, dto.getReviewid());
				pstmt.setInt(2, dto.getUserid());
				pstmt.setString(3, dto.getPassword());
				
				//rset
				int presert=pstmt.executeUpdate();
				if(presert>0) {result=1;}
				
				
	   		} catch (Exception e) {	e.printStackTrace();
			} finally {
				try {
					if(conn!=null){conn.close();}
					if(pstmt!=null){pstmt.close();}
					if(rset!=null){rset.close();}
				}catch (Exception e) {	e.printStackTrace();}
			}
 	   		
 	   		return result;
 	   }
 	  
 	   
 	   //5. 수정페이지에서 select 누락됨!!
 	   
		public ReDto select(int reviewid) {
			
			//sql : select * from review where reviewid=?
		   	//기본
	   		Connection conn = null;  PreparedStatement pstmt = null;   ResultSet  rset = null;
	   		String driver   = "oracle.jdbc.driver.OracleDriver";
	   		String url      = "jdbc:oracle:thin:@localhost:1521:xe";
	   		String user     = "scott"; 
	   		String pass     = "tiger";
	   		
	   		String sql="select r.reviewid as reviewid, r.userid as userid, b.brandid as brandid, b.brandname as brandname, f.foodid as foodid, f.foodname as foodname, r.foodimg as foodimg, r.rating as rating, r.title as title, r.reviewcomment as reviewcomment, u.nickname as nickname, r.createdat as createdat from review r join users u on(r.userid= u.userid) join food f on(r.foodid=f.foodid) join foodbrand b on(f.brandid=b.brandid) where r.reviewid =?";
	   		ReDto result = new ReDto();
			
	   		try {
				
	   //드커프리			
	   			Class.forName(driver);
	   			conn=DriverManager.getConnection(url,user,pass);			
	   //pstmt
	   			
	   		pstmt=conn.prepareStatement(sql);
	   		pstmt.setInt(1, reviewid);
	   		
	   		//rset
	   		rset=pstmt.executeQuery();

	   		while(rset.next()) {
				Timestamp ts = rset.getTimestamp("createdat");
				String created = (ts != null) ? ts.toLocalDateTime().toString() : "미기록";
	   			
				result = new ReDto(
					    rset.getInt("reviewid"),
					    rset.getInt("userid"),
					    rset.getInt("brandid"),
					    rset.getInt("foodid"),
					    rset.getString("foodimg"),
					    rset.getInt("rating"),
					    rset.getString("title"),
					    rset.getString("reviewcomment"),
					    created,
					    rset.getString("brandname"),
					    rset.getString("foodname"),
					    rset.getString("nickname")
					);
	   			
//select r.reviewid as reviewid, 
//b.brandname as brandname, 
//f.foodname as foodname, 
//r.foodimg as foodimg, 
//r.rating as rating, 
//r.title as title, 
//r.reviewcomment as reviewcomment,
//u.nickname as nickname, 
//r.createdat as createdat
	   			//foodid brandid
	   	

	   		}
	
	   		} catch (Exception e) {	e.printStackTrace();	
			} finally {
				try {
					if(conn!=null){conn.close();}
					if(pstmt!=null){pstmt.close();}
					if(rset!=null){rset.close();}
				}catch (Exception e) {	e.printStackTrace();}
			}
 	   	
	   		return result;
	   }

 	   

}

/*
 ----리뷰 테이블
--이름            널?       유형            
--------------- -------- ------------- 
--REVIEWID      NOT NULL NUMBER        
--USERID                 NUMBER        
--PASSWORD      NOT NULL VARCHAR2(50)  
--BRANDID                NUMBER        
--FOODID                 NUMBER        
--FOODIMG                VARCHAR2(300) 
--RATING                 NUMBER(1)     
--TITLE                  VARCHAR2(100) 
--REVIEWCOMMENT          VARCHAR2(500) 
--CREATEDAT              DATE       

   1. 글쓰기 sql
   insert into review values(review_seq.nextval, ? , ? , ? , ? , '#' , ?, ? , ? ,sysdate)

   public int insert(ReDto dto){
   		int result = -1;
   		
   		//수행내용
   		
   		return insert;
   }
   
   
   2. 전체 글 가져오기 리뷰테이블+사료테이블+유저테이블 종합
   select r.reviewid, b.brandname, b.foodname, r.foodimg, r.rating, r.title, r.reviewcomment, u.nickname, r.createdat from review r join testuser u on(r.userid= u.userid) join testbrand b on(r.foodid=b.foodid);
   
      public ArrayList<ReDto dto> selectALL(){
   		ArrayList<ReDto dto> result = new ArrayList();
   		
   		//수행내용
   		
   		return insert;
   }
   
   
   3. 글 수정하기 sql
   update review set rating=?, title=?, reviewcomment=? where userid=? and password=?
   
      public int update(ReDto dto){
   		int result = -1;
   		
   		//수행내용
   		
   		return insert;
   }
   
   
   4. 글 삭제하기 sql
	delete from review  where userid=? and password=?
	
	   public int delete(ReDto dto){
   		int result = -1;
   		
   		//수행내용
   		
   		return insert;
   }
   
 
 
 	   		try {
				Class.forName(driver);
				conn=DriverManager.getConnection(url,user,pass);
	   		
	   		
	   		} catch (Exception e) {	e.printStackTrace();
			} finally {
				try {
					if(conn!=null){conn.close();}
					if(pstmt!=null){pstmt.close();}
					if(rset!=null){rset.close();}
				}catch (Exception e) {	e.printStackTrace();}
			}
 
 
 */