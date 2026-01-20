package com.pawject.service.review;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.pawject.dto.food.FoodDtoForList;
import com.pawject.dto.review.ReviewDto;
import com.pawject.dto.review.ReviewImgDto;
import com.pawject.dto.user.UserAuthDto;
import com.pawject.dto.user.UserDto;

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
	public ReviewImgDto reviewimginsert(int reviewid, MultipartFile file);  //아작스용
	public ReviewImgDto reviewimgupdate(int reviewimgid, MultipartFile file);
	public int reviewimgdeleteAll(int reviewid);
	public int reviewimgdelete(int reviewimgid);
	public ReviewImgDto  reviewimgIdSelect(int reviewimgid);
	
	public List<ReviewDto> reviewSelect10(int pstartno);	
	public int reviewSelectCnt();
	

	public List<ReviewDto> reviewsearch(String keyword, String searchType);
	public int reviewsearchcnt(String keyword, String searchType);
	
	
	
	//권한
	public UserAuthDto readAuthForReview(UserDto udto);
	public int selectUserIdForReview(String email);
	
	
	//사료-리뷰 연결
	public List<ReviewDto> reviewsearchByFoodid(int foodid);
	public int reviewsearchByFoodidCnt(int foodid);
}