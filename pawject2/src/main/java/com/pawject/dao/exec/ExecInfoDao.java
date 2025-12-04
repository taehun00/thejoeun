package com.pawject.dao.exec;

import java.util.HashMap;
import java.util.List;

import com.pawject.dao.MyDao;
import com.pawject.dto.exec.ExecBoardDto;
import com.pawject.dto.exec.ExecInfoDto;

@MyDao
public interface ExecInfoDao {	
	//운동정보(운동챌린지게시판 작성시 참고용)
	public int insert2(ExecInfoDto dto);
	public List<ExecInfoDto> selectAll2();
	public ExecInfoDto select2(int execId);


	/* UPLOAD */
	public int insert2(ExecBoardDto dto);
	public int update2(ExecBoardDto dto); 
	
	/*Ajax*/
	public List<ExecInfoDto> selectSearch2(HashMap<String, String> para);

	/*paging*/
	public List<ExecInfoDto> select10(HashMap<String, Object> para);
	public 		int   selectTotalCnt();	
	
	/* idouble */
	public int iddouble(int postid);
	
	/* admin  - 전체유저정보 selectAll 
	           / 게시글아이디(postid) 주면 해당게시글 찾기
	           / 수정하기 updateMember
	           / 삭제하기 deleteMember*/
//	public int updateAdmin(ExecBoardDto dto);
//	public int deleteAdmin(ExecBoardDto dto);
	
	public int updateMember(ExecBoardDto dto);
	public int deleteMember(ExecBoardDto dto);
	
	/* security */
//	public int insertAuth(ExecAuthDto dto);
//	public ExecBoardAuthDto readAuth(ExecBoardAuthDto dto);
	
}




