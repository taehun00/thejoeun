package com.pawject.dto.exec;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@AllArgsConstructor
@NoArgsConstructor

public class ExecinfoDto {
	private int    execid;			//EXECID
	private String exectype;		//EXECTYPE
	private String description;		//DESCRIPTION
	private float  avgkcal30min;	//AVGKCAL30MIN
	private int    exectargetmin;	//EXECTARGETMIN
	private String suitablefor; 	//SUITABLEFOR
	private String intensitylevel;	//INTENSITYLEVEL
	private String createdAt;		//CREATEDAT
	private String updatedAt;		//UPDATEDAT
}
/*
Name                                      Null?    Type
----------------------------------------- -------- ----------------------------
EXECID                                    NOT NULL NUMBER(38)
EXECTYPE                                           VARCHAR2(50)
DESCRIPTION
AVGKCAL30MIN                                       FLOAT(126)
EXECTARGETMIN                                      NUMBER(38)
SUITABLEFOR                                        VARCHAR2(100)
INTENSITYLEVEL                                     VARCHAR2(100)
CREATEDAT                                          DATE
UPDATEDAT                                          DATE
*/