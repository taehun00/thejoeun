package com.pawject.dto.food;

import lombok.Data;

@Data
public class SearchPetfoodCondition {
	//검색 파라미터 모아보기
    private String keyword;
    private Integer pettypeid;
    private String foodtype;
    private Integer brandid;
    private Integer foodid;
    private String category;
    private String petagegroup;
    private String isgrainfree;
    private String origin;
    private Integer rangeid;
    private Integer minvalue;
    private Integer maxvalue;


    private String sort;   // condition
    private int page = 1;  // pstartno
    private int start;
    private int end;
}