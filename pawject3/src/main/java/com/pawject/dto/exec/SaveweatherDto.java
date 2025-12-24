package com.pawject.dto.exec;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@AllArgsConstructor
@NoArgsConstructor

public class SaveweatherDto {
	private String basedate;	//BASEDATE
	private String weather;		//WEATHER
	private int    maxtemp;		//MAXTEMP
	private int    mintemp;		//MINTEMP
	private int    moispercent; //MOISPERCENT
	private int    rainpercent; //RAINPERCENT
}
/*
이름          널?       유형           
----------- -------- ------------ 
BASEDATE             DATE         
WEATHER              VARCHAR2(30) 
MAXTEMP     NOT NULL NUMBER       
MINTEMP     NOT NULL NUMBER       
MOISPERCENT NOT NULL NUMBER       
RAINPERCENT NOT NULL NUMBER   
*/
