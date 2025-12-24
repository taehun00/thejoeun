package com.pawject.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pawject.dto.support.FAQDto;
import com.pawject.service.support.FAQService;

@Controller
@RequestMapping("/faqBoard")
public class FAQController {
	@Autowired private FAQService service;
	
	//리스트
	@GetMapping("/faqlist")
	public String list(Model model) {
			model.addAttribute("list", service.selectFAQAll());
		return "faqBoard/faqlist";
	}
	
	// 글쓰기
	@GetMapping("/faqwrite") public String write_get(Model model) {  
		model.addAttribute("categories", List.of("계정", "서비스", "이벤트", "기타"));
		return "faqBoard/faqwrite";
		}
	
	@PostMapping("/faqwrite") public String write_post(FAQDto dto, 
														RedirectAttributes rttr) {
		String result ="글쓰기 실패";
		if(service.insertFAQ(dto) > 0) { result="글쓰기 성공!";}
		rttr.addFlashAttribute("success" , result);
		return "redirect:/faqBoard/faqlist";
	} 	
	
		// 수정
		@GetMapping("/faqedit")
		public String edit_get(int faqid , Model model) {
			model.addAttribute("dto", service.selectFAQ(faqid));   
			model.addAttribute("categories", List.of("계정", "서비스", "이벤트", "기타"));
			return "faqBoard/faqedit";
		}	
		
		@PostMapping("/faqedit") 
		public String edit_post(FAQDto dto, 
									RedirectAttributes rttr) {
			String result ="글수정 실패";
			if(service.updateFAQ(dto)> 0) { result="글수정 성공!";}
			rttr.addFlashAttribute("success" , result);
			return "redirect:/faqBoard/faqlist";
		} 	
		
		
		// 빠른활성화-아작스
		@ResponseBody
		@PostMapping("/quickactive")
		public Map<String, Object> quickActive(FAQDto dto) {

		    Map<String, Object> result = new HashMap<>();

		    int updated = service.activeFAQ(dto);
		    result.put("result", updated);

		    return result;
		}
}
