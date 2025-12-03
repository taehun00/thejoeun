package com.pawject.service.review;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.pawject.dto.review.ReviewDto;
import com.pawject.dto.review.ReviewImgDto;

public interface ReviewService {
	
	//리뷰테이블
	public List<ReviewDto> reviewSelectAll();
	public ReviewDto reviewSelect(int reviewid);
	public int reviewInsert(ReviewDto dto);
	public int reviewUpdate(ReviewDto dto);
	public int reviewDelete(int reviewid);	
	
	//이미지테이블
	public List<ReviewImgDto> reviewimgselectAll();
	public List<ReviewImgDto> reviewimgSelect(int reviewid);
//	public int reviewimginsert(int reviewid, List<MultipartFile> files);	//mvc용
	public ReviewImgDto reviewimginsert(int reviewid, MultipartFile file);  //아작스용
//	public int reviewimgupdate(int reviewid, List<MultipartFile> files);	//mvc
	public ReviewImgDto reviewimgupdate(int reviewimgid, MultipartFile file);
	public int reviewimgdeleteAll(int reviewid);
	public int reviewimgdelete(int reviewimgid);
	public ReviewImgDto  reviewimgIdSelect(int reviewimgid);

}
