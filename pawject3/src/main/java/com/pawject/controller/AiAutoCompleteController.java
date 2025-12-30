package com.pawject.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pawject.dto.AiAuto.AiAutoRequest;
import com.pawject.dto.AiAuto.AiAutoResponse;
import com.pawject.service.PetChaApi.OpenAiService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AiAutoCompleteController {

    private final OpenAiService openAiService;

    @PostMapping("/autocomplete")
    public AiAutoResponse autocomplete(@RequestBody AiAutoRequest req) {
        return openAiService.generateDiseaseInfo(req.getDisname());
    }
}
