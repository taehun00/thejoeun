package com.pawject.dao.exec;

import java.util.HashMap;
import java.util.List;

import com.pawject.dao.MyDao;
import com.pawject.dto.exec.ExecBoardDto;
import com.pawject.dto.exec.ExecInfoDto;

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
	
	/* idouble */
//	public int iddouble1(int postid);
	
	/* admin  - 전체유저정보 selectAll 
	           / 게시글아이디(postid) 주면 해당게시글 찾기
	           / 수정하기 updateAdmin / updateMember
	           / 삭제하기 deleteAdmin / deleteMember*/
//	public int updateAdmin1(ExecBoardDto dto);
//	public int deleteAdmin1(ExecBoardDto dto);
	
//	public int updateMember1(ExecBoardDto dto);
//	public int deleteMember1(ExecBoardDto dto);
	
	/* security */
//	public int insertAuth(ExecAuthDto dto);
//	public ExecBoardAuthDto readAuth(ExecBoardAuthDto dto);
	
}




