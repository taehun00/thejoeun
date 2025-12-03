package com.pawject.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pawject.dto.review.ReviewDto;
import com.pawject.dto.review.ReviewImgDto;
import com.pawject.service.review.ReviewService;

@RestController
public class ReviewAjaxController {

    @Autowired
    ReviewService service;

    // 글 업로드 
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
}
    
