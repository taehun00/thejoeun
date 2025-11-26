package com.pawject.model.review;

public class ReDto {

	//리뷰
	private int reviewid;
	private int userid;
	private String password;
	private int brandid;
	private int foodid;
	private String foodimg;
	private int rating;
	private String title;
	private String reviewcomment;
	private String createdat;


	//푸드
	private String brandname;
	private String foodname;
	private String foodingre;
	
	//유저
	private String email;
	private String nickname;

	
	
	public ReDto() {
		super();
		// TODO Auto-generated constructor stub
	}

		//dao select
		public ReDto(int reviewid, String brandname, String foodname, String foodimg,
		            int rating, String title, String reviewcomment, String nickname, String createdat) {
		   this.reviewid = reviewid;
		   this.brandname = brandname;
		   this.foodname = foodname;
		   this.foodimg = foodimg;
		   this.rating = rating;
		   this.title = title;
		   this.reviewcomment = reviewcomment;
		   this.nickname = nickname;
		   this.createdat = createdat;
		}
			
			//전체
	public ReDto(int reviewid, int userid, String password, int brandid, int foodid, String foodimg, int rating,
			String title, String reviewcomment, String createdat, String brandname, String foodname, String foodingre,
			String email, String nickname) {
		super();
		this.reviewid = reviewid;
		this.userid = userid;
		this.password = password;
		this.brandid = brandid;
		this.foodid = foodid;
		this.foodimg = foodimg;
		this.rating = rating;
		this.title = title;
		this.reviewcomment = reviewcomment;
		this.createdat = createdat;
		this.brandname = brandname;
		this.foodname = foodname;
		this.foodingre = foodingre;
		this.email = email;
		this.nickname = nickname;
	}
	
	
	
	



//	rset.getInt("reviewid"), rset.getInt("userid"), rset.getInt("brandid"), rset.getString("brandname")
//	rset.getInt("foodid"), rset.getString("foodname"),
//	rset.getString("foodimg"), rset.getInt("rating"), rset.getString("title"),
//	rset.getString("reviewcomment"), rset.getString("nickname"), created
					
	

	public ReDto(int reviewid, int brandid, String foodimg, int rating, String title, String reviewcomment,
		String createdat, String brandname, String foodname, String nickname) {
	super();
	this.reviewid = reviewid;
	this.brandid = brandid;
	this.foodimg = foodimg;
	this.rating = rating;
	this.title = title;
	this.reviewcomment = reviewcomment;
	this.createdat = createdat;
	this.brandname = brandname;
	this.foodname = foodname;
	this.nickname = nickname;
}

	public ReDto(int reviewid, int userid, int brandid, int foodid, String foodimg, int rating, String title,
		String reviewcomment, String createdat, String brandname, String foodname, String nickname) {
	super();
	this.reviewid = reviewid;
	this.userid = userid;
	this.brandid = brandid;
	this.foodid = foodid;
	this.foodimg = foodimg;
	this.rating = rating;
	this.title = title;
	this.reviewcomment = reviewcomment;
	this.createdat = createdat;
	this.brandname = brandname;
	this.foodname = foodname;
	this.nickname = nickname;
}
	


	@Override
	public String toString() {
		return "ReDto [reviewid=" + reviewid + ", userid=" + userid + ", password=" + password + ", brandid=" + brandid
				+ ", foodid=" + foodid + ", foodimg=" + foodimg + ", rating=" + rating + ", title=" + title
				+ ", reviewcomment=" + reviewcomment + ", createdat=" + createdat + ", brandname=" + brandname
				+ ", foodname=" + foodname + ", foodingre=" + foodingre + ", email=" + email + ", nickname=" + nickname
				+ "]";
	}


	public int getReviewid() {
		return reviewid;
	}



	public void setReviewid(int reviewid) {
		this.reviewid = reviewid;
	}



	public int getUserid() {
		return userid;
	}



	public void setUserid(int userid) {
		this.userid = userid;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public int getBrandid() {
		return brandid;
	}



	public void setBrandid(int brandid) {
		this.brandid = brandid;
	}



	public int getFoodid() {
		return foodid;
	}



	public void setFoodid(int foodid) {
		this.foodid = foodid;
	}



	public String getFoodimg() {
		return foodimg;
	}



	public void setFoodimg(String foodimg) {
		this.foodimg = foodimg;
	}



	public int getRating() {
		return rating;
	}



	public void setRating(int rating) {
		this.rating = rating;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getReviewcomment() {
		return reviewcomment;
	}



	public void setReviewcomment(String reviewcomment) {
		this.reviewcomment = reviewcomment;
	}



	public String getCreatedat() {
		return createdat;
	}



	public void setCreatedat(String createdat) {
		this.createdat = createdat;
	}



	public String getBrandname() {
		return brandname;
	}



	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}



	public String getFoodname() {
		return foodname;
	}



	public void setFoodname(String foodname) {
		this.foodname = foodname;
	}



	public String getFoodingre() {
		return foodingre;
	}



	public void setFoodingre(String foodingre) {
		this.foodingre = foodingre;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getNickname() {
		return nickname;
	}



	public void setNickname(String nickname) {
		this.nickname = nickname;
	}



}	

	//
	
	
	

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
 
 	//푸드
	BRANDID   NOT NULL NUMBER        
	BRANDNAME          VARCHAR2(100) 
	FOODID             NUMBER        
	FOODNAME           VARCHAR2(100) 
	FOODINGRE          VARCHAR2(100) 
	
	
	//유저
	--------- -------- ------------- 
	USERID    NOT NULL NUMBER        
	EMAIL              VARCHAR2(200) 
	NICKNAME  NOT NULL VARCHAR2(100) 
	PASSWORD  NOT NULL VARCHAR2(100) 
	CREATEDAT NOT NULL VARCHAR2(200) 
	
	
 */