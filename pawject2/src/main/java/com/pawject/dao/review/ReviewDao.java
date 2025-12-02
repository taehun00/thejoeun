package com.pawject.dao.review;

import java.util.List;

import com.pawject.dao.MyDao;
import com.pawject.dto.review.ReviewDto;
@MyDao
public interface ReviewDao {
	//	<select  resultMap="ReviewMap" id="reviewSelectAll">
		public List<ReviewDto> reviewSelectAll();
	
	//	<select resultMap="ReviewMap" id="reviewSelect" parameterType="int">
		public int reviewSelect(int reviewid);

	//	<insert id="reviewInsert" parameterType="ReviewDto">
		public int reviewInsert(ReviewDto dto);
		
	//	<update id="reviewUpdate" parameterType="ReviewDto">
		public int reviewUpdate(ReviewDto dto);
		
	//	<delete id="reviewDelete" parameterType="int">
		public int reviewDelete(int reviewid);
}

