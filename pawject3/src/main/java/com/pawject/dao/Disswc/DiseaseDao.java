package com.pawject.dao.Disswc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.pawject.dto.Disswc.DisswcDto;

@Mapper
public interface DiseaseDao {
	public int       insert(DisswcDto dto);
	public int       update(DisswcDto dto);
	public int       updateHit(int  disno);
	public int       delete(int disno);
	public DisswcDto	     select(int disno);
	public List<DisswcDto> selectAll(Map<String, Object> params);
	
	/*Ajax*/
//	public List<DisswcDto>  selectSearch( HashMap<String, String> para);
	  List<DisswcDto> select3(Map<String,Object> params);
	    int selectSearchTotalCnt(String keyword);

	
	
	
	/*Paging*/
	public List<DisswcDto> select10(HashMap<String,Object> para);
	public int selectTotalCnt();
	 
            
}


/*
Q3.  Dao 
    SQL>
    SQL> desc sboard2;
    Name                                      Null?    Type
    ----------------------------------------- -------- ----------------------------
    ID                                        NOT NULL NUMBER
    APP_USER_ID                               NOT NULL NUMBER
    BTITLE                                    NOT NULL VARCHAR2(1000)
    BCONTENT                                  NOT NULL CLOB
    BPASS                                     NOT NULL VARCHAR2(255)
    BFILE                                              VARCHAR2(255)
    BHIT                                               NUMBER
    BIP                                       NOT NULL VARCHAR2(255)
    CREATED_AT                                         DATE
    
    1. 테이블
    CREATE TABLE sboard2 (
	  id NUMBER PRIMARY KEY,
	  app_user_id NUMBER NOT NULL,
	  btitle VARCHAR2(1000) NOT NULL,
	  bcontent CLOB NOT NULL,
	  bpass VARCHAR2(255) NOT NULL,
	  bfile VARCHAR2(255) DEFAULT '0.png',
	  bhit NUMBER DEFAULT 0,
	  bip VARCHAR2(255) NOT NULL,
	  created_at DATE  default sysdate
	); 
	create sequence sboard2_seq;
    
    2. dto
    3. dao / mapper작성
    4. 테스트
*/