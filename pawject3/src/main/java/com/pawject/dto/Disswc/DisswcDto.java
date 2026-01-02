package com.pawject.dto.Disswc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DisswcDto {
	private Integer disno;
	private Integer userid;
	private String disname;
	private String disex;
	private String kindpet;
	private String infval;
	private String mannote;
	private Integer bhit;
    private String createdat;
    private String bip;
    private String bpass;
}