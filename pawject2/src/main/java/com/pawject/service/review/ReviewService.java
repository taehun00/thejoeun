package com.pawject.service.review;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.pawject.dto.review.ReviewDto;

public interface ReviewService {
	public List<ReviewDto> reviewSelectAll();
	public int reviewSelect(int reviewid);
	public int reviewInsert(ReviewDto dto, MultipartFile file);
	public int reviewUpdate(ReviewDto dto, MultipartFile file);
	public int reviewDelete(int reviewid);	

}
