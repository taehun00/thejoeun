package com.pawject.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthDto {
	private int authId;
	private String email;
	private String auth;
	
	public AuthDto(String auth, String email) {
		super();
		this.auth = auth;
		this.email = email;
	}
}
