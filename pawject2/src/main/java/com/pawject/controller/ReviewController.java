package com.pawject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pawject.dto.review.ReviewDto;
import com.pawject.dto.review.ReviewImgDto;
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
		model.addAttribute("imglist", service.reviewimgselectAll());
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
	public String write_post(@RequestParam("files") List<MultipartFile> files, 
							 ReviewDto rdto, 
							 RedirectAttributes rttr) {

		int result1 = service.reviewInsert(rdto);
		if(result1<=0) {
			rttr.addFlashAttribute("success", "리뷰 등록 실패");
			return "redirect:/reviewlist.fn";
		}
	
		int reviewid= rdto.getReviewid();
		int imgcount = service.reviewimginsert(reviewid, files);
		
		int uploadcount = 0;
		for (MultipartFile f : files) {
		    if (!f.isEmpty()) {
		    	uploadcount++;
		    }
		}
		
		//글은 써졌는데 이미지 덜 올라가거나 안올라감
		if(result1>0 && !(imgcount==uploadcount)){
			rttr.addFlashAttribute("success", "첨부하신 사진을 다시 확인해 주세요");
			return "redirect:/reviewlist.fn";
			
		}else {rttr.addFlashAttribute("success", "리뷰 등록 성공");
			return "redirect:/reviewlist.fn";
		}
	}
	
	//수정 get
	@RequestMapping("/reviewedit.fn")
	public String edit_get(@RequestParam("reviewid") int reviewid, Model model) { //여기는 안고쳐도됨
		model.addAttribute("rdto", service.reviewSelect(reviewid));
		model.addAttribute("brandlist", fservice.brandSelectAll());
		model.addAttribute("foodlist", fservice.foodselectAll());
		model.addAttribute("imglist", service.reviewimgSelect(reviewid)); //해당 아이디 이미지 묶음 추가

		return "reviewboard/reviewedit";		
	}
	
	@RequestMapping(value="/reviewedit.fn", method=RequestMethod.POST)
		public String edit_post(@RequestParam("files") List<MultipartFile> files, 
								ReviewDto rdto,
								RedirectAttributes rttr) {
		int result1 = service.reviewUpdate(rdto);
		//실패
		if(result1<=0) {
			rttr.addFlashAttribute("success", "수정 실패");
			return "redirect:/reviewedit.fn";
			}

		int reviewid= rdto.getReviewid();
		int imgcount = service.reviewimginsert(reviewid, files);
		
		int uploadcount = 0;
		for (MultipartFile f : files) {
		    if (!f.isEmpty()) {
		    	uploadcount++;
		    }
		}
		if(result1>0 && !(imgcount==uploadcount)){
			rttr.addFlashAttribute("success", "첨부하신 사진을 다시 확인해 주세요");
			return "redirect:/reviewedit.fn";
			
		}else {rttr.addFlashAttribute("success", "수정 성공");
			return "redirect:/reviewlist.fn";
		}
	}
	
	//삭제-버튼만 연결
	@RequestMapping("/reviewdelete.fn")
	public String delete(@RequestParam int reviewid,  RedirectAttributes rttr) {
		 
		 int delete = service.reviewDelete(reviewid);
		 
			if(delete>0) {
				String result="삭제 성공";
				rttr.addFlashAttribute("success", result);
				return "redirect:/reviewlist.fn";
			} else {
				String result="삭제 실패";
				rttr.addFlashAttribute("success", result);
				return "redirect:/reviewlist.fn";
			}
	}
	
	
}
