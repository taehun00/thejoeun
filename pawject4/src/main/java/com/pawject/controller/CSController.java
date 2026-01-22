package com.pawject.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pawject.dto.support.CSAnswerDto;
import com.pawject.dto.support.CSQuestionDto;
import com.pawject.dto.support.FAQDto;
import com.pawject.service.support.CSService;
import com.pawject.util.UtilPaging;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
@Tag(name = "CS", description = "고객센터 CS") 
@RestController 
@RequestMapping("/csBoard") 
@RequiredArgsConstructor 
public class CSController {
	@Autowired private CSService service;
	
	//리스트 - 유저용
	@Operation(summary = "유저용 CS 조회")
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/cslistuser")
	public ResponseEntity<List<CSQuestionDto>> forUserList() {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String email = auth.getName();
	    return ResponseEntity.ok(service.selectCSQByEmail(email));
	}
	
	// 리스트 - 관리자용
	@Operation(summary = "관리자용 CS 페이징 조회")
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/cspaged")
	@ResponseBody
	public Map<String, Object> cspaged(
	        @RequestParam(defaultValue = "1") int pstartno,
	        @RequestParam(required = false) String condition){
	    Map<String, Object> result = new HashMap<>();

	    int total = service.selectTotalCntCSQ();
	    List<CSQuestionDto> list = service.select10CSQ(condition, pstartno);

	    UtilPaging paging = new UtilPaging(total, pstartno);

	    result.put("paging", paging);
	    result.put("total", total);
	    result.put("list", list);

	    return result;
	}
	
	// 관리자용 CS 페이징 + 검색
	@Operation(summary = "관리자용 CS 페이징 + 검색 조회")
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/cssearch")
	@ResponseBody
	public Map<String, Object> cssearch(
	        @RequestParam("searchType") String searchType,
	        @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
	        @RequestParam(value = "keyword", required = false) String keyword,
	        @RequestParam(required = false) String condition){
	    Map<String, Object> result = new HashMap<>();

	    int total = service.selectSearchTotalCntCSQ(keyword, searchType, condition);

	    List<CSQuestionDto> list =
	            service.selectSearchCSQ(keyword, searchType, condition, pageNo);

	    UtilPaging paging = new UtilPaging(total, pageNo);

	    result.put("total", total);
	    result.put("list", list);
	    result.put("paging", paging);
	    result.put("search", keyword);

	    return result;
	}

	//글쓰기
	@Operation(summary = "CS 카테고리")
	@GetMapping("/categories")   //model 말고 주소창에서 받아오기
	public ResponseEntity<List<String>> categories() {
	    return ResponseEntity.ok(
	        List.of("계정", "서비스", "이벤트", "기타")
	    );
	}

	@Operation(summary = "1:1 질문 작성")
	@PreAuthorize("isAuthenticated()")
	@PostMapping
	public ResponseEntity<Void> write(@RequestBody CSQuestionDto dto) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();  

		int userid = service.selectUserIdByEmail(email);
	    dto.setUserid(userid);  		
		
	    service.insertCSQ(dto);
	    return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	@Operation(summary = "1:1 질문 단건 조회")
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/cs/{questionid}")
	public ResponseEntity<CSQuestionDto> getCSQuestion(
	        @PathVariable int questionid) {

	    return ResponseEntity.ok(service.selectCSQ(questionid));
	}
	
	@Operation(summary = "1:1 답변 작성")
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/cs/{questionid}/answer")   //주소 조심!
	public ResponseEntity<Void> writeAnswer(
	        @PathVariable int questionid,
	        @RequestBody CSAnswerDto dto,
	        @RequestBody CSQuestionDto qdto) {

	    dto.setQuestionid(questionid);

	    service.insertCSA(dto);
	    service.answerCSQ(qdto); // 답변 완료 처리

	    return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@Operation(summary = "답변완료 버튼")
	@PreAuthorize("hasRole('ADMIN')")
	@PatchMapping("/{questionid}/active")
	public ResponseEntity<Void> quickAnswer(
	        @PathVariable int questionid,
	        @RequestBody CSQuestionDto dto
	) {
	    dto.setQuestionid(questionid);
	    service.answerCSQ(dto);
	    return ResponseEntity.ok().build();
	}

}


 