package com.pawject.dto.food;

import java.util.Date;

import lombok.Data;

@Data
public class FoodDtoForList {
	
	private int foodid;
	private String foodname;
	private int brandid;
	private int pettypeid;
	private String createdat;
	private String updatedat;
	private String brandname;

}

/*
		<select resultMap="FoodMap"  id="foodselectForList">
			foodid,
			foodname, 
	        brandid,  
	        brandname,
	        pettypeid,
	        createdat, 
	        updatedat
	
 
		<select resultMap="FoodMap"  id="foodselectForList">
		select f.foodid as foodid, f.foodname as foodname, 
        b.brandid as brandid, b.brandname as brandname,
        f.pettypeid as pettypeid,
        TO_CHAR(f.createdat, 'YYYY-MM-DD') as createdat, 
         TO_CHAR(f.updatedat , 'YYYY-MM-DD') as updatedat
		from food f join foodbrand b on(f.brandid=b.brandid)
		order by f.foodid desc
		</select> 
 
FOODID         NOT NULL NUMBER        
FOODNAME       NOT NULL VARCHAR2(100) 
BRANDID        NOT NULL NUMBER        
DESCRIPTION             VARCHAR2(500) 
MAININGREDIENT NOT NULL VARCHAR2(200) 
SUBINGREDIENT           VARCHAR2(200) 
PETTYPEID      NOT NULL NUMBER        
CATEGORY       NOT NULL VARCHAR2(50)  
PETAGEGROUP             VARCHAR2(50)  
ISGRAINFREE             CHAR(1)       
CALORIE                 NUMBER(5,1)   
FOODTYPE                VARCHAR2(20)  
FOODIMG                 VARCHAR2(300) 
 */