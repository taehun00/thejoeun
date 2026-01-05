package com.pawject.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pawject.dto.paging.PagingDto10;
import com.pawject.dto.user.UserDto;
import com.pawject.service.notification.NotificationService;
import com.pawject.service.pet.PetService;
import com.pawject.service.user.AnnouncementService;
import com.pawject.service.user.UserSecurityService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final NotificationService notificationService;
    private final AnnouncementService announcementService;
    @Autowired 
    private UserSecurityService service;

    public AdminController(NotificationService notificationService,
                           AnnouncementService announcementService) {
        this.notificationService = notificationService;
        this.announcementService = announcementService;
    }

    /**
     * 관리자 공지 목록
     */
    @GetMapping("/announcements")
    public String list(Model model) {
        model.addAttribute(
            "announcements",
            announcementService.getAllAnnouncements()
        );
        return "admin/announcements";
    }

    /**
     * 공지 작성 폼
     */
    @GetMapping("/announcements/new")
    public String createForm() {
        return "admin/announcement-form";
    }

    /**
     * 공지 등록
     */
    @PostMapping("/announcements")
    public String create(@RequestParam String message) {
        announcementService.saveAnnouncement(message);
        notificationService.sendAnnouncement(message);
        return "redirect:/admin/announcements";
    }

    /**
     * 공지 수정 폼
     */
    @GetMapping("/announcements/{id}/edit")
    public String editForm(@PathVariable int id, Model model) {
        model.addAttribute(
            "announcement",
            announcementService.getAnnouncementById(id)
        );
        return "admin/announcement-edit";
    }

    /**
     * 공지 수정
     */
    @PutMapping("/announcements/{id}")
    @ResponseBody
    public ResponseEntity<Void> update(
            @PathVariable int id,
            @RequestParam String message) {

        announcementService.updateAnnouncement(id, message);
        return ResponseEntity.ok().build();
    }

    /**
     * 공지 삭제
     */
    @DeleteMapping("/announcement/{id}")
    @ResponseBody
    public ResponseEntity<Void> delete(@PathVariable int id) {
    	announcementService.deleteAnnouncement(id);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/userList")
    public String userListPage() {
        return "admin/userList"; // Thymeleaf 템플릿 이름
    }

    
 // 유저 목록 (Ajax)
    @GetMapping("/users")
    @ResponseBody
    public Map<String, Object> listUsers(@RequestParam(value="pstartno", defaultValue="1") int pstartno) {
        Map<String, Object> result = new HashMap<>();
        result.put("list", service.listUsers(pstartno));
        result.put("paging", new PagingDto10(service.selectTotalCnt(), pstartno));
        return result;
    }

    // 특정 유저 조회
    @GetMapping("/users/{userId}")
    @ResponseBody
    public UserDto selectUser(@PathVariable int userId) {
        return service.selectUser(userId);
    }

    // 닉네임 수정
    @PostMapping("/users/{userId}/nickname")
    public String updateNickname(@PathVariable int userId,
                                 @RequestParam String nickname,
                                 RedirectAttributes rttr) {
        UserDto dto = new UserDto();
        dto.setUserId(userId);
        dto.setNickname(nickname);

        int updated = service.updateNickname(dto);
        if(updated > 0){
            rttr.addFlashAttribute("success", "닉네임이 수정되었습니다.");
        } else {
            rttr.addFlashAttribute("error", "수정 실패");
        }
        return "redirect:/admin/usersPage";
    }

    // 유저 삭제
    @PostMapping("/users/delete")
    @ResponseBody
    public Map<String, Object> deleteUser(@RequestParam("email") String email) {
        Map<String, Object> result = new HashMap<>();
        int deleted = service.deleteUser(email);
        result.put("result", deleted > 0 ? 1 : 0);
        return result;
    }

    // 유저 검색
    @GetMapping("/users/search")
    @ResponseBody
    public List<UserDto> searchUsers(@RequestParam("keyword") String keyword,
                                     @RequestParam(value="type", required=false) String type) {
        return service.searchUsers(keyword, type);
    }

    // 유저 상세 페이지
    @GetMapping("/users/{userId}/detail")
    public String detail(@PathVariable int userId, Model model) {
        UserDto user = service.selectUser(userId);
        model.addAttribute("user", user);
        return "admin/user-detail"; // Thymeleaf 템플릿
    }

}