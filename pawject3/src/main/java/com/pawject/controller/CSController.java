package com.pawject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pawject.dto.support.CSAnswerDto;
import com.pawject.dto.support.CSQuestionDto;
import com.pawject.service.support.CSService;

@Controller
@RequestMapping("/cssBoard")
public class CSController {
	
	@Autowired private CSService service;
	
	//본인 글만 조회
	@GetMapping("/cslistuser")
	public String userlist(Model model,CSQuestionDto qdto) {
		model.addAttribute("list", service.selectCSQUser(qdto));
		return "cssBoard/cslistuser";

	}
	
	//전체 글 조회 + 서치 기능 추가할것
	@GetMapping("/cslistadmin")
	public String adminlist(Model model,CSQuestionDto qdto) {
		model.addAttribute("list", service.selectCSQAll());
		return "cssBoard/cslistadmin";

	}
	
	
	
	
	

}
