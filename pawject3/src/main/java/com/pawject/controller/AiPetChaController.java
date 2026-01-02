package com.pawject.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.pawject.service.PetChaApi.PetChaApiService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/api/pet")
@RequiredArgsConstructor
public class AiPetChaController {

    private final PetChaApiService petChaApiService;

    /**
     * GET 요청 처리 (페이지 반환)
     * - 브라우저에서 직접 /api/pet/character 접근 시 HTML 페이지 반환
     */
    @GetMapping("/character")
    public String characterForm(Model model) {
        model.addAttribute("message", "반려동물 캐릭터 생성 페이지");
        return "board/Aicharacter"; // templates/admin/board/Aicharacter.html 렌더링
    }

    /**
     * POST 요청 처리 (캐릭터 생성)
     * - 요청: 원본 이미지(file) 또는 반려동물 종(kindpet), 사용자 ID(userid)
     * - 응답: 생성된 캐릭터 이미지 URL
     */
    @PostMapping("/character")
    @ResponseBody
    public ResponseEntity<?> createCharacter(
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam(value = "kindpet", required = false) String kindpet,
            @RequestParam(value = "userid") Integer userid) {

        if ((file == null || file.isEmpty()) && (kindpet == null || kindpet.isBlank())) {
            return ResponseEntity.badRequest()
                .body(Map.of("message", "원본 이미지 또는 반려동물 종을 입력해야 합니다."));
        }

        try {
            String imageUrl = petChaApiService.createCharacter(userid, file, kindpet);
            return ResponseEntity.ok(Map.of("url", imageUrl));
        } catch (Exception e) {
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        		    .body(Map.of("message", "OpenAI API 호출 중 오류가 발생했습니다."));
			/*
			 * return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
			 * .body(Map.of("message", "캐릭터 생성 실패", "error", e.getMessage()));
			 */
        }
    }
}