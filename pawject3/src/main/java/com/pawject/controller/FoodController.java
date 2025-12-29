package com.pawject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pawject.dto.food.FoodDto;
import com.pawject.dto.food.NutriDto;
import com.pawject.dto.paging.PagingDto10;
import com.pawject.service.food.FoodService;


@Controller
@RequestMapping("/foodboard")
//@PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
public class FoodController {
	@Autowired FoodService service;

	//리스트
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
	@RequestMapping("/foodlist.fn")
	public String list(Model model,
						@RequestParam(value="pstartno", defaultValue = "1") int pstartno ) { //전체 목록 게시판 뷰
	    int total = service.foodselectcnt(); // 총 개수
	    PagingDto10 paging = new PagingDto10(total, pstartno);
	    model.addAttribute("foodlist", service.foodselect10(paging.getCurrent()));
	    model.addAttribute("foodpaging", paging);

	    return "foodboard/foodlist";
	}

	//신규등록
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
	@RequestMapping("/foodwrite.fn")
	public String write_get(Model model) { //신규사료+영양입력폼
		
	    model.addAttribute("brandlist", service.brandSelectAll());
	    model.addAttribute("nutrientlist", service.nutrientSelectName());
	    
		return "foodboard/foodwrite";
	}
	
	@RequestMapping(value="/foodwrite.fn", method=RequestMethod.POST)
	public String write_post(
		    FoodDto fdto,
		    @RequestParam(required = false) List<String> nutrientid,  //Integer 했더니 오류남 -> String 
		    @RequestParam(required = false) List<String> amount,
		    @RequestParam("file") MultipartFile	file,  //이미지
		    RedirectAttributes rttr) {

	    // 사료 입력
	    int result1 = service.foodinsert(fdto, file);
	    // 여기서 fdto.foodid 가 채워져 있어야 함!

	    // 영양소 입력
	    int result2 = 1;

	    if(nutrientid != null && amount != null){
	        for(int i=0; i<nutrientid.size(); i++){
	            
	            if(amount.get(i) == null || amount.get(i).trim().equals("")) continue;
	            if(nutrientid.get(i) == null || nutrientid.get(i).trim().equals("")) continue;

	            NutriDto ndto = new NutriDto();
	            ndto.setFoodid(fdto.getFoodid());
	            ndto.setNutrientid(Integer.parseInt(nutrientid.get(i)));
	            ndto.setAmount(Double.parseDouble(amount.get(i)));

	            service.nutriinsert(ndto);
	        }
	    }	
	    
	    if(result1 > 0){
	        rttr.addFlashAttribute("success", "사료 등록 성공");
	        return "redirect:/foodboard/fooddetail.fn?foodid=" + fdto.getFoodid();
	    } else {
	        rttr.addFlashAttribute("success", "사료 등록 실패");
	        return "redirect:/foodboard/foodwrite.fn";
	    }
	}
	

	//상세페이지
	@RequestMapping("/fooddetail.fn")
	public String detail(int foodid, Model model) { //개별상세페이지
		model.addAttribute("fdto", service.foodselectwithBrand(foodid));
		model.addAttribute("nutrientList", service.nutriselectWithInfo(foodid));

		
		return "foodboard/fooddetail";
	}
	
	
	//편집
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
	@RequestMapping("/foodedit.fn")
	public String edit_get(int foodid, Model model) { //수정폼
		model.addAttribute("fdto", service.foodselect(foodid));
	    model.addAttribute("brandlist", service.brandSelectAll());
	    model.addAttribute("nutrientlist", service.nutrientSelectName());
	    model.addAttribute("nutriList", service.nutriselectWithInfo(foodid));

		return "foodboard/foodedit";
	}
	
	@RequestMapping(value="/foodedit.fn", method=RequestMethod.POST)
	public String edit_post(			//수정 - 입력 참고
	        FoodDto fdto,
	        @RequestParam(required = false) List<Integer> nutrientid,
	        @RequestParam(required = false) List<String> amount,
	        @RequestParam("file") MultipartFile file,
	        RedirectAttributes rttr) {

	    // 푸드 먼저
	    int result1 = service.foodupdate(fdto, file);

	    // 기존 영양소 전체 삭제** - 골라서 수정하기 빡셈 걍 해당 사료 영양소 다 날리고 새로 넣기로...
	    service.nutrideleteAll(fdto.getFoodid());

	    // 새 영양소 입력
	    if(nutrientid != null && amount != null){
	        for(int i=0; i<nutrientid.size(); i++){
	            if(amount.get(i) == null || amount.get(i).trim().equals("")) continue;
	            NutriDto ndto = new NutriDto();
	            ndto.setFoodid(fdto.getFoodid());
	            ndto.setNutrientid(nutrientid.get(i));
	            ndto.setAmount(Double.parseDouble(amount.get(i).trim()));

	            service.nutriinsert(ndto);
	        }
	    }

	    if(result1 > 0){
	        rttr.addFlashAttribute("success", "사료 수정 성공");
	        return "redirect:/foodboard/fooddetail.fn?foodid=" + fdto.getFoodid();
	    } else {
	        rttr.addFlashAttribute("success", "사료 수정 실패");
	        return "redirect:/foodboard/foodedit.fn?foodid=" + fdto.getFoodid();
	    }

	}
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
	@RequestMapping("/fooddelete.fn")
	public String delete(@RequestParam int foodid,  RedirectAttributes rttr) {//삭제폼
							//dto 쓰기는 과함 id만 있으면 됨
		 
		 int result1 = service.nutrideleteAll(foodid);
		 int result2 = service.fooddelete(foodid);
		 
			if(result1>0 && result2>0) {
				String result="삭제 성공";
				rttr.addFlashAttribute("success", result);
				return "redirect:/foodboard/foodlist.fn";
			} else {
				String result="삭제 실패";
				rttr.addFlashAttribute("success", result);
				return "redirect:/foodboard/fooddetail.fn?foodid=" + foodid;
			}
	}
	
	
	

	
}

