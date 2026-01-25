package com.pawject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.pawject.dto.petdisease.PetdiseaseRequestDto;
import com.pawject.dto.petdisease.PetdiseaseResponseDto;
import com.pawject.dto.tester.TesterAdminRequestDto;
import com.pawject.dto.tester.TesterAdminResponseDto;
import com.pawject.dto.tester.TesterUserRequestDto;
import com.pawject.dto.tester.TesterUserResponseDto;
import com.pawject.service.tester.TesterService;

@SpringBootTest
@Transactional
class Pawject4ApplicationTests_tester {
	@Autowired TesterService service;


    private Long testerid;

    @BeforeEach
    void setup() {
    		//글쓰기-관리자
    		TesterAdminRequestDto admin = new TesterAdminRequestDto();
    		admin.setCategory("모집");
    		admin.setTitle("제목");
    		admin.setContent("내용");
    		admin.setFoodid(1);
    		admin.setStatus(1);
    		admin.setIsnotice(1);
    		admin.setPosttype(1);

    		TesterAdminResponseDto adminres = service.adminWrite(1L, admin, null);
    		this.testerid = adminres.getTesterid();
  
        assertThat(adminres.getTitle()).isEqualTo("제목");
        
        
		//글쓰기-사용자
		TesterUserRequestDto user = new TesterUserRequestDto();
		user.setCategory("후기");
		user.setTitle("제목");
		user.setContent("내용");
		
		TesterUserResponseDto userres = service.userWrite(1L, user, null);
		this.testerid = userres.getTesterid();

		assertThat(userres.getCategory()).isEqualTo("후기");
        
    }

    @Test
    @DisplayName("■ TesterService-CRUD ")
    void testerService() {
    		//관리자 수정
    	TesterAdminRequestDto adminupdate = new TesterAdminRequestDto();
    		adminupdate.setCategory(null);
    		adminupdate.setIsnotice(0);

    		TesterAdminResponseDto updated = service.adminUpdate(1L, testerid, adminupdate, null);
        assertThat(updated.getIsnotice()).isEqualTo(0);


    }
}

