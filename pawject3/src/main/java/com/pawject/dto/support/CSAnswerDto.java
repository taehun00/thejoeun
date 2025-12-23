package com.pawject.dto.support;

import lombok.Data;

@Data
public class CSAnswerDto {
	
	private int questionid;
	private int usrid;
	private String category;
	private String title;
	private String content;
	private int status;
	private String createdat;
	
	

}

/*
 이름         널?       유형            
---------- -------- ------------- 
QUESTIONID NOT NULL NUMBER        
USERID     NOT NULL NUMBER        
CATEGORY            VARCHAR2(50)  
TITLE      NOT NULL VARCHAR2(200) 
CONTENT    NOT NULL CLOB          
STATUS              NUMBER(1)     
CREATEDAT           DATE          

 */