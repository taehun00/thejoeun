package com.pawject.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pawject.dto.paging.PagingDto10;
import com.pawject.dto.user.UserAuthDto;
import com.pawject.service.food.FoodService;
import com.pawject.service.review.ReviewService;
import com.pawject.service.user.UserSecurityService;

@Controller
public class ReviewController {

@Autowired ReviewService service;
@Autowired FoodService fservice;
@Autowired UserSecurityService uservice;

		//전체 리스트 페이지+시큐리티
		@RequestMapping("/reviewlist.fn")
		public String reviewlist(Model model,
		                       @RequestParam(value="pstartno", defaultValue = "1") int pstartno,
		                       Principal principal) {
		
		  int total = service.reviewSelectCnt();
		  PagingDto10 paging = new PagingDto10(total, pstartno);
		  model.addAttribute("reviewlist", service.reviewSelect10(paging.getCurrent()));
		  model.addAttribute("reviewpaging", paging);
		
	
		  if (principal != null) {
		      // principal.getName() → username(email 또는 userid)
		      UserAuthDto user = uservice.readAuth(principal.getName());
		
		      model.addAttribute("userid", user.getUserId());
		      model.addAttribute("author", user.getAuthList().get(0).getAuth()); // ROLE_ADMIN / ROLE_MEMBER
		  }
		
		  return "reviewboard/reviewlist";
		}
	
//	@RequestMapping("/reviewlist.fn")
//		public String reviewlist(Model model) {
//		model.addAttribute("reviewlist", service.reviewSelectAll());
//		model.addAttribute("imglist", service.reviewimgselectAll());
//		return "reviewboard/reviewlist";
//	}
//	

	
	//글쓰기 get
	@RequestMapping("/reviewwrite.fn")
	@PreAuthorize("isAuthenticated() and hasAnyRole('ROLE_ADMIN', 'ROLE_MEMBER')") 
	public String write_get(Model model) {
	    model.addAttribute("brandlist", fservice.brandSelectAll());
	    model.addAttribute("foodlist", fservice.foodselectAll());
	    
		return "reviewboard/reviewwrite";
	} 
	
	//글쓰기 post
//	@RequestMapping(value="/reviewwrite.fn", method=RequestMethod.POST)
//	public String write_post(@RequestParam("files") List<MultipartFile> files, 
//							 ReviewDto rdto, 
//							 RedirectAttributes rttr) {
//
//		int result1 = service.reviewInsert(rdto);
//		if(result1<=0) {
//			rttr.addFlashAttribute("success", "리뷰 등록 실패");
//			return "redirect:/reviewlist.fn";
//		}
//	
//		int reviewid= rdto.getReviewid();
//		int imgcount = service.reviewimginsert(reviewid, files);
//		
//		int uploadcount = 0;
//		for (MultipartFile f : files) {
//		    if (!f.isEmpty()) {
//		    	uploadcount++;
//		    }
//		}
//		
//		//글은 써졌는데 이미지 덜 올라가거나 안올라감
//		if(result1>0 && !(imgcount==uploadcount)){
//			rttr.addFlashAttribute("success", "첨부하신 사진을 다시 확인해 주세요");
//			return "redirect:/reviewlist.fn";
//			
//		}else {rttr.addFlashAttribute("success", "리뷰 등록 성공");
//			return "redirect:/reviewlist.fn";
//		}
//	}
	
	//수정 get
	@RequestMapping("/reviewedit.fn")
	@PreAuthorize("isAuthenticated() and hasAnyRole('ROLE_ADMIN', 'ROLE_MEMBER')") 
	public String edit_get(@RequestParam("reviewid") int reviewid, Model model) { //여기는 안고쳐도됨
		model.addAttribute("rdto", service.reviewSelect(reviewid));
		model.addAttribute("brandlist", fservice.brandSelectAll());
		model.addAttribute("foodlist", fservice.foodselectAll());
		model.addAttribute("imglist", service.reviewimgSelect(reviewid)); //해당 아이디 이미지 묶음 추가

		return "reviewboard/reviewedit";		
	}
//	
//	@RequestMapping(value="/reviewedit.fn", method=RequestMethod.POST)
//		public String edit_post(@RequestParam("files") List<MultipartFile> files, 
//								ReviewDto rdto,
//								RedirectAttributes rttr) {
//		int result1 = service.reviewUpdate(rdto);
//		//실패
//		if(result1<=0) {
//			rttr.addFlashAttribute("success", "수정 실패");
//			return "redirect:/reviewedit.fn";
//			}
//
//		int reviewid= rdto.getReviewid();
//		int imgcount = service.reviewimginsert(reviewid, files);
//		
//		int uploadcount = 0;
//		for (MultipartFile f : files) {
//		    if (!f.isEmpty()) {
//		    	uploadcount++;
//		    }
//		}
//		if(result1>0 && !(imgcount==uploadcount)){
//			rttr.addFlashAttribute("success", "첨부하신 사진을 다시 확인해 주세요");
//			return "redirect:/reviewedit.fn";
//			
//		}else {rttr.addFlashAttribute("success", "수정 성공");
//			return "redirect:/reviewlist.fn";
//		}
//	}
	
	//삭제-버튼만 연결
	@RequestMapping("/reviewdelete.fn")
	@PreAuthorize("isAuthenticated() and hasAnyRole('ROLE_ADMIN', 'ROLE_MEMBER')") 
	public String delete(@RequestParam int reviewid,  RedirectAttributes rttr) {
		 //순서 주의! 이미지->리뷰 순 삭제
		 int delete1 = service.reviewimgdeleteAll(reviewid); 
		 int delete2 = service.reviewDelete(reviewid);
		 
			if(delete1>0 && delete2>0) {
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


//if(delete>0) {
//	String result="삭제 성공";
//	rttr.addFlashAttribute("success", result);
//	return "redirect:/reviewlist.fn";
//} else {
//	String result="삭제 실패";
//	rttr.addFlashAttribute("success", result);
//	return "redirect:/reviewlist.fn";
//}
