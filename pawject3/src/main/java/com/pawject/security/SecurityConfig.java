package com.pawject.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.pawject.oauth.Oauth2IUserService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor // ##
public class SecurityConfig {

	private final Oauth2IUserService oauth2IUserService; // ##
	private final CustomLoginSuccessHandler customLoginSuccessHandler;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http /* 1. 허용경로 */
			.authorizeHttpRequests(auth -> auth
					// 누구나 다 접근가능
					.antMatchers("/users/join", "/users/login", "/users/iddouble", "/images/**", "/api/**",
							"/notifications/**",   // 알림 API 허용
		                    "/ws/**"               // WebSocket 엔드포인트 허용
							).permitAll()
					// 로그인한 유저들만 접근가능
					.antMatchers("/users/mypage", "/users/update", "/users/delete").authenticated()
					// 관리자만 접근 가능
		            .antMatchers("/admin/**").hasRole("ADMIN")
					.anyRequest().permitAll()
			)
			/* 2. 로그인처리 */
			.formLogin( form -> form
					.loginPage("/users/login") 					// 로그인 폼
					.loginProcessingUrl("/users/loginProc")		// 로그인 경로
					.successHandler(customLoginSuccessHandler) 	// 로그인성공시 경로
					.failureUrl("/users/fail")					// 로그인실패시 경로
					.permitAll()
			)
			/* 3. 로그아웃 */
			.logout( logout -> logout
				.logoutRequestMatcher( new AntPathRequestMatcher("/users/logout"))	// 로그아웃 경로
				.logoutSuccessUrl("/users/login")		// 로그아웃 성공시
				.invalidateHttpSession(true)			// 세션 다 지우기
				.permitAll()							
			)
			.oauth2Login(oauth2 -> oauth2
					.loginPage("/users/login") // 로그인폼 통합
					.successHandler(customLoginSuccessHandler) // 로그인성공시 경로
					.userInfoEndpoint(userInfo -> userInfo.userService(oauth2IUserService))
			)
			/* 4. csrf 예외처리*/
			.csrf( csrf -> csrf.ignoringAntMatchers("/users/join", "/users/update", "/users/delete",  "/notifications/**",
						"/ws/**"   // WebSocket 엔드포인트도 CSRF 예외 처리)
				)
			);
		return http.build();
	}
	
//		@Bean
//		public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
//			
//			return authConfig.getAuthenticationManager();
//		}
}
