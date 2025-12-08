package com.pawject.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pawject.dto.exec.ExecInfoDto;
import com.pawject.service.exec.ExecInfoService;

@Controller     //처리 (service) + 화면
@RestController //처리(service) + 나온값 
public class ExecInfoAjaxController {
	
	@Autowired ExecInfoService iservice;	
	
	//////////////////////////////////////////
	@RequestMapping("/ExecInfoselectAll")
	public List<ExecInfoDto> selectAll2(){
		return iservice.selectAll2();
	}
	@RequestMapping("/ExecInfoselect")
	public Map<String, Object> select2(@RequestParam int execId){
		Map<String, Object> result = new HashMap<>();
		result.put("result", iservice.select2(execId));
		return result;
	}
	/////////////////////////////////////////////////////

}
