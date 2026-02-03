package com.pawject.dto.exec;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


//@Data
@Getter @Setter @ToString
@AllArgsConstructor
@NoArgsConstructor
public class WalkingcourseDto {
	private Integer  courseid;	//COURSEID
	private int  	 postid;	//POSTID
	private String   location;	//LOCATION
	private int      lat;	//LAT
	private int 	 lng;	//LNG
	private String createdAt;	//CREATEDAT
	
}
/*
이름          널?       유형           
----------- -------- ------------ 
COURSEID              NUMBER(10)         
POSTID                NUMBER
LOCATION   NOT NULL   VATCHAR2(255)       
LAT         NOT NULL  NUMBER(5)       
LNG         NOT NULL  NUMBER(5)       
CREATEDAT   NOT NULL  DATE 
*/
