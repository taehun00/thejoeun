package com.pawject.service.exec;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.pawject.dto.exec.ExecBoardDto;

public interface ExecBoardService {
	public int insert1(ExecBoardDto dto);
	public int update1(ExecBoardDto dto);
	public int delete1(ExecBoardDto dto);
	public List<ExecBoardDto> selectAll1();
	public ExecBoardDto select1( int postId);
	public ExecBoardDto       selectUpdateForm(int postid);

	/* UPLOAD */
	public int insert2( MultipartFile file, ExecBoardDto dto);
	public int update2( MultipartFile file, ExecBoardDto dto); 
	
	/* Search - Ajax*/
	public List<ExecBoardDto> selectSearch1(String keyword);

	/*paging*/
	public List<ExecBoardDto> select10(int pstartno); //1(1,10), 2(11,20), 3(21,30)
	public 		int   selectTotalCnt();
	

}
