package com.pawject.model.food;

public class FoodDto {
	
	private int foodid; 
	private String foodname;
	private int brandid;
	private String description;
	private String mainingredient;
	private String subingredient;
	private int pettypeid;
	
	////////////////////////////
	private String typename; 
	////////////////////////////
	private String brandname;	
	private String country;	
	////////////////////////////
	
	public FoodDto() { super();  }
	public FoodDto(int foodid, String foodname, int brandid, String description,  String mainingredient, String subingredient, int pettypeid) { super(); this.foodid = foodid; this.foodname = foodname; this.brandid = brandid; this.description = description; this.mainingredient = mainingredient; this.subingredient = subingredient; this.pettypeid = pettypeid; }
	public FoodDto(int foodid, String typename, String brandname, String foodname, String mainingredient, String subingredient, String country, String description) { this.foodid = foodid; this.typename = typename; this.brandname = brandname; this.foodname = foodname; this.mainingredient = mainingredient; this.subingredient = subingredient; this.country = country; this.description = description; }

	public String getTypename() { return typename; } public void setTypename(String typename) { this.typename = typename; } public int getFoodid() { return foodid; } public void setFoodid(int foodid) { this.foodid = foodid; } public String getFoodname() { return foodname; } public void setFoodname(String foodname) { this.foodname = foodname; } public int getBrandid() { return brandid; } public void setBrandid(int brandid) { this.brandid = brandid; } public String getDescription() { return description; } public void setDescription(String description) { this.description = description; }
	public String getCountry() { return country; } public void setCountry(String country) { this.country = country; }
	public String getBrandname() { return brandname; }
	public void setBrandname(String brandname) { this.brandname = brandname; }
	public String getMainingredient() { return mainingredient; } public void setMainingredient(String mainingredient) { this.mainingredient = mainingredient; } public String getSubingredient() { return subingredient; } public void setSubingredient(String subingredient) { this.subingredient = subingredient; } public int getPettypeid() { return pettypeid; } public void setPettypeid(int pettypeid) { this.pettypeid = pettypeid; }
	@Override public String toString() { return "FoodDto [foodid=" + foodid + ", foodname=" + foodname + ", brandid=" + brandid + ", description=" + description + ", mainingredient=" + mainingredient + ", subingredient=" + subingredient + ", pettypeid=" + pettypeid + ", typename=" + typename + ", brandname=" + brandname + ", country=" + country + "]"; }
	
}

