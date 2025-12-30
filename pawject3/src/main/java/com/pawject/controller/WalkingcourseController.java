package com.pawject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pawject.service.exec.SaveweatherService;
import com.pawject.service.exec.WalkingcourseService;

@Controller
@RequestMapping("/walking")
public class WalkingcourseController {
	@Autowired
	private WalkingcourseService cservice;
	//    /walking/list
	//	  /walking/write(글쓰기 폼)
	//	  /walking/write(글쓰기기능)
	//    /walking/detail(상세보기)
	//    /walking/edit  (수정폼)
	//    /walking/edit  (수정기능)
	//    /walking/delete(삭제폼)
	//    /walking/delete(삭제기능)

}
