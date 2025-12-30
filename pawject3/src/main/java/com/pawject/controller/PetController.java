package com.pawject.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pawject.dto.paging.PagingDto10;
import com.pawject.dto.pet.PetDto;
import com.pawject.dto.user.UserDto;
import com.pawject.security.CustomUserDetails;
import com.pawject.service.pet.PetService;
import com.pawject.service.user.UserSecurityService;


@Controller
@RequestMapping("/pet")
public class PetController {
	@Autowired
	UserSecurityService service;
	@Autowired
    PetService pservice;

    // 펫 등록 페이지 (GET)
    @GetMapping("/join")
    public String joinForm() {
        return "pet/join";
    }

    // 펫 등록 처리 (POST)
    @PostMapping("/join")
    public String petJoin(@RequestParam("file") MultipartFile pfile,
                          PetDto dto,
                          HttpSession session,
                          RedirectAttributes rttr) {

        String result = "펫 등록 실패";

        try {
        	// 로그인 유저 정보 가져오기 (Spring Security)
            Authentication auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
            CustomUserDetails userDetails = (CustomUserDetails) ((org.springframework.security.core.Authentication) auth).getPrincipal();
            dto.setUserId(userDetails.getUser().getUserId());



            if (pservice.petJoin(pfile, dto) > 0) {
                result = "펫 등록 성공";
            }
        } catch (Exception e) {
            result = "펫 등록 처리 중 오류가 발생했습니다.";
            e.printStackTrace();
        }

        rttr.addFlashAttribute("success", result);
        return "redirect:/users/mypage";
    }

    // 펫 상세 페이지 (사용자)
    @GetMapping("/detail")
    public String petDetailPage(@RequestParam("petId") int petId,
                                Principal principal,
                                Model model) {
        UserDto userDto = service.myPage(principal.getName());
        int userId = userDto.getUserId();

        PetDto pet = pservice.selectPetDetail(userId, petId);
        model.addAttribute("pet", pet);

        return "pet/detail";
    }

    // 관리자 펫 상세 조회
    @GetMapping("/detail/admin")
    public String petDetailAdminPage(@RequestParam("petId") int petId, Model model) {
        PetDto pet = pservice.selectPetDetailByAdmin(petId);
        model.addAttribute("pet", pet);
        return "pet/detail";
    }

    // 펫 수정 페이지 (사용자)
    @GetMapping("/update")
    public String petUpdateUserForm(@RequestParam("petId") int petId,
                                    Principal principal,
                                    Model model) {
        int userId = service.myPage(principal.getName()).getUserId();
        PetDto pet = pservice.selectPetDetail(userId, petId);
        model.addAttribute("pet", pet);
        return "pet/update";
    }

    @PostMapping("/update")
    public String petUpdateUser(@RequestParam("file") MultipartFile file,
                                PetDto pet,
                                Principal principal,
                                RedirectAttributes rttr
                                ) {
    	int userid = service.myPage(principal.getName()).getUserId();


        String result = "수정 실패";
        if (pservice.updatePetByUser(file, pet, userid) > 0) {
            result = "펫 수정 성공";
        }
        rttr.addFlashAttribute("success1", result);

        return "redirect:/users/mypage";
    }

    // 펫 수정 페이지 (관리자)
    @GetMapping("/update/admin")
    public String petUpdateAdminPage(@RequestParam("petId") int petId, Model model) {
        PetDto pet = pservice.selectPetDetailByAdmin(petId);
        model.addAttribute("pet", pet);
        return "pet/updateAdmin";
    }

    // 펫 이름 수정 처리 (관리자)
    @PostMapping("/update/admin")
    @ResponseBody
    public Map<String, Object> petUpdateAdmin(@RequestParam("petId") int petId,
                                              @RequestParam("petName") String petName) {
        Map<String, Object> result = new HashMap<>();
        PetDto pet = new PetDto();
        pet.setPetId(petId);
        pet.setPetName(petName);

        int updated = pservice.updatePetByAdmin(pet);

        if (updated > 0) {
            result.put("result", 1);
            result.put("message", "펫 이름 수정 성공");
        } else {
            result.put("result", 0);
            result.put("message", "펫 이름 수정 실패");
        }
        return result;
    }

    // 펫 검색 페이지
    @GetMapping("/search")
    public String petSearchPage() {
        return "pet/list";
    }

    // 검색 실행 (Ajax)
    @GetMapping("/search/result")
    @ResponseBody
    public List<PetDto> searchPets(@RequestParam("keyword") String keyword,
                                   @RequestParam(value="type", required=false) String type) {
        return pservice.searchPets(keyword, type);
    }

    // 펫 삭제 폼
    @GetMapping("/delete")
    public String deletePetForm(@RequestParam("petId") int petId,
    							Principal principal,
                                Model model) {
    	int userid = service.myPage(principal.getName()).getUserId();

        model.addAttribute("pet", pservice.selectPetDetail(userid, petId));
        return "pet/delete";
    }

    // 펫 삭제 처리 (AJAX)
    @PostMapping("/delete")
    public String deletePet(@RequestParam("petId") int petId,
    									Principal principal, RedirectAttributes rttr) {
        Map<String, Object> result = new HashMap<>();
        // 로그인 사용자 ID 가져오기
        int userId = service.myPage(principal.getName()).getUserId();


        // 본인 펫 삭제 처리
        int rows = pservice.deletePetByUser(petId, userId);

        if(rows > 0){
            rttr.addFlashAttribute("msg", "삭제 완료");
            return "redirect:/users/mypage";
        } else {
            rttr.addFlashAttribute("msg", "삭제 실패");
            return "redirect:/pet/detail?petId=" + petId;
        }


    }

    // 관리자 펫 리스트 페이지
    @GetMapping("/listPetPage")
    public String adminListPage(Model model,
                                @RequestParam(value="pstartno", defaultValue="1") int pstartno) {
        model.addAttribute("list", pservice.selectPet10(pstartno));
        model.addAttribute("paging", new PagingDto10(pservice.selectTotalPetCnt(), pstartno));
        return "pet/list";
    }

    // 관리자 펫 리스트 (JSON)
    @GetMapping("/list")
    @ResponseBody
    public Map<String, Object> list(@RequestParam(value="pstartno", defaultValue="1") int pstartno) {
        Map<String, Object> result = new HashMap<>();
        result.put("list", pservice.selectPet10(pstartno));
        result.put("paging", new PagingDto10(pservice.selectTotalPetCnt(), pstartno));
        return result;
    }

}
