package com.pawject.service.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

import com.pawject.dao.UserDao;
import com.pawject.dto.user.UserDto;
import com.pawject.security.CustomUserDetails;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final SimpMessagingTemplate messagingTemplate;
    private final UserDao userDao;
    @Autowired private SessionRegistry sessionRegistry;
    
    public NotificationServiceImpl(SimpMessagingTemplate messagingTemplate, UserDao userDao) {
        this.messagingTemplate = messagingTemplate;
        this.userDao = userDao;
    }

    @Override
    public void sendAnnouncement(String message) {

        // 모든 사용자에게 브로드캐스트
        messagingTemplate.convertAndSend("/topic/announcement", message);
    }

    @Override
    public void sendWelcome(int userId) {
        // 특정 사용자에게만 알림
        UserDto user = userDao.selectUser(userId);
        messagingTemplate.convertAndSendToUser(
            user.getEmail(), // Principal 이름과 동일하게 email 사용
            "/queue/announcement",
            "환영합니다!"
        );
    }

    // 다른 세션에게만 WebSocket 알림 보내기
    @Override
    public void sendPasswordChange(int userId) {
    	String currentSessionId =
    	        RequestContextHolder.currentRequestAttributes().getSessionId();

    	    for (Object principal : sessionRegistry.getAllPrincipals()) {

    	        if (!(principal instanceof CustomUserDetails)) continue;

    	        CustomUserDetails details = (CustomUserDetails) principal;

    	        if (details.getUserId() != userId) continue;

    	        sessionRegistry.getAllSessions(principal, false)
    	            .forEach(session -> {

    	                // 🔥 현재 세션은 제외
    	                if (session.getSessionId().equals(currentSessionId)) {
    	                    return;
    	                }

    	                messagingTemplate.convertAndSendToUser(
    	                    details.getEmail(),
    	                    "/queue/notifications",
    	                    "다른 기기에서 비밀번호가 변경되었습니다."
    	                );
    	            });
    	    }
    }
}
