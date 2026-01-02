package com.pawject.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pawject.dto.Writeauto.exec.AiRequestExecDto;
import com.pawject.dto.Writeauto.exec.AiResponseExecDto;
import com.pawject.service.exec.WriteautoExecService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/execapi/ai")
@RequiredArgsConstructor
public class AutoWriteExecController {
	 private final WriteautoExecService writeautoExecService;

	    @PostMapping("/write")
	    public AiResponseExecDto write(@RequestBody AiRequestExecDto wadto) {

	        String prompt =
	            "주제: " + wadto.getTopic() + "\n" +
	            "키워드: " + wadto.getKeyword() + "\n" +
	            "글길이: " + wadto.getLength() + "자 이제\n" +
	            "글을 작성해줘";

	        String result = writeautoExecService.generateText(prompt);

	        AiResponseExecDto res = new AiResponseExecDto();
	        res.setContent(result);
	        return res;
	    }
}
