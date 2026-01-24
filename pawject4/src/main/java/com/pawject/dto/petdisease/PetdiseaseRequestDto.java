package com.pawject.dto.petdisease;

import com.pawject.dto.tester.TesterRequestDto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PetdiseaseRequestDto {

	private int pettypeid;
	private String disname;
	private String disexplain;
	private String recommend;

	
}
