package com.pawject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.pawject.dto.paging.PagingDto10;
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



    // 펫 상세 페이지 (조회) (사용자)
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
    
    // 관리자 펫 상세 조회
    @RequestMapping("/detail/admin")
    public String petDetailAdminPage(@RequestParam("petId") int petId, Model model) {
        // 관리자라면 userId 조건 없이 petId만으로 조회
        PetDto pet = pservice.selectPetDetailByAdmin(petId);
        model.addAttribute("pet", pet);

        System.out.println("admin petId=" + petId);

        return "pet/detail";  // 같은 JSP를 재사용 가능
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
    public String petUpdateAdminPage(@RequestParam("petId") int petId, Model model) {
        // 수정할 펫 정보 조회
        PetDto pet = pservice.selectPetDetailByAdmin(petId);
        model.addAttribute("pet", pet);
        return "pet/updateAdmin";  // /WEB-INF/view/pet/updateAdmin.jsp
    }

    // 펫 이름 수정 처리 (관리자) - POST
    @RequestMapping(value="/update/admin", method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> petUpdateAdmin(@RequestParam("petId") int petId,
                                              @RequestParam("petName") String petName) {
        Map<String, Object> result = new HashMap<>();
        PetDto pet = new PetDto();
        pet.setPetId(petId);
        pet.setPetName(petName);

        int updated = pservice.updatePetByAdmin(pet);  //  이름만 수정

        if (updated > 0) {
            result.put("result", 1);
            result.put("message", "펫 이름 수정 성공");
        } else {
            result.put("result", 0);
            result.put("message", "펫 이름 수정 실패");
        }
        return result;   // JSON 응답
    }


    // 펫 검색 페이지
    // 검색 페이지 이동 (검색 폼을 보여줄 때)
    @RequestMapping("/search")
    public String petSearchPage() {
        return "pet/list";
    }

    // 검색 실행 (Ajax 요청 처리)
    @RequestMapping(value="/search", method=RequestMethod.GET)
    @ResponseBody
    public List<PetDto> searchPets(@RequestParam("keyword") String keyword,
                                   @RequestParam(value="type", required=false) String type) {
        // 서비스 호출
        List<PetDto> pets = pservice.searchPets(keyword, type);
        return pets;  // JSON 응답
    }



    // 펫 삭제 폼 (뷰 반환)
    @RequestMapping("/delete")
    public String deletePetForm(@RequestParam("petId") int petId,
                                HttpSession session,
                                Model model) {
        int userid = (int) session.getAttribute("userid");
        model.addAttribute("pet", pservice.selectPetDetail(userid, petId));
        return "pet/delete";   // /WEB-INF/view/pet/delete.jsp
    }

    // 펫 삭제 처리 (AJAX JSON 반환)
    @PostMapping(value="/delete", produces="application/json;charset=UTF-8")
    @ResponseBody
    public Map<String, Object> deletePetAjax(@RequestParam("petId") int petId,
                                             HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        Integer userId = (Integer) session.getAttribute("userid");

        if (userId != null && pservice.deletePetByUser(petId, userId) > 0) {
            result.put("result", 1);
            result.put("message", "펫 삭제 성공!");
        } else {
            result.put("result", 0);
            result.put("message", "펫 삭제 실패!");
        }
        return result;
    }


    
    @RequestMapping("/listPetPage")
    public String adminListPage(Model model,
                                @RequestParam(value="pstartno", defaultValue="1") int pstartno) {
       
        model.addAttribute("list", pservice.selectPet10(pstartno));
        model.addAttribute("paging", new PagingDto10(pservice.selectTotalPetCnt(), pstartno));
        return "pet/list";
    }

    @ResponseBody
    @RequestMapping("list")
    public Map<String, Object> list(Model model,
    		@RequestParam( value="pstartno", defaultValue="1" ) int pstartno
    		) {
    	Map<String, Object> result = new HashMap<>();
    	result.put("list", pservice.selectPet10(pstartno));
        result.put("paging", new PagingDto10(pservice.selectTotalPetCnt(), pstartno));
        return result;
    }
}
