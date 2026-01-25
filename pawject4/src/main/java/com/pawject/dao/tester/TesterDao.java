package com.pawject.dao.tester;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pawject.dto.tester.TesterAdminResponseDto;
@Mapper
public interface TesterDao {

//	<!--페이징+정렬(1)-->
//	<select resultMap="testerMap" id="select20Tester" parameterType="java.util.HashMap">
	public List<TesterAdminResponseDto> select20Tester(HashMap<String,Object> para);
	 
//<!--페이징+정렬-카운트(2)-->
//<select id="countByTesterPaging" parameterType="java.util.HashMap">
	public int countByTesterPaging(HashMap<String,Object> para);
	
//<!--페이징+정렬+검색(3)-->
//<select resultMap="testerMap" id="searchTester" parameterType="java.util.HashMap">
	public List<TesterAdminResponseDto> searchTester(HashMap<String,Object> para);
	
//<!--페이징+정렬+검색-카운트(4)-->
//<select resultType="int" id="searchTesterCnt" parameterType="java.util.HashMap">
	public int searchTesterCnt(HashMap<String,Object> para);

//<!--공지 올리고 내리기 0공지x 1공지중 -->
//<update id="updateIsnotice" parameterType="int">
	public int updateIsnotice(Long testerid);
//반환<select id="selectIsnotice" resultType="int">
	public int selectIsnotice(Long testerid);
//<!--모집 여부 0모집중 1모집완료 -->
//<update id="updateStatus" parameterType="int">
	public int updateStatus(Long testerid);
//반환<select id="selectStatus" resultType="int">
	public int selectStatus(Long testerid);
//<!--조회수 증가-->
//<update id="updateViews" parameterType="int">
	public int updateViews(Long testerid);

}
