package com.pawject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pawject.dto.exec.ExecPagingDto;
import com.pawject.service.exec.ExecInfoService;

@Controller
public class ExecInfoController {
	@Autowired ExecInfoService iservice;
	
	////////////////////////////////////
	//전체보기(+ Paging)
	@RequestMapping("/list.execinfo")
	public String list( Model model,
			@RequestParam(value="pstartno", defaultValue="1") int pstartno
			) {
		model.addAttribute("list", iservice.select10(pstartno));  //
		model.addAttribute("paging", new ExecPagingDto( iservice.selectTotalCnt(), pstartno));
		return "/execinfo/list";
	}
	////////////////////////////////////
	//상세보기
	@RequestMapping("/detail.execinfo")
	public String detail_get(int execid, Model model) {
		model.addAttribute("dto", iservice.select2(execid));
		return "execboard/detail";
	}
	////////////////////////////////////
}

/*
 /list.execinfo            /view/execinfo/list.jsp 
 /detail.execinfo        /view/execinfo/detail.jsp    (상세보기)
*/
