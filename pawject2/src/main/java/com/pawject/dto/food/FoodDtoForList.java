package com.pawject.dto.food;

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
