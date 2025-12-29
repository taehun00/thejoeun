package com.pawject.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pawject.dto.paging.PagingDto10;
import com.pawject.dto.review.ReviewDto;
import com.pawject.dto.review.ReviewImgDto;
import com.pawject.dto.user.UserAuthDto;
import com.pawject.dto.user.UserDto;
import com.pawject.service.food.FoodService;
import com.pawject.service.review.ReviewService;
import com.pawject.service.user.UserSecurityService;

@Controller
public class ReviewController {

	@Autowired
	ReviewService service;
	@Autowired
	FoodService fservice;
	@Autowired
	UserSecurityService uservice;

	// 전체 리스트 페이지+시큐리티
	@RequestMapping("/reviewlist.fn")
	public String reviewlist(Model model, @RequestParam(value = "pstartno", defaultValue = "1") int pstartno,
			Principal principal) {

		int total = service.reviewSelectCnt();
		PagingDto10 paging = new PagingDto10(total, pstartno);
		model.addAttribute("reviewlist", service.reviewSelect10(paging.getCurrent()));
		model.addAttribute("reviewpaging", paging);

		if (principal != null) {
		    UserDto param = new UserDto(principal.getName(), null);

		    UserAuthDto auth = service.readAuthForReview(param);
		    int userid = service.selectUserIdForReview(principal.getName());

		    model.addAttribute("author", auth.getAuthList().get(0).getAuth());
		    model.addAttribute("userid", userid);
		}

		return "reviewboard/reviewlist";
	}

	// 글쓰기 get
	@RequestMapping("/reviewwrite.fn")
	@PreAuthorize("isAuthenticated() and hasAnyRole('ROLE_ADMIN', 'ROLE_MEMBER')")
	public String write_get(Model model) {
		model.addAttribute("brandlist", fservice.brandSelectAll());
		model.addAttribute("foodlist", fservice.foodselectAll());

		return "reviewboard/reviewwrite";
	}

	// 수정 get
	@RequestMapping("/reviewedit.fn")
	@PreAuthorize("isAuthenticated() and hasAnyRole('ROLE_ADMIN', 'ROLE_MEMBER')")
	public String edit_get(@RequestParam("reviewid") int reviewid, Model model) { // 여기는 안고쳐도됨
		model.addAttribute("rdto", service.reviewSelect(reviewid));
		model.addAttribute("brandlist", fservice.brandSelectAll());
		model.addAttribute("foodlist", fservice.foodselectAll());
		model.addAttribute("imglist", service.reviewimgSelect(reviewid)); // 해당 아이디 이미지 묶음 추가

		return "reviewboard/reviewedit";
	}

	// 삭제-버튼만 연결
	@RequestMapping("/reviewdelete.fn")
	@PreAuthorize("isAuthenticated() and hasAnyRole('ROLE_ADMIN', 'ROLE_MEMBER')")
	public String delete(@RequestParam int reviewid, RedirectAttributes rttr) {
		// 순서 주의! 이미지->리뷰 순 삭제
		int delete1 = service.reviewimgdeleteAll(reviewid);
		int delete2 = service.reviewDelete(reviewid);

		if (delete1 > 0 && delete2 > 0) {
			String result = "삭제 성공";
			rttr.addFlashAttribute("success", result);
			return "redirect:/reviewlist.fn";
		} else {
			String result = "삭제 실패";
			rttr.addFlashAttribute("success", result);
			return "redirect:/reviewlist.fn";
		}
	}

}
