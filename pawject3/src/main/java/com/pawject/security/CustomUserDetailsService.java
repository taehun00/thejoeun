package com.pawject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pawject.dao.UserDao;
import com.pawject.dto.user.UserAuthDto;
import com.pawject.dto.user.UserDto;



@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired UserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		System.out.println("[CustomUserDetailsService] 로그인 시도 username 파라미터: " + username);


		// username "1@1 : local , 2@2 : kakao ,,,"
		String [] parts = username.split(":");
		String email = parts[0];
		String provider = parts.length > 1 ? parts[1] : "local";
		
		UserDto param = new UserDto();
		param.setEmail(email);
		param.setProvider(provider);
		
		UserAuthDto authDto = userDao.readAuthByEmail(param);
		if( authDto == null ) {
			System.out.println("[CustomUserDetailsService] 인증 정보 없음: " + username);

			throw new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + username);
		}
		
		UserDto user = userDao.findByEmail(param);
		if( user == null ) {
			System.out.println("[CustomUserDetailsService] 사용자 기본정보 없음: " + username);

			throw new UsernameNotFoundException("사용자 기본정보를 찾을 수 없습니다: " + username);
		}
		
		CustomUserDetails details = new CustomUserDetails(user, authDto);
        System.out.println("[CustomUserDetailsService] 생성된 Principal 이름(getUsername): " + details.getUsername());


		return details;
	}
	
	
}
