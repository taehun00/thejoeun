package com.pawject.controller;   //com.pawject

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.pawject.dto.exec.AdRequestDto;
import com.pawject.dto.exec.AdResponseDto;
import com.pawject.service.exec.AdService;
import com.pawject.service.user.AuthUserJwtService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Ad", description = "광고 API")
@RestController
@RequestMapping("/ads")
@RequiredArgsConstructor
public class AdController {

    private final AdService adService;
    private final AuthUserJwtService authUserJwtService;

    @Operation(summary = "광고 단건 조회 (공개)")
    @GetMapping("/{adId}")
    public ResponseEntity<AdResponseDto> getAd(
            @PathVariable(name = "adId") Long adId) { // ✅ 변경됨: name 속성 추가
        return ResponseEntity.ok(adService.getAd(adId)); 
    }

    @Operation(summary = "광고 작성 (JWT 인증 필요)")
    @PostMapping( value="/ads"  , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdResponseDto> createAd(
            Authentication authentication,
            @ModelAttribute AdRequestDto dto,
            @Parameter(description = "업로드할 이미지 파일")
            @RequestPart(value = "file", required = false ) MultipartFile file) {
    	// @RequestPart(name="file", required=false) MultipartFile file)
        Long userId = authUserJwtService.getCurrentUserId(authentication);
        return ResponseEntity.ok(adService.createAd(userId, dto, file));
    }

//    @Operation(summary = "회원가입")   //Swagger 문서 설명
//    @PostMapping( value="/signup"  , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public   ResponseEntity<UserResponseDto> singup(
//    		@ModelAttribute UserRequestDto  request,
//    		@RequestPart(name="ufile" , required= false) MultipartFile ufile
//    		
//    	){
//    		return  ResponseEntity.ok(   appUserService.signup(request, ufile)  );
//    }

    
    
    
    @Operation(summary = "광고 수정 (JWT 인증 필요)")
    @PutMapping(value="/{adId}/ads" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)  //( value="/ads"  , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdResponseDto> updateAd(
            Authentication authentication,
            @PathVariable(name = "adId") Long adId, // ✅ 변경됨: name 속성 추가
            @ModelAttribute AdRequestDto dto,
            @Parameter(description = "업로드할 이미지 파일")
            @RequestPart(value = "file", required = false ) MultipartFile file) {
        Long userId = authUserJwtService.getCurrentUserId(authentication);
        return ResponseEntity.ok(adService.updateAd(userId, adId, dto, file));
    }

    @Operation(summary = "광고 삭제 (JWT 인증 필요)")
    @DeleteMapping("/{adId}")
    public ResponseEntity<Void> deleteAd(
            Authentication authentication,
            @PathVariable(name = "adId") Long adId) { // ✅ 변경됨: name 속성 추가
        Long userId = authUserJwtService.getCurrentUserId(authentication);
        adService.deleteAd(userId, adId);
        return ResponseEntity.noContent().build();
    }
}
