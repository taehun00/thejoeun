package com.pawject.service.notification;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.pawject.dao.UserDao;
import com.pawject.dto.user.UserDto;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final SimpMessagingTemplate messagingTemplate;
    private final UserDao userDao;

    public NotificationServiceImpl(SimpMessagingTemplate messagingTemplate, UserDao userDao) {
        this.messagingTemplate = messagingTemplate;
        this.userDao = userDao;
    }

    @Override
    public void sendAnnouncement(String message) {
    	System.out.println("공지 브로드캐스트: " + message);

        // 모든 사용자에게 브로드캐스트
        messagingTemplate.convertAndSend("/topic/notifications", message);
    }

    @Override
    public void sendWelcome(int userId) {
        // 특정 사용자에게만 알림
        UserDto user = userDao.selectUser(userId);
        messagingTemplate.convertAndSendToUser(
            user.getEmail(), // Principal 이름과 동일하게 email 사용
            "/queue/notifications",
            "환영합니다!"
        );
    }

    @Override
    public void sendPasswordChange(int userId) {
        // 특정 사용자에게만 알림
        UserDto user = userDao.selectUser(userId);
        messagingTemplate.convertAndSendToUser(
            user.getEmail(), // Principal 이름과 동일하게 email 사용
            "/queue/notifications",
            "비밀번호가 변경되었습니다!"
        );
    }
}
