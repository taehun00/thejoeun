package com.pawject.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pawject.dto.user.UserDto;
import com.pawject.service.pet.PetService;
import com.pawject.service.user.UserSecurityService;

@Controller
@RequestMapping("/security/*")
public class UserSecurityController {
	@Autowired UserSecurityService service;
	@Autowired PetService pservice;
	
	@RequestMapping("/join")	// 회원가입 폼
	public String joinForm() {
		return "/user/join";
	}
	
	@PreAuthorize("isAnonymous()")
	@RequestMapping(value="/join", method=RequestMethod.POST, headers=("content-type=multipart/*")) // 회원가입 기능
	public String join(@RequestParam("file") MultipartFile file, UserDto dto, RedirectAttributes rttr) {
		
		String result = "회원가입 실패";
		
		try {
            if( service.join(file, dto) > 0 ) { 
            	result="회원가입 성공"; 
            }
        } catch (DuplicateKeyException e) {
            // mobile 중복 등 UNIQUE 제약 조건 위반 시
            result = "이미 등록된 휴대폰 번호입니다.";
        } catch (Exception e) {
            // 그 외 예외 처리
            result = "회원가입 처리 중 오류가 발생했습니다.";
        }
		
		rttr.addFlashAttribute("success", result);
		
		return "redirect:/security/login";
	}
	
	@RequestMapping("/login")	// 로그인 폼
	public String login() {
		return "/user/login";
	}
	
	
	@RequestMapping("/fail")	// 로그인실패 폼
	public String fail(HttpServletResponse response, RedirectAttributes rttr) {
		String result ="로그인 실패";
		rttr.addFlashAttribute("loginError", result);
		
		return "redirect:/security/login";
	}
	
	@RequestMapping("/mypage")	// 마이페이지 - 로그인 정보 Principal
	public String mypage(Principal principal, Model model) {
		model.addAttribute("dto", service.myPage(principal.getName()));
		
		model.addAttribute("pets", pservice.selectPetsByUserId(
		        service.myPage(principal.getName()).getUserId()
		    ));

		return "/user/mypage";
	}
	
	@RequestMapping("/update")
	public String updateForm(Principal principal, Model model) {
		model.addAttribute("dto", service.myPage(principal.getName()));
		
		return "/user/update";
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value="/update", method=RequestMethod.POST, headers=("content-type=multipart/*")) // 수정 기능
	public String update(@RequestParam("file") MultipartFile file, UserDto dto, RedirectAttributes rttr) {
		
		String result = "비밀번호를 확인해주세요";
		if(service.update(file, dto) > 0) {
			result="유저 수정 성공";
		}
		rttr.addFlashAttribute("success", result);
		
		return "redirect:/security/mypage";
	}
	
	@RequestMapping("/delete")
	public String deleteForm(Principal principal, Model model) {
		model.addAttribute("dto", service.myPage(principal.getName()));
		
		return "/user/delete";
	}
	
	// 회원 탈퇴 (사용자)
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value="/deleteMember", method=RequestMethod.POST)
    public String deleteMember(UserDto dto, RedirectAttributes rttr,
                               HttpServletRequest request,
                               HttpServletResponse response) {

        String result = "비밀번호를 확인해주세요";

        // 서비스에서 userId 기반 삭제
        if(service.deleteMember(dto.getUserId()) > 0) {
            // 로그아웃 처리
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if(auth != null) {
                new SecurityContextLogoutHandler().logout(request, response, auth);
            }

            result = "삭제 성공";
            rttr.addFlashAttribute("success", result);
            return "redirect:/security/login";
        } else {
            rttr.addFlashAttribute("deleteError", result);
            return "redirect:/security/mypage";
        }
    }


	
}
