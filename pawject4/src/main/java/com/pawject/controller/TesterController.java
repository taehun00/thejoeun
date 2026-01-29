package com.pawject.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pawject.dto.tester.TesterAdminRequestDto;
import com.pawject.dto.tester.TesterAdminResponseDto;
import com.pawject.dto.tester.TesterUserRequestDto;
import com.pawject.dto.tester.TesterUserResponseDto;
import com.pawject.service.tester.TesterService;
import com.pawject.service.user.AuthUserJwtService;
import com.pawject.util.UtilPaging;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Tester", description = "체험단 API")
@RestController
@RequestMapping("/tester")
@RequiredArgsConstructor
public class TesterController {
	private final AuthUserJwtService authUserJwtService;  
	private final TesterService service;

	@Operation(summary = "게시글 단건 조회 (공개)")
	@GetMapping("{testerid}")
	public ResponseEntity<TesterAdminResponseDto> getTester(@PathVariable(name = "testerid") Long testerid){
		return ResponseEntity.ok(service.findById(testerid));
	}
	
	//카테고리-관리자용
	@GetMapping("/categories/admin")
	public ResponseEntity<List<String>> adminCategories() {
	    return ResponseEntity.ok(List.of("공지", "모집", "모집완료"));
	}
	//카테고리-사용자용
	@GetMapping("/categories/user")
	public ResponseEntity<List<String>> userCategories() {
	    return ResponseEntity.ok(List.of("후기"));
	}
	
	@Operation(summary = "게시글 작성-관리자 (JWT 인증 필요)")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@PostMapping(value="/admin", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<TesterAdminResponseDto> createTesterAdmin(
            Authentication authentication,
            @ModelAttribute TesterAdminRequestDto dto,
            @Parameter(description = "업로드할 이미지 파일")
            @RequestPart(name = "files", required = false) List<MultipartFile> files){
		Long userid = authUserJwtService.getCurrentUserId(authentication);
		return ResponseEntity.ok(service.adminWrite(userid, dto, files)); 
		
	}
	@Operation(summary = "게시글 작성-유저 (JWT 인증 필요)")
	@PreAuthorize("isAuthenticated()")
	@PostMapping(value="/user", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<TesterUserResponseDto> createTesterUser(
            Authentication authentication,
            @ModelAttribute TesterUserRequestDto dto,
            @Parameter(description = "업로드할 이미지 파일")
            @RequestPart(name = "files", required = false) List<MultipartFile> files){
		Long userid = authUserJwtService.getCurrentUserId(authentication);
		return ResponseEntity.ok(service.userWrite(userid, dto, files)); 
		
	}
	
	@Operation(summary = "게시글 수정 (JWT 인증 필요)")
	@PutMapping(value="/{testerid}", consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> update(
	        Authentication authentication,
	        @PathVariable Long testerid,
	        @ModelAttribute TesterAdminRequestDto dto,
	        @RequestPart(required=false) List<MultipartFile> files
	){
	    Long userid = authUserJwtService.getCurrentUserId(authentication);
	    boolean isAdmin = authUserJwtService.isAdmin(authentication);

	    if (isAdmin) {
	        return ResponseEntity.ok(service.adminUpdate(userid, testerid, dto, files));
	    } else { //유저는 필요한 필드만 복사해서 덮어쓰기
	        TesterUserRequestDto userDto = new TesterUserRequestDto();
	        userDto.setTitle(dto.getTitle());
	        userDto.setContent(dto.getContent());
	        return ResponseEntity.ok(service.userUpdate(userid, testerid, userDto, files));
	    }
	}
	
    @Operation(summary = "게시글 삭제 (JWT 인증 필요)")
    @DeleteMapping("/{testerid}")
    public ResponseEntity<Void> deletePost(
            Authentication authentication,
            @PathVariable(name = "testerid") Long testerid
    ) {
        Long userid = authUserJwtService.getCurrentUserId(authentication);
        service.delete(testerid, userid);
        return ResponseEntity.noContent().build();
    }
	
	///mybatis
	@Operation(summary = "체험단 페이징")
	@GetMapping("/paged")
	@ResponseBody
	public Map<String, Object> testerPaged(
	        @RequestParam(defaultValue = "1") int pageNo,
	        @RequestParam(required = false) String condition){
	    Map<String, Object> result = new HashMap<>();

	    int total = service.countByTesterPaging(condition);
		List<TesterAdminResponseDto> list = service.select20Tester(condition, pageNo);
	    
	    UtilPaging paging = new UtilPaging(total, pageNo);
	    result.put("paging", paging);
	    result.put("total", total);
	    result.put("list", list);

	    return result;
	}
	
	@Operation(summary = "체험단 검색")
	@GetMapping("/search")
	@ResponseBody
	public Map<String, Object> testerSearch(
	        @RequestParam("searchType") String searchType,
	        @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
	        @RequestParam(value = "keyword", required = false) String keyword,
	        @RequestParam(required = false) String condition){
	    Map<String, Object> result = new HashMap<>();
	    int total = service.searchTesterCnt(keyword, searchType, condition);
	    List<TesterAdminResponseDto> list = service.searchTester(keyword, searchType, condition, pageNo);

	    UtilPaging paging = new UtilPaging(total, pageNo);

	    result.put("total", total);
	    result.put("list", list);
	    result.put("paging", paging);
	    result.put("search", keyword);

	    return result;
	}
	@Operation(summary = "공지전환-관리자전용")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@PatchMapping("/{testerid}/notice")
	public ResponseEntity<Integer> updateNotice(@PathVariable Long testerid) {
		return ResponseEntity.ok(service.updateIsnotice(testerid)); // 0 or 1
	}

	@Operation(summary = "모집전환-관리자전용")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@PatchMapping("/{testerid}/status")
	public ResponseEntity<Integer> updateStatus(@PathVariable Long testerid) {
		return ResponseEntity.ok(service.updateStatus(testerid)); // 0 or 1
	}
	
}
