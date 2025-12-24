package com.pawject.service.support;

import java.util.List;

import com.pawject.dto.support.FAQDto;

public interface FAQService {
	public List<FAQDto> selectFAQAll();
	public FAQDto selectFAQ(int faqid);
	public int insertFAQ(FAQDto dto);
	public int updateFAQ(FAQDto dto);
	public int activeFAQ(FAQDto dto);
}
