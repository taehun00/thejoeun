package com.pawject.dao.support;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pawject.dto.support.CSQuestionDto;

@Mapper
public interface CSQuestionDao {
	
	//  <select resultMap="CSQuestionMap" id="selectCSQAll">
	public List<CSQuestionDto> selectCSQAll();
	
	//  <select resultMap="CSQuestionMap" id="selectCSQ" parameterType="int">
	public 	CSQuestionDto selectCSQ(int questionid);
	
	//  <insert id="insertCSQ" parameterType="CSQuestionDto">
	public int insertCSQ(CSQuestionDto dto);
	
	//  <update id="answerCSQ" parameterType="CSQuestionDto">
	public int answerCSQ(CSQuestionDto dto);
	
	//  <delete id="deleteCSQ" parameterType="int">
	public int deleteCSQ(int questionid);
	
	// <select resultMap="CSQuestionMap" id="selectCSQUser" parameterType="CSQuestionDto">
	public List<CSQuestionDto> selectCSQUser(CSQuestionDto dto); 

}
