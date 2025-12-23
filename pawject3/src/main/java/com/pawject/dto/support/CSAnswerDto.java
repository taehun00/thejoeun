package com.pawject.dto.support;

import lombok.Data;

@Data
public class CSAnswerDto {
	
	private int answerid;
	private int questionid;
	private int adminid;
	private String content;
	private String createdat;

}

/*
ANSWERID   NOT NULL NUMBER 
QUESTIONID NOT NULL NUMBER 
ADMINID    NOT NULL NUMBER 
CONTENT    NOT NULL CLOB   
CREATEDAT           DATE   
 */