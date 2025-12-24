package com.pawject.oauth;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.pawject.dao.UserDao;
import com.pawject.dto.AuthDto;
import com.pawject.dto.UserAuthDto;
import com.pawject.dto.UserDto;
import com.pawject.oauth.Oauth2IUserService;
import com.pawject.security.CustomUserDetails;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Oauth2IUserService extends DefaultOAuth2UserService {
	@Autowired UserDao dao;
	@Autowired PasswordEncoder passwordEncoder;
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2User oAuth2User = super.loadUser(userRequest);
		
		String provider = userRequest.getClientRegistration().getRegistrationId();
		UserInfoOAuth2 info;
		if("google".equals(provider)) {
			info = new UserInfoGoogle(oAuth2User.getAttributes());
		} else if("kakao".equals(provider)) {
			info = new UserInfoKakao(oAuth2User.getAttributes());
		} else if("naver".equals(provider)) {
			info = new UserInfoNaver(oAuth2User.getAttributes());
		} else {
			throw new OAuth2AuthenticationException("지원하지 않는 OAuth2 provider : " + provider);
		}
		
		String email = info.getEmail();
		String nickname = info.getNickname();
		String providerId = info.getProviderId();
		
		UserDto userParam = new UserDto();
		userParam.setEmail(email);
		userParam.setProvider(provider);
		UserDto user = dao.findByEmail(userParam);			//유저정보가져오기
		
		if(user == null) { //회원가입
			user = new UserDto();
			user.setEmail(email);
			user.setNickname(nickname != null && nickname.isBlank() ? nickname : "사용자");
			user.setProvider(provider);
			user.setProviderId(providerId);
			user.setPassword(passwordEncoder.encode(UUID.randomUUID().toString())); //인코딩 적용
			dao.insertUser(user);
			
			AuthDto auth = new AuthDto();
			auth.setEmail(email);
			auth.setAuth("ROLE_MEMBER");
			dao.insertAuth(auth);
			System.out.println("..... 신규 소셜 사용자가입" + email);
		}else { //업데이트
			if( nickname != null && !nickname.isBlank()) {
				user.setNickname(nickname);
				dao.updateUser(user);
				System.out.println("..... 소셜 사용자 업데이트" + email);
			}
		}
		
		UserAuthDto authDto = dao.readAuthByEmail(userParam);//유저권한확인
		CustomUserDetails customUserDetails = new CustomUserDetails(user, authDto);
		
		Map<String, Object> attrs = new HashMap<>(oAuth2User.getAttributes());
		attrs.put("provider", provider);
		attrs.put("email", email);
		attrs.put("nickname", nickname);
		customUserDetails.setAttributes(attrs);
		
		return customUserDetails;
	}	
	
	
}
