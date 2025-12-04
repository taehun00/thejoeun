package com.pawject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pawject.dto.exec.ExecBoardDto;
import com.pawject.dto.exec.ExecPagingDto;
import com.pawject.service.exec.ExecBoardService;

@Controller
public class ExecBoardController {
	@Autowired ExecBoardService service;
	
	///////////////////////////////////////////////////////
	//전체보기(+ Paging)
	@RequestMapping("/list.execboard")
	public String list( Model model,
			@RequestParam(value="pstartno", defaultValue="1") int pstartno
			) {
		model.addAttribute("list", service.select10(pstartno));  //
		model.addAttribute("paging", new ExecPagingDto( service.selectTotalCnt(), pstartno));
		return "execboard/boardlist";
	}
	///////////////////////////////////////////////////////
	//글쓰기폼
	@RequestMapping(value="/write.execboard", method=RequestMethod.GET)
	public String write_get() { return "/execboard/boardwrite.jsp"; }
	//글쓰기기능
	//@PreAuthorize("isAthenticated()")
	@RequestMapping(value="/write.execboard", method=RequestMethod.POST)
	public String write_post(ExecBoardDto dto,  RedirectAttributes rttr ) {
		String result = " 글쓰기실패";
		if(service.insert1(dto) > 0) { result="글쓰기가 완료되었습니다."; }
		rttr.addFlashAttribute("success", result);
		return "redirect:/list.execboard";
	}
	///////////////////////////////////////////////////////
	//상세보기
	@RequestMapping("/detail.execboard")
	public String detail_get(int postid, Model model) {
		model.addAttribute("dto", service.select1(postid));
		return "execboard/boarddetail";
	}
	///////////////////////////////////////////////////////
	//수정폼
	@RequestMapping(value="", method=RequestMethod.GET)
	public String edit_get(int postid, Model model) {
		model.addAttribute("dto", service.selectUpdateForm(postid));
		return "execboard/boardedit";
	}
	//수정기능
	//@PreAuthorize("isAthenticated()")
	@RequestMapping(value="/edit.execboard", method=RequestMethod.POST)
	public String edit_post( ExecBoardDto dto,  RedirectAttributes rttr ) {
		String result = "사용자 아이디를 확인해주세요.";
		if( service.update1(dto) > 0 ) { result = "수정이 왼료되었습니다."; }
		rttr.addFlashAttribute("success", result);
		return "redirect:/detail.execboard?id=" + dto.getPostid();
	}
	///////////////////////////////////////////////////////
	//삭제폼
	@RequestMapping(value="/delete.execboard", method=RequestMethod.GET)
	public String delete_get() { return "execboard/boarddelete"; }
	
	//삭제기능
	//@PreAuthorize("isAthenticated()")
	@RequestMapping(value="/delete.execboard", method=RequestMethod.POST)
	public String delete_post( ExecBoardDto dto,  RedirectAttributes rttr ) {
		String result = "사용자 아이디를 확인해주세요.";
		if( service.delete1(dto) > 0 ) { result="삭제가 왼료되었습니다.";}
		rttr.addFlashAttribute("success", result);
		return "redirect:/list.execboard";
	}
	///////////////////////////////////////////////////////
	//Upload
	//글쓰기기능
	//@PreAuthorize("isAthenticated()")
	@RequestMapping(value="/upload.execboard", method=RequestMethod.POST)
	public String upload_post( @RequestParam("file")MultipartFile file
			   					, ExecBoardDto dto,  RedirectAttributes rttr) {
		String result = "글쓰기실패"; 
		if( service.insert2(file, dto) > 0 ) { result="글쓰기성공"; }
		rttr.addFlashAttribute("success", result);
		return "redirect:/list.execboard";
	}
	//@PreAuthorize("isAthenticated()")
	@RequestMapping(value="/updateEdit.execboard", method=RequestMethod.POST)
	public String updateEdit_post( @RequestParam("file")MultipartFile file
								, ExecBoardDto dto,  RedirectAttributes rttr ) {
		
		return "redirect:/list.execboard?id=" + dto.getPostid();
	}
	
	///////////////////////////////////////////////////////

	
}
/*
 /list.execboard            /view/execboard/boardlist.jsp     (전체보기)
 /write.execboard           /view/execboard/boardwrite.jsp    (글쓰기폼)
 /detail.execboard          /view/execboard/boarddetail.jsp    (상세보기)
 /edit.execboard            /view/execboard/boardedit.jsp     (수정하기폼)
 /delete.execboard         /view/execboard/boarddelete.jsp   (삭제하기폼)
*/





