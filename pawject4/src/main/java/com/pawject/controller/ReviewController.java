package com.pawject.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pawject.dto.review.ReviewDto;
import com.pawject.service.food.FoodService;
import com.pawject.service.review.ReviewApi;
import com.pawject.service.review.ReviewService;
import com.pawject.util.UtilPaging;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "REVIEW", description = "리뷰게시판") 
@RestController 
@RequiredArgsConstructor 
@RequestMapping("/reviewboard")
public class ReviewController {
	 private final ReviewService service;
     private final FoodService fservice;
	
	@Operation(summary = "리뷰 등록/수정 정보")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
	@GetMapping("/form")
	public Map<String, Object> foodFormData(
	        @RequestParam(required = false) Integer reviewid) {
	    Map<String, Object> result = new HashMap<>();
	    //공통
	    result.put("brandlist", fservice.brandSelectAll());
	    result.put("foodlist", fservice.foodselectAll());

	    //수정폼 정보
	    if (reviewid != null) {
	        result.put("dto", service.reviewSelect(reviewid));
	        result.put("imglist", service.reviewimgSelect(reviewid));
	    }
	    return result;
	}
	
	
	@Operation(summary = "리뷰작성 (글+이미지)")
	@PostMapping("/reviewwrite")
	@PreAuthorize("isAuthenticated() and hasAnyRole('ROLE_ADMIN', 'ROLE_MEMBER')")
	public int write(
	        @ModelAttribute ReviewDto dto,
	        @RequestParam(required = false) List<MultipartFile> files,
	        Principal principal
	) {
	    int userid = service.selectUserIdForReview(principal.getName());
	    dto.setUserid(userid);

	    return service.reviewInsertWithImg(dto, files);
	}

	
	@Operation(summary = "리뷰 수정 (글+이미지)")
	@PostMapping("/reviewedit/{reviewid}")
	public int update(
	    @PathVariable int reviewid,
	    @ModelAttribute ReviewDto dto,
	    @RequestParam(required=false) List<MultipartFile> files,
	    Principal principal
	) {
	    int userid = service.selectUserIdForReview(principal.getName());
	    dto.setUserid(userid);
	    dto.setReviewid(reviewid);

	    return service.reviewUpdatetWithImg(dto, files);
	}
	
	
	@Operation(summary = "모달 연동")
    @RequestMapping("/reviewsearchByFoodid")
    public Map<String, Object> reviewsearchByFoodid(@RequestParam int foodid){
    	Map<String, Object> result = new HashMap<>();
    	int total = service.reviewsearchByFoodidCnt(foodid);
    	List<ReviewDto> list = service.reviewsearchByFoodid(foodid);
    	
    	result.put("total", total);
    	result.put("list", list);
    	return result;
    }
    
    
	@Operation(summary = "페이징")
    @RequestMapping("/reviewPaging")
    public Map<String, Object> reviewPaging(
    	     		@RequestParam(required=false) String condition,
    			    @RequestParam(defaultValue="1") int pageNo){	
    	Map<String, Object> result = new HashMap<>();
    	
    	int total= service.reviewSelectCnt();
    	List<ReviewDto> list = service.reviewSelect10(pageNo, condition);

    	UtilPaging paging = new UtilPaging(total, pageNo);  
        result.put("paging", paging);
    	result.put("total", total);
    	result.put("list", list);

		
	    return result;
	}
    		
	@Operation(summary = "리뷰검색")
    @RequestMapping("/reviewsearch")
    public Map<String, Object> reviewsearch(
	        @RequestParam("keyword") String keyword,
	        @RequestParam("searchType") String searchType,
	        @RequestParam(required=false) String condition,
	        @RequestParam(value="pageNo", defaultValue="1") int pageNo){
    	
	    Map<String, Object> result = new HashMap<>();
	    int total = service.reviewsearchcnt(keyword, searchType);
	    List<ReviewDto> list = service.reviewsearch(keyword, searchType, condition, pageNo);
	    UtilPaging paging = new UtilPaging(total, pageNo);
	    
	      result.put("total", total);   
	      result.put("list", list);
	      result.put("paging", paging);
	      result.put("search", keyword);

	    return result;
	}

	@DeleteMapping
	public ResponseEntity<Void> deleteByreviewid(
	        @RequestParam int reviewid,
	        Principal principal
	) {
	    int userid = service.selectUserIdForReview(principal.getName());
	    service.reviewimgdeleteById(reviewid);
	    int result = service.reviewDelete(reviewid, userid);

	    if (result > 0) return ResponseEntity.noContent().build();
	    return ResponseEntity.status(403).build(); // 또는 notFound 구분
	}
	
	
	
	
	@Autowired private ReviewApi apiservice;
	@Operation(summary = "리뷰 api")
	@PostMapping("/reviewapi")
	@ResponseBody
	public String openai(@RequestParam String title,
						 @RequestParam String reviewcomment) {
	    return apiservice.helpReviewWriting(title, reviewcomment);
	}
	

}



