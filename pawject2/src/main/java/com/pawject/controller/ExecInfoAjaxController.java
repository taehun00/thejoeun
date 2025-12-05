package com.pawject.controller;

//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.pawject.dto.exec.ExecInfoDto;
//import com.pawject.service.exec.ExecInfoService;

//@Controller     처리 (service) + 화면
//@RestController //처리(service) + 나온값 
//public class ExecInfoAjaxController {
//	
//	@Autowired ExecInfoService iservice;	
	
//	@RequestMapping("/iddouble")
//	public Map<String, Object> iddouble(@RequestParam String email){
//		Map<String, Object> result = new HashMap<>();
//		result.put("cnt", service.iddouble(email));
//		return result;
//	}
	//////////////////////////////////////////
	/* admin 유저관리 - 전체유저정보 selectAll
	 * 				/ 아이디주면 해당 유저정보찾기 select 
	 * 				/ 수정하기 updateAdimin
	 * 				/ 삭제하기 deleteAdimin */
//	@RequestMapping("/ExecInfoselectAll")
//	public List<ExecInfoDto> selectAll2(){
//		return iservice.selectAll2();
//	}
//	//http://localhost:8282/EX005_member/select?appUserId=121
//	@RequestMapping("/ExecInfoselect")
//	public Map<String, Object> select2(@RequestParam int execId){
//		Map<String, Object> result = new HashMap<>();
//		result.put("result", iservice.select2(execId));
//		return result;
//	}
	/////////////////////////////////////////////////////
	//권한부여 Admin
//	@RequestMapping("/updateExecBoardAdmin")
//	public Map<String, Object> updateAdmin2(@RequestParam int execId,
//										   @RequestParam String execType){
//		Map<String, Object> result = new HashMap<>();
//		ExecInfoDto dto = new ExecInfoDto();
//		dto.setExecid(execId); dto.setExectype(execType);
	   //result.put("result", iservice.updateAdmin2(dto));
//		return result;
//	}
//	@RequestMapping("/deleteAdmin")
//	public Map<String, Object> deleteAdmin2(@RequestParam int execId){
//		Map<String, Object> result = new HashMap<>();
//		ExecInfoDto dto = new ExecInfoDto();
//		dto.setExecid(execId);
	//	result.put("result", iservice.deleteAdmin2(dto));
//		return result;
//	}

//}
