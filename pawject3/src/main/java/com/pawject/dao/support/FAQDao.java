package com.pawject.dao.support;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pawject.dto.support.FAQDto;
@Mapper
public interface FAQDao {
	
	//<select resultMap="FAQMap" id="selectFAQAll">
	public List<FAQDto> selectFAQAll();
	
	//<select resultMap="FAQMap" id="selectFAQ" parameterType="int">
	public FAQDto selectFAQ(int faqid);
	
	//<insert id="insertFAQ" parameterType="FAQDto">
	public int insertFAQ(FAQDto dto);
	
	//<update id="activeFAQ" parameterType="FAQDto">
	public int activeFAQ(FAQDto dto);
}
