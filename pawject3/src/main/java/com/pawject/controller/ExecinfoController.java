package com.pawject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pawject.service.exec.ExecinfoService;

@Controller
@RequestMapping("/info")
public class ExecinfoController {
	@Autowired
	private ExecinfoService iservice;
	//    /info/list
	//	  /info/write(글쓰기 폼)
	//	  /info/write(글쓰기기능)
	//    /info/detail(상세보기)
	//    /info/edit  (수정폼)
	//    /info/edit  (수정기능)
	//    /info/delete(삭제폼)
	//    /info/delete(삭제기능)

}
