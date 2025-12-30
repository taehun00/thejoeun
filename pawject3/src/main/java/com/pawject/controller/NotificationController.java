package com.pawject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pawject.service.notification.NotificationService;

@Controller
@RequestMapping("/notifications")
public class NotificationController {

	private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }
    
    @GetMapping("/test")
    public String testPage() {
        return "notifications/test";
    }


    @PostMapping("/announcement")
    @ResponseBody
    public void sendAnnouncement(@RequestBody String message) {
        notificationService.sendAnnouncement(message);
    }

    @PostMapping("/welcome/{userId}")
    @ResponseBody
    public void sendWelcome(@PathVariable int userId) {
        notificationService.sendWelcome(userId);
    }

    @PostMapping("/password/{userId}")
    @ResponseBody
    public void sendPasswordChange(@PathVariable int userId) {
        notificationService.sendPasswordChange(userId);
    }

}
