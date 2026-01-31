package com.pawject.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pawject.domain.Petdisease;
import com.pawject.dto.petdisease.PetdiseaseRequestDto;
import com.pawject.dto.petdisease.PetdiseaseResponseDto;
import com.pawject.service.petdisease.PetdiseaseService;
import com.pawject.service.user.AuthUserJwtService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Petdisease", description = "질병정보 API")
@RestController
@RequestMapping("/petdisease")
@RequiredArgsConstructor
public class PetdiseaseController {
	private final PetdiseaseService service;
	private final AuthUserJwtService authUserJwtService;  
	
	@Operation(summary = "게시글 단건 조회 (공개)")
	@GetMapping("/{disno}")
	public ResponseEntity<PetdiseaseResponseDto> getPetdis(
			@PathVariable(name = "disno") Long disno){
		return ResponseEntity.ok(service.getPetdis(disno));
	}

	@Operation(summary = "페이징+정렬(공개)")
    @GetMapping("/list")
    public Page<Petdisease> list(
            @RequestParam Long pettypeid,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String condition
    ) {
        return service.getPetdiseasePage(page, size, condition, pettypeid);
    }
	
	@Operation(summary = "페이징+정렬+검색(공개)")
    @GetMapping("/search")
    public Page<PetdiseaseResponseDto> search(
            @RequestParam Long pettypeid,
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String condition
    ) {
        Pageable pageable = PageRequest.of(page - 1, size); 
        return service.list(pettypeid, keyword, pageable, condition);
    }
	
	@Operation(summary = "게시글 작성 (JWT 인증 필요)")
	@PostMapping
	public ResponseEntity<PetdiseaseResponseDto> createPetdis(
	        Authentication authentication,
	        @RequestParam Long pettypeid,
	        @ModelAttribute PetdiseaseRequestDto dto){
	    Long userid = authUserJwtService.getCurrentUserId(authentication);
	    return ResponseEntity.ok(service.createPost(userid, dto, pettypeid));
	}
	
	@Operation(summary = "게시글 수정 (JWT 인증 필요)")
	@PutMapping(value = "/{disno}")
	public ResponseEntity<PetdiseaseResponseDto> updatePetdis(
	        Authentication authentication,
	        @PathVariable(name = "disno") Long disno,
	        @RequestParam Long pettypeid,
	        @ModelAttribute PetdiseaseRequestDto dto){
		Long userid = authUserJwtService.getCurrentUserId(authentication);
		return ResponseEntity.ok(service.updatePetdis(disno, dto, pettypeid));
	}
	
    @Operation(summary = "게시글 삭제 (JWT 인증 필요)")
    @DeleteMapping("/{petdis}")
    public ResponseEntity<Void> deletePetdis(
            Authentication authentication,
            @PathVariable(name = "disno") Long disno){
    	Long userid = authUserJwtService.getCurrentUserId(authentication);
    	service.deletePetdis(disno);
    	return ResponseEntity.noContent().build(); 
    }


}
