package com.pawject.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pawject.dto.paging.PagingDto10;
import com.pawject.dto.user.UserDto;
import com.pawject.service.user.UserSecurityService;


@Controller
@RequestMapping("/security")
public class UserAdminController {
	
    @Autowired
    private UserSecurityService userService;

    @RequestMapping("/listPage")
    public String adminListPage(Model model,
                                @RequestParam(value="pstartno", defaultValue="1") int pstartno) {
        // JSP에서 바로 EL로 출력하고 싶으면 model에 담아줌
        model.addAttribute("list", userService.listUsers(pstartno));
        model.addAttribute("paging", new PagingDto10(userService.selectTotalCnt(), pstartno));
        return "user/list"; // /WEB-INF/view/user/list.jsp
    }

    
    // 전체 유저 리스트 (관리자용)
    // 관리자 전용 페이지 이동
    @ResponseBody
    @RequestMapping("/list")
    public Map<String, Object> adminList(
    		@RequestParam (value="pstartno", defaultValue="1") int pstartno
    ) {
    	Map<String, Object> result = new HashMap<>();
    	result.put("list", userService.listUsers(pstartno));
    	result.put("paging", new PagingDto10( userService.selectTotalCnt(), pstartno));
        return result;
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
        return "redirect:/security/listPage";
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

    
    // 검색 페이지 이동 (검색 폼을 보여줄 때)
    @RequestMapping("/search")
    public String userSearchPage() {
        return "security/list";  // /WEB-INF/view/security/list.jsp
    }

    // 검색 실행 (Ajax 요청 처리)
    @RequestMapping(value="/search", method=RequestMethod.GET)
    @ResponseBody
    public List<UserDto> searchUsers(@RequestParam("keyword") String keyword,
                                     @RequestParam(value="type", required=false) String type) {
        // 서비스 호출
        List<UserDto> users = userService.searchUsers(keyword, type);
        return users;  // JSON 응답
    }


    
    @RequestMapping(value="/detail", method=RequestMethod.GET)
    public String detail(@RequestParam("userId") int userId, Model model) {
        UserDto user = userService.selectUser(userId);
        model.addAttribute("user", user);
        return "user/listDetail"; // listDetail.jsp로 이동
    }


}