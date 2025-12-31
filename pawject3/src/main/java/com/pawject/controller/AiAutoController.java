package com.pawject.controller;

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

    @PostMapping("/autocomplete")
    public ResponseEntity<?> autoComplete(@RequestBody AiAutoRequest req) {

        // 1️⃣ 필수값 검증
        if (req.getDisname() == null || req.getDisname().isBlank()) {
            return ResponseEntity
                .badRequest()
                .body("질환명은 필수입니다.");
        }

        // 2️⃣ OpenAI 호출
        try {
            AiAutoResponse response = aiAutoService.autoFill(req.getDisname());
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            // 로그는 반드시 남길 것
            e.printStackTrace();

            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("AI 자동완성 처리 중 오류가 발생했습니다.");
        }
    }
}
