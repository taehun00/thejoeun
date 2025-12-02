package com.pawject.dao.review;

import java.util.List;

import com.pawject.dao.MyDao;
import com.pawject.dto.review.ReviewDto;
@MyDao
public interface ReviewDao {
	//	<select resultMap="ReviewMap" id="reviewselectList" >
	public List<ReviewDto> reviewselectList();
	
//	/    <select resultMap="ReviewMap" id="reviewselect" parameterType="int">
	public ReviewDto reviewselect(int reviewid);
	
	//	<insert id="reviewinsert" parameterType="ReviewDto">
	public int reviewinsert(ReviewDto rdto);
	
//	<update id="reviewupdate" parameterType="ReviewDto">
	public int reviewupdate(ReviewDto rdto);
	
	//	<update id="reviewupdateAdmin" parameterType="ReviewDto">
	public int reviewupdateAdmin(ReviewDto rdto);
	
	//	<delete id="reviewdelete" parameterType="ReviewDto">
	public int reviewdelete(ReviewDto rdto);	
	
	//	<delete id="reviewdeleteAdmin" parameterType="int">
	public int reviewdeleteAdmin(int reviewid);	
}

