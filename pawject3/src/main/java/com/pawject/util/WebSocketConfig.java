package com.pawject.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // 클라이언트가 구독할 수 있는 prefix
        config.enableSimpleBroker("/topic", "/queue");
        // 서버로 메시지를 보낼 때 prefix
        config.setApplicationDestinationPrefixes("/app");
        // 🔥 개인 메시지 prefix (이게 핵심)
        config.setUserDestinationPrefix("/user");
    }

    
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 클라이언트가 연결할 엔드포인트
    	registry.addEndpoint("/ws")
        .setAllowedOriginPatterns("*")
        .withSockJS();

    }
}
