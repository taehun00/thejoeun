package com.pawject.controller;

import java.util.HashMap;
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

import com.pawject.dto.paging.PagingDto10;
import com.pawject.service.notification.NotificationService;
import com.pawject.service.pet.PetService;
import com.pawject.service.user.AnnouncementService;
import com.pawject.service.user.UserSecurityService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final NotificationService notificationService;
    private final AnnouncementService announcementService;

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
}