package com.pawject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pawject.dto.exec.ExecBoardDto;
import com.pawject.dto.exec.ExecInfoDto;
import com.pawject.dto.paging.PagingDto10;
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
		model.addAttribute("list", iservice.select10(pstartno));  
		model.addAttribute("paging", new PagingDto10( iservice.selectTotalCnt(), pstartno));
		return "execinfo/infolist";
	}
	////////////////////////////////////
	@RequestMapping(value="/write.execinfo", method=RequestMethod.GET)
	public String write_get() { return "/execboard/infowrite"; }
	//글쓰기기능
	//@PreAuthorize("isAthenticated() and hasRole('ROLE_ADMIN')") //2. 로그인 → ADMIN 권한이 있다면 
	@RequestMapping(value="/write.execinfo", method=RequestMethod.POST)
	public String write_post(ExecInfoDto dto,  RedirectAttributes rttr ) {
		String result = " 글쓰기실패";
		if(iservice.insert2(dto)> 0) { result="글쓰기가 완료되었습니다."; }
		rttr.addFlashAttribute("success", result);
		return "redirect:/list.execinfo";
	}
	////////////////////////////////////
	//상세보기
	@RequestMapping("/detail.execinfo")
	public String detail_get( int execid, Model model) {
		model.addAttribute("dto", iservice.select2(execid));
		return "execinfo/infodetail";
	}
	////////////////////////////////////
	//수정폼
	@RequestMapping(value="/edit.execinfo", method=RequestMethod.GET)
	public String edit_get(int execid, Model model) {
		model.addAttribute("dto", iservice.selectUpdateForm(execid));
		return "execinfo/infoedit";
	}
	//수정기능
	//@PreAuthorize("isAthenticated() and hasRole('ROLE_ADMIN')") //2. 로그인 → ADMIN 권한이 있다면 
	@RequestMapping(value="/edit.execinfo", method=RequestMethod.POST)
	public String edit_post( ExecInfoDto dto,  RedirectAttributes rttr ) {
		String result = "운동아이디를 확인해주세요.";
		if( iservice.update2(dto) > 0 ) { result = "수정이 왼료되었습니다."; }
		rttr.addFlashAttribute("success", result);
		return "redirect:/detail.execinfo?id=" + dto.getExecid();
	}
	///////////////////////////////////////////////////////
	//삭제폼
	@RequestMapping(value="/delete.execinfo", method=RequestMethod.GET)
	public String delete_get() { return "execinfo/infodelete"; }
	
	//삭제기능
	//@PreAuthorize("isAthenticated() and hasRole('ROLE_ADMIN')") //2. 로그인 → ADMIN 권한이 있다면 
	@RequestMapping(value="/delete.execinfo", method=RequestMethod.POST)
	public String delete_post( ExecInfoDto dto,  RedirectAttributes rttr ) {
		String result = "운동아이디를 확인해주세요.";
		if( iservice.delete2(dto) > 0 ) { result="삭제가 왼료되었습니다.";}
		rttr.addFlashAttribute("success", result);
		return "redirect:/list.execinfo";
	}

	
	
}

/*
 /list.execinfo            /view/execinfo/infolist.jsp     (전체보기)
 /write.execinfo           /view/execinfo/infowrite.jsp    (글쓰기폼)
 /detail.execinfo        /view/execinfo/infodetail.jsp    (상세보기)
 /edit.execinfo            /view/execinfo/infoedit.jsp     (수정하기폼)
 /delete.execinfo         /view/execinfo/infoelete.jsp   (삭제하기폼)
*/
