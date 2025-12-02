package com.pawject.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pawject.dto.review.ReviewDto;
import com.pawject.service.food.FoodService;
import com.pawject.service.review.ReviewService;

@Controller
public class ReviewController {

@Autowired ReviewService service;
@Autowired FoodService fservice;

	//전체 리스트 페이지
	@RequestMapping("/reviewlist.fn")
		public String reviewlist(Model model) {
		model.addAttribute("reviewlist", service.reviewSelectAll());
		return "reviewboard/reviewlist";
	}
	
	//글쓰기 get
	@RequestMapping("/reviewwrite.fn")
	public String write_get(Model model) {
		
	    model.addAttribute("brandlist", fservice.brandSelectAll());
	    model.addAttribute("foodlist", fservice.foodselectAll());
	    
		return "reviewboard/reviewwrite";
	} 
	
	//글쓰기 post
	@RequestMapping(value="/reviewwrite.fn", method=RequestMethod.POST)
	public String write_post(@RequestParam("file") MultipartFile file,
								ReviewDto rdto,   RedirectAttributes rttr) {
		String result = "리뷰 등록 실패";
	    
	    if(service.reviewInsert(rdto, file) > 0){
	    	result="리뷰 등록 성공";
	        rttr.addFlashAttribute("success", result);
	        return "redirect:/reviewlist.fn"; 
	    } else {
	        rttr.addFlashAttribute("success", result);
	        return "redirect:/reviewwrite.fn";
	    }
			
	}
	
	//수정 get
	@RequestMapping("/reviewedit.fn")
	public String edit_get(@RequestParam("reviewid") int reviewid, Model model) {
		model.addAttribute("rdto", service.reviewSelect(reviewid));
		model.addAttribute("brandlist", fservice.brandSelectAll());
		model.addAttribute("foodlist", fservice.foodselectAll());

		return "reviewboard/reviewedit";		
	}
	
	@RequestMapping(value="/reviewedit.fn", method=RequestMethod.POST)
		public String edit_post(@RequestParam("file") MultipartFile file,
								ReviewDto rdto,
								RedirectAttributes attr) {
		int result=service.reviewUpdate(rdto, file);
		
		if(result>0) {
			attr.addFlashAttribute("success", "수정 성공");
			return "redirect:/reviewlist.fn";
		}else {attr.addFlashAttribute("success", "비밀번호를 확인해 주세요");
			return "redirect:/reviewedit.fn";
		}
			
		}
	
	@RequestMapping("/reviewdelete.fn")
	public String delete_get(@RequestParam("reviewid") int reviewid, Model model) {
	    model.addAttribute("rdto", service.reviewSelect(reviewid));
	    return "reviewboard/reviewdelete";
	}
	
	@RequestMapping(value="/reviewdelete.fn", method=RequestMethod.POST)
		public String reviewdelete_post( int reviewid, RedirectAttributes rttr) {
		int result = service.reviewDelete(reviewid);
		 if(result>0) {
			rttr.addFlashAttribute("success", "삭제 성공");
			return "redirect:/reviewlist.fn";
		 }else {
			 rttr.addFlashAttribute("success", "비밀번호를 확인해 주세요");
			 return "redirect:/reviewdelete.fn";
		 }
		
		
		
	}
	
	
}
