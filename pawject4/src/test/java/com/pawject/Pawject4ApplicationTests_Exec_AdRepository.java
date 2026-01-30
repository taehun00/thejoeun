package com.pawject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.pawject.domain.Ad;
import com.pawject.domain.User;
import com.pawject.repository.AdRepository;
import com.pawject.repository.UserRepository;

@SpringBootTest
@Transactional
public class Pawject4ApplicationTests_Exec_AdRepository {

    @Autowired AdRepository adRepository;
    @Autowired UserRepository userRepository;

    private Long adId;

    @BeforeEach
    void setup() {
        // User 먼저 생성
        User user = new User("test@test.com", "닉네임", "1234");
        userRepository.save(user);

        // Ad 생성
        Ad ad = new Ad();
        ad.setTitle("광고 제목");
        ad.setContent("광고 내용");
        ad.setImg("test.png");
        ad.setActive(true);
        ad.setUser(user);

        Ad saved = adRepository.save(ad);
        this.adId = saved.getId();

        assertThat(saved.getTitle()).isEqualTo("광고 제목");
        assertThat(saved.getUser().getEmail()).isEqualTo("test@test.com");
    }

    @Test
    @DisplayName("■ AdRepository - CRUD")
    void adRepositoryCrud() {
        // Update
        Ad ad = adRepository.findById(adId).orElseThrow();
        ad.setActive(false);
        Ad updated = adRepository.save(ad);

        assertThat(updated.isActive()).isFalse();

        // Read
        Optional<Ad> found = adRepository.findById(adId);
        assertThat(found).isPresent();
        assertThat(found.get().getTitle()).isEqualTo("광고 제목");

        // Delete
        adRepository.deleteById(adId);
        assertThrows(RuntimeException.class, () -> {
            adRepository.findById(adId).orElseThrow(() -> new RuntimeException("Ad not found"));
        });
    }
}
