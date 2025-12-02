package com.pawject.dto.user;

import java.util.List;

import lombok.Data;

@Data
public class UserAuthDto {
	private String email;
	private String password;
	private int userId;
	private List<AuthDto> authList;
}
