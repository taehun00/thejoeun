package com.pawject.model.review;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class FoodDao {
	
	
//	private int brandid;
//	private String brandname;
//	private int foodid;
//	private String foodname;
//	private int pettypeid;
//	private String pettypename;
	
	
	public ArrayList<FoodDto> FoodList() {
	    ArrayList<FoodDto> result = new ArrayList<>();

   		
	   	//기본
	   		Connection conn = null;  PreparedStatement pstmt = null;   ResultSet  rset = null;
	   		String driver   = "oracle.jdbc.driver.OracleDriver";
	   		String url      = "jdbc:oracle:thin:@localhost:1521:xe";
	   		String user     = "scott"; 
	   		String pass     = "tiger";
	   		
	   //sql
	   		//String sql = "select b.brandid as brandid, b.brandname as brandname, d.foodid as foodid, d.foodname as foodname  from foodbrand b join food d on(b.brandid=d.brandid)";
	   		String sql ="select distinct f.foodid as foodid, f.foodname as foodname, f.brandid as brandid, b.brandname as brandname, f.pettypeid as pettypeid, p.pettypename as pettypename from food f join foodbrand b on f.brandid = b.brandid join pettype p on f.pettypeid = p.pettypeid order by b.brandname, f.foodname";

	   		
	   		
	   		
	   
	   
	   try {
				
	   //드커프리			
	   			Class.forName(driver);
	   			conn=DriverManager.getConnection(url,user,pass);			
	   //pstmt
	   			
	   		pstmt=conn.prepareStatement(sql);
	   		rset=pstmt.executeQuery();
	   		
	   		while(rset.next()) {

	   			result.add(new FoodDto(rset.getInt("brandid"), rset.getString("brandname"),
	   					rset.getInt("foodid"), rset.getString("foodname"),
	   					rset.getInt("pettypeid"), rset.getString("pettypename")
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
	
	
	
	public ArrayList<FoodDto> BrandList() {
	    ArrayList<FoodDto> result = new ArrayList<>();

   		
	   	//기본
	   		Connection conn = null;  PreparedStatement pstmt = null;   ResultSet  rset = null;
	   		String driver   = "oracle.jdbc.driver.OracleDriver";
	   		String url      = "jdbc:oracle:thin:@localhost:1521:xe";
	   		String user     = "scott"; 
	   		String pass     = "tiger";
	   		
	   		
	   //sql
	   		//String sql = "select b.brandid as brandid, b.brandname as brandname, d.foodid as foodid, d.foodname as foodname  from foodbrand b join food d on(b.brandid=d.brandid)";
//	   		String sql="select distinct brandid, brandname from foodbrand";
	   		String sql="select b.brandid as brandid, b.brandname as brandname,  p.pettypeid as pettypeid, p.pettypename as pettypename from foodbrand b, pettype p  where exists ( select 1 from food f where f.brandid = b.brandid  and f.pettypeid = p.pettypeid )   order by b.brandname";
	   		try {
				
	   //드커프리			
	   			Class.forName(driver);
	   			conn=DriverManager.getConnection(url,user,pass);			
	   //pstmt
	   			
	   		pstmt=conn.prepareStatement(sql);
	   		rset=pstmt.executeQuery();
	   		
	   		while(rset.next()) {

	   			result.add(new FoodDto( rset.getInt("brandid"), rset.getString("brandname"),rset.getInt("pettypeid"), rset.getString("pettypename")));

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
