package com.pawject.service.notification;

public interface NotificationService {
    public void sendAnnouncement(String message);      // 공지사항 (모든 사용자에게)
    public void sendWelcome(int userId);              // 회원가입 알림 (개인)
    public void sendPasswordChange(int userId);       // 비밀번호 변경 알림 (개인)
}
