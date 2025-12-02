package com.pawject.dto.user;

import lombok.Data;

@Data
public class AuthDto {
	private String email;
	private String auth;
}

//'1@1'  'ROLE_ADMIN'
//'1@1'  'ROLE_MEMBER'