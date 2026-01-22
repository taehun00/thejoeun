package com.pawject.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pawject.dto.review.ReviewDto;
import com.pawject.dto.review.ReviewImgDto;
import com.pawject.dto.user.UserAuthDto;
import com.pawject.dto.user.UserDto;
import com.pawject.service.review.ReviewApi;
import com.pawject.service.review.ReviewService;
import com.pawject.service.user.UserSecurityService;
import com.pawject.util.UtilPaging;
@RestController
@RequestMapping("/reviewboard")
public class ReviewAjaxController {

    @Autowired
    ReviewService service;
    @Autowired UserSecurityService uservice;
    
    // 글 업로드 - get은 기존 컨트롤러 활용
    @PostMapping("/reviewwrite")
    @PreAuthorize("isAuthenticated() and hasAnyRole('ROLE_ADMIN', 'ROLE_MEMBER')") 
    public int writeAjax(ReviewDto dto,  Principal principal) {
	
	    UserDto param = new UserDto(principal.getName(), null);

	    UserAuthDto auth = service.readAuthForReview(param);
	    int userid = service.selectUserIdForReview(principal.getName());
	    dto.setUserid(userid);

    	
        service.reviewInsert(dto);
        return dto.getReviewid();
    }

    // 이미지 업로드 
    @PostMapping("/reviewimg/upload")
    public Map<String, Object> reviewUpload(
            @RequestParam("reviewid") int reviewid,
            @RequestParam("file") MultipartFile file) {

        ReviewImgDto dto = service.reviewimginsert(reviewid, file);

        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("reviewimgname", dto.getReviewimgname());
        result.put("reviewimgid", dto.getReviewimgid());

        return result;
    }
    
    //글 수정
    @PostMapping("/reviewedit")
    @PreAuthorize("isAuthenticated() and hasAnyRole('ROLE_ADMIN', 'ROLE_MEMBER')") 
    public int updateAjax(ReviewDto dto) {
    	service.reviewUpdate(dto);
    	return dto.getReviewid();
    }
    
   //이미지 수정
    @PostMapping("/reviewimg/update")
    public Map<String, Object> reviewUpdate(
            @RequestParam("reviewimgid") int reviewimgid,
            @RequestParam("file") MultipartFile file) {

        ReviewImgDto dto = service.reviewimgupdate(reviewimgid, file);

        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("reviewimgname", dto.getReviewimgname());
        result.put("reviewimgid", dto.getReviewimgid());

        return result;
    }
    
    //이미지 삭제
    @PostMapping("/reviewimg/delete")
    public int deletereviewimg( @RequestParam("reviewimgid") int reviewimgid) {
    	return service.reviewimgdelete(reviewimgid);
    }
    
    //모달연동
    @RequestMapping("/reviewsearchByFoodid")
    public Map<String, Object> reviewsearchByFoodid(@RequestParam int foodid){
    	Map<String, Object> result = new HashMap<>();
    	int total = service.reviewsearchByFoodidCnt(foodid);
    	List<ReviewDto> list = service.reviewsearchByFoodid(foodid);
    	
    	result.put("total", total);
    	result.put("list", list);
    	return result;
    }
    
    
    
    //페이징
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
    		
    //서치+페이징
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


}


