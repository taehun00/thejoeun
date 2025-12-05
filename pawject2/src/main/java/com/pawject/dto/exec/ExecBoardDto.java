package com.pawject.dto.exec;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExecBoardDto {
	private int postid;
	private int execid;  //외래키
	private int userid;  //외래키
	private String etitle;
	private String econtent;
	private String eimg;
	private int ehit;
    private String createdat;
    private String updatedat;
	}

//LocalDateTime이 안되면 String으로 바꿔서 실행해주세요.