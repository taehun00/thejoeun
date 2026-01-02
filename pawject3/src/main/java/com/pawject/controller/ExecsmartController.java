package com.pawject.controller;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pawject.dto.exec.ExecsmartDto;
import com.pawject.dto.exec.WalkingcourseDto;
import com.pawject.service.exec.ExecsmartService;
import com.pawject.util.UtilPaging;

@Controller
@RequestMapping("/smart")
public class ExecsmartController {
	@Autowired
	private ExecsmartService sbservice;
	//    /smart/smartlist
	
	//전체글보기
	//@PreAuthorize("isAuthenticated")
	@GetMapping("/smartlist")
	public String list(Model model, @RequestParam(value="pageNo", defaultValue="1") int pageNo) {
		model.addAttribute("paging", new UtilPaging( sbservice.selectsmartTotalCnt(), pageNo ));
		model.addAttribute("smartlist", sbservice.selectsmart10(pageNo));
		return "smart/smartlist"; //화면
	}
	//Search
	//@PreAuthorize("isAuthenticated")
	@GetMapping("/search")
	@ResponseBody
	public Map<String, Object> search(
			@RequestParam(value="pageNo"  , defaultValue="1")  int pageNo ,
			@RequestParam(value="keyword" , required=false  )  String keyword
	){
		Map<String, Object> result = new HashMap<>();
		int stotalCnt = sbservice.selectsmartSearchTotalCnt(keyword);
		result.put("search", keyword);
		result.put("list", sbservice.selectsmart3(keyword, pageNo));
		result.put("paging", new UtilPaging( stotalCnt, pageNo, 3, 10 ));
		return result;
	}
	
	//	  /smart/smartwrite(글쓰기 폼)
	//@PreAuthorize("isAuthenticated() and hasRole('ROLE_MEMBER')")
	@GetMapping("/smartwrite") public String write_get( Model model ) { 
		model.addAttribute("execsmartDto", new ExecsmartDto());
		return "smart/smartwrite"; 
	}
	
	//	  /smart/smartwrite(글쓰기기능)
	//@PreAuthorize("isAuthenticated() and hasRole('ROLE_MEMBER')")
	@PostMapping("/smartwrite") public String write_post(
			MultipartFile file , ExecsmartDto sdto, RedirectAttributes rttr) {
		String result = "글쓰기에 실패했습니다.";
		if(sbservice.insertsmart(file, sdto) > 0) { result="글쓰기에 성공했습니다~!"; }
		rttr.addFlashAttribute("success", result);
		return "redirect:/smart/smartlist";
	}
	//    /smart/smartdetail(상세보기)
	//@PreAuthorize("isAuthenticated() and hasRole('ROLE_MEMBER')")
	@GetMapping("/smartdetail")
	public String detail(int postid, Model model) {
		model.addAttribute("sdto", sbservice.selectsmart(postid));
		return "smart/smartdetail";
	}
	//    /smart/smartedit  (수정폼)
	//@PreAuthorize("isAuthenticated()")
	@GetMapping("/smartedit")
	public String edit_get(int postid, Model model) {
		model.addAttribute("sdto", sbservice.selectsmartUpdateForm(postid));
		return "smart/smartedit";
	}
	//    /smart/smartedit  (수정기능)
	//@PreAuthorize("isAuthenticated()")
	@PostMapping("/smartedit")
	public String edit_post( 
			MultipartFile file , ExecsmartDto sdto, RedirectAttributes rttr ) {
		String result = "글수정에 실패했습니다.";
		if(sbservice.updatesmart(file, sdto) > 0) { result="글수정에 성공했습니다~!"; }
		rttr.addFlashAttribute("success", result);
		return "redirect:/smart/smartdetail?postid=" + sdto.getPostid();
	}
	
	//    /smart/smartdelete(삭제폼)
	//@PreAuthorize("isAuthenticated")
	@GetMapping("/smartdelete")
	public String delete_get( Model model ) { 
		model.addAttribute("ExecsmartDto", new ExecsmartDto());
		return "smart/smartdelete"; 
	}
	
	//    /smart/smartdelete(삭제기능
	//@PreAuthorize("isAuthenticated")
	@PostMapping("/smartdelete")
	public String delete_post( ExecsmartDto sdto, RedirectAttributes rttr ) {
		String result = "글삭제에 실패했습니다.";
		if(sbservice.deletesmart(sdto) > 0) { result="글삭제에 성공했습니다~!"; }
		rttr.addFlashAttribute("success", result);
		return "redirect:/smart/smartlist";
	}
}
