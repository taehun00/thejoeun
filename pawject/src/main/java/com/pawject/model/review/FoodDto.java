package com.pawject.model.review;

public class FoodDto {

	private int brandid;
	private String brandname;
	private int foodid;
	private String foodname;
	private int pettypeid;
	private String typename;
	
	
	public FoodDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	
	public FoodDto(int brandid, String brandname, int foodid, String foodname, int pettypeid, String typename) {
		super();
		this.brandid = brandid;
		this.brandname = brandname;
		this.foodid = foodid;
		this.foodname = foodname;
		this.pettypeid = pettypeid;
		this.typename = typename;
	}





	public FoodDto(int brandid, String brandname, int pettypeid, String typename) {
		super();
		this.brandid = brandid;
		this.brandname = brandname;
		this.pettypeid = pettypeid;
		this.typename = typename;
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
				+ foodname + ", pettypeid=" + pettypeid + ", typename=" + typename + "]";
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





	public String getTypename() {
		return typename;
	}





	public void setTypename(String typename) {
		this.typename = typename;
	}





}
