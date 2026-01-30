package com.pawject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;

import com.pawject.domain.User;
import com.pawject.dto.exec.AdRequestDto;
import com.pawject.dto.exec.AdResponseDto;
import com.pawject.repository.UserRepository;
import com.pawject.service.exec.AdService;

@SpringBootTest
@Transactional
class Pawject4ApplicationTests_Exec_AdService {

    @Autowired AdService adService;
    @Autowired UserRepository userRepository;

    private Long userId;
    private Long adId;

    @BeforeEach
    void setup() {
        // 유저 생성
        User user = new User("test@test.com", "닉네임", "1234");
        userRepository.save(user);
        this.userId = user.getUserId();

        // 광고 작성
        AdRequestDto dto = new AdRequestDto();
        dto.setTitle("테스트 광고");
        dto.setContent("테스트 내용");

        MockMultipartFile file = new MockMultipartFile(
                "file", "test.png", "image/png", "dummy".getBytes()
        );

        AdResponseDto res = adService.createAd(userId, dto, file);
        this.adId = res.getId();

        assertThat(res.getTitle()).isEqualTo("테스트 광고");
        assertThat(res.isActive()).isTrue(); // 무조건 true
    }

    @Test
    @DisplayName("■ AdService - CRUD")
    void adServiceCrud() {
//        // 조회
//        AdResponseDto found = adService.getAd(adId);
//        assertThat(found.getContent()).isEqualTo("테스트 내용");
//
//        // 수정
//        AdRequestDto updateDto = new AdRequestDto();
//        updateDto.setTitle("수정된 광고");
//        updateDto.setContent("수정된 내용");
//
//        MockMultipartFile newFile = new MockMultipartFile(
//                "file", "new.png", "image/png", "newdummy".getBytes()
//        );
//
//        AdResponseDto updated = adService.updateAd(adId, updateDto, newFile);
//        assertThat(updated.getTitle()).isEqualTo("수정된 광고");
//        assertThat(updated.isActive()).isTrue(); // 여전히 true
//
//        // 삭제
//        adService.deleteAd(adId);
//        assertThrows(RuntimeException.class, () -> adService.getAd(adId));
    }
}
