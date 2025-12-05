package com.pawject.controller;

//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.pawject.dto.exec.ExecBoardDto;
//import com.pawject.service.exec.ExecBoardService;

//@Controller     처리 (service) + 화면
//@RestController //처리(service) + 나온값 
//public class ExecBoardAjaxController {
//	
//	@Autowired ExecBoardService service;	
	
//	@RequestMapping("/iddouble")
//	public Map<String, Object> iddouble1(@RequestParam String email){
//		Map<String, Object> result = new HashMap<>();
//	//	result.put("cnt", service.iddouble(email));
//		return result;
//	}
	//////////////////////////////////////////
//	/* admin 유저관리 - 전체유저정보 selectAll
//	 * 				/ 아이디주면 해당 유저정보찾기 select 
//	 * 				/ 수정하기 updateAdimin
//	 * 				/ 삭제하기 deleteAdimin */
//	@RequestMapping("/ExecBoardselectAll")
//	public List<ExecBoardDto> selectAll(){
//		return service.selectAll1();
//	}
//	@RequestMapping("/ExecBoardselect")
//	public Map<String, Object> select1(@RequestParam int postId){
//		Map<String, Object> result = new HashMap<>();
//		result.put("result", service.select1(postId));
//		return result;
//	}
	/////////////////////////////////////////////////////
	//권한부여 Admin, Member
//	@RequestMapping("/updateExecBoardAdmin")
//	public Map<String, Object> updateAdmin1(@RequestParam int postId,
//										   @RequestParam int execId){
//		Map<String, Object> result = new HashMap<>();
//		ExecBoardDto dto = new ExecBoardDto();
//		dto.setPostid(postId); dto.setExecid(execId);
//	   //result.put("result", service.updateAdimin1(dto));
//		return result;
//	}
//	@RequestMapping("/deleteExecBoardAdmin")
//	public Map<String, Object> deleteAdmin1(@RequestParam int postId){
//		Map<String, Object> result = new HashMap<>();
//		ExecBoardDto dto = new ExecBoardDto();
//		dto.setPostid(postId);
	//	result.put("result", service.deleteAdimin1(dto));
//		return result;
//	}
//	@RequestMapping("/updateExecBoardMember")
//	public Map<String, Object> updateMember1(@RequestParam int postId,
//										   @RequestParam int execId){
//		Map<String, Object> result = new HashMap<>();
//		ExecBoardDto dto = new ExecBoardDto();
//		dto.setPostid(postId); dto.setExecid(execId);
	   //result.put("result", service.updateMember1(dto));
//		return result;
//	}
//	@RequestMapping("/deleteExecBoardAdmin")
//	public Map<String, Object> deleteMember1(@RequestParam int postId){
//		Map<String, Object> result = new HashMap<>();
//		ExecBoardDto dto = new ExecBoardDto();
//		dto.setPostid(postId);
	//	result.put("result", service.deleteMember1(dto));
//		return result;
//	}

	
	
	
	
//}
