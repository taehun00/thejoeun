package com.pawject.dto.tester;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TesterResponseDto {
	
	private Long testerid;
	private String category;
	private String title;
	private String content;
	private String userid;
	private int foodid;
	private int status;
	private int views;
	private int isnotice;
	private String createdat;
	private String updatedat;

}
/**
 * tester 테이블 필요 컬럼
testerid - 시퀀스 이용, 고유 번호
category - 분류 (사료, 용품 등)
title 제목
content 내용
userid 유저
foodid 푸드랑 연관 있을 경우 - 이 경우 사료검색 게시판에서 테스터 모집중 버튼 노출 가능할지도?
status 모집상태
views 조회수
isnotice 공지여부-모집상태와 연동
createdat 등록일
updatedat 수정일
deketed 삭제여부

 */