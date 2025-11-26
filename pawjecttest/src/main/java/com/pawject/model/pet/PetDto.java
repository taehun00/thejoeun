package com.pawject.model.pet;

import java.time.LocalDateTime;

public class PetDto {
	private int petid;
	private int userid;
	private String petname;
	private String petbreed;
	private String birthdate;
	private int pettypeid;
	private LocalDateTime createdat;
	public PetDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PetDto(int petid, int userid, String petname, String petbreed, String birthdate, int pettypeid,
			LocalDateTime createdat) {
		super();
		this.petid = petid;
		this.userid = userid;
		this.petname = petname;
		this.petbreed = petbreed;
		this.birthdate = birthdate;
		this.pettypeid = pettypeid;
		this.createdat = createdat;
	}
	@Override
	public String toString() {
		return "PetDto [petid=" + petid + ", userid=" + userid + ", petname=" + petname + ", petbreed=" + petbreed
				+ ", birthdate=" + birthdate + ", pettypeid=" + pettypeid + ", createdat=" + createdat + "]";
	}
	public int getPetid() {
		return petid;
	}
	public void setPetid(int petid) {
		this.petid = petid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getPetname() {
		return petname;
	}
	public void setPetname(String petname) {
		this.petname = petname;
	}
	public String getPetbreed() {
		return petbreed;
	}
	public void setPetbreed(String petbreed) {
		this.petbreed = petbreed;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public int getPettypeid() {
		return pettypeid;
	}
	public void setPettypeid(int pettypeid) {
		this.pettypeid = pettypeid;
	}
	public LocalDateTime getCreatedat() {
		return createdat;
	}
	public void setCreatedat(LocalDateTime createdat) {
		this.createdat = createdat;
	}
	
	
}
