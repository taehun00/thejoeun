package com.thejoa703;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.pawject.Pawject3Application;
import com.pawject.dao.support.FAQDao;
import com.pawject.dto.support.FAQDto;
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
	
	

}
