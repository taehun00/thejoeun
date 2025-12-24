package com.pawject.dto.exec;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@AllArgsConstructor
@NoArgsConstructor
public class ExecsmartDto {
	private int    postid;    //POSTID 
	private int    execid;    //EXECID
	private int    userid;    //USERID
	private String basedate;  //BASEDATE
	private int    courseid;  //COURSEID
	private String etitle;    //ETITLE
	private String econtent;  //ECONTENT
	private String eimg;	  //EIMG
	private int    ehit;	  //EHIT
	private String createdAt; //CREATEDAT
	private String updatedAt; //UPDATEDAT
}
/*
 Name                                      Null?    Type
 ----------------------------------------- -------- ----------------------------
 POSTID                                    NOT NULL NUMBER
 EXECID                                    NOT NULL NUMBER
 USERID                                    NOT NULL NUMBER
 BASEDATE                                           DATE
 COURSEID                                           NUMBER(10)
 ETITLE                                    NOT NULL VARCHAR2(100)
 ECONTENT                                  NOT NULL CLOB
 EIMG                                               VARCHAR2(255)
 EHIT                                               NUMBER
 CREATEDAT                                          DATE
 UPDATEDAT                                          DATE

    2. dto
    3. dao / mapper작성
    4. 테스트
*/
