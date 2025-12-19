package com.pawject.oauth;

public interface UserInfoOAuth2 {
	String getProvider();
	String getProviderId();
	String getEmail();
	String getNickname();
	String getImage();
}
