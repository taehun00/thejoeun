package com.pawject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import com.pawject.dto.pet.PetDto;
import com.pawject.dto.user.UserAuthDto;
import com.pawject.dto.user.UserDto;
import com.pawject.service.pet.PetService;
import com.pawject.service.user.UserSecurityService;

@Controller
@RequestMapping("/pet/*")
public class PetController {
	@Autowired UserSecurityService service;
	@Autowired PetService pservice;

	// 펫 등록 페이지 (GET)
    @RequestMapping("/join")
    public String joinForm() {
        return "pet/join";
    }

    // 펫 등록 처리 (POST)
    @RequestMapping(value="/join", method=RequestMethod.POST, headers=("content-type=multipart/*"))
    public String petJoin(@RequestParam("file") MultipartFile pfile,
                          PetDto dto,
                          HttpSession session,
                          RedirectAttributes rttr) {

        String result = "펫 등록 실패";

        try {
        	// 세션에서 로그인한 유저의 userid 꺼내기
            int userid = (int) session.getAttribute("userid");
            dto.setUserId(userid); // dto에 userid 세팅

            // 서비스 호출 (파일 + DTO 저장)
            if (pservice.petJoin(pfile, dto) > 0) {
                result = "펫 등록 성공";
            }
        } catch (Exception e) {
            // 그 외 예외 처리
            result = "펫 등록 처리 중 오류가 발생했습니다.";
        }

        // 결과 메시지를 FlashAttribute로 전달
        rttr.addFlashAttribute("success", result);

        // 등록 후 마이페이지로 이동
        return "redirect:/security/mypage";

    }



    // 펫 상세 페이지 (조회)
    @RequestMapping("/detail")
    public String petDetailPage(@RequestParam("petId") int petId,
	            Principal principal,
	            Model model) {
	// 로그인한 유저 정보 조회
	UserDto userDto = service.myPage(principal.getName());
	int userId = userDto.getUserId();
	
	// 펫 상세 조회
	PetDto pet = pservice.selectPetDetail(userId, petId);
	model.addAttribute("pet", pet);
	
	System.out.println("userId=" + userId + ", petId=" + petId);
	
	return "pet/detail";
	}


    // 펫 수정 페이지 (사용자)
    @RequestMapping("/update/user")
    public String petUpdateUserForm(@RequestParam("petId") int petId,
	            Principal principal,
	            Model model) {
		int userId = service.myPage(principal.getName()).getUserId();
		
		PetDto pet = pservice.selectPetDetail(userId, petId);
		model.addAttribute("pet", pet);
		
		return "pet/updateUser"; // 수정 폼 JSP
	}
    
    @RequestMapping(value="/update/user", method=RequestMethod.POST, headers=("content-type=multipart/*"))
    public String petUpdateUser(@RequestParam("file") MultipartFile file,
	            PetDto pet, RedirectAttributes rttr, HttpSession session) {
    	int userid = (int) session.getAttribute("userid");
    	
    	String result = "수정 실패";
    	if(pservice.updatePetByUser(file, pet, userid) > 0) {
    		result = "펫 수정 성공";
    		
    	}
    	rttr.addFlashAttribute("success1", result);
    	
    	return "redirect:/security/mypage";
	}



    // 펫 수정 페이지 (관리자)
    @RequestMapping("/update/admin")
    public String petUpdateAdminPage() {
        return "pet/updateAdmin"; 
    }

    // 펫 검색 페이지
    @RequestMapping("/search")
    public String petSearchPage() {
        return "pet/list";
    }

    // 펫 삭제 페이지 (사용자)
    @RequestMapping("/delete")
    public String deletePetFrom(@RequestParam("petId") int petId,
            Principal principal,HttpSession session,
            Model model) {
    	int userid = (int) session.getAttribute("userid");
    	model.addAttribute("pet", pservice.selectPetDetail(userid, petId));
    	
    	return "pet/delete";
    }
    
    @RequestMapping(value="/delete", method=RequestMethod.POST)
    public String deletePet(@RequestParam("petId") int petId,
                            HttpSession session,
                            RedirectAttributes rttr) {

        String result = "펫 삭제 실패";

        Integer userId = (Integer) session.getAttribute("userid");

        if (userId != null && pservice.deletePetByUser(petId, userId) > 0) {
            // 로그아웃은 필요 없지만, 형식을 맞추려면 그대로 둘 수 있음
            

            result = "펫 삭제 성공";
            rttr.addFlashAttribute("success1", result);
            return "redirect:/security/mypage";
        } else {
            rttr.addFlashAttribute("deleteError", result);
            return "redirect:/security/mypage";
        }
    }


}
