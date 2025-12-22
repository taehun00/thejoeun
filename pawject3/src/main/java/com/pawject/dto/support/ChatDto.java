package com.pawject.dto.support;

import lombok.Data;

@Data
public class ChatDto {
	private int roomid;
	private int userid;
	private int adminid;
	private int status;
	private String createdat;
	private String closedat;
	
	private int messageid;
	private int senderid;
	private int sendertype;
	private String message;
	private int faqid;
	private String mcreatedat;  //채팅방 생성일과 구분 필요
	private int isread;
	

}

/*
이름        널?       유형        
--------- -------- --------- 
ROOMID    NOT NULL NUMBER    
USERID    NOT NULL NUMBER    
ADMINID            NUMBER    
STATUS             NUMBER(1) 
CREATEDAT          DATE      
CLOSEDAT           DATE      
이름         널?       유형             
---------- -------- -------------- 
MESSAGEID  NOT NULL NUMBER         
ROOMID     NOT NULL NUMBER         
SENDERID   NOT NULL NUMBER         
SENDERTYPE NOT NULL NUMBER(1)      
MESSAGE             VARCHAR2(2000) 
FAQID               NUMBER         
CREATEDAT           DATE           
ISREAD              NUMBER(1)     
 */
