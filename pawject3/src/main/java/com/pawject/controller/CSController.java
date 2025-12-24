package com.pawject.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pawject.dto.support.CSAnswerDto;
import com.pawject.dto.support.CSQuestionDto;
import com.pawject.service.support.CSService;
import com.pawject.util.UtilPaging;


@Controller
@RequestMapping("/csBoard")
public class CSController {
	
	@Autowired private CSService service;
	
	//본인 글만 조회
	@GetMapping("/cslistuser")
	public String userlist(Model model) {

	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

	    String email = auth.getName();
	    model.addAttribute("list", service.selectCSQByEmail(email));
	    return "csBoard/cslistuser";
	    
	}
	
	
	//전체 글 조회 
	@GetMapping("/cslistadmin")
	public String adminlist(Model model, CSQuestionDto qdto) {
		model.addAttribute("list", service.selectCSQAll());
		return "csBoard/cslistadmin";

	}
	
	//전체글+페이징
  @RequestMapping("/cspaging")
  @ResponseBody
    public Map<String, Object> cspaging(
    		@RequestParam(defaultValue="1") int pstartno){	
    	Map<String, Object> result = new HashMap<>();

    	int total= service.selectTotalCntCSQ();
    	List<CSQuestionDto> list = service.select10CSQ(pstartno);
    	
        // UtilPaging 활용 - 이거 없으면 버튼 안나타남
        UtilPaging paging = new UtilPaging(total, pstartno);  

        result.put("paging", paging);
    	result.put("total", total);
    	result.put("list", list);

    	return result;
    }
  

	 

	
	
	
	
	//(서치+페이징)	
	@GetMapping("/search")
	@ResponseBody
	public Map<String, Object>  search(
		@RequestParam(value="pageNo"  , defaultValue="1")  int pageNo ,
		@RequestParam(value="keyword" , required=false  )  String keyword
	){
	
		Map<String, Object> result = new HashMap<>();
		int totalCnt = service.selectSearchTotalCntCSQ(keyword);
		
		result.put("search", keyword);
		result.put("list"  , service.selectSearchCSQ(keyword, pageNo));
		result.put("paging", new UtilPaging(  totalCnt      , pageNo , 5, 10)); //3. 페이징계산
		return result;                        //키워드검색갯수, 페이지번호, 몇개씩, 하단블록
	}
	
	
	//질문 작성
	@GetMapping("/cswrite") public String write_get(Model model) {  
		model.addAttribute("categories", List.of("계정", "서비스", "이벤트", "기타"));
		return "csBoard/cswrite";
		}
	
	@PostMapping("/cswrite") public String write_post(CSQuestionDto dto, 
														Authentication auth,
														RedirectAttributes rttr) {
	    String email = auth.getName();  
	    int userid = service.selectUserIdByEmail(email);

	    dto.setUserid(userid);            

		String result ="글쓰기 실패";
		if(service.insertCSQ(dto) > 0) { result="글쓰기 성공!";}
		rttr.addFlashAttribute("success" , result);
		
		return "redirect:/csBoard/cslistuser";
	} 
	
	
	
	//답변 작성
	@GetMapping("/cswriteanswer") public String writenaswer_get(Model model, int questionid) {  
		model.addAttribute("dto", service.selectCSQ(questionid));
		return "csBoard/cswriteanswer";
		}
	
	@PostMapping("/cswriteanswer") public String writeanswer_post(CSAnswerDto adto, CSQuestionDto qdto, 
														RedirectAttributes rttr) {

		String result ="글쓰기 실패";
		if(service.insertCSA(adto) > 0) { 
			service.answerCSQ(qdto);
			result="글쓰기 성공!";}
		rttr.addFlashAttribute("success" , result);
		
		return "redirect:/csBoard/cslistadmin";
	}	
	
	
    // 빠른활성화-아작스
	@ResponseBody
	@PostMapping("/quickanswer")
	public Map<String, Object> quickanswer(CSQuestionDto dto) {

	    Map<String, Object> result = new HashMap<>();

	    int updated = service.answerCSQ(dto);
	    result.put("result", updated);

	    return result;
	}
	
	
	

}
