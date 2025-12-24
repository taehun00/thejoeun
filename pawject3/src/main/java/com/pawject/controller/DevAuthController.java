package com.pawject.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dev")
@Profile("dev")   // ⭐ 이거 꼭
public class DevAuthController {

	@GetMapping("/login/user")
	public String devLoginuser(HttpSession session) {

	    Authentication auth =
	        new UsernamePasswordAuthenticationToken(
	            "user@test.com",
	            null,
	            List.of(new SimpleGrantedAuthority("ROLE_USER"))
	        );

	    SecurityContext context = SecurityContextHolder.createEmptyContext();
	    context.setAuthentication(auth);
	    SecurityContextHolder.setContext(context);

	    session.setAttribute(
	        HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
	        context
	    );

	    return "redirect:/csBoard/cslistuser";
	}
    
    @GetMapping("/login/admin")
    public String devLoginadnim(HttpSession session) {

	    Authentication auth =
		        new UsernamePasswordAuthenticationToken(
		            "user@test.com",
		            null,
		            List.of(new SimpleGrantedAuthority("ROLE_ADMIN"))
		        );

		    SecurityContext context = SecurityContextHolder.createEmptyContext();
		    context.setAuthentication(auth);
		    SecurityContextHolder.setContext(context);

		    session.setAttribute(
		        HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
		        context
		    );
         return "redirect:/csBoard/cslistadmin"; // 로그인 후 보고 싶은 페이지
    } 
    
    
}