package com.pawject.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.pawject.dto.user.LoginRequest;
import com.pawject.dto.user.UserRequestDto;
import com.pawject.dto.user.UserResponseDto;
import com.pawject.service.user.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;
	
	/* =========================
	    회원가입 (프로필 이미지 포함)
	  ========================= */
	 @PostMapping("/signup")
	 public ResponseEntity<UserResponseDto> signup(
	         @RequestPart("user") UserRequestDto request,
	         @RequestPart(value = "profileImage", required = false) MultipartFile profileImage
	 ) {
	     return ResponseEntity.ok(userService.signup(request, profileImage));
	 }
	
	 /* =========================
	    로그인
	  ========================= */
	 @PostMapping("/login")
	 public ResponseEntity<UserResponseDto> login(
	         @RequestBody LoginRequest request
	 ) {
	     return ResponseEntity.ok(userService.login(request));
	 }
	
	 /* =========================
	    사용자 조회
	  ========================= */
	 @GetMapping("/{userId}")
	 public ResponseEntity<UserResponseDto> findById(@PathVariable Long userId) {
	     return ResponseEntity.ok(userService.findById(userId));
	 }
	
	 /* =========================
	    닉네임 변경
	  ========================= */
	 @PatchMapping("/{userId}/nickname")
	 public ResponseEntity<UserResponseDto> updateNickname(
	         @PathVariable Long userId,
	         @RequestParam String nickname
	 ) {
	     return ResponseEntity.ok(userService.updateNickname(userId, nickname));
	 }
	
	 /* =========================
	    사용자 탈퇴
	  ========================= */
	 @DeleteMapping
	 public ResponseEntity<Void> deleteByEmail(@RequestParam String email) {
	     userService.deleteUserByEmail(email);
	     return ResponseEntity.noContent().build();
	 }
}