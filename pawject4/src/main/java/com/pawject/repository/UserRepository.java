package com.pawject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pawject.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

    // 로그인 / 회원 조회
    Optional<User> findByEmailAndProvider(String email, String provider);

    // 이메일 중복 체크
    boolean existsByEmailAndProvider(String email, String provider);

    // 닉네임 중복체크
    boolean existsByNickname(String nickname);
    
    // 관리자 기능: 이메일로 사용자 조회
    Optional<User> findByEmail(String email);

    // 관리자 기능: 이메일로 삭제
    void deleteByEmail(String email);
}