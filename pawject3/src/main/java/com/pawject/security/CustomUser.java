package com.pawject.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.pawject.dto.user.UserAuthDto;

import lombok.Getter;

@Getter
public class CustomUser  extends User{   
	private static final long serialVersionUID = 1L;
	UserAuthDto  dto; 
	//2. 유저아이디와 비밀번호를 받아서 권한이 있는지 체크
	public CustomUser(String username, 
			          String password, 
			          Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities); 
	}

	//1.  유저아이디(email)와 비밀번호(password)를 받아서 권한이 다를경우 맞게셋팅
	public CustomUser( UserAuthDto dto) { 
		super(    dto.getEmail()
				, dto.getPassword()
				, dto.getAuthList().stream()
				                   .map(auth-> new SimpleGrantedAuthority(auth.getAuth()))
				                   .collect(Collectors.toList())
		);
		this.dto = dto;
	} 
}
