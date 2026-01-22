package com.pawject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.pawject.dto.request.UserRequestDto;
import com.pawject.dto.response.UserResponseDto;
import com.pawject.entity.User;
import com.pawject.repository.UserRepository;
import com.pawject.service.user.UserService;

@ExtendWith(MockitoExtension.class)
@Transactional
class Pawject4ApplicationTests {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("회원가입 성공")
    void signup_success() {
        // given
        UserRequestDto request = UserRequestDto.builder()
                .email("test@test.com")
                .password("1234")
                .nickname("닉네임")
                .provider("local")
                .build();

        when(userRepository.existsByEmailAndProvider(any(), any()))
                .thenReturn(false);
        when(userRepository.existsByNickname(any()))
                .thenReturn(false);
        when(passwordEncoder.encode(any()))
                .thenReturn("ENCODED_PASSWORD");

        User savedUser = User.builder()
                .userId(1L)
                .email("test@test.com")
                .password("ENCODED_PASSWORD")
                .nickname("닉네임")
                .provider("local")
                .role("ROLE_USER")
                .build();

        when(userRepository.save(any(User.class)))
                .thenReturn(savedUser);

        // when
        UserResponseDto response = userService.signup(request, null);

        // then
        assertThat(response.getEmail()).isEqualTo("test@test.com");
        assertThat(response.getNickname()).isEqualTo("닉네임");

        verify(userRepository).save(any(User.class));
    }

}
