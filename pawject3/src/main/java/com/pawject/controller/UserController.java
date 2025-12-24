package com.pawject.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pawject.dto.user.UserDto;
import com.pawject.service.user.UserSecurityService;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired 
    private UserSecurityService service;

    //@Autowired 
    //private PetService pservice;

    
    @PreAuthorize("permitAll()")
    @GetMapping("/iddouble")
    @ResponseBody
    public Map<String, Object> iddouble(@RequestParam String email, @RequestParam String provider) {
        Map<String, Object> result = new HashMap<>();
        int cnt = service.iddouble(email, provider);
        System.out.println("중복검사 결과 cnt=" + cnt); // 로그 찍기

        result.put("cnt", service.iddouble(email, provider));
        return result;
    }

    
    // 회원가입 폼
    @GetMapping("/join")
    public String joinForm() {
        return "users/join";
    }

    // 회원가입 처리
    @PreAuthorize("isAnonymous()")
    @PostMapping("/join")
    public String join(@RequestParam("file") MultipartFile file, UserDto dto, RedirectAttributes rttr) {
        String result = "회원가입 실패";
        try {
            if (service.insert(file, dto) > 0) {
                result = "회원가입 성공";
            }
        } catch (DuplicateKeyException e) {
            result = "이미 등록된 휴대폰 번호입니다.";
        } catch (Exception e) {
            result = "회원가입 처리 중 오류가 발생했습니다.";
        }
        rttr.addFlashAttribute("success", result);
        return "redirect:/users/login";
    }

    // 로그인 폼
    @GetMapping("/login")
    public String login() {
        return "users/login";
    }

    // 로그인 실패
    @GetMapping("/fail")
    public String fail(RedirectAttributes rttr) {
        rttr.addFlashAttribute("loginError", "로그인 실패");
        return "redirect:/users/login";
    }

    // 마이페이지
    @GetMapping("/mypage")
    public String mypage(Principal principal, Model model) {
        UserDto user = service.selectEmail(principal.getName(), "local");
        model.addAttribute("dto", user);
//        model.addAttribute("pets", pservice.selectPetsByUserId(user.getUserId()));
        return "users/mypage";
    }

    // 수정 폼
    @GetMapping("/update")
    public String updateForm(Principal principal, Model model) {
        model.addAttribute("dto", service.selectEmail(principal.getName(), "local"));
        return "users/update";
    }

    // 수정 처리
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/update")
    public String update(@RequestParam("file") MultipartFile file, UserDto dto, RedirectAttributes rttr) {
        String result = "비밀번호를 확인해주세요";
        if (service.update(file, dto) > 0) {
            result = "유저 수정 성공";
        }
        rttr.addFlashAttribute("success", result);
        return "redirect:/users/mypage";
    }

    // 탈퇴 폼
    @GetMapping("/delete")
    public String deleteForm(Principal principal, Model model) {
        model.addAttribute("dto", service.selectEmail(principal.getName(), "local"));
        return "users/delete";
    }

    // 회원 탈퇴
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/deleteMember")
    public String deleteMember(UserDto dto, RedirectAttributes rttr,
                               HttpServletRequest request,
                               HttpServletResponse response) {
        String result = "비밀번호를 확인해주세요";
        if (service.delete(dto, true) > 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null) {
                new SecurityContextLogoutHandler().logout(request, response, auth);
            }
            result = "삭제 성공";
            rttr.addFlashAttribute("success", result);
            return "redirect:/users/login";
        } else {
            rttr.addFlashAttribute("deleteError", result);
            return "redirect:/users/mypage";
        }
    }
}