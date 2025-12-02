package com.pawject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pawject.dto.food.FoodDto;
import com.pawject.dto.food.FoodPagingDto;
import com.pawject.dto.food.NutriDto;
import com.pawject.service.food.FoodService;


@Controller
public class FoodController {
	@Autowired FoodService service;
	
//	@RequestMapping("/foodlist.fn")
//	public String list(Model model) { //전체 목록 게시판 뷰
//		model.addAttribute("foodlist", service.foodselectForList());
//		return "foodboard/foodlist";
//	}
	
	
	@RequestMapping("/foodlist.fn")
	public String list(Model model,
						@RequestParam(value="pstartno", defaultValue = "1") int pstartno ) { //전체 목록 게시판 뷰
	    int total = service.foodselectcnt(); // 총 개수
	    FoodPagingDto paging = new FoodPagingDto(total, pstartno);
	    model.addAttribute("foodlist", service.foodselect10(paging.getCurrent()));
	    model.addAttribute("foodpaging", paging);


	    return "foodboard/foodlist";
	}
	

	
	
	
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
		    RedirectAttributes rttr) {

	    // 사료 입력
	    int result1 = service.foodinsert(fdto);  
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
	        return "redirect:/fooddetail.fn?foodid=" + fdto.getFoodid();
	    } else {
	        rttr.addFlashAttribute("success", "사료 등록 실패");
	        return "redirect:/foodwrite.fn";
	    }
	}
	
	
	
	
//	@RequestMapping(value="/foodwrite.fn", method=RequestMethod.POST)
//	public String write_post(	//신규사료등록
//	        FoodDto fdto,
//	        @RequestParam(required = false) List<Integer> nutrientid,  //양이랑 분리해야됨 
//	        @RequestParam(required = false) List<String> amount,		//사실 이해는 잘 안되는데 분리하니까 작동함ㅋㅋㅋㅋ
//	        RedirectAttributes rttr) {									//그냥 마음으로 받아들이고 나중ㅇ에 이해하기...
//
//	    // 외래키 때문에 푸드 먼저 입력해야됨 순서 바뀌면 큰일남 ->근데 아직 안걸긴 했음ㅋㅋ
//	    int result1 = service.foodinsert(fdto);
//
//	    // 영양소 insert 반복 ->안하면 오류 터짐.. 머리도 터짐...
//	    int result2 = 0;
//	    for(int i=0; i<nutrientid.size(); i++){							// 안맞으면 패스!
//	        if(amount.get(i) == null || amount.get(i).trim().equals("")) continue;
//	        										// .공백 제거	.빈칸인지 확인 ->isEmpty()는 공백까지 계산해서 부적합
//	        NutriDto ndto = new NutriDto();
//	        ndto.setFoodid(fdto.getFoodid());
//	        ndto.setNutrientid(nutrientid.get(i));
//	        ndto.setAmount(Double.parseDouble(amount.get(i)));  //소수점 안하면 인함량 표시안됨
//
//	        result2 += service.nutriinsert(ndto);
//	    }
//
//	    if(result1 > 0 && result2 > 0){
//	        rttr.addFlashAttribute("success", "사료 등록 성공");
//	        return "redirect:/foodlist";
//	    } else {
//	        rttr.addFlashAttribute("success", "사료 등록 실패");
//	        return "redirect:/foodwrite";
//	    }
//	}

	
	@RequestMapping("/fooddetail.fn")
	public String detail(int foodid, Model model) { //개별상세페이지
		model.addAttribute("fdto", service.foodselectwithBrand(foodid));
		model.addAttribute("nutrientList", service.nutriselectWithInfo(foodid));

		
		return "foodboard/fooddetail";
	}
	
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
	        RedirectAttributes rttr) {

	    // 푸드 먼저
	    int result1 = service.foodupdate(fdto);

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
	        return "redirect:/fooddetail.fn?foodid=" + fdto.getFoodid();
	    } else {
	        rttr.addFlashAttribute("success", "사료 수정 실패");
	        return "redirect:/foodedit.fn?foodid=" + fdto.getFoodid();
	    }

	}
	
	@RequestMapping("/fooddelete.fn")
	public String delete(@RequestParam int foodid,  RedirectAttributes rttr) {//삭제폼
							//dto 쓰기는 과함 id만 있으면 됨
		 
		 int result1 = service.nutrideleteAll(foodid);
		 int result2 = service.fooddelete(foodid);
		 
			if(result1>0 && result2>0) {
				String result="삭제 성공";
				rttr.addFlashAttribute("success", result);
				return "redirect:/foodlist.fn";
			} else {
				String result="삭제 실패";
				rttr.addFlashAttribute("success", result);
				return "redirect:/fooddetail.fn?foodid=" + foodid;
			}
	}
	
	
	
	
//	@RequestMapping(value="/fooddelete.fn", method=RequestMethod.POST) 
//	public String delete_post(FoodDto fdto,  RedirectAttributes rttr) {//삭제
//		 int foodid=fdto.getFoodid();
//		 
//		 int result1 = service.nutrideleteAll(foodid);
//		 int result2 = service.fooddelete(foodid);
//		 
//			if(result1>0 && result2>0) {
//				String result="삭제 성공";
//				rttr.addFlashAttribute("success", result);
//				return "redirect:/foodlist";
//			} else {
//				String result="삭제 실패";
//				rttr.addFlashAttribute("success", result);
//				return "redirect:/fooddelete";
//			}
//
//	}
	
}


/*							
/foodlist.fn			/view/foodboard/foodlist.jsp   
/foodwrite.fn			/view/foodboard/foodwrite.jsp  (글쓰기폼)
/fooddetail.fn			/view/foodboard/fooddetail.jsp  (상세보기)
/foodedit.fn			/view/foodboard/foodedit.jsp  (수정폼)
/fooddelete.fn			/view/foodboard/fooddelete.jsp  (삭제폼)
							ㄴ/view/, .jsp는 서블릿에 자동으로 붙게 설정해둠
*/