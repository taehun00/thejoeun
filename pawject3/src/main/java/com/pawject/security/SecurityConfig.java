package com.pawject.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

			
		http
		/* 1. 허용경로 */
		.authorizeHttpRequests(auth -> auth
				/* 전부 허용 */
			.antMatchers("/users/**", "/images/**", "/api/**", "/faqBoard/**").permitAll()
				/* 로그인 유저만 접근 가능*/
			.antMatchers("/users/mypage", "/users/delete", "/users/update").authenticated()
				/* 그 외 요청 모두 허용*/
			.anyRequest().permitAll()
			);

		return http.build();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}


}



