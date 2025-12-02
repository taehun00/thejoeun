package com.pawject.dto.user;

import lombok.Data;

@Data
public class AuthDto {
	private String auth;
	private int userId;
}

//'1@1'  'ROLE_ADMIN'
//'1@1'  'ROLE_MEMBER'