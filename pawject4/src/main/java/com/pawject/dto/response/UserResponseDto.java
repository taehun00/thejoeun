package com.pawject.dto.response;

import java.time.LocalDateTime;

import com.pawject.entity.User;
import com.pawject.dto.response.UserResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

    private Long userId;
    private String email;
    private String nickname;
    private String ufile;
    private String mobile;
    private String provider;
    private String providerId;
    private String role;
    private LocalDateTime createdAt;

    public static UserResponseDto fromEntity(User user) {
        return UserResponseDto.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .ufile(user.getUfile())
                .mobile(user.getMobile())
                .provider(user.getProvider())
                .providerId(user.getProviderId())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
