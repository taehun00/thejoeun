package com.pawject2.dto;

import lombok.Data;

@Data
public class Disease_ox {
    private int oxno;
    private int disno;
    private String oxquestion;
    private String oxanswer;
    private String oxcomment;
    private int oxbhit;
    private String createdat;
    
    //add
    private int oxbip;
    private String oxfile;

    // 페이징용 필드 추가
    private int start;
    private int end;
}