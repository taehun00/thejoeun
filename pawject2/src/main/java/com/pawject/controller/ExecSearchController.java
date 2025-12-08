package com.pawject.controller;

import java.util.List;

<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Autowired; 
=======
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> cb4a38b2ec94f84ae8d5b1165d16b0247b5b119c
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pawject.dto.exec.ExecBoardDto;
import com.pawject.dto.exec.ExecInfoDto;
import com.pawject.service.exec.ExecBoardService;
import com.pawject.service.exec.ExecInfoService;

@RestController //@Controller + @ResponseBody
public class ExecSearchController {
	
	@Autowired private ExecBoardService service;
	@Autowired private ExecInfoService iservice;
	
//	@RequestMapping("/searchTest")
//	//@ResponseBody  //해당하는 값을 던져줌 (Ajax기본원리)
//	public String hi() {
//		//처리하고
//		return "HI"; //화면 + 값줄게(@ResponseBody)
//	}
	
	@RequestMapping("/selectSearch1")
	public List<ExecBoardDto> selectSearch1(@RequestParam("search") String search) {
		return service.selectSearch1(search); //값줄게(@ResponseBody)
	}
	
	@RequestMapping("/selectSearch2")
	public List<ExecInfoDto> selectSearch2(@RequestParam("search") String search) {
		return iservice.selectSearch2(search); //값줄게(@ResponseBody)
	}

	
}

/*
@Controller  
public class SearchController {
	
	@Autowired private Sboard1Service service;
	@RequestMapping("/searchTest")
	@ResponseBody  //해당하는 값을 던져줌 (Ajax기본원리)
	public String hi() {
		//처리하고
		return "HI"; //값줄게(@ResponseBody)
	}

*/