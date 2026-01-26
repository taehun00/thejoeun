package com.pawject.controller;

import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.pawject.dto.user.LoginRequest;
import com.pawject.dto.user.UserRequestDto;
import com.pawject.dto.user.UserResponseDto;
import com.pawject.security.JwtProvider;
import com.pawject.security.TokenStore;
import com.pawject.service.user.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;
    private final JwtProvider jwtProvider;
    private final TokenStore tokenStore;

	
	/* =========================
	    회원가입 (프로필 이미지 포함)
	  ========================= */
	 @PostMapping("/signup")
	 public ResponseEntity<UserResponseDto> signup(
			 @ModelAttribute UserRequestDto  request,
	         @RequestPart(value = "ufile", required = false) MultipartFile profileImage
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
	 public ResponseEntity<UserResponseDto> findById(@PathVariable("userId") Long userId) {
	     return ResponseEntity.ok(userService.findById(userId));
	 }
	
	 /* =========================
	    닉네임 변경
	  ========================= */
	 @PatchMapping("/{userId}/nickname")
	 public ResponseEntity<UserResponseDto> updateNickname(
	         @PathVariable("userId") Long userId,
	         @RequestParam(name = "nickname") String nickname
	 ) {
	     return ResponseEntity.ok(userService.updateNickname(userId, nickname));
	 }
	 
	 /* =========================
	     프로필 이미지 변경
	 ========================= */
	 @PostMapping(value = "/{userId}/profile-image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	 public ResponseEntity<UserResponseDto> updateProfileImage(
	         @PathVariable("userId") Long userId,
	         @RequestParam("ufile") MultipartFile ufile
	 ) {
	     return ResponseEntity.ok(userService.updateProfileImage(userId, ufile));
	 }
	
	 /* =========================
	     Access Token 재발급
	 ========================= */
	 @PostMapping("/refresh")
	 public ResponseEntity<Map<String, Object>> refresh(@CookieValue("refreshToken") String refreshToken) {
	     var claims = jwtProvider.parse(refreshToken).getBody();
	     String userId = claims.getSubject();
	
	     String stored = tokenStore.getRefreshToken(userId);
	     if (stored == null || !stored.equals(refreshToken)) {
	         return ResponseEntity.status(401).body(Map.of("error", "Invalid refresh token"));
	     }
	
	     String role = userService.findRoleByUserId(Long.valueOf(userId));
	     String newAccessToken = jwtProvider.createAccessToken(
	             userId,
	             Map.of("role", role)
	     );
	
	     return ResponseEntity.ok(Map.of("accessToken", newAccessToken));
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