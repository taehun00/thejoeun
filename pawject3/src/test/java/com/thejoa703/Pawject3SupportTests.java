package com.thejoa703;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.pawject.Pawject3Application;
import com.pawject.dao.support.CSAnswerDao;
import com.pawject.dao.support.CSQuestionDao;
import com.pawject.dao.support.FAQDao;
import com.pawject.dto.support.CSAnswerDto;
import com.pawject.dto.support.CSQuestionDto;
import com.pawject.dto.support.FAQDto;
import com.pawject.service.support.CSService;
import com.pawject.service.support.FAQService;

@Transactional
@SpringBootTest(classes = Pawject3Application.class)
class Pawject3SupportTests {
	@Autowired FAQDao dao;
	@Autowired FAQService service;
	
	@Disabled @Test
	void contextLoads() {
		//1. insert
		FAQDto insert = new FAQDto();
		
		insert.setCategory("기타");
		insert.setQuestion("test");
		insert.setAnswer("test");
		insert.setKeywords("test, test");
		
		int insertResult = dao.insertFAQ(insert);
		assertEquals(1, insertResult);

		//update
		FAQDto update = new FAQDto();
		update.setIsactive(1);
		update.setFaqid(2);
		int updateResult = dao.activeFAQ(update);
		assertEquals(1, updateResult);
				
		//select
		FAQDto select = new FAQDto();

		assertNotNull(dao.selectFAQAll());
		assertNotNull(dao.selectFAQ(2));

	}
	
	@Disabled @Test
	void contextLoads2() {
		//1. insert
		FAQDto insert = new FAQDto();
		
		insert.setCategory("기타");
		insert.setQuestion("test");
		insert.setAnswer("test");
		insert.setKeywords("test, test");
		
		int insertResult = service.insertFAQ(insert);
		assertEquals(1, insertResult);

		//update
		FAQDto update = new FAQDto();
		update.setIsactive(1);
		update.setFaqid(2);
		int updateResult = service.activeFAQ(update);
		assertEquals(1, updateResult);
				
		//select
		FAQDto select = new FAQDto();

		assertNotNull(service.selectFAQAll());
		assertNotNull(service.selectFAQ(2));

	}
	
	@Autowired CSQuestionDao qdao;
	
	@Disabled @Test
	void contextLoads3() {
		//1. insert
		 CSQuestionDto insert2 = new CSQuestionDto();

		insert2.setUserid(1);
		insert2.setCategory("기타");
		insert2.setTitle("문의염");
		insert2.setContent("ㅎㅎ");
		
		int insertResult = qdao.insertCSQ(insert2);
		assertEquals(1, insertResult);

		//update
		CSQuestionDto update2 = new CSQuestionDto();
		update2.setQuestionid(2);
		int updateResult = qdao.answerCSQ(update2);
		assertEquals(1, updateResult);
				
		//select
		CSQuestionDto select2 = new CSQuestionDto();

		assertNotNull(qdao.selectCSQAll());
		assertNotNull(qdao.selectCSQ(2));
		
		//delete
		CSQuestionDto delete2 = new CSQuestionDto();
		assertNotNull(qdao.deleteCSQ(2));
	}
	
	@Autowired CSAnswerDao adao;
	@Disabled @Test
	void contextLoads4() {
		//1. insert
		CSAnswerDto insert3 = new CSAnswerDto();
		insert3.setAdminid(100);
//		insert3.setContent("ggg");
		insert3.setQuestionid(3);

		
		int insertResult = adao.insertCSA(insert3);
		assertEquals(1, insertResult);

	}
	
	
	
	@Autowired CSService csservice;
	@Disabled @Test
	void contextLoads5() {
		//1. insert
		 CSQuestionDto insert2 = new CSQuestionDto();

		insert2.setUserid(1);
		insert2.setCategory("기타");
		insert2.setTitle("문의염");
		insert2.setContent("ㅎㅎ");
		
		int insertResult = csservice.insertCSQ(insert2);
		assertEquals(1, insertResult);

		//update
		CSQuestionDto update2 = new CSQuestionDto();
		update2.setQuestionid(2);
		int updateResult = csservice.answerCSQ(update2);
		assertEquals(1, updateResult);
				
		//select
		CSQuestionDto select2 = new CSQuestionDto();

		assertNotNull(csservice.selectCSQAll());
		assertNotNull(csservice.selectCSQ(2));
		
		//delete
		CSQuestionDto delete2 = new CSQuestionDto();
		assertNotNull(csservice.deleteCSQ(2));
		
		
		CSAnswerDto insert3 = new CSAnswerDto();
		insert3.setAdminid(100);
//		insert3.setContent("ggg");
		insert3.setQuestionid(3);

		
		int insertResult2 = csservice.insertCSA(insert3);
		assertEquals(1, insertResult2);
	}
	

	
	
	

}
