package com.pawject.dao.exec;

import java.util.HashMap;
import java.util.List;

import com.pawject.dao.MyDao;
import com.pawject.dto.exec.ExecBoardDto;

@MyDao
public interface ExecBoardDao {
	//운동챌린지게시판
	public int insert1(ExecBoardDto dto);
	public int update1(ExecBoardDto dto);
	public int updateHit(int postid);
	public int delete1(ExecBoardDto dto);
	public List<ExecBoardDto> selectAll1();
	public ExecBoardDto select1(int postId);
		

	/* UPLOAD */
	public int insert2(ExecBoardDto dto);
	public int update2(ExecBoardDto dto); 
	
	/*Ajax*/
	public List<ExecBoardDto> selectSearch1 (HashMap<String, String> para);


	/*paging*/
	public List<ExecBoardDto> select10(HashMap<String, Object> para);
	public 		int   selectTotalCnt();
	
	
}




