package com.pawject.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pawject.dto.review.ReviewDto;
import com.pawject.dto.review.ReviewImgDto;
// ** import com.pawject.dto.user.UserAuthDto;
import com.pawject.service.review.ReviewService;
 // ** import com.pawject.service.user.UserSecurityService;
@RestController
public class ReviewAjaxController {

    @Autowired
    ReviewService service;
  // ** @Autowired UserSecurityService uservice;
    
    // 글 업로드 - get은 기존 컨트롤러 활용
    @PostMapping("/reviewwrite.fn")
    @PreAuthorize("isAuthenticated() and hasAnyRole('ROLE_ADMIN', 'ROLE_MEMBER')") 
    public int writeAjax(ReviewDto dto,  Principal principal) {
  // **     UserAuthDto user = uservice.readAuth(principal.getName());
  // **     dto.setUserid(user.getUserId()); 
    	
        service.reviewInsert(dto);
        return dto.getReviewid();
    }

    // 이미지 업로드 
    @PostMapping("/reviewimg/upload")
    @PreAuthorize("isAuthenticated() and hasAnyRole('ROLE_ADMIN', 'ROLE_MEMBER')") 
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
    @PostMapping("/reviewedit.fn")
    @PreAuthorize("isAuthenticated() and hasAnyRole('ROLE_ADMIN', 'ROLE_MEMBER')") 
    public int updateAjax(ReviewDto dto) {
    	service.reviewUpdate(dto);
    	return dto.getReviewid();
    }
    
   //이미지 수정
    @PostMapping("/reviewimg/update")
    @PreAuthorize("isAuthenticated() and hasAnyRole('ROLE_ADMIN', 'ROLE_MEMBER')") 
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
    @PreAuthorize("isAuthenticated() and hasAnyRole('ROLE_ADMIN', 'ROLE_MEMBER')") 
    public int deletereviewimg( @RequestParam("reviewimgid") int reviewimgid) {
    	return service.reviewimgdelete(reviewimgid);
    }
    
    //페이징 
//	@RequestMapping("/reviewPaging")
//	public List<ReviewDto> reviewPaging(
//	    @RequestParam(defaultValue="1") int pstartno
//	){
//	    return service.reviewSelect10(pstartno);
//	}
    
    @RequestMapping("/reviewPaging")
    public Map<String, Object> reviewPaging(
    		@RequestParam(defaultValue="1") int pstartno){	
    	Map<String, Object> result = new HashMap<>();
    	
    	int total= service.reviewSelectCnt();
    	List<ReviewDto> list = service.reviewSelect10(pstartno);
    	
    	result.put("total", total);
    	result.put("list", list);

    	return result;
    }
    
    
    //서치
    @RequestMapping("/reviewsearch")
    @PreAuthorize("isAuthenticated() and hasAnyRole('ROLE_ADMIN', 'ROLE_MEMBER')") 
    public Map<String, Object> reviewsearch(
	        @RequestParam("keyword") String keyword,
	        @RequestParam("searchType") String searchType){
    	
	    Map<String, Object> result = new HashMap<>();

	    List<ReviewDto> list = service.reviewsearch(keyword, searchType);

	    result.put("list", list);
	    result.put("total", list.size());
	    result.put("pstartno", 1);

	    return result;
	}

}


