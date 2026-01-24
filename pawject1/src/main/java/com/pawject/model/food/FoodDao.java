package com.pawject.model.food;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.pawject.model.food.FoodDto;





public class FoodDao {
	
	public int foodinsert(FoodDto fooddto) {
	    int result = -1;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rset = null;

	    String driver = "oracle.jdbc.driver.OracleDriver";
	    String url = "jdbc:oracle:thin:@localhost:1521:xe";
	    String user = "scott", pass = "tiger";

	    try {
	        Class.forName(driver);
	        conn = DriverManager.getConnection(url, user, pass);

	        String chkSql = " SELECT brandid FROM foodbrand WHERE brandname = ? AND country = ? ";
	        pstmt = conn.prepareStatement(chkSql);
	        pstmt.setString(1, fooddto.getBrandname());
	        pstmt.setString(2, fooddto.getCountry());
	        rset = pstmt.executeQuery();

	        if (rset.next()) {
	            int existingId = rset.getInt("brandid");
	            fooddto.setBrandid(existingId);
	            System.out.println("기존 브랜드 존재: brandid = " + existingId);
	        } else {
	            pstmt.close(); rset.close();

	            String maxSql = " SELECT NVL(MAX(brandid), 0) + 1 FROM foodbrand ";
	            pstmt = conn.prepareStatement(maxSql);
	            rset = pstmt.executeQuery();
	            if (rset.next()) {
	                int newId = rset.getInt(1);
	                fooddto.setBrandid(newId);
	                System.out.println("신규 브랜드 생성: brandid = " + newId);
	            }

	            pstmt.close(); rset.close();

	            String insertBrandSql = "INSERT INTO foodbrand ( brandid, brandname, country ) VALUES ( ?, ? , ? )";
	            pstmt = conn.prepareStatement(insertBrandSql);
	            pstmt.setInt(1, fooddto.getBrandid());
	            pstmt.setString(2, fooddto.getBrandname());
	            pstmt.setString(3, fooddto.getCountry());

	            System.out.println("브랜드 insert 실행: 브랜드id = " + fooddto.getBrandid() + ", 브랜드이름 = " + fooddto.getBrandname()+ ", 제조국 = " + fooddto.getCountry());
	            int brandInsertResult = pstmt.executeUpdate();
	            System.out.println("브랜드 insert 결과: " + brandInsertResult);
	            pstmt.close();
	        }

	        String foodSql = " INSERT INTO food (foodid, foodname, brandid, description, mainingredient, subingredient, pettypeid) "
	                + "VALUES (foodseq.nextval, ?, ?, ?, ?, ?, ?) ";
	        pstmt = conn.prepareStatement(foodSql);
	        pstmt.setString(1, fooddto.getFoodname());
	        pstmt.setInt(2, fooddto.getBrandid());
	        pstmt.setString(3, fooddto.getDescription());
	        pstmt.setString(4, fooddto.getMainingredient());
	        pstmt.setString(5, fooddto.getSubingredient());
	        pstmt.setInt(6, fooddto.getPettypeid());

	        System.out.println("food insert 실행: 브랜드id = " + fooddto.getBrandid());
	        int foodInsertResult = pstmt.executeUpdate();
	        System.out.println("food insert 결과: " + foodInsertResult);

	        if (foodInsertResult > 0) {
	            result = 1;
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try { if (rset != null) rset.close(); } catch (SQLException e) {}
	        try { if (pstmt != null) pstmt.close(); } catch (SQLException e) {}
	        try { if (conn != null) conn.close(); } catch (SQLException e) {}
	    }
	    return result;
	}

	
	public ArrayList<FoodDto> selectAll(){
		ArrayList<FoodDto> result = new ArrayList<>();
		String sql = 
			    " SELECT f.foodid, f.foodname, f.brandid, f.description, " +
			    "       f.mainingredient, f.subingredient, f.pettypeid, pt.pettypename " +
			    " FROM food f " +
			    " JOIN pettype pt ON f.pettypeid = pt.pettypeid " +
			    " ORDER BY f.foodid DESC ";


		Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
		String driver="oracle.jdbc.driver.OracleDriver";
		String 	 url="jdbc:oracle:thin:@localhost:1521:xe";
		String  user="scott" , pass="tiger";
		
		try { 
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, pass);
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next()) { 
				result.add(new FoodDto(						
						rset.getInt("foodid"),
						rset.getString("foodname"),
						rset.getInt("brandid"),	
						rset.getString("description"),
						rset.getString("mainingredient"),
						rset.getString("subingredient"),
						rset.getInt("pettypeid"))); 
			}
		 } catch (Exception e) { e.printStackTrace();
		 } finally { 
			if (rset != null ) { try { rset.close(); } catch(SQLException e){ e.printStackTrace(); } }
			if (pstmt != null ) { try { pstmt.close(); } catch(SQLException e){ e.printStackTrace(); } }
			if (conn != null ) { try { conn.close(); } catch(SQLException e){ e.printStackTrace(); } }
		 }
		return result;
	}
	
	
	
	public int update(FoodDto fooddto ){ 
		   int result = -1;
		   String sql = " UPDATE food "
		   		+ " SET foodname = ? , "
		   		+ "    description = ? , "
		   		+ "    mainingredient = ? , "
		   		+ "    subingredient = ? , "
		   		+ "    pettypeid = ? , "
		   		+ "    brandid = ? "
		   		+ " WHERE foodid = ? " ;
		   
		   Connection conn = null; 
		   PreparedStatement pstmt = null; 
		   ResultSet rset = null;
				
		   
		   String driver="oracle.jdbc.driver.OracleDriver";
		   String 	 url="jdbc:oracle:thin:@localhost:1521:xe";
		   String  user="scott" , pass="tiger";
		   
				try { 

					Class.forName(driver);
					conn = DriverManager.getConnection(url, user, pass);
					
					String chkSql = " SELECT brandid FROM foodbrand WHERE brandname = ? AND country = ? ";
					pstmt = conn.prepareStatement(chkSql);
			        pstmt.setString(1, fooddto.getBrandname());
			        pstmt.setString(2, fooddto.getCountry());
			        rset = pstmt.executeQuery();
			        
			        Integer newbrandid = null;
			        
			        if (rset.next()) {	// duplicate exist
			        	newbrandid = rset.getInt("brandid");
			        }else {	// new Brand insert
			        	pstmt.close();
			        	rset.close();
			        	String maxsql = " SELECT NVL(MAX(brandid), 0)+1 FROM foodbrand ";
			        	pstmt = conn.prepareStatement(maxsql);
			        	rset = pstmt.executeQuery();
			        	
			        	if (rset.next()) {
			        		newbrandid = rset.getInt(1);
			        	}
			        	
			        	pstmt.close();
			        	rset.close();
			        	
			        	String insertbrandsql = "INSERT INTO foodbrand (brandid, brandname, country) VALUES ( ? , ? , ? ) ";
			        	pstmt = conn.prepareStatement(insertbrandsql);
			        	pstmt.setInt(1,  newbrandid );
			        	pstmt.setString(2, fooddto.getBrandname());
			        	pstmt.setString(3, fooddto.getCountry());
			        	pstmt.executeUpdate();
			        	pstmt.close();
			        }
			        
			        fooddto.setBrandid(newbrandid);
					
				    pstmt = conn.prepareStatement(sql);
			        pstmt.setString(1, fooddto.getFoodname());
			        pstmt.setString(2, fooddto.getDescription());
			        pstmt.setString(3, fooddto.getMainingredient());
			        pstmt.setString(4, fooddto.getSubingredient());
			        pstmt.setInt(5, fooddto.getPettypeid());
			        pstmt.setInt(6, fooddto.getBrandid());
			        pstmt.setInt(7, fooddto.getFoodid());

					int presult = pstmt.executeUpdate();
					
					if   (presult > 0) {result = 1;}
					
				 } catch (Exception e) { 
					 e.printStackTrace();
				 } finally { 
					if (rset != null ) { try { rset.close(); } catch(SQLException e){ e.printStackTrace(); } }
					if (pstmt != null ) { try { pstmt.close(); } catch(SQLException e){ e.printStackTrace(); } }
					if (conn != null ) { try { conn.close(); } catch(SQLException e){ e.printStackTrace(); } }
				 }
		   
		   
		   return result;
		  }
	
		   
	public int delete(FoodDto fooddto ){ 
		   int result = -1;
		   String sql = " delete from food where foodid=? ";
		   
		   Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
				String driver = "oracle.jdbc.driver.OracleDriver";
				String 	 url = "jdbc:oracle:thin:@localhost:1521:xe";
				String  user = "scott" , pass="tiger";
				
				try { 

					Class.forName(driver);

					conn = DriverManager.getConnection(url, user, pass);

					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(		1,  fooddto.getFoodid());

					int presult = pstmt.executeUpdate();
					if(presult >0 ) {result = 1;}
				 } catch (Exception e) { e.printStackTrace();
				 } finally { 
					if (rset != null ) { try { rset.close(); } catch(SQLException e){ e.printStackTrace(); } }
					if (pstmt != null ) { try { pstmt.close(); } catch(SQLException e){ e.printStackTrace(); } }
					if (conn != null ) { try { conn.close(); } catch(SQLException e){ e.printStackTrace(); } }
				 }
		   
		   
		   return result;
		  }
	
	public int update_hit( int foodid ){ 
		   int result = -1;
		   String sql = 
		   		  " update food "
		   		+ " set hit=hit+1 "
		   		+ " where foodid=? ";
		   
		   
		   Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
				String driver="oracle.jdbc.driver.OracleDriver";
				String 	  url="jdbc:oracle:thin:@localhost:1521:xe";
				String  user="scott" , pass="tiger";
				try { 

					Class.forName(driver);

					conn = DriverManager.getConnection(url, user, pass);

					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, foodid);

					int presult = pstmt.executeUpdate();
					if (presult >0) {result = 1; }
						
					
					
				 } catch (Exception e) { e.printStackTrace();
				 } finally { 
					if (rset != null ) { try { rset.close(); } catch(SQLException e){ e.printStackTrace(); } }
					if (pstmt != null ) { try { pstmt.close(); } catch(SQLException e){ e.printStackTrace(); } }
					if (conn != null ) { try { conn.close(); } catch(SQLException e){ e.printStackTrace(); } }
				 }
		   
		   
		   return result;
		  }
	
	public FoodDto select(int foodid){ 
		  FoodDto result = new FoodDto();
		   String sql = 
				    " SELECT " +
				    "    f.foodid, " +
				    "    pt.pettypename, " +
				    "    fb.brandname, " +
				    "    f.foodname, " +
				    "    f.mainingredient, " +
				    "    f.subingredient, " +
				    "    fb.country, " +
				    "    f.description " +
				    " FROM food f " +
				    " JOIN pettype pt ON f.pettypeid = pt.pettypeid " +
				    " JOIN foodbrand fb ON f.brandid = fb.brandid " +
				    " WHERE f.foodid = ? ";

		   Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
				String driver="oracle.jdbc.driver.OracleDriver";
				String 	 url="jdbc:oracle:thin:@localhost:1521:xe";
				String  user="scott" , pass="tiger";
				try { 
					
					Class.forName(driver);

					conn = DriverManager.getConnection(url, user, pass);

					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1,  foodid);

					rset = pstmt.executeQuery();
					while(rset.next()) { 
						result = new FoodDto(
							    rset.getInt("foodid"),
							    rset.getString("pettypename"),
							    rset.getString("brandname"),
							    rset.getString("foodname"),
							    rset.getString("mainingredient"),
							    rset.getString("subingredient"),
							    rset.getString("country"),
							    rset.getString("description")
							);

					}
				 } catch (Exception e) { e.printStackTrace();
				 } finally { 
					if (rset != null ) { try { rset.close(); } catch(SQLException e){ e.printStackTrace(); } }
					if (pstmt != null ) { try { pstmt.close(); } catch(SQLException e){ e.printStackTrace(); } }
					if (conn != null ) { try { conn.close(); } catch(SQLException e){ e.printStackTrace(); } }
				 }
				
		   return result;
		  }

	
	//여기가 이식한 부분이영
	
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

	   			result.add(new FoodDto(
	   					rset.getInt("foodid"),
	   	                rset.getString("foodname"),
	   	                rset.getInt("brandid"),
	   	                rset.getInt("pettypeid"),
	   	                rset.getString("pettypename"),
	   	                rset.getString("brandname")
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
	   		//String sql="select b.brandid as brandid, b.brandname as brandname,  p.pettypeid as pettypeid, p.pettypename as pettypename from foodbrand b, pettype p  where exists ( select 1 from food f where f.brandid = b.brandid  and f.pettypeid = p.pettypeid )   order by b.brandname";
	   	    String sql = "select b.brandid as brandid, b.brandname as brandname, p.pettypeid as pettypeid, p.pettypename as pettypename from foodbrand b, pettype p where exists (select 1 from food f where f.brandid = b.brandid and f.pettypeid = p.pettypeid) order by brandname";
	   		try {
				
	   //드커프리			
	   			Class.forName(driver);
	   			conn=DriverManager.getConnection(url,user,pass);			
	   //pstmt
	   			
	   		pstmt=conn.prepareStatement(sql);
	   		rset=pstmt.executeQuery();
	   		
	   		while(rset.next()) {

	   			result.add(new FoodDto(
	   	                rset.getInt("brandid"),
	   	                rset.getInt("pettypeid"),
	   	                rset.getString("pettypename"),
	   	                rset.getString("brandname")
	   					
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
	
}
	
