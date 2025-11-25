package com.pawject.model;

public class FoodDto {

	private int brandid;
	private String brandname;
	private int foodid;
	private String foodname;
	private int pettypeid;
	private String pettypename;
	
	
	public FoodDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	
	public FoodDto(int brandid, String brandname, int foodid, String foodname, int pettypeid, String pettypename) {
		super();
		this.brandid = brandid;
		this.brandname = brandname;
		this.foodid = foodid;
		this.foodname = foodname;
		this.pettypeid = pettypeid;
		this.pettypename = pettypename;
	}





	public FoodDto(int brandid, String brandname, int pettypeid, String pettypename) {
		super();
		this.brandid = brandid;
		this.brandname = brandname;
		this.pettypeid = pettypeid;
		this.pettypename = pettypename;
	}





	public FoodDto(int brandid, int foodid, String foodname) {
		super();
		this.brandid = brandid;
		this.foodid = foodid;
		this.foodname = foodname;
	}





	@Override
	public String toString() {
		return "FoodDto [brandid=" + brandid + ", brandname=" + brandname + ", foodid=" + foodid + ", foodname="
				+ foodname + ", pettypeid=" + pettypeid + ", pettypename=" + pettypename + "]";
	}
	public int getBrandid() {
		return brandid;
	}
	public void setBrandid(int brandid) {
		this.brandid = brandid;
	}
	public String getBrandname() {
		return brandname;
	}
	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}
	public int getFoodid() {
		return foodid;
	}
	public void setFoodid(int foodid) {
		this.foodid = foodid;
	}
	public String getFoodname() {
		return foodname;
	}
	public void setFoodname(String foodname) {
		this.foodname = foodname;
	}
	public int getPettypeid() {
		return pettypeid;
	}
	public void setPettypeid(int pettypeid) {
		this.pettypeid = pettypeid;
	}
	public String getPettypename() {
		return pettypename;
	}
	public void setPettypename(String pettypename) {
		this.pettypename = pettypename;
	}


	
}
