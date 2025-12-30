package com.pawject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pawject.service.exec.SaveweatherService;

@Controller
@RequestMapping("/weather")
public class SaveweatherController {
	@Autowired
	private SaveweatherService    wservice;
	//    /weather/list
	//	  /weather/write(글쓰기 폼)
	//	  /weather/write(글쓰기기능)
	//    /weather/detail(상세보기)
	//    /weather/edit  (수정폼)
	//    /weather/edit  (수정기능)
	//    /weather/delete(삭제폼)
	//    /weather/delete(삭제기능)

}
