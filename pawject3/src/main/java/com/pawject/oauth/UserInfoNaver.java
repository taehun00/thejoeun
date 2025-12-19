package com.pawject.oauth;

import java.util.Map;

import com.pawject.oauth.UserInfoOAuth2;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserInfoNaver implements UserInfoOAuth2 {
	
	private final Map<String, Object> attributes;
	
	@Override
	public String getProvider() {
		return "naver";
	}

	@SuppressWarnings("unchecked")
	private Map<String, Object> getResponse(){
		Object response = attributes.get("response");
		return response instanceof Map? (Map<String, Object>)response : null;
	}
	
	@Override
	public String getProviderId() {
		Map<String, Object> response = getResponse();
		return response != null? String.valueOf(response.get("id")) : null;
	}

	@Override
	public String getEmail() {
		Map<String, Object> response = getResponse();
		return response != null? String.valueOf(response.get("email")) : null;
	}

	@Override
	public String getNickname() {
		Map<String, Object> response = getResponse();
		return response != null? String.valueOf(response.get("nickname")) : null;
	}

	@Override
	public String getImage() {
		Map<String, Object> response = getResponse();
		return response != null? String.valueOf(response.get("profile_image")) : "no.png";
	}

}
