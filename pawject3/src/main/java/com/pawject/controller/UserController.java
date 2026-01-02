package com.pawject.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pawject.dto.paging.PagingDto10;
import com.pawject.dto.pet.PetDto;
import com.pawject.dto.user.AnnouncementDto;
import com.pawject.dto.user.UserDto;
import com.pawject.security.CustomUserDetails;
import com.pawject.security.CustomUserDetailsService;
import com.pawject.service.notification.NotificationService;
import com.pawject.service.pet.PetService;
import com.pawject.service.user.AnnouncementService;
import com.pawject.service.user.HiddenAnnouncementService;
import com.pawject.service.user.UserSecurityService;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired 
    private UserSecurityService service;

    @Autowired 
    private PetService pservice;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    

    
    
    private final SimpMessagingTemplate messagingTemplate;

    public UserController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }


    
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
    public String join(@RequestParam("file") MultipartFile file, UserDto dto, RedirectAttributes rttr,
            HttpSession session) {
        String result = "회원가입 실패";
        try {
            if (service.insert(file, dto) > 0) {
                
                // 자동 로그인 처리
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(dto.getEmail());
                Authentication auth = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);

                
                // 환영 메시지 플래그 세션에 저장
                session.setAttribute("welcome", true);

                result = "회원가입 성공";


            }
        } catch (DuplicateKeyException e) {
            result = "이미 등록된 휴대폰 번호입니다.";
        } catch (Exception e) {
            result = "회원가입 처리 중 오류가 발생했습니다.";
        }
        rttr.addFlashAttribute("welcome", true);
        return "redirect:/users/mainpage";
    }
    
    
    
    @GetMapping("/welcome")
    @ResponseBody
    public void sendWelcomeMessage(Principal principal, HttpSession session) {
        
    	Boolean welcome = (Boolean) session.getAttribute("welcome");
        if (welcome != null && welcome) {
            messagingTemplate.convertAndSendToUser(
                principal.getName(),
                "/queue/notifications",
                "회원가입을 축하합니다!"
            );
            // 플래그 제거 (한 번만 보내도록)
            session.removeAttribute("welcome");
        }
    }
    
    // 메인페이지
	/*
	 * @GetMapping("/mainpage") public String mainpage() { return "users/mainpage";
	 * // templates/users/mainpage.html 뷰로 이동 }
	 */



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
        model.addAttribute("pets", pservice.selectPetsByUserId(user.getUserId()));
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
    
    // 비밀번호 변경 페이지 열기
    @GetMapping("/changepassword")
    public String passwordPage() {
        return "users/changePassword";  // HTML 경로
    }

    // 실제 비밀번호 변경 처리
    @PostMapping("/changepassword")
    public String changePassword(@RequestParam String currentPassword,
                                 @RequestParam String newPassword,
                                 @RequestParam String confirmPassword,
                                 Principal principal,
                                 Model model) {

        int userId = service.getUserIdByEmail(principal.getName());

        // 1. 현재 비밀번호 확인
        if (!service.checkCurrentPassword(userId, currentPassword)) {
            model.addAttribute("error", "현재 비밀번호가 올바르지 않습니다.");
            return "users/changePassword";
        }

        // 2. 새 비밀번호 확인
        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("error", "새 비밀번호가 일치하지 않습니다.");
            return "users/changePassword";
        }

        // 3. 비밀번호 변경
        service.changePassword(userId, newPassword);

        // 4. mypage에 필요한 데이터 조회
        UserDto user = service.selectEmail(principal.getName(), "local");
        model.addAttribute("dto", user);
        model.addAttribute("pets", pservice.selectPetsByUserId(user.getUserId()));
        
        model.addAttribute("success", "비밀번호가 변경되었습니다.");

        return "users/mypage";
    }
    // 현재 비밀번호 실시간 확인 (AJAX)
    @PostMapping("/checkCurrentPassword")
    @ResponseBody
    public Map<String, Object> checkCurrentPassword(@RequestParam String currentPassword, Principal principal) {
        Map<String, Object> result = new HashMap<>();

        if(principal == null) {
            result.put("match", false);
            return result;
        }

        int userId = service.getUserIdByEmail(principal.getName());
        boolean match = service.checkCurrentPassword(userId, currentPassword);
        result.put("match", match);
        return result;
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