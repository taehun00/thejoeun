package com.pawject.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pawject.dto.exec.ExecBoardDto;
import com.pawject.service.exec.ExecBoardService;

@Controller     //처리 (service) + 화면
@RestController //처리(service) + 나온값 
public class ExecBoardAjaxController {
	
	@Autowired ExecBoardService service;	
	
	//////////////////////////////////////////
	@RequestMapping("/ExecBoardselectAll")
	public List<ExecBoardDto> selectAll(){
		return service.selectAll1();
	}
	@RequestMapping("/ExecBoardselect")
	public Map<String, Object> select1(@RequestParam int postId){
		Map<String, Object> result = new HashMap<>();
		result.put("result", service.select1(postId));
		return result;
	}
	/////////////////////////////////////////////////////
}
