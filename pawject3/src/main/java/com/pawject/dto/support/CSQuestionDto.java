package com.pawject.dto.support;

import lombok.Data;

@Data
public class CSQuestionDto {
	private int answerid;
	private int questionid;
	private String adminid;
	private String content;
	private String createdat;
}

/*
이름         널?       유형     
---------- -------- ------ 
ANSWERID   NOT NULL NUMBER 
QUESTIONID NOT NULL NUMBER 
ADMINID    NOT NULL NUMBER 
CONTENT    NOT NULL CLOB   
CREATEDAT           DATE   
 */