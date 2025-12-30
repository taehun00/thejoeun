package com.pawject.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pawject.dto.paging.PagingDto10;
import com.pawject.service.notification.NotificationService;
import com.pawject.service.pet.PetService;
import com.pawject.service.user.UserSecurityService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	private final NotificationService notificationService;

    public AdminController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }
    
    @GetMapping("/announcement")
    public String announcementForm() {
        return "admin/announcement";
    }


	
	@PostMapping("/announcement")
    public String sendAnnouncement(@RequestParam String message) {
        notificationService.sendAnnouncement(message);
        return "redirect:/admin/announcement";
    }

}
