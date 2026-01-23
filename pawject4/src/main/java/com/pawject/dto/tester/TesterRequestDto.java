package com.pawject.dto.tester;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TesterRequestDto {
	
	private String category;
	private String title;
	private String content;
	private int foodid;
	private int status;
	private int isnotice;

}
