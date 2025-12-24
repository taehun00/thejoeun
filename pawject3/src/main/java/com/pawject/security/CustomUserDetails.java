package com.pawject.security;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.pawject.dto.UserAuthDto;
import com.pawject.dto.UserDto;

public class CustomUserDetails implements UserDetails, OAuth2User{
	private UserDto user;
	private UserAuthDto authDto;
	private Map<String, Object> attributes;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if(authDto == null || authDto.getAuthList() == null || authDto.getAuthList().isEmpty()) {
			return List.of(new SimpleGrantedAuthority("ROLE_MEMBER"));
		} // 권한이 없다면 ROLE_MEMBER
		
		return authDto.getAuthList().stream()
				.filter( a -> a.getAuth() != null && !a.getAuth().isBlank())
				.map( a -> new SimpleGrantedAuthority(a.getAuth()))
				.collect(Collectors.toList()); // 해당유저의 권한
	}
	@Override
	public String getPassword() {
		return user.getPassword();
	}
	@Override
	public String getUsername() {
		//return user.getEmail() + ":" + user.getProvider(); // 1@1:local , 2@2:kakao
		return user.getEmail();
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	// 1. 일반 로그인
	public CustomUserDetails(UserDto user, UserAuthDto authDto) {
		super();
		this.user = user;
		this.authDto = authDto;
		this.attributes = new HashMap<>();
		this.attributes.put("email", user.getEmail());
		this.attributes.put("provider", user.getProvider());
		
	}
	
	// 2. Oauth2 소셜로그인
	public CustomUserDetails(UserDto user, Map<String, Object> attributes) {
		super();
		this.user = user;
		this.authDto = new UserAuthDto();
		this.authDto.setAuthList(List.of());
		this.attributes = new HashMap<>(attributes != null ? attributes : Map.of());
		this.attributes.put("email", user.getEmail());
		this.attributes.put("provider", user.getProvider());
	}
	
	
	public UserDto getUser() {
		return user;
	}
	public String getEmail() {
		return user.getEmail();
	}
	public String getProvider() {
		return user.getProvider();
	}
	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}
	@Override
	public String getName() {
		return user.getEmail()+":"+user.getProvider();
	}
	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
	
	
	
	
}
