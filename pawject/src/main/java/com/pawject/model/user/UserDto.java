package com.pawject.model.user;

import java.time.LocalDateTime;

public class UserDto {
	private int userid;
	private String email;
	private String nickname;
	private String password;
	private LocalDateTime createdat;
	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserDto(int userid, String email, String nickname, String password, LocalDateTime createdat) {
		super();
		this.userid = userid;
		this.email = email;
		this.nickname = nickname;
		this.password = password;
		this.createdat = createdat;
	}
	@Override
	public String toString() {
		return "UserDto [userid=" + userid + ", email=" + email + ", nickname=" + nickname + ", password=" + password
				+ ", createdat=" + createdat + "]";
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LocalDateTime getCreatedat() {
		return createdat;
	}
	public void setCreatedat(LocalDateTime createdat) {
		this.createdat = createdat;
	}
	
	
	
}
