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

import com.pawject.dto.exec.ExecinfoDto;
import com.pawject.dto.exec.ExecsmartDto;
import com.pawject.dto.user.UserAuthDto;
import com.pawject.dto.user.UserDto;
import com.pawject.service.exec.ExecinfoService;
import com.pawject.service.review.ReviewService;
import com.pawject.util.UtilPaging;

@Controller
@RequestMapping("/info")
public class ExecinfoController {
	@Autowired
	private ExecinfoService iservice;
	
	@Autowired
	private ReviewService rservice;
	
	//    /info/infolist
	//@PreAuthorize("isAuthenticated")
	@GetMapping("/infolist")
	public String infolist(Model model, @RequestParam(value="pageNo", defaultValue="1") int pageNo) {
		model.addAttribute("paging", new UtilPaging( iservice.selectinfoTotalCnt(), pageNo));
		model.addAttribute("infolist", iservice.selectinfo10(pageNo));
		return "info/infolist"; //화면
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
		int itotalCnt = iservice.selectinfoSearchTotalCnt(keyword);
		result.put("search", keyword);
		result.put("list", iservice.selectinfo3(keyword, pageNo));
		result.put("paging", new UtilPaging( itotalCnt, pageNo, 3, 10 ));
		return result;
	}
	
	//	  /info/infowrite(글쓰기 폼)
	//@PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
	@GetMapping("/infowrite") public String write_get(ExecinfoDto idto, Model model ) { 
		model.addAttribute("idto", idto);
		return "info/infowrite"; 
	}

	//	  /info/infowrite(글쓰기기능)
	//@PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
	@PostMapping("/infowrite") public String write_post(
			ExecinfoDto idto, RedirectAttributes rttr, Principal principal) {
		String result = "글쓰기에 실패했습니다.";
	    UserDto param = new UserDto(principal.getName(), null);

	    UserAuthDto auth = rservice.readAuthForReview(param);
	    int userid = rservice.selectUserIdForReview(principal.getName());
	    idto.setExecid(userid);

		
		if(iservice.insertinfo(idto) > 0) { result="글쓰기에 성공했습니다~!"; }
		rttr.addFlashAttribute("success", result);
		return "redirect:/info/infolist";
	}
	//    /info/infodetail(상세보기)
	//@PreAuthorize("isAuthenticated")
	@GetMapping("/infodetail")
	public String detail(int execid, Model model) {
		model.addAttribute("idto", iservice.selectinfo(execid));
		return "info/infodetail";
	}
	
	//    /info/infoedit  (수정폼)
	//@PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
	@GetMapping("/infoedit")
	public String edit_get(int execid , Model model) {
		model.addAttribute("idto", iservice.selectinfoUpdateForm(execid));   
		return "info/infoedit";  
	}	
	
	//    /info/infoedit  (수정기능)
	//@PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
	@PostMapping("/infoedit") 
	public String edit_post(
			ExecinfoDto idto, RedirectAttributes rttr) {
		String result ="글수정에 실패했습니다.";
		if(iservice.updateinfo(idto) > 0) { result="글수정에 성공했습니다!";}
		rttr.addFlashAttribute("success" , result);
		return "redirect:/info/infodetail?execid=" + idto.getExecid();
	} 	

	//    /info/infodelete(삭제폼)
	//@PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
//	@GetMapping("/infodelete")
//	public String delete_get( Model model ) { 
//		model.addAttribute("ExecinfoDto", new ExecinfoDto());
//		return "info/infodelete";  
//	}

	//    /info/infodelete(삭제기능)
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
	@RequestMapping("/infodelete") 
	public String delete_post( @RequestParam int execid, RedirectAttributes rttr) {
		String result ="글삭제에 실패했습니다.";
		if(iservice.deleteinfo(execid) > 0) { result="글삭제에 성공했습니다!";}
		rttr.addFlashAttribute("success" , result);
		return "redirect:/info/infolist";
	} 
}
