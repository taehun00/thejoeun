package com.pawject.service.support;

import java.util.List;

import com.pawject.dto.support.CSAnswerDto;
import com.pawject.dto.support.CSQuestionDto;

public interface CSService {
	
	public List<CSQuestionDto> selectCSQAll();
	public 	CSQuestionDto selectCSQ(int questionid);
	public int insertCSQ(CSQuestionDto dto);
	public int answerCSQ(CSQuestionDto dto);
	public int deleteCSQ(int questionid);
	public List<CSQuestionDto> selectCSQUser(CSQuestionDto dto); 
	
	
	public int insertCSA(CSAnswerDto dto);
	public List<CSAnswerDto> selectByQuestionid(int questionid);
}
