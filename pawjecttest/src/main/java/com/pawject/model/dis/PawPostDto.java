package com.pawject.model.dis;

/*
 
  SQL> desc disease
 Name                                      Null?    Type
 ----------------------------------------- -------- ----------------------------
 DISNO                                     NOT NULL NUMBER
 DISNAME                                            VARCHAR2(50)
 DISEX                                              VARCHAR2(150)
 KINDPET                                            VARCHAR2(200)
 INFVAL                                             VARCHAR2(100)
 MANNOTE                                            VARCHAR2(200)
 
 4-2. dto [com.company.model]
 public class PawPostDto {
	private int disno;
	private String disname;
	private String disex;
	private String kindpet;
	private String infval;
	private String mannote;
 
 4-3. dao [com.company.model]
 create: insert into disease(disno,disname,disex,kindpet,infval,mannote) values(disno_seq.nextval, ?, ?, ?, ?, ?);
 read : select * from disease;
  		select * from disease where disno=?;
 update: update disease set disname=?,disex=?,kindpet=?, infval = ?, mannote = ?  where disno = ?;
 delete: delete from disease where disno = ?;  	
 */

public class PawPostDto {
	private int disno;
	private String disname;
	private String disex;
	private String kindpet;
	private String infval;
	private String mannote;
	
	public PawPostDto() { super();  }
	public PawPostDto(int disno, String disname, String disex, String kindpet, String infval, String mannote) {
		super();
		this.disno = disno;
		this.disname = disname;
		this.disex = disex;
		this.kindpet = kindpet;
		this.infval = infval;
		this.mannote = mannote;
	}
	@Override public String toString() { return "PawPostDto [disno=" + disno + ", disname=" + disname + ", disex=" + disex + ", kindpet=" + kindpet + ", infval=" + infval + ", mannote=" + mannote + "]"; }
	public int getDisno() { return disno; }
	public void setDisno(int disno) { this.disno = disno; }
	public String getDisname() { return disname; }
	public void setDisname(String disname) { this.disname = disname; }
	public String getDisex() { return disex; }
	public void setDisex(String disex) { this.disex = disex; }
	public String getKindpet() { return kindpet; }
	public void setKindpet(String kindpet) { this.kindpet = kindpet; }
	public String getInfval() { return infval; }
	public void setInfval(String infval) { this.infval = infval; }
	public String getMannote() { return mannote; }
	public void setMannote(String mannote) { this.mannote = mannote; }
	public void setPass(String pass) {
		
		
	}
	public void setId(int id) {
		
		
	}
	
	

}
