package com.pawject.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pawject.dto.food.FoodDtoForList;
import com.pawject.dto.review.ReviewDto;
import com.pawject.dto.review.ReviewImgDto;
import com.pawject.service.review.ReviewService;
@RestController
public class ReviewAjaxController {

    @Autowired
    ReviewService service;

    // 글 업로드 - get은 기존 컨트롤러 활용
    @PostMapping("/reviewwrite.fn")
    public int writeAjax(ReviewDto dto) {
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
    @PostMapping("/reviewedit.fn")
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
	

}


