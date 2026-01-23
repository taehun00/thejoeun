package com.pawject.service.petdisease;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pawject.domain.PetType;
import com.pawject.domain.Petdisease;
import com.pawject.domain.User;
import com.pawject.dto.petdisease.PetdiseaseRequestDto;
import com.pawject.dto.petdisease.PetdiseaseResponseDto;
import com.pawject.repository.PetRepository;
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
	
	//게시글 작성
	public PetdiseaseResponseDto createPost(Long userid, PetdiseaseRequestDto dto, Long pettypeid){
		//유저랑 펫정보 확인
		User user = urepo.findById(userid) .orElseThrow(() -> new IllegalArgumentException("사용자 없음"));
	    PetType pettype = petrepo.findById(pettypeid) .orElseThrow(() -> new IllegalArgumentException("펫타입 없음"));
	    // 값 넣기
	    Petdisease petdis = new Petdisease();
	    petdis.setAdmin(user);
	    petdis.setPettype(pettype);
	    petdis.setDisname(dto.getDisname());
	    petdis.setDisexplain(dto.getDisexplain());
	    petdis.setRecommend(dto.getRecommend());
	    // 저장
	    Petdisease saved = disrepo.save(petdis);
	    // dto 리턴
	    return PetdiseaseResponseDto.form(saved);
	}
	//게시글 수정
	
	//게시글 삭제
	
	//게시글 조회 - 페이징/정렬/검색
	
	
}
