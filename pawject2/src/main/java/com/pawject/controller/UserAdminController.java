package com.pawject.controller;

import org.springframework.stereotype.Controller;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.authentication.UserServiceBeanDefinitionParser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pawject.dao.user.UserMapper;
import com.pawject.dto.user.UserDto;
import com.pawject.service.user.UserSecurityService;

@Controller
@RequestMapping("/security")
public class UserAdminController {
	@Autowired private UserMapper dao;
	
    @Autowired
    private UserSecurityService userService;

    // 관리자 전용 페이지 이동
    @RequestMapping("/list")
    public String adminList() {
        return "/user/list";
    }

    
    // 전체 유저 리스트 (관리자용)
    @RequestMapping(value="/selectAll", method=RequestMethod.GET)
    @ResponseBody
    public List<UserDto> selectAll() {
        return userService.listUsers(0, 100); // 페이징 범위 예시
    }

    // 특정 유저 조회 (관리자용)
    @RequestMapping(value="/select", method=RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> select(@RequestParam("userId") int userId) {
        Map<String, Object> result = new HashMap<>();
        UserDto dto = userService.selectUser(userId); // 필요시 userId 기반 조회 메서드 추가
        result.put("result", dto);
        return result;
    }

    // 닉네임 수정 (관리자용)
    @RequestMapping(value="/updateAdmin", method=RequestMethod.POST)
    public String updateAdmin(@RequestParam("userId") int userId,
                              @RequestParam("nickname") String nickname,
                              RedirectAttributes rttr) {
        UserDto dto = new UserDto();
        dto.setUserId(userId);
        dto.setNickname(nickname);

        int updated = userService.updateNickname(dto);

        if(updated > 0){
            rttr.addFlashAttribute("success", "닉네임이 수정되었습니다.");
        } else {
            rttr.addFlashAttribute("error", "수정 실패");
        }

		
        // list.jsp로 리다이렉트
        return "redirect:/security/list";
    }


    // 유저 삭제 (관리자용)
    @PostMapping("/deleteUser")
    @ResponseBody
    public Map<String, Object> deleteUser(@RequestParam("email") String email) {
        Map<String, Object> result = new HashMap<>();
        int deleted = userService.deleteUser(email);

        if (deleted > 0) {
            result.put("result", 1);
        } else {
            result.put("result", 0);
        }
        return result;
    }

    
    // 검색 (이메일/닉네임)
    @RequestMapping(value="/search", method=RequestMethod.GET)
    @ResponseBody
    public List<UserDto> search(@RequestParam("keyword") String keyword) {
        return userService.searchUsers(keyword);
    }
    
    @RequestMapping(value="/detail", method=RequestMethod.GET)
    public String detail(@RequestParam("userId") int userId, Model model) {
        UserDto user = userService.selectUser(userId);
        model.addAttribute("user", user);
        return "user/listDetail"; // listDetail.jsp로 이동
    }


}