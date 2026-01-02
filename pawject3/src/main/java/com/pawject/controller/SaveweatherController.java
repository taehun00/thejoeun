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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pawject.dto.exec.ExecsmartDto;
import com.pawject.dto.exec.SaveweatherDto;
import com.pawject.service.exec.SaveweatherService;
import com.pawject.util.UtilPaging;

@Controller
@RequestMapping("/weather")
public class SaveweatherController {
	@Autowired
	private SaveweatherService    wservice;
	
	//    /weather/weatherlist
	//@PreAuthorize("isAuthenticated()")
	@GetMapping("/weatherlist")
	public String weatherlist(Model model, @RequestParam(value="pageNo", defaultValue="1") int pageNo) {
		model.addAttribute("paging", new UtilPaging( wservice.selectweatherTotalCnt(), pageNo));
		model.addAttribute("weatherlist", wservice.selectweather10(pageNo));
		return "weather/weatherlist"; //화면
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
		int wtotalCnt = wservice.selectweatherSearchTotalCnt(keyword);
		result.put("search", keyword);
		result.put("list", wservice.selectweather3(keyword, pageNo));
		result.put("paging", new UtilPaging( wtotalCnt, pageNo, 3, 10 ));
		return result;
	}
	
	//	  /weather/weatherwrite(글쓰기 폼)
	//@PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
	@GetMapping("/weatherwrite") public String write_get( Model model ) { 
		model.addAttribute("SaveweatherDto", new SaveweatherDto());
		return "weather/weatherwrite"; 
	}

	//	  /weather/weatherwrite(글쓰기기능)
	//@PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
	@PostMapping("/weatherwrite") public String write_post(
			SaveweatherDto wdto, RedirectAttributes rttr) {
		String result = "글쓰기에 실패했습니다.";
		if(wservice.insertweather(wdto) > 0) { result="글쓰기에 성공했습니다~!"; }
		rttr.addFlashAttribute("success", result);
		return "redirect:/weather/weatherlist";
	}

	//    /weather/weatherdetail(상세보기)
	//@PreAuthorize("isAuthenticated()")
	@GetMapping("/weatherdetail")
	public String detail( @RequestParam(defaultValue = "0") int wid, Model model) { 
		model.addAttribute("wdto", wservice.selectweather(wid));
	    return "weather/weatherdetail";
	}

	//    /weather/weatheredit  (수정폼)
	//@PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
	@GetMapping("/weatheredit")
	public String edit_get(  int wid, Model model  ) {
		model.addAttribute("wdto", wservice.selectweatherUpdateForm(wid));   
		return "weather/weatheredit";  
	}	

	//    /weather/weatheredit  (수정기능)
	//@PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
	@PostMapping("/weatheredit") 
	public String edit_post(
			SaveweatherDto wdto, RedirectAttributes rttr) {
		String result ="글수정에 실패했습니다.";
		if(wservice.updateweather(wdto) > 0) { result="글수정에 성공했습니다!";}
		rttr.addFlashAttribute("success" , result);
		return "redirect:/weather/weatherdetail?wid=" + wdto.getWid();
	} 	
	
	//    /weather/weatherdelete(삭제폼)
	//@PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
	@GetMapping("/weatherdelete")
	public String delete_get( Model model ) { 
		model.addAttribute("SaveweatherDto", new SaveweatherDto());
		return "weather/weatherdelete";  
	}
	
	//    /weather/weatherdelete(삭제기능)
	//@PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
	@PostMapping("/weatherdelete") 
	public String delete_post( SaveweatherDto wdto, RedirectAttributes rttr) {
		String result ="글삭제에 실패했습니다";
		if(wservice.deleteweather(wdto) > 0) { result="글삭제에 성공했습니다!";}
		rttr.addFlashAttribute("success" , result);
		return "redirect:/weather/weatherlist";
	} 
}
