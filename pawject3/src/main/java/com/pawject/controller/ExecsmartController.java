package com.pawject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pawject.service.exec.ExecsmartService;
import com.pawject.util.UtilPaging;

@Controller
@RequestMapping("/smart")
public class ExecsmartController {
	@Autowired
	private ExecsmartService sbservice;
	//    /smart/list
	@GetMapping("/list")
	public String list(Model model, @RequestParam(value="pageNo", defaultValue="1") int pageNo) {
		model.addAttribute("paging", new UtilPaging( sbservice.selectsmartTotalCnt(), pageNo ));
		model.addAttribute("list", sbservice.selectsmart10(pageNo));
		return "/smart/list"; //화면
	}
	//	  /smart/write(글쓰기 폼)
	//	  /smart/write(글쓰기기능)
	//    /smart/detail(상세보기)
	//    /smart/edit  (수정폼)
	//    /smart/edit  (수정기능)
	//    /smart/delete(삭제폼)
	//    /smart/delete(삭제기능)
}
