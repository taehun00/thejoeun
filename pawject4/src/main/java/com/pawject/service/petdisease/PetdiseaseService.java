package com.pawject.service.petdisease;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pawject.domain.PetType;
import com.pawject.domain.Petdisease;
import com.pawject.domain.User;
import com.pawject.dto.petdisease.PetdiseaseRequestDto;
import com.pawject.dto.petdisease.PetdiseaseResponseDto;
import com.pawject.repository.PetTypeRepository;
import com.pawject.repository.PetdiseaseReopsitory;
import com.pawject.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor  //##
@Transactional   //org.springframework.transaction.annotation.Transactional
public class PetdiseaseService {
	private final PetdiseaseReopsitory disrepo;
	private final UserRepository urepo;
	private final PetTypeRepository petrepo;
	
    public PetdiseaseResponseDto createPost(Long userid, PetdiseaseRequestDto dto, Long pettypeid) {

        // 유저 확인
        User user = urepo.findById(userid) .orElseThrow(() -> new IllegalArgumentException("사용자 없음"));
        // 펫타입 확인
        PetType pettype = petrepo.findById(pettypeid) .orElseThrow(() -> new IllegalArgumentException("펫타입 없음"));

        // 엔티티 생성 및 값 세팅
        Petdisease petdis = new Petdisease();
        petdis.setAdmin(user);        // ADMINID FK
        petdis.setPettype(pettype);   // PETTYPEID FK
        petdis.setDisname(dto.getDisname());
        petdis.setDisexplain(dto.getDisexplain());
        petdis.setRecommend(dto.getRecommend());

        // 저장
        Petdisease saved = disrepo.save(petdis);

        // dto 반환
        return PetdiseaseResponseDto.from(saved);
    }
    
    
	//게시글 수정
    public PetdiseaseResponseDto updatePetdis(Long disno, PetdiseaseRequestDto dto, Long pettypeid) {
        Petdisease petdis = disrepo.findById(disno).orElseThrow(() -> new IllegalArgumentException("질환정보 없음"));
        PetType pettype = petrepo.findById(pettypeid).orElseThrow(() -> new IllegalArgumentException("펫타입 없음"));

        petdis.setPettype(pettype);   
        petdis.setDisname(dto.getDisname());
        petdis.setDisexplain(dto.getDisexplain());
        petdis.setRecommend(dto.getRecommend());

        Petdisease updated = disrepo.save(petdis);

        return PetdiseaseResponseDto.from(updated);
    }

	//게시글 삭제
    public void deletePetdis(Long disno) {
        Petdisease petdis = disrepo.findById(disno).orElseThrow(() -> new IllegalArgumentException("질환정보 없음"));
        disrepo.deleteById(disno);		
    }

    //단일 게시글 조회
    @Transactional(readOnly = true)
    public PetdiseaseResponseDto getPetdis(Long disno) {
    		Petdisease petdis = disrepo.findById(disno) .orElseThrow(() -> new IllegalArgumentException("질환정보 없음"));
    	    return PetdiseaseResponseDto.from(petdis);
        }
    

	//게시글 조회 - 페이징/정렬
    @Transactional(readOnly = true)
    public Page<Petdisease> getPetdiseasePage(int page, int size, String condition, Long pettypeid) {

        Sort sort;
        if(condition == null) condition = "new";
        switch (condition) {
            case "disnameAsc": sort = Sort.by("disname").ascending(); break;
            case "disnameDesc": sort = Sort.by("disname").descending(); break;
            case "old": sort = Sort.by("disno").ascending(); break;
            case "new": sort = Sort.by("disno").descending(); break;
            default: sort = Sort.by("disno").descending(); break;
        }

        Pageable pageable = PageRequest.of(page - 1, size, sort);

        return disrepo.findByPettypeid(pettypeid, pageable);
    }

    //검색
    @Transactional(readOnly = true)
    public Page<PetdiseaseResponseDto> list(Long pettypeid, String keyword, Pageable pageable, String condition) {
        Sort sort;
        if(condition == null) condition = "new";
        switch (condition) {
            case "disnameAsc": sort = Sort.by("disname").ascending(); break;
            case "disnameDesc": sort = Sort.by("disname").descending(); break;
            case "old": sort = Sort.by("disno").ascending(); break;
            case "new": sort = Sort.by("disno").descending(); break;
            default: sort = Sort.by("disno").descending(); break;
        }
        	//기존 페이징에 sort 덮기
        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        Page<Petdisease> page = disrepo.searchKeyword(pettypeid, keyword, sortedPageable);

        return page.map(p -> PetdiseaseResponseDto.from(p));
    }
    
}
