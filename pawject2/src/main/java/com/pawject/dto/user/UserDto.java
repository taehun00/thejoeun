package com.pawject.dto.user;

import lombok.Data;

@Data
public class UserDto {
	private int userId;
	private String email;
	private String nickname;
	private String password;
	private String createdAt;
	private String ufile;
	private String mobile;
}
