package com.pawject.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pawject.dto.AiAuto.AiAutoRequest;
import com.pawject.dto.AiAuto.AiAutoResponse;
import com.pawject.service.PetChaApi.AiAutoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AiAutoController {

    private final AiAutoService aiAutoService;

    /**
     * AI 자동완성 API
     * - 요청: AiAutoRequest (질환명 disname)
     * - 응답: AiAutoResponse (질환 설명, 반려동물 종, 관련 수치, 주의사항)
     * - 내부적으로 OpenAI Responses API (https://api.openai.com/v1/responses) 호출
     */
    @PostMapping("/autocomplete")
    public ResponseEntity<?> autoComplete(@RequestBody AiAutoRequest req) {

        // 요청 유효성 검사
        if (req.getDisname() == null || req.getDisname().isBlank()) {
            return ResponseEntity
                .badRequest()
                .body(Map.of("message", "질환명(disname)은 필수 입력값입니다."));
        }

        try {
            AiAutoResponse result = aiAutoService.autoFill(req.getDisname());
            return ResponseEntity.ok(result);

        } catch (Exception e) {
            // 예외 발생 시 에러 메시지 반환
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of(
                    "message", "AI 자동완성 처리 중 오류가 발생했습니다.",
                    "error", e.getMessage()
                ));
        }
    }
}