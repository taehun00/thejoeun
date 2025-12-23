package com.pawject.service.support;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pawject.dao.support.CSAnswerDao;
import com.pawject.dao.support.CSQuestionDao;
import com.pawject.dto.support.CSAnswerDto;
import com.pawject.dto.support.CSQuestionDto;
@Service
public class CSServiceImpl implements CSService {
	@Autowired CSQuestionDao qdao;
	@Autowired CSAnswerDao adao;

	@Override
	public List<CSQuestionDto> selectCSQAll() {
		List<CSQuestionDto> questions = qdao.selectCSQAll();
	    for(CSQuestionDto q : questions) {
	    	q.setAnswers(adao.selectByQuestionid(q.getQuestionid()));
	    }
	    return questions;
	}
	
	@Override
	public CSQuestionDto selectCSQ(int questionid) {
		return qdao.selectCSQ(questionid);
	}

	@Override
	public int insertCSQ(CSQuestionDto dto) {
		return qdao.insertCSQ(dto);
	}

	@Override
	public int answerCSQ(CSQuestionDto dto) {
		return qdao.answerCSQ(dto);
	}

	@Override
	public int deleteCSQ(int questionid) {
		return qdao.deleteCSQ(questionid);
	}

	@Override
	public int insertCSA(CSAnswerDto dto) {
		return adao.insertCSA(dto);
	}
	
	//질문+답변 매칭
	@Override
	public List<CSQuestionDto> selectCSQUser(CSQuestionDto dto) {
		List<CSQuestionDto> questions = qdao.selectCSQUser(dto);
	    for(CSQuestionDto q : questions) {
	    	q.setAnswers(adao.selectByQuestionid(q.getQuestionid()));
	    }
	    return questions;
	}

	@Override
	public List<CSAnswerDto> selectByQuestionid(int questionid) {
		return adao.selectByQuestionid(questionid);
	}

}
