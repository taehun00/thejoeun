package com.pawject.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {  //## 인증(누구)
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        //## 인가(접근, 허용)
        List<String> roles = new ArrayList<>(); // 권한리스트에 추가
        authentication.getAuthorities().forEach(auth -> {
            roles.add(auth.getAuthority());
        });

        // CustomUser로 캐스팅해서 DB PK(userId) 꺼내기
        CustomUser customUser = (CustomUser) authentication.getPrincipal();
        int userId = customUser.getUserId();   // UserAuthDto 안의 PK 값

        HttpSession session = request.getSession();
        session.setAttribute("userid", userId);   // 세션에 PK 저장

        // 각각의 유저처리
        // if (roles.contains("ROLE_ADMIN"))  { response.sendRedirect(request.getContextPath() + "/security/admin"); return; }
        // if (roles.contains("ROLE_MEMBER")) { response.sendRedirect(request.getContextPath() + "/security/member"); return; }
        // response.sendRedirect("/"); 
        response.sendRedirect(request.getContextPath() + "/security/mypage");
    }
}

