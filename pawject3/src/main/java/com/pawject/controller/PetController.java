package com.pawject.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.pawject.service.pet.PetService;
import com.pawject.service.user.UserSecurityService;


@Controller
@RequestMapping("/pets")
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
            int userid = (int) session.getAttribute("userid");
            dto.setUserId(userid);

            if (pservice.petJoin(pfile, dto) > 0) {
                result = "펫 등록 성공";
            }
        } catch (Exception e) {
            result = "펫 등록 처리 중 오류가 발생했습니다.";
        }

        rttr.addFlashAttribute("success", result);
        return "redirect:/security/mypage";
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
    @GetMapping("/update/user")
    public String petUpdateUserForm(@RequestParam("petId") int petId,
                                    Principal principal,
                                    Model model) {
        int userId = service.myPage(principal.getName()).getUserId();
        PetDto pet = pservice.selectPetDetail(userId, petId);
        model.addAttribute("pet", pet);
        return "pet/updateUser";
    }

    @PostMapping("/update/user")
    public String petUpdateUser(@RequestParam("file") MultipartFile file,
                                PetDto pet,
                                RedirectAttributes rttr,
                                HttpSession session) {
        int userid = (int) session.getAttribute("userid");

        String result = "수정 실패";
        if (pservice.updatePetByUser(file, pet, userid) > 0) {
            result = "펫 수정 성공";
        }
        rttr.addFlashAttribute("success1", result);

        return "redirect:/security/mypage";
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
                                HttpSession session,
                                Model model) {
        int userid = (int) session.getAttribute("userid");
        model.addAttribute("pet", pservice.selectPetDetail(userid, petId));
        return "pet/delete";
    }

    // 펫 삭제 처리 (AJAX)
    @PostMapping("/delete")
    @ResponseBody
    public Map<String, Object> deletePet(@RequestParam("petId") int petId,
                                         HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        Integer userId = (Integer) session.getAttribute("userid");

        if (userId != null && pservice.deletePetByAdmin(petId) > 0) {
            result.put("result", 1);
        } else {
            result.put("result", 0);
        }
        return result;
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
