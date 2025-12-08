package com.pawject.dao.review;

import java.util.HashMap;
import java.util.List;

import com.pawject.dao.MyDao;
import com.pawject.dto.review.ReviewDto;
@MyDao
public interface ReviewDao {
	
		//기초 crud
	//	<select  resultMap="ReviewMap" id="reviewSelectAll">
		public List<ReviewDto> reviewSelectAll();
	
	//	<select resultMap="ReviewMap" id="reviewSelect" parameterType="int">
		public ReviewDto reviewSelect(int reviewid);

	//	<insert id="reviewInsert" parameterType="ReviewDto">
		public int reviewInsert(ReviewDto dto);
		
	//	<update id="reviewUpdate" parameterType="ReviewDto">
		public int reviewUpdate(ReviewDto dto);
		
	//	<delete id="reviewDelete" parameterType="int">
		public int reviewDelete(int reviewid);
		
		
		//페이징
	//<select resultMap="ReviewMap" id="reviewSelect10"  parameterType="java.util.Map">
		public List<ReviewDto> reviewSelect10(HashMap<String, Object> para);
		
	//<select resultType="int" id="reviewSelectCnt">	
		public int reviewSelectCnt();
		
	// 서치
	//<select  resultMap="ReviewMap" id="reviewsearch" parameterType="java.util.HashMap">
		public List<ReviewDto> reviewsearch(HashMap<String, Object> para);
		
		
	//<select  resultMap="ReviewMap" id="reviewsearchcnt" parameterType="java.util.HashMap">
		public int reviewsearchcnt(HashMap<String, Object> para);
		
}


