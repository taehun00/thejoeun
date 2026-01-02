package com.pawject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pawject.dto.user.AnnouncementDto;
import com.pawject.security.CustomUserDetails;
import com.pawject.service.user.AnnouncementService;
import com.pawject.service.user.HiddenAnnouncementService;

@Controller
@RequestMapping("/announcements")
public class AnnouncementController {
	
	@Autowired
    private HiddenAnnouncementService hiddenService;
    @Autowired
    private AnnouncementService announcementService;
	
	
	//  여기는 공지사항
	
	
	 @GetMapping
	 public String getMyAnnouncements(@AuthenticationPrincipal CustomUserDetails user, Model model) {
	     int userId = user.getUserId(); // 로그인한 사용자 ID 가져오기
	     List<AnnouncementDto> announcements = announcementService.getVisibleAnnouncementsByUserId(userId);
	     model.addAttribute("announcements", announcements);
	     return "users/announcements"; // Thymeleaf 템플릿 이름
	 }
	 
	 /**
	  * 공지 숨김 처리
	  */
	 @DeleteMapping("/{announcementId}")
	 @ResponseBody
	 public ResponseEntity<Void> hideAnnouncement(
	         @PathVariable int announcementId,
	         @AuthenticationPrincipal CustomUserDetails user) {
	
	     int userId = user.getUserId();
	
	     hiddenService.hideAnnouncement(userId, announcementId);
	
	     return ResponseEntity.ok().build();
	 }
	 
	
	 //     공지사항 끝
}
