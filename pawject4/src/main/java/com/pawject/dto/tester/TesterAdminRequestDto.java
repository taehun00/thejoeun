package com.pawject.dto.tester;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TesterAdminRequestDto {
	
	private String category;
	private String title;
	private String content;
	private int foodid;
	private int status;
	private int isnotice;
	private Integer  posttype;


}
