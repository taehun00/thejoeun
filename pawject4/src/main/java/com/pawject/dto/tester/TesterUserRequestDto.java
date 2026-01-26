package com.pawject.dto.tester;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TesterUserRequestDto {
	
	private String category;
	private String title;
	private String content;

}
