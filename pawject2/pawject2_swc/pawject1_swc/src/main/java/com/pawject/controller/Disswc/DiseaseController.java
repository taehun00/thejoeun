package com.pawject.controller.Disswc;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pawject.dto.Disswc.DisswcDto;
import com.pawject.service.Disswc.DiseaseService;






@Controller
public class DiseaseController {
	
	@Autowired DiseaseService  service;
	@RequestMapping("/list.quest")   //       
	public String list(Model model) {
		Map<String, Object> params = new HashMap<>();
		params.put("start", 0);
		params.put("end", 10);
		model.addAttribute("list",service.selectAll(params));
		  // 처리하고
		return "quest_board/list";   //해당화면    /view/ 폴더안에    +  파일명    + .jsp
	}
	// 글쓰기 폼
	@RequestMapping(value="/write.quest" , method=RequestMethod.GET)
	public String write_get() { return "quest_board/write"; }
	// 글쓰기 기능
	@RequestMapping(value="/write.quest" , method=RequestMethod.POST)
	public String write_post( DisswcDto dto, RedirectAttributes rttr) { 
		String result ="글쓰기 실패";
		if( service.insert(dto)  > 0  ) {  result ="글쓰기 성공"; }
		rttr.addFlashAttribute("success", result);
		return "redirect:/list.quest"; 
	}
	 
	@RequestMapping("/detail.quest") //상세보기
	public String detail(int disno, Model model) { 
		model.addAttribute("dto", service.select(disno));
		return "quest_board/detail"; 
	}
	
	@RequestMapping(value="/edit.quest" , method=RequestMethod.GET)  //수정폼
	public String edit_get(int disno, Model model) {
		model.addAttribute("dto", service.selectUpdateForm(disno));
		return "quest_board/edit"; 
	}
	
	@RequestMapping(value="/edit.quest" , method=RequestMethod.POST) //수정기능
	public String edit_post( DisswcDto dto,   RedirectAttributes rttr) { 
		System.out.println("......... edit.quest" + dto );
		String result = "비밀번호를 확인해주세요";
		if( service.update(dto)  > 0  ) {  result ="수정 성공"; }
		rttr.addFlashAttribute("success" , result);
		return "redirect:/detail.quest?disno=" + dto.getDisno(); 
	
	}
	//Q1. 수정기능도    비밀번호를 확인해주세요 알림창 + /detail.quest  경로넘기기
	
	@RequestMapping(value="/delete.quest" , method=RequestMethod.GET) //삭제폼
	public String delete_get() { return "quest_board/delete"; }
	
	@RequestMapping(value="/delete.quest" , method=RequestMethod.POST) //삭제기능
	public String delete_post(DisswcDto dto, RedirectAttributes rttr) { 
		String result = "비밀번호를 확인해주세요";
		if( service.delete(dto.getDisno())  > 0  ) {  result ="삭제 성공"; }
		rttr.addFlashAttribute("success" , result);
		return "redirect:/list.quest"; 
	
	}
	//Q2. 삭제기능도   비밀번호를 확인해주세요 알림창 + /list.quest  경로넘기기
	
	
	/*  Upload	 */
	// 글쓰기 기능
	
	 
}


/*
/list.quest            /view/quest_board/list.jsp 
/write.quest           /view/quest_board/write.jsp    (글쓰기폼)
/detail.quest          /view/quest_board/detailjsp    (상세보기)
/edit.quest            /view/quest_board/edit.jsp     (수정하기폼)
/delete.quest          /view/quest_board/delete.jsp   (삭제하기폼)
*/


