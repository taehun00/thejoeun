package com.pawject.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pawject.dto.exec.SaveweatherDto;
import com.pawject.dto.exec.WalkingcourseDto;
import com.pawject.dto.user.UserAuthDto;
import com.pawject.dto.user.UserDto;
import com.pawject.service.exec.ExecsmartService;
import com.pawject.service.exec.WalkingcourseService;
import com.pawject.service.review.ReviewService;
import com.pawject.util.UtilPaging;

@Controller
@RequestMapping("/walking")
public class WalkingcourseController {
	@Autowired
	private WalkingcourseService cservice;

	@Autowired
	private ExecsmartService sbservice;
	
	@Autowired
	private ReviewService rservice;
	
	//    /walking/walkinglist
	//@PreAuthorize("isAuthenticated()")
	@GetMapping("/walkinglist")
	public String walkinglist(Model model, @RequestParam(value="pageNo", defaultValue="1") int pageNo) {
		model.addAttribute("paging", new UtilPaging( cservice.selectwalkingTotalCnt(), pageNo));
		model.addAttribute("walkinglist", cservice.selectwalking10(pageNo));
		return "walking/walkinglist"; //화면
	}
	//Search
	//@PreAuthorize("isAuthenticated()")
	@GetMapping("/search")
	@ResponseBody
	public Map<String, Object> search(
			@RequestParam(value="pageNo"  , defaultValue="1")  int pageNo ,
			@RequestParam(value="keyword" , required=false  )  String keyword
	){
		Map<String, Object> result = new HashMap<>();
		int ctotalCnt = cservice.selectwalkingSearchTotalCnt(keyword);
		result.put("search", keyword);
		result.put("list", cservice.selectwalking3(keyword, pageNo));
		result.put("paging", new UtilPaging( ctotalCnt, pageNo, 3, 10 ));
		return result;
	}
	
	//	  /walking/write(글쓰기 폼)
	//@PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
	@GetMapping("/walkingwrite") public String write_get(WalkingcourseDto cdto, Model model ) { 
		model.addAttribute("WalkingcourseDto", sbservice.selectAllsmart());
		model.addAttribute("cdto", cdto);
		return "walking/walkingwrite";
	}

	//	  /walking/write(글쓰기기능)
	//@PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
	@PostMapping("/walkingwrite") public String write_post(
			WalkingcourseDto cdto, RedirectAttributes rttr, Principal principal) {
		String result = "글쓰기에 실패했습니다.";
	    UserDto param = new UserDto(principal.getName(), null);

	    UserAuthDto auth = rservice.readAuthForReview(param);
	    int userid = rservice.selectUserIdForReview(principal.getName());
	    cdto.setCourseid(userid);

		if(cservice.insertwalking(cdto) > 0) { result="글쓰기에 성공했습니다~!"; }
		rttr.addFlashAttribute("success", result);
		return "redirect:/walking/walkinglist";
	}

	//    /walking/walkingdetail(상세보기)
	//@PreAuthorize("isAuthenticated()")
	@GetMapping("/walkingdetail")
	public String detail(int courseid, Model model) {
		model.addAttribute("cdto", cservice.selectwalking(courseid));
		return "walking/walkingdetail";
	}

	//    /walking/walkingedit  (수정폼)
	//@PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
	@GetMapping("/walkingedit")
	public String edit_get(int courseid , Model model) {
		model.addAttribute("cdto", cservice.selectwalkingUpdateForm(courseid));   
		return "walking/walkingedit";  
	}	

	//    /walking/walkingedit  (수정기능)
	//@PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
	@PostMapping("/walkingedit") 
	public String edit_post(
			WalkingcourseDto cdto, RedirectAttributes rttr) {
		String result ="글수정에 실패했습니다.";
		if(cservice.updatewalking(cdto) > 0) { result="글수정에 성공했습니다!";}
		rttr.addFlashAttribute("success" , result);
		return "redirect:/walking/walkingdetail?courseid=" + cdto.getCourseid();
	} 	

	//    /walking/walkingdelete(삭제폼)
	//@PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
//	@GetMapping("/walkingdelete")
//	public String delete_get( Model model ) {  
//		model.addAttribute("WalkingcourseDto", new WalkingcourseDto());
//		return "walking/walkingdelete";  
//	}
	
	//    /walking/walkingdelete(삭제기능)
	//@PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
	@RequestMapping("/walkingdelete") 
	public String delete_post( @RequestParam int courseid, RedirectAttributes rttr) {
		String result ="글삭제에 실패했습니다";
		if(cservice.deletewalking(courseid) > 0) { result="글삭제에 성공했습니다!";}
		rttr.addFlashAttribute("success" , result);
		return "redirect:/walking/walkinglist";
	}
}
